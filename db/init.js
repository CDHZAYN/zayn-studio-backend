const mongoose = require('mongoose')
const neo4j = require("neo4j-driver")

// mongodb
const userMG = 'cdhzayn'
const passwordMG = 'ZAYNsalt66%40'
const pathMG = (process.env.NODE_ENV === 'dev' ? '1.15.223.250' : 'mongo')
const portMG = (process.env.NODE_ENV === 'dev' ? '8082' : '27017')
const urlMG = 'mongodb://' + userMG + ':' + passwordMG + '@' + pathMG + ':' + portMG + '/studio-database'

mongoose.connect(urlMG)
const db = mongoose.connection
mongoose.connection.on('connected', function () {
    console.log('Mongoose connection open')
})
mongoose.connection.on('error', function (err) {
    console.log('Mongoose connection error: ' + err)
})
mongoose.connection.on('disconnected', function () {
    console.log('Mongoose connection disconnected')
})

// neo4j
const userNEO = 'neo4j'
const passwordNEO = 'zaynsalt66'
const pathNEO = (process.env.NODE_ENV === 'dev' ? '1.15.223.250' : 'neo4j')
const portNEO = '7687'
const urlNEO = 'bolt://'+pathNEO+':'+portNEO


const cyDriver = neo4j.driver(urlNEO, neo4j.auth.basic(userNEO, passwordNEO),
    { disableLosslessIntegers: true })


console.log(urlMG, urlNEO)
module.exports = {mongoose, cyDriver}