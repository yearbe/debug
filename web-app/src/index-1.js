// 1. 导入React
import React from 'react'   // 创建组件、虚拟DOM元素，生命周期
import ReactDOM from 'react-dom'    // 把创建好的组件和虚拟DOM放到页面上展示的

/*
// 2. 创建虚拟DOM元素
const myh1 = React.createElement('h1', {id: 'myh1', title: 'this is a h1'}, '这是一个大大的H1标签')
const mydiv = React.createElement('div', null, '这是一个div元素', myh1)

// 3. 使用ReactDOM把虚拟DOM渲染到页面上
ReactDOM.render(mydiv, document.getElementById('app'))
*/

// 渲染页面上的DOM结构，最好的方式就是写HTML代码
// 在JS中写HTML代码叫做JSX语法，可以使用babel来转换这些JS中的标签
/* let a = 10
let str = '10 + 1 ='
const myDiv = <div id="myDiv">
    {str} {a + 1}
</div> */

/**
 * JSX注释需要用大括号括起来
 * JSX中的元素添加class名需要使用className属性，label的for属性需要使用htmlFor
 * JSX创建的虚拟DOM，必须有唯一的根元素进行包裹
 * JSX语法中标签必须成对出现，如果是单标签必须自闭
 */
// 数组渲染的DOM需要在最外层节点增加key属性（防止DOM节点变动时，无法正确更新原状态）
const ary = [{id: 1, name: "第一个"}, {id: 2, name: "第二个"}, {id: 3, name: "第三个"}]
const myDiv = <div>
    {ary.map(item => <div key={item.id}><h5>{item.name}</h5>{/* 这是一个注释 */}</div>)}
</div>
ReactDOM.render(myDiv, document.getElementById('app'))