const express = require("express");
const game = require('../db/game')
const controller = express.Router()

controller.get('/get', async function(req, res, next){
    res.locals.msg = await game.find()
    next()
})

module.exports = controller;