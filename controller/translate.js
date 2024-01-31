const express = require("express");
const controller = express.Router()
const axios = require('axios')
const md5 = require('../util/md5')

controller.get('', async function (req, res, next) {
    const text = req.query.text
    const to = req.query.to
    const salt = Math.floor(Math.random() * 1000000000).toString(16)
    const appid = '20230825001793515'
    const joking = 'T_uvJgwRFHyXFlNo4X32'
    const md5Before = appid + text + salt + joking
    const md5After = encodeURI(md5(md5Before))
    await axios.get('https://fanyi-api.baidu.com/api/trans/vip/translate', {
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'Access-Control-Allow-Origin': '*'
        },
        params: {
            q: text,
            from: 'auto',
            to,
            appid,
            salt,
            sign: md5After
        }
    }).then((TransTes) => {
        console.log(TransTes.data['trans_result'][0].dst)
        if (!TransTes.data['error_code']) {
            res.locals.msg = TransTes.data['trans_result'][0].dst
            next()
        } else
            next(new Error(TransTes.data['error_msg']))
    }).catch((err) => {
        console.log(err)
        next(new Error(err))
    })
})

module.exports = controller;