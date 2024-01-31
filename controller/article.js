const express = require("express")
const scrypt = require("scrypt-js")
const controller = express.Router()
const session = require("../db/article/article.js").getSession()
const killSession = require("../db/article/article.js").killSession
const removeMd = require('remove-markdown')
const jieba = require('nodejieba')

const marksReg = /[\s\x21-\x2f\x3a-\x40\x5b-\x60\x7B-\x7F\u3002\uff1f\uff01\uff0c\u3001\uff1b\uff1a\u201c\u201d\u2018\u2019\uff08\uff09\u300a\u300b\u3010\u3011\u007e]/g

// controller.get('/getAll', async function(req, res, next){
//     res.locals.msg = await article.find()
//     next()
// })
//
// controller.get('/getHtml', async function(req, res, next){
//     let _id = req.query._id
//     res.locals.msg = await articleHTML.findOne({'article_id': _id})
//     next()
// })

// match string like this: shabi(shabi, shabi):
// /^([\u4e00-\u9fa5\w]+)[(（]([\u4e00-\u9fa5\w]+)((, )|，)([\u4e00-\u9fa5\w]+)[)）][:：]/

function isNumber(str) {
    const reg = /[0-9.]/
    return str.match(reg) && str[0] !== '.'

}

controller.post('/verify', function (req, res, next) {

    const password = new Buffer.from(req.body.pwd.normalize('NFKC'))
    const salt = new Buffer.from("someSalt".normalize('NFKC'))
    const N = 1024, r = 8, p = 1;
    const dkLen = 32;

    const key = scrypt.syncScrypt(password, salt, N, r, p, dkLen)
    const answerList = [
        74, 136, 200, 237, 146, 46, 219, 34,
        114, 204, 11, 51, 88, 212, 185, 32,
        9, 173, 146, 148, 179, 1, 179, 109,
        90, 104, 13, 158, 80, 24, 240, 63]
    const answer = Uint8Array.from(answerList);
    for (let i = 0; i < key; ++i)
        if (key[i] !== answer[i]) {
            next(new Error('unmatchable password!'))
            return
        }
    next()
})

controller.post('/create', function (req, res, next) {
    console.log('new article creating!')
    const blockList = req.body.blockList

    let title = ''
    let subtitle = ''
    let labelsList = []

    // strip all markdown marks off and recognize label and (sub)title
    for (let i = 0; i < blockList.length; ++i) {
        const textParsed = removeMd(blockList[i].text)

        // recognize (sub)title
        if (i === 0 && textParsed)
            title = textParsed.split('\n')[0]
        if (i === 1 && textParsed)
            subtitle = textParsed.split('\n')[0]

        // recognize label (heuristic)
        const labelsTemp = textParsed.split(/:：/)[0].split(/[,，()（）]/)
        const reg = /^[A-Za-z 0-9]+$/
        let labels = []
        labelsTemp.forEach(e => {
            // if in English, then choose phrases less/equal than 6 words as labels
            if (e.match(reg)) {
                if (e.trim() && e.split(' ').length <= 6 && e.length <= 32 && !isNumber(e)) {
                    let temp = e.split(' ').join('_').toLowerCase()
                    temp = temp.replaceAll(marksReg, '_')
                    labels.push(temp)
                }
            } else {
                if (e.trim() && e.length <= 6) {
                    let temp = e.split(' ').join('_').toLowerCase()
                    if (temp.length >= 16)
                        temp = temp.substring(0, 16)
                    temp = temp.replaceAll(marksReg, '_')
                    labels.push(temp)
                }
            }
        })

        // recognize label (Jieba)
        jieba.extract(textParsed, Math.max(0, 6 - labels.length)).forEach(e => {
            let word = e.word
            if (word.length >= 16)
                word = word.substring(0, 16)
            word = word.replaceAll(marksReg, '_')
            if (!isNumber(word)) {
                labels.push(word)
            }
        })

        labels = labels.slice(0, 6)

        labelsList[i] = labels
    }

    let user = 'cdhzayn'
    user = user.toLowerCase()
    let user36 = ''
    for (let i = 0; i < user.length; ++i)
        user36 += (user.charCodeAt(i) - 'a'.charCodeAt(0)).toString(36)

    let articleId = ''

    // get & increase article counter
    const writeTx = session.writeTransaction(async txc => {
        const createTime = Date.now()

        // determine article id (num) and form article id (36-radix string)
        let counter = (await txc.run('match (n:ArticleCounter) return n.counter as counter')).records[0].get('counter') || 0

        articleId = createTime.toString(36) + user36 + counter.toString(36)
        await txc.run('match (n:ArticleCounter) set n={counter: $counter}', {
            counter: ++counter
        })

        // create article node
        await txc.run('create (:article {articleId: $articleId, createTime: $createTime, title: $title, subtitle: $subtitle, blockNum: $blockNum})', {
            articleId,
            createTime,
            title,
            subtitle,
            blockNum: blockList.length
        })

        // create block nodes
        for (let i = 0; i < blockList.length; ++i) {
            let propsStr = ''
            blockList[i].articleId = articleId
            blockList[i].index = i
            for (let key in blockList[i]) {
                propsStr += key + ': $' + key + ','
            }
            propsStr = propsStr.substring(0, propsStr.length - 1)

            let labelsStr = labelsList[i].join(':')
            if (labelsStr)
                labelsStr = ':' + labelsStr

            const query = 'create (:block' + labelsStr + '{' + propsStr + '})'
            await txc.run(query, blockList[i])
        }

        // connect nodes that just created
        txc.run('match (t1:article {articleId: $articleId}), (t2:block {articleId: $articleId, index: 0}) ' +
            'create (t1)-[:OWNS]->(t2)', {
            articleId
        })

        // connect block nodes
        for (let i = 0; i < blockList.length - 1; ++i) {
            txc.run('match (t1:block {articleId: $articleId, index: $index1}), (t2:block {articleId: $articleId, index: $index2}) ' +
                'create (t1)-[:FOLLOWED_BY]->(t2)', {
                articleId,
                index1: i,
                index2: i + 1
            })
        }
        return 's'
    })

    writeTx.then((response) => {
        console.log('article ' + articleId + ' created!')
        next()
    }).catch((err) => {
        next(new Error(err))
    })
})

controller.post('/delete', function (req, res, next) {
    const articleId = req.query.articleId
    const writeTx = session.writeTransaction(async txc => {
        await txc.run('match (t1:block{articleId: $articleId})-[r:FOLLOWED_BY]->(t2:block{articleId: $articleId}) delete r',{
            articleId
        })
        await txc.run('match (t1:article{articleId: $articleId})-[r:OWNS]->(t2:block{articleId: $articleId}) delete r',{
            articleId
        })
        await txc.run('match (t1:article{articleId: $articleId}), (t2:block{articleId: $articleId}) delete t1, t2',{
            articleId
        })
    })

    writeTx.then((response) => {
        console.log('article ' + articleId + ' deleted!')
        next()
    }).catch((err) => {
        next(new Error(err))
    })
})

module.exports = controller;