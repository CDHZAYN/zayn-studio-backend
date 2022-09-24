const mongoose = require('./init')

const bannerSchema = mongoose.Schema({
    name: String,
    desc: String
}, {collection : 'banner'});

module.exports = mongoose.model('Banner', bannerSchema)