import React from 'react'
import ReactDOM from 'react-dom'

// 默认不做配置的话，不能省略.jsx后缀名
// import Hello from './components/Hello.jsx'
// 查看webpack.config.js中的配置
import Hello from '@/components/Hello'


const user = {
    name: 'Dog',
    age: 3,
    gender: '雄性'
}

ReactDOM.render(<div>
    {/**
     * 直接使用function名称渲染
     * 属性直接引用存在变量值
     */}
    {/* <Hello name={user.name} age={user.age} gender={user.gender}></Hello> */}
    <Hello {...user}></Hello>
</div>, document.getElementById("app"))
