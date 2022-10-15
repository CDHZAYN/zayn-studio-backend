const mongoose = require('./init')

const gameSchema = mongoose.Schema({
    name: String,
    keywords: Array,
    avatar: String
}, {collection : 'game'});

module.exports = mongoose.model('Game', gameSchema)