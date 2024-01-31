const mongoose = require('./init').mongoose

const broSponsorSchema = mongoose.Schema({
    name:String,
    desc: String,
    avatar: String,
    src: String
}, {collection : 'cute_sponsor'});

module.exports = mongoose.model('CuteSponsor', broSponsorSchema)