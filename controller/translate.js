const express = require("express");
const controller = express.Router()
const axios = require('axios')
const md5 = require('../util/md5')

controller.get('/', async function(req, res, next){
    const text = req.query.text
    const to = req.query.to
    const salt = Math.floor(Math.random() * 1000000000).toString(16)
    res.locals.msg = await axios.get('https://fanyi-api.baidu.com/api/trans/vip/translate', {
        headers: {'Content-Type': 'application/x-www-form-urlencoded',
            'Access-Control-Allow-Origin': '*'},
        data: {
            q: text,
            from: 'auto',
            to,
            appid: '20230825001793515',
            salt,
            sign: md5('20230825001793515' + text + salt + 'T_uvJgwRFHyXFlNo4X32')
        }
    }).then((TransTes)=>{
        console.log(TransTes.data)
        if(TransTes.data['error_code'] === '52000') {
            res.locals.msg = TransTes.data['trans_result'].dst
            next()
        } else
            next(new Error('get error response: ' + TransTes.data['error_msg']))
    }).catch((err)=>{
        console.log(err)
        next(new Error(err))
    })
})

module.exports = controller;