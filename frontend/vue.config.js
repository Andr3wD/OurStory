module.exports = {
  devServer: {
    port: 8081,
    headers: { 'Access-Control-Allow-Origin': '*' },
    proxy: 'http://localhost:8080/'
  }
}
