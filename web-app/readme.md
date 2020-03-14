## `webpack`创建项目

1. 创建`package.json`，快速初始化项目

   ```sh
   npm init -y
   ```

2. 在项目根目录下创建`src`源代码目录与`dist`产品目录

3. 在`src`目录下创建`index.html`首页页面

4. 在`src`下创建`main.js`入口文件

5. 安装`webpack`

   ```sh
   npm i webpack webpack-cli -D
   ```

6. 在根目录下创建`webpack.config.js`文件，向外暴露一个打包的配置对象

   `webpack 4.x`提供了约定大于配置的概念；目的是为了尽量减少配置文件的体积。

   - 默认打包的入口是`src/index.js`
   - 打包的输出文件是`dist/main.js`
   - `4.x`新增了`mode`选项，可选的值为：`development`与`production`

7. 在命令行使用`webpack`命令打包

   ```sh
   node_modules/.bin/webpack
   ```

8. 安装`webpack`启动服务

   ```
   npm i webpack-dev-server -D
   ```

9. 配置运行命令，在`package.json`中下的`scripts`中增加`"dev": "webpack-dev-server"`选项，使用`npm`命令启动项目

   ```
   npm run dev
   ```

10. `webpack-dev-server`打包好的`main.js`是托管到了内存中，所以在项目根目录中看不到，但是可以直接引用该`main.js`

    ```
    # 热部署与启动打开浏览器，端口号，IP，传输压缩
    webpack-dev-server --open firefox --host 127.0.0.1 --port 7777 --hot --progress --compress
    ```

11. 安装`html-webpack-plugin`帮助生成在内存中首页文件，配置`webpack plugin`

    ```
    npm i html-webpack-plugin -D
    ```

12. 完成`webpack.config.js`

    ```js
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
    ```



## `React`

1. 导入`react`包

   ```
   npm i react react-dom -S
   ```

   - `react` 专门用于创建组件和虚拟DOM的，同时组件的生命周期都在这个包中
   - `react-dom` 专门进行DOM操作的，最主要的应用场景，就是`ReactDOM.render()`

2. 在`index.html`页面中，创建容器

   ```html
   <div id="app"></div>
   ```

3. 在`index.js`导入包

   ```javascript
   import React from 'react'
   import ReactDOM from 'react-dom'
   ```

4. 创建虚拟DOM元素

   ```js
   /*
    * 创建虚拟DOM元素，<h1 title="Hello, React." id="myApp">Welcome to React study.</h1>
    * 第一个参数：字符串类型，表示要创建的标签名称
    * 第二个参数：对象型，表示创建的元素的属性
    * 第三个参数：子节点
    */
   const myApp = React.createElement('h1', {title: 'Hello, React.', id: 'myApp'}, 'Welcome to React study.')
   ```

5. 渲染

   ```js
   /*
    * 渲染虚拟DOM元素
    * 参数1：表示要渲染的虚拟DOM元素
    * 参数2：指定容器，需要传入一个容器的DOM对象
    */
   ReactDOM.render(myApp, document.getElementById('app'))
   ```

   

## `JSX`语法

> 什么是`JSX`语法，就是符合`xml`规范的`JS`语法（语法格式要比HTML严谨）

1. 如果启用`JSX`语法

   - 安装`babel`插件

     ```sh
     # 要使用babel-core，则要使用babel-loader的7.x版本
     npm i babel-core babel-loader@7 babel-plugin-transform-runtime babel-preset-env babel-preset-stage-0 -D
     ```

     ```sh
     # 要使用babel-loader的8.x版本，则要使用@babel/core
     # webpack 4.x | bebel-loader 8.x | babel 7.x
     npm i -D babel-loader @babel/core @babel/preset-env webpack
     # webpack 4.x | babel-loader 7.x | babel 6.x
     npm i -D babel-loader@7 babel-core babel-preset-env webpack
     ```

   - 安装能够识别转换`JSX`语法的包

     ```sh
     npm i babel-preset-react -D
     ```

   - 添加`.babelrc`配置文件

     ```json
     {
     	"presets": ["env", "stage-0", "react"],
     	"plugins": ["transform-runtime"]
     }
     ```

   - 在`webpack.config.js`下添加`babel-loader`配置项

     ```json
     module: {   // 所有第三方模块的配置规则
         rules: [    // 第三方匹配规则
             { 
                 test: /\.js|jsx$/, 
                 use: 'babel-loader', 
                 exclude: /node_modules/     // 不可缺少该排除项
             }
         ]
     }
     ```



## `CSS`引入

1. `webpack`安装支持`css`的`loader`

   ```sh
   npm i style-loader css-loader -D
   ```

2. `webpack.config.js`下配置模块规则

   ```json
   {
       test: /\.css$/,
       // loader会以从右到左的顺序执行，先执行css-loader，再执行style-loader
       // css-loader使用参数modules为普通的css启动模块化
       use: ['style-loader', 'css-loader?modules'] // 打包处理css样式的loader
   }
   ```

3. 使用`localIdentName`自定义生成的类名格式，可选参数有：

   - `path` 样式表相对于项目根目录的所在路径
   - `name`  样式表文件名称
   - `local` 样式的类名定义名称
   - `hash:length` 表示32位的hash值

   ```json
   # css-loader2
   use: ['style-loader', 'css-loader?modules&localIdentName=[path][name]-[local]-[hash:6]']
   # css-loader3
   use: ['style-loader', {
       loader: 'css-loader',
       options: {
           modules: {  // 开启模块化
               localIdentName: '[path][name]-[local]-[hash:6]' // 配置css选择器生成类名规则
           }
       }
   }]
   ```

4. 安装字体处理`loader`

   ```sh
   npm i url-loader file-loader -D
   ```

5. 增加字体处理配置

   ```json
   {
       test: /\.ttf|woff|woff2|eot|svg$/,
       use: 'url-loader'
   }
   ```

6. 为解决引入第三方`css`文件被模块化，项目中的样式文件都使用`.scss`或`.less`文件

   ```sh
   # 安装sass文件处理loader
   npm i sass-loader node-sass -D
   ```

   