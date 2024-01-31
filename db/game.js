const mongoose = require('./init').mongoose

const gameSchema = mongoose.Schema({
    name: String,
    keywords: Array,
    avatar: String
}, {collection : 'game'});

module.exports = mongoose.model('Game', gameSchema)