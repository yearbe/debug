import React from 'react'
import cssobj from '@/css/cmtitem.scss'

export default function CmtItem(props) {
    {/* 使用{cssobj.item}的形式可以实现css模块化，解决css作用域冲突，直接使用样式无法处理该问题 */}
    return <div id={cssobj.item} className="item">
        <h4 className="user">评论人：{props.user}</h4>
        <p className="content">评论内容：{props.content}</p>
    </div>
}