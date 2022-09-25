const express = require("express");
const article = require('../db/article/article')
const articleHTML = require('../db/article/articleHTML')
const mongoose = require("mongoose");
const controller = express.Router()

controller.get('/getAll', async function(req, res, next){
    res.locals.msg = await article.find()
    next()
})

controller.get('/getHtml', async function(req, res, next){
    let _id = req.query._id
    res.locals.msg = await articleHTML.findOne(mongoose.Types.ObjectId(_id))
    next()
})

module.exports = controller;