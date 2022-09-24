const express = require("express");
const banner = require('../db/banner')
const controller = express.Router()

controller.get('/get', async function(req, res, next){
    res.locals.msg = await banner.find()
    next()
})

module.exports = controller;