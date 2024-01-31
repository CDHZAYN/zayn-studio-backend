const mongoose = require('./init').mongoose

const bannerSchema = mongoose.Schema({
    name: String,
    desc: String
}, {collection : 'banner'});

module.exports = mongoose.model('Banner', bannerSchema)