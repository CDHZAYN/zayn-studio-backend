const express = require("express");
const broSponsor = require('../db/broSponsor')
const controller = express.Router()

controller.get('/get', async function(req, res, next){
    res.locals.msg = await broSponsor.find()
    next()
})

module.exports = controller;