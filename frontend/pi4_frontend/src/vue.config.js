module.exports = {
    devServer: {
       proxy: 'http://localhost:8081',
       host: 'localhost',
       port: 8080
   },

  baseUrl: '/'
}