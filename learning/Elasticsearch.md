Elasticsearch6.0官方文档：https://www.elastic.co/guide/en/elasticsearch/reference/6.0/index.html



\# 查询节点信息，v：带表头返回

GET /_cat/nodes?v



\# 查询运行状态

GET /_cat/health?v



\# 查询所有索引

GET /_cat/indices?v



\# <REST Verb> /<Index>/<Type>/<ID>

\# 创建索引index，pretty：格式化_source返回

PUT /thisismyindex?pretty



\# 创建或替换数据

PUT /thisismyindex/doc/1?pretty

{

  "name": "ohmygod"

}



\# 自动生成id

POST /thisismyindex/doc?pretty

{

  "name": "ohyeah"

}



\# 读取数据

GET /thisismyindex/doc/1?pretty



\# 更新数据

POST /thisismyindex/doc/1/_update?pretty

{

  "doc": {

​    "name": "ohmyladygaga",

​    "sex": "lady",

​    "age": 30

  }

}



\# 表达式更新，ctx查询对象，_source查询结果

POST /thisismyindex/doc/1/_update?pretty

{

  "script": "ctx._source.age += 1"

}



\# 删除索引

DELETE /thisismyindex?pretty



\# 多条件查询

GET /_mget

{

  "docs": [

​    {

​        "_index" : "lwjtarget_upload_qq",

​        "_type" : "doc",

​        "_id" : "22"

​    },

​    {

​        "_index" : "lwjtarget_upload_qq",

​        "_type" : "doc",

​        "_id" : "2"

​    }

  ]

}



GET lwjtarget_upload_qq/_mget

{

  "docs": [

​    {

​        "_type" : "doc",

​        "_id" : "22"

​    },

​    {

​        "_type" : "doc",

​        "_id" : "2"

​    }

  ]

}



GET lwjtarget_upload_qq/doc/_mget

{

  "docs": [

​    {

​        "_id" : "22"

​    },

​    {

​        "_id" : "2"

​    }

  ]

}



GET lwjtarget_upload_qq/doc/_mget

{

  "ids": ["2", "22", "222"]

}



\# 批量操作/_bulk, /{index}/_bulk, 或{index}/{type}/_bulk

POST _bulk

{ "index" : { "_index" : "test", "_type" : "type1", "_id" : "1" } } # 创建

{ "field1" : "value1" } # 为上一行创建赋值

{ "delete" : { "_index" : "test", "_type" : "type1", "_id" : "2" } } # 删除

{ "create" : { "_index" : "test", "_type" : "type1", "_id" : "3" } } # 创建

{ "field1" : "value3" } # 为上一行创建赋值

{ "update" : {"_id" : "1", "_type" : "type1", "_index" : "test"} } # 更新

{ "doc" : {"field2" : "value2"} } # 更新



\# 查询多个索引或类型，中间用逗号隔开

<REST Verb> /<Index1>[,<index2>]/<Type>[,<Type2>]?q=<field>:<value>

如：

GET lwjtarget_upload_027b3c313db54946abcb533b9215b0b9,lwjtarget_upload_qq/_search # 查询两个索引

GET lwjtarget_upload_qq/doc/_search?q=SDEFINE1:222 # 查询索引下SDEFINE1=222的记录



\# 请求Body参数 ########################################

\# 查询

GET /lwjtarget_upload_qq/doc/_search

{

  "query": {

​    "term": {"SDEFINE1": "222"} # 查询项

  }

}

\# 分页

GET  /lwjtarget_upload_qq/doc/_search

{

​    "from" : 0, "size" : 10,

​    "query" : {

​        "term": {"SDEFINE1": "222"}

​    }

}



\# 排序 ###################

https://www.elastic.co/guide/en/elasticsearch/reference/6.0/search-request-sort.html#_sort_mode_option