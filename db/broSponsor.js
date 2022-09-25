const mongoose = require('./init')

const broSponsorSchema = mongoose.Schema({
    name:String,
    desc: String,
    avatar: String,
    src: String
}, {collection : 'bro_sponsor'});

module.exports = mongoose.model('BroSponsor', broSponsorSchema)