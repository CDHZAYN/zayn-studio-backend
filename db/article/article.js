const mongoose = require('../init')

const articleSchema = mongoose.Schema({
    _id: String,
    title: String,
    desc: String,
    category: Array,
}, {collection : 'article'});

module.exports = mongoose.model('Article', articleSchema)