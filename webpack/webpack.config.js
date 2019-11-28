const path = require('path')
// 导入在内存中自动生成的index页面插件
const HtmlWebPackPlugin = require('html-webpack-plugin')

// 创建一个插件的实例对象，用于生成webpack-dev-server打包生中在内存中的默认首页文件
const htmlPlugin = new HtmlWebPackPlugin({
    template: path.join(__dirname, './src/index.html'), // 源文件
    filename: 'index.html'  // 生成的内存中首页的名称
})


// 向外暴露一个打包的配置对象
// 因为webpack是基于node构建的，所以webpack支持所有的node api和语法
module.exports = {
    // 在webpack 4.x中， 有一个很大的特性，就是约定大于配置，约定默认的打包入口路径是src -> index.js
    mode: 'development', // development production: 生成生产环境代码，会执行代码压缩
    plugins: [
        htmlPlugin
    ]
}