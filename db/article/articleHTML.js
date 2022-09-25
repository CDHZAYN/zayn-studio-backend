const mongoose = require('../init')

const articleSchema = mongoose.Schema({
    _id: String,
    html: String
}, {collection : 'article_html'});

module.exports = mongoose.model('ArticleHTML', articleSchema)