const express = require('express');
const path = require("path");
const app = express();

const bannerController = require('./controller/banner')
const broSponsorController = require('./controller/broSponsor')
const articleController = require('./controller/article')
const cuteSponsorController = require('./controller/cuteSponsor')
const gameController = require('./controller/game')
const translateController = require('./controller/translate')

app.use(express.json())
app.use('/static', express.static(path.join(__dirname, 'public')))
app.use(express.urlencoded({ extended: true }))

app.use((req,res,next)=>{
    console.log('to: ',req.originalUrl)
    next()
})

app.use('/banner', bannerController)
app.use('/bro-sponsor', broSponsorController)
app.use('/article', articleController)
app.use('/cute-sponsor', cuteSponsorController)
app.use('/game', gameController)
app.use('/translate', translateController)

app.use((req, res, next)=>{
    res.json({
        'code': '0000',
        'msg': res.locals.msg
    })
    res.end()
})
app.use((err, req, res, next)=>{
    console.log(err)
    res.status(500).json({
        'code': 0,
        'msg': err.toString()
    })
    res.end()
})

module.exports = app;