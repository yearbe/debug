import React from 'react'

/**
 * 使用class关键字创建的组件，有自己的私有属性和生命周期函数，有状态组件：state属性
 * 使用function关键字的组件，只有props属性（无状态组件）
 * props是通过参数传递进来，不能重新赋值
 * state为组件私有属性，可重新赋值，可通过组件内ajax请求修改
 */

// 使用class定义React组件，需要继承React.Component
export default class Show extends React.Component {
    constructor() {
        super() // 继承的构造器中需要使用super()函数初始化父类构造器
        // 组件私有数据 state 可读可写
        this.state = {
            msg : 'This is a class component.'
        }
    }
    
    // React组件必须要render函数
    render () {
        // render函数返回JSX结构的虚拟DOM结构
        // 通过class创建的组件中，直接使用this.props使用外界传进来的参数，props为只读，不可修改
        this.state.msg = '我想要什么就什么。'
        return <div>
            <h1>{this.props.msg}</h1>
            <hr/>
            <h1>{this.state.msg}</h1>
        </div>
    }
}