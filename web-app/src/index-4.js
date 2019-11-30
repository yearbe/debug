import React from 'react'
import ReactDOM from 'react-dom'
import Show from '@/components/Show'

const msg = "我想要一个苹果。"

ReactDOM.render(<div>
    <Show msg={msg}></Show>
</div>, document.getElementById("app"))