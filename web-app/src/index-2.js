import React from 'react'
import ReactDOM from 'react-dom'

// 使用function方式创建组件，组件名称以大写开头
function Hello(props) {
    // 直接返回组件内容（必须是合法的JSX内容），如果组件返回一个null，则表示组件是空的，不渲染
    // props 为只读属性，不可修改其属性值
    // 使用参数props接收传入属性，直接使用属性名获取属性值
    return <h1>Hello, {props.name}! age: {props.age}, gender: {props.gender}</h1>
}

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
