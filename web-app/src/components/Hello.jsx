// 创建React组件，必须要导入React包
import React from 'react'

// 使用导出的方式创建组件
export default function Hello(props) {
    return <h1>Hello, {props.name}!</h1>
}