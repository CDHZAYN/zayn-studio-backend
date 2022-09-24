const express = require('express');
const path = require("path");
const app = express();

const bannerController = require('./controller/banner')

app.use(express.json())
app.use('/static', express.static(path.join(__dirname, 'public')))
app.use(express.urlencoded({ extended: true }))

app.use((req,res,next)=>{
    console.log('newRequest')
    next()
})

app.use('/banner', bannerController)

app.use((req, res, next)=>{
    res.json({
        'code': '0000',
        'msg': res.locals.msg
    })
    res.end()
})
app.use((err, req, res, next)=>{
    res.status(500).json({
        'code': 0,
        'msg': err
    })
    res.end()
})

module.exports = app;