const express = require("express");
const cuteSponsor = require('../db/cuteSponsor')
const controller = express.Router()

controller.get('/get', async function(req, res, next){
    res.locals.msg = await cuteSponsor.find()
    // console.log(res.locals.msg)
    next()
})

module.exports = controller;