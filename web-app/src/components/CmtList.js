import React from 'react'

// 使用绝对路径替代相对路径好处，若当前组件移动目录，还是可以直接使用所引用的组件，不需要修改相对路径
import CmtItem from '@/components/CmtItem'

class CmtList extends React.Component {
    constructor() {
        super()
        this.state = {
            commentList: [
                { id: 1, user: '张三', content: '沙发' },
                { id: 2, user: '李四', content: '板凳' },
                { id: 3, user: '王五', content: '地板' },
                { id: 4, user: '赵六', content: '下水道' },
                { id: 5, user: '田七', content: '楼下三炮' }
            ]
        }
    }

    render() {
        return <div>
            {/* 
                在JSX中，行内样式不能直接为style写字符串的值
                需要写成JSX对象形式，如果是横杠分割的样式，修改为驼峰形式
                如：style={{ color: 'red' }}
            */}
            <h1 style={{ color: 'red', fontSize: '40px', fontWeight: 200 }}>这是评论列表</h1>
            { this.state.commentList.map(item => <CmtItem {...item} key={item.id}></CmtItem>) }
        </div>
    }
}

export default CmtList