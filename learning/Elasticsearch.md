## Elasticsearch

#### 基本使用

>索引（index）：一个索引可以理解成一个关系型数据库。
>类型（type）：一种类型就像一个数据库表。
>	ES5一个索引可以有多种类型。
>	ES6一个索引只能有一种类型。
>	ES7已经移除类型。
>映射（mapping）：定义了每个字段的类型等信息。相当于关系型数据库中的表结构。
>文档（doc）：一个文档相当于关系型数据库中的一行记录。
>字段：相当于关系型数据库表中的字段。
>请求JSON格式结果，可在url后使用?pretty返回格式化后结果。
>请求列表结果，可在url后使用?v返回表头信息。

1. 查看集群信息

   ```sh
   curl http://localhost:9200?pretty
   ```

2. 创建索引`nba`

   ```sh
   curl -X PUT http://localhost:9200/nba?pretty
   ```

3. 查看索引`nba`

   ```sh
   curl -X GET http://localhost:9200/nba?pretty
   ```

4. 删除索引`nba`

   ```sh
   curl -X DELETE http://localhost:9200/nba?pretty
   ```

5. 查看多个索引

   ```sh
   curl -X GET http://localhost:9200/nba,cba?pretty
   ```

6. 查看所有索引

   ```sh
   curl -X GET http://localhost:9200/_all?pretty
   ```

7. 列出所有索引

   ```sh
   curl -X GET http://localhost:9200/_cat/indices?v
   ```

8. 判断索引是否存在

   ```sh
   curl -I http://localhost:9200/nba
   返回状态200为存在，404为不存在。
   ```

9. 关闭索引（非删除）

   ```sh
   curl -X POST http://localhost:9200/nba/_close?pretty
   索引信息多一个{ "verified_before_close" : "true" }信息
   ```

10. 打开索引

    ```sh
    curl -X POST http://localhost:9200/nba/_open?pretty
    ```

    

#### 映射（mapping）

> 映射设置就是规定文档的数据结构

1. 新增

   ```sh
   curl -X PUT http://localhost:9200/nba/_mapping?pretty -H 'Content-Type: application/json' -d '
   {
   	"properties": {
   		"name": {
   			"type": "text"
   		},
   		"team_name": {
   			"type": "text"
   		},
   		"position": {
   			"type": "keyword"
   		},
   		"play_year": {
   			"type": "keyword"
   		},
   		"jerse_no": {
   			"type": "keyword"
   		}
   	}
   }
   '
   ```

2. 查看

   ```sh
   # 查看指定
   curl -X GET http://localhost:9200/nba/_mapping?pretty
   # 查看多个
   curl -X GET http://localhost:9200/nba,cba/_mapping?pretty
   # 查看所有
   curl -X GET http://localhost:9200/_all/_mapping?pretty
   curl -X GET http://localhost:9200/_mapping?pretty
   ```

3. 修改

   ```sh
   # 字段信息只能新增，不可修改，要修改只能重建索引
   curl -X POST http://localhost:9200/nba/_mapping?pretty -H 'Content-Type: application/json' -d '
   {
   	"properties": {
   		"name": {
   			"type": "text"
   		},
   		"team_name": {
   			"type": "text"
   		},
   		"position": {
   			"type": "keyword"
   		},
   		"play_year": {
   			"type": "keyword"
   		},
   		"jerse_no": {
   			"type": "keyword"
   		},
   		"country": {
   			"type": "keyword"
   		}
   	}
   }
   '
   ```

   

   ### 文档（Document）

> elasticsearch默认将类型type固定为_doc，不可新增或修改
>
> 新增文档时，需要使用默认类型_doc

1. 新增（传入的值存在不存在映射的字段时，会自动增加字段）

   ```sh
   # 指定ID
   curl -X PUT http://localhost:9200/nba/_doc/2\?pretty -H 'Content-Type: application/json' -d '
   {
           "name": "科比",  
           "team_name": "湖人",
           "position": "得分后卫",
           "play_year": "20",
           "jerse_no": "24", 
           "country": "美国", 
           "nickname": "黑曼巴"
   }
   '
   
   # 不指定ID（使用POST请求）
   curl -X POST http://localhost:9200/nba/_doc\?pretty -H 'Content-Type: application/json' -d '
   {
           "name": "杜兰特",  
           "team_name": "篮网",
           "position": "小前锋",
           "play_year": "12",
           "jerse_no": "35", 
           "country": "美国", 
           "nickname": "死神"
   }
   '
   ```

2. 查看（<index>/<type>/<id>）

   ```sh
   curl -X GET http://localhost:9200/nba/_doc/1?pretty
   ```

3. 查看多个文档

   ```sh
   curl -X GET http://localhost:9200/_mget?pretty -H 'Content-Type: application/json' -d'
   {
   	"docs": [
   		{
   			"_index": "nba",
   			"_type": "_doc",
   			"_id": "1"
   		},
   		{
   			"_index": "nba",
   			"_type": "_doc",
   			"_id": "2"
   		}
   	]
   }
   '
   # 指定索引
   curl -X GET http://localhost:9200/nba/_mget?pretty -H 'Content-Type: application/json' -d'
   {
   	"docs": [
   		{
   			"_type": "_doc",
   			"_id": "1"
   		},
   		{
   			"_type": "_doc",
   			"_id": "2"
   		}
   	]
   }
   '
   # 指定索引与类型
   curl -X GET http://localhost:9200/nba/_doc/_mget?pretty -H 'Content-Type: application/json' -d'
   {
   	"docs": [
   		{
   			"_id": "1"
   		},
   		{
   			"_id": "2"
   		}
   	]
   }
   '
   curl -X GET http://localhost:9200/nba/_doc/_mget?pretty -H 'Content-Type: application/json' -d'
   {
   	"ids": ["1", "2"]
   }
   '
   ```

4. 自动创建索引

   - 查看auto_create_index开关状态

     ```sh
     curl http://localhost:9200/_cluster/settings?pretty
     ```

   - 当索引不存在并auto_create_index为true的时候，新增文档时就会自动创建索引

   - 修改auto_create_index状态

     ```sh
     curl -X PUT http://localhost:9200/_cluster/settings?pretty -H 'Content-Type: application/json' -d '
     {
     	"persistent" : {
     		"action.auto_create_index": "false"
     	}
     }
     '
     ```

5. 防止新增变修改（已存在相同的索引/类型/ID时，提示冲突）

   ```sh
   # 使用op_type参数
   curl -X PUT http://localhost:9200/nba/_doc/2\?pretty&op_type=create -H 'Content-Type: application/json' -d '{ ... }'
   # 在url末端使用_create
   curl -X PUT http://localhost:9200/nba/_doc/2/_create\?pretty -H 'Content-Type: application/json' -d '{ ... }'
   ```

6. 修改

   ```sh
   curl -X POST http://localhost:9200/nba/_update/2?pretty -H 'Content-Type: application/json' -d '
   {
   	"doc": {
   		"name": "科比",
   		"team_name": "湖人",
   		"position": "锋卫摇摆",
   		"play_year": "20",
   		"jerse_no": "24", 
   		"country": "美国", 
   		"nickname": "黑曼巴"
   	}
   }
   '
   ```

7. 向_source增加一个字段

   ```sh
   curl -X POST http://localhost:9200/nba/_update/2?pretty -H 'Content-Type: application/json' -d '
   {
   	"script": "ctx._source.age = 38"
   }
   '
   ```

8. 从_source中删除一个字段

   ```sh
   curl -X POST http://localhost:9200/nba/_update/2?pretty -H 'Content-Type: application/json' -d '
   {
   	"script": "ctx._source.remove(\"age\")"
   }
   '
   ```

9. 根据参数值，更新指定的文档字段

   ```sh
   # 为age字段值+1，需要有age字段
   curl -X POST http://localhost:9200/nba/_update/2?pretty -H 'Content-Type: application/json' -d '
   {
   	"script": {
   		"source": "ctx._source.age += params.age",
   		"params": {
   			"age": 1
   		}
   	}
   }
   '
   # upsert针对不存在的文档，第一次新增文档，后面更新字段(对非upsert创建的文档使用upsert会报错)
   curl -X POST http://localhost:9200/nba/_update/3?pretty -H 'Content-Type: application/json' -d '
   {
   	"script": {
   		"source": "ctx._source.allstar += params.allstar",
   		"params": {
   			"allstar": 1
   		}
   	},
   	"upsert": {
   		"allstar": 1,
   		"age": 30
   	}
   }
   '
   ```

10. 删除

    ```sh
    curl -X DELETE http://localhost:9200/nba/_doc/3?pretty
    ```

    



---

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