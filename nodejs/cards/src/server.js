const http = require('http')
const app = require('./configurations/express')
const process = require('process')

http.createServer(app).listen(3000, function () {
    console.log(`[Cards Application - ${(new Date()).toISOString()}] The server is running on port: ${this.address().port}. This PID is ${process.pid}`)
})