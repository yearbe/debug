import React from 'react'

// 单独抽取作为模块
import styles from '@/components/styles'

// 可使用JS对象
/* const styles = {
    itemStyle: { border: '1px dashed #ccc', margin: '10px', padding: '10px', boxShadow: '0 0 10px #ccc' },
    userStyle: { fontSize: '18px' },
    contentStyle: { fontSize: '16px' }
} */

// 将组件定义为无状态组件
export default function CmtItem(props) {
    return <div style={styles.itemStyle}>
        <h4 style={styles.userStyle}>评论人：{props.user}</h4>
        <p style={styles.contentStyle}>评论内容：{props.content}</p>
    </div>
}