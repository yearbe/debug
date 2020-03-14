import React from 'react'

export default class BindEvent extends React.Component {
    constructor() {
        super()
        this.state = {
            isLiked: false
        }
    }

    
    /* clickLike() {
        // 私有函数中不能直接读写state中的值
        console.log(this.state.isLiked)
    } */

    clickLike = () => this.setState({
        // setState函数中可以读写state中的值
        isLiked: !this.state.isLiked
    })

    render() {
        return <div>
            BindEvent Component.
            <hr/>
            {/* 在React中，有一套自己的事件绑定机制，
                事件名是小写开头的驼峰命名，
                事件处理函数必须传入一个function */}
            {/* <button onClick={function(){alert('Hello World')}}>按钮</button>
            <button onClick={() => alert('Haha~')}>按钮</button> */}
            {/* 调用类内函数需要加this关键字 */}
            {/* <button onClick={ this.myClickHandler }>按钮</button>
            <button onClick={ globalClick }>点我</button> */}
            <button onClick={ this.clickLike }>
                { this.state.isLiked ? '取消' : '点赞' }
            </button>
        </div>
    }

    myClickHandler() {
        alert('Click handler.')
    }
}

function globalClick() {
    alert('Global click.')
}