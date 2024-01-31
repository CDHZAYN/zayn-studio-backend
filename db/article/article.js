const cyDriver = require('../init').cyDriver

const getSession = function () {
    return cyDriver.session()
}

const killSession = function (session) {
    session.close()
}

module.exports = {getSession, killSession}