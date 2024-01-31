const express = require("express");
const banner = require('../db/banner')
const controller = express.Router()

controller.get('/get', async function(req, res, next){
    let result = await banner.find()
    res.locals.msg = result
    next()
})

module.exports = controller;