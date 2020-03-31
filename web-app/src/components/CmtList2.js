import React from 'react'
import CmtItem from '@/components/CmtItem2'

// 直接用cssobj导入，然后使用css的class
// 直接导入的css样式，是全局生效的，可以全局使用，会影响整个项目的样式（在webpack.config.js中css-loader?modules启用css模块化解决）
import cssobj from '@/css/cmtlist.scss'

// 在node_modules目录下的包可以直接从包名开始引入
/**
 * 这样导入的第三方css会被本地模块化
 * 为解决这样的问题，定义项目写的css时，以.scss或.less结尾，配置css-loader只处理scss和less文件
 */
import _ from 'bootstrap/dist/css/bootstrap.css'

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
            {/* 使用css模块化，需要使用cssobj调用 */}
            <h1 className={[cssobj.title, "showTitle"].join(" ")}>这是评论列表</h1>
            <button className="btn btn-primary">按钮</button>
            <div className="panel panel-primary"></div>
            { this.state.commentList.map(item => <CmtItem {...item} key={item.id}></CmtItem>) }
        </div>
    }
}

export default CmtList