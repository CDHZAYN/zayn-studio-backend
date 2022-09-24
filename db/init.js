const mongoose = require('mongoose')

const user = 'cdhzayn'
const password = 'ZAYNsalt66%40'
const path = (process.env.NODE_ENV === 'dev' ? '1.15.223.250' : 'mongo')
const port = (process.env.NODE_ENV === 'dev' ? '8082' : '27017')
const url = 'mongodb://' + user + ':' + password + '@' + path + ':' + port + '/studio-database'

console.log(process.env.NODE_ENV, typeof process.env.NODE_ENV, process.env.NODE_ENV === 'dev')
console.log(url)

mongoose.connect(url);
const db = mongoose.connection;
mongoose.connection.on('connected', function () {
    console.log('Mongoose connection open')
})
mongoose.connection.on('error', function (err) {
    console.log('Mongoose connection error: ' + err)
})
mongoose.connection.on('disconnected', function () {
    console.log('Mongoose connection disconnected')
})

module.exports = mongoose