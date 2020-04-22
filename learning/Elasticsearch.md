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

DSL 介绍
这个才是实际最常用的方式，可以构建复杂的查询条件。
不用一开始就想着怎样用 Java Client 端去调用 Elasticsearch 接口。DSL 会了，Client 的也只是用法问题而已。
DSL 语句的校验以及 score 计算原理
对于复杂的查询，最好都先校验下，看有没有报错。

```
GET /product_index/product/_validate/query?explain
{
  "query": {
    "match": {
      "product_name": "toothbrush"
    }
  }
}
```

DSL 简单用法
查询所有的商品：

```
GET /product_index/product/_search
{
  "query": {
    "match_all": {}
  }
}
```

查询商品名称包含 toothbrush 的商品，同时按照价格降序排序：

```
GET /product_index/product/_search
{
  "query": {
    "match": {
      "product_name": "toothbrush"
    }
  },
  "sort": [
    {
      "price": "desc"
    }
  ]
}
```

分页查询商品：

```
GET /product_index/product/_search
{
  "query": {
    "match_all": {}
  },
  "from": 0, ## 从第几个商品开始查，最开始是 0
  "size": 1  ## 要查几个结果
}
```

指定查询结果字段（field）

```
GET /product_index/product/_search
{
  "query": {
    "match_all": {}
  },
  "_source": [
    "product_name",
    "price"
  ]
}
```

相关符号标识，官网：https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-range-query.html
符号标识	代表含义
gte	大于或等于
gt	大于
lte	小于或等于
lt	小于
搜索商品名称包含 toothbrush，而且售价大于 400 元，小于 700 的商品

```
GET /product_index/product/_search
{
  "query": {
    "bool": {
      "must": {
        "match": {
          "product_name": "toothbrush"
        }
      },
      "filter": {
        "range": {
          "price": {
            "gt": 400,
            "lt": 700
          }
        }
      }
    }
  }
}
```

full-text search 全文检索，倒排索引
索引中只要有任意一个匹配拆分后词就可以出现在结果中，只是匹配度越高的排越前面
比如查询：PHILIPS toothbrush，会被拆分成两个单词：PHILIPS 和 toothbrush。只要索引中 product_name 中只要含有任意对应单词，都会在搜索结果中，只是如果有数据同时含有这两个单词，则排序在前面。

```
GET /product_index/product/_search
{
  "query": {
    "match": {
      "product_name": "PHILIPS toothbrush"
    }
  }
}
```

phrase search 短语搜索
索引中必须同时匹配拆分后词就可以出现在结果中
比如查询：PHILIPS toothbrush，会被拆分成两个单词：PHILIPS 和 toothbrush。索引中必须有同时有这两个单词的才会在结果中。

```
GET /product_index/product/_search
{
  "query": {
    "match_phrase": {
      "product_name": "PHILIPS toothbrush"
    }
  }
}
```

Highlight Search 高亮搜索
给匹配拆分后的查询词增加高亮的 html 标签，比如这样的结果："<em>PHILIPS</em> <em>toothbrush</em> HX6730/02"

```
GET /product_index/product/_search
{
  "query": {
    "match": {
      "product_name": "PHILIPS toothbrush"
    }
  },
  "highlight": {
    "fields": {
      "product_name": {}
    }
  }
}
```

range 用法，查询数值、时间区间：

```
GET /product_index/product/_search
{
  "query": {
    "range": {
      "price": {
        "gte": 30.00
      }
    }
  }
}
```

match 用法（与 term 进行对比）：
查询的字段内容是进行分词处理的，只要分词的单词结果中，在数据中有满足任意的分词结果都会被查询出来

```
GET /product_index/product/_search
{
  "query": {
    "match": {
      "product_name": "PHILIPS toothbrush"
    }
  }
}
```

match 还有一种情况，就是必须满足分词结果中所有的词，而不是像上面，任意一个就可以的。（这个常见，所以很重要）
看下面的 JSON 其实你也可以猜出来，其实上面的 JSON 和下面的 JSON 本质是：operator 的差别，上面是 or，下面是 and 关系。

```
GET /product_index/product/_search
{
  "query": {
    "match": {
      "product_name": {
        "query": "PHILIPS toothbrush",
        "operator": "and"
      }
     }
   }
}
```

match 还还有一种情况，就是必须满足分词结果中百分比的词，比如搜索词被分成这样子：java 程序员 书 推荐，这里就有 4 个词，假如要求 50% 命中其中两个词就返回，我们可以这样：
当然，这种需求也可以用 must、must_not、should 匹配同一个字段进行组合来查询

```
GET /product_index/product/_search
{
  "query": {
    "match": {
      "product_name": {
        "query": "java 程序员 书 推荐",
        "minimum_should_match": "50%"
      }
    }
  }
}
```

multi_match 用法：
查询 product_name 和 product_desc 字段中，只要有：toothbrush 关键字的就查询出来。

```
GET /product_index/product/_search
{
  "query": {
    "multi_match": {
      "query": "toothbrush",
      "fields": [
        "product_name",
        "product_desc"
      ]
    }
  }
}
```

multi_match 跨多个 field 查询，表示查询分词必须出现在相同字段中。

```
GET /product_index/product/_search
{
  "query": {
    "multi_match": {
      "query": "PHILIPS toothbrush",
      "type": "cross_fields",
      "operator": "and",
      "fields": [
        "product_name",
        "product_desc"
      ]
    }
  }
}
```

match_phrase 用法（短语搜索）（与 match 进行对比）：
对这个查询词不进行分词，必须完全匹配查询词才可以作为结果显示。

```
GET /product_index/product/_search
{
  "query": {
    "match_phrase": {
      "product_name": "PHILIPS toothbrush"
    }
  }
}
```

match_phrase + slop（与 match_phrase 进行对比）：
在说 slop 的用法之前，需要先说明原数据是：PHILIPS toothbrush HX6730/02，被分词后至少有：PHILIPS，toothbrush，HX6730 三个 term。
match_phrase 的用法我们上面说了，按理说查询的词必须完全匹配才能查询到，PHILIPS HX6730 很明显是不完全匹配的。
但是有时候我们就是要这种不完全匹配，只要求他们尽可能靠谱，中间有几个单词是没啥问题的，那就可以用到 slop。slop = 2 表示中间如果间隔 2 个单词以内也算是匹配的结果（）。
其实也不能称作间隔，应该说是移位，查询的关键字分词后移动多少位可以跟 doc 内容匹配，移动的次数就是 slop。所以 HX6730 PHILIPS 其实也是可以匹配到 doc 的，只是 slop = 5 才行。

```
GET /product_index/product/_search
{
  "query": {
    "match_phrase": {
      "product_name" : {
          "query" : "PHILIPS HX6730",
          "slop" : 1
      }
    }
  }
}
```

match + match_phrase + slop 组合查询，使查询结果更加精准和结果更多
但是 match_phrase 性能没有 match 好，所以一般需要先用 match 第一步进行过滤，然后在用 match_phrase 进行进一步匹配，并且重新打分，这里又用到了：rescore，window_size 表示对前 10 个进行重新打分
下面第一个是未重新打分的，第二个是重新打分的

```
GET /product_index/product/_search
{
  "query": {
    "bool": {
      "must": {
        "match": {
          "product_name": {
            "query": "PHILIPS HX6730"
          }
        }
      },
      "should": {
        "match_phrase": {
          "product_name": {
            "query": "PHILIPS HX6730",
            "slop": 10
          }
        }
      }
    }
  }
}
```

```
GET /product_index/product/_search
{
  "query": {
    "match": {
      "product_name": "PHILIPS HX6730"
    }
  },
  "rescore": {
    "window_size": 10,
    "query": {
      "rescore_query": {
        "match_phrase": {
          "product_name": {
            "query": "PHILIPS HX6730",
            "slop": 10
          }
        }
      }
    }
  }
}
```

match_phrase_prefix 用法（不常用），一般用于类似 Google 搜索框，关键字输入推荐
max_expansions 用来限定最多匹配多少个 term，优化性能
但是总体来说性能还是很差，因为还是会扫描整个倒排索引。推荐用 edge_ngram 做该功能

```
GET /product_index/product/_search
{
  "query": {
    "match_phrase_prefix": {
      "product_name": "PHILIPS HX",
      "slop": 5,
      "max_expansions": 20
    }
  }
}
```

term 用法（与 match 进行对比）（term 一般用在不分词字段上的，因为它是完全匹配查询，如果要查询的字段是分词字段就会被拆分成各种分词结果，和完全查询的内容就对应不上了。）：
所以自己设置 mapping 的时候有些不分词的时候就最好设置不分词。
其实 Elasticsearch 5.X 之后给 text 类型的分词字段，又默认新增了一个子字段 keyword，这个字段的类型就是 keyword，是不分词的，默认保留 256 个字符。假设 product_name 是分词字段，那有一个 product_name.keyword 是不分词的字段，也可以用这个子字段来做完全匹配查询。

```
GET /product_index/product/_search
{
  "query": {
    "term": {
      "product_name": "PHILIPS toothbrush"
    }
  }
}
```

```
GET /product_index/product/_search
{
  "query": {
    "constant_score": {
      "filter":{
        "term": {
          "product_name": "PHILIPS toothbrush"
        }
      }
    }
  }
}
```

terms 用法，类似于数据库的 in

```
GET /product_index/product/_search
{
  "query": {
    "constant_score": {
      "filter": {
        "terms": {
          "product_name": [
            "toothbrush",
            "shell"
          ]
        }
      }
    }
  }
}
```

query 和 filter 差异
只用 query：

```
GET /product_index/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "terms": {
            "product_name": [
              "PHILIPS",
              "toothbrush"
            ]
          }
        },
        {
          "range": {
            "price": {
              "gt": 12.00
            }
          }
        }
      ]
    }
  }
}
```

只用 filter：
下面语句使用了 constant_score 查询，它可以包含查询或过滤，为任意一个匹配的文档指定评分 1 ，忽略 TF/IDF 信息，不需再计算评分。
也还可以指定分数：https://www.elastic.co/guide/cn/elasticsearch/guide/current/ignoring-tfidf.html

```
GET /product_index/product/_search
{
  "query": {
    "constant_score": {
      "filter": {
        "range": {
          "price": {
            "gte": 30.00
          }
        }
      }
    }
  }
}
```

query 和 filter 一起使用的话，filter 会先执行，看本文下面的：多搜索条件组合查询
官网文档：https://www.elastic.co/guide/en/elasticsearch/guide/current/_queries_and_filters.html
从搜索结果上看：
filter，只查询出搜索条件的数据，不计算相关度分数
query，查询出搜索条件的数据，并计算相关度分数，按照分数进行倒序排序
从性能上看：
filter（性能更好，无排序），无需计算相关度分数，也就无需排序，内置的自动缓存最常使用查询结果的数据
缓存的东西不是文档内容，而是 bitset，具体看：https://www.elastic.co/guide/en/elasticsearch/guide/2.x/_finding_exact_values.html#_internal_filter_operation
query（性能较差，有排序），要计算相关度分数，按照分数进行倒序排序，没有缓存结果的功能
filter 和 query 一起使用可以兼顾两者的特性，所以看你业务需求。324
多搜索条件组合查询（最常用）
bool 下包括：must（必须匹配，类似于数据库的 =），must_not（必须不匹配，类似于数据库的 !=），should（没有强制匹配，类似于数据库的 or），filter（过滤）

```
GET /product_index/product/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "match": {
            "product_name": "PHILIPS toothbrush"
          }
        }
      ],
      "should": [
        {
          "match": {
            "product_desc": "刷头"
          }
        }
      ],
      "must_not": [
        {
          "match": {
            "product_name": "HX6730"
          }
        }
      ],
      "filter": {
        "range": {
          "price": {
            "gte": 33.00
          }
        }
      }
    }
  }
}
```

```
GET /product_index/product/_search
{
  "query": {
    "bool": {
      "should": [
        {
          "term": {
            "product_name": "飞利浦"
          }
        },
        {
          "bool": {
            "must": [
              {
                "term": {
                  "product_desc": "刷头"
                },
                "term": {
                  "price": 30
                }
              }
            ]
          }
        }
      ]
    }
  }
}
```

should 有一个特殊性，如果组合查询中没有 must 条件，那么 should 中必须至少匹配一个。我们也还可以通过 minimum_should_match 来限制它匹配更多个。

```
GET /product_index/product/_search
{
  "query": {
    "bool": {
      "should": [
        {
          "match": {
            "product_name": "java"
          }
        },
        {
          "match": {
            "product_name": "程序员"
          }
        },
        {
          "match": {
            "product_name": "书"
          }
        },
        {
          "match": {
            "product_name": "推荐"
          }
        }
      ],
      "minimum_should_match": 3
    }
  }
}
```

下面还用到自定义排序。
排序最好别用到字符串字段上。因为字符串字段会进行分词，Elasticsearch 默认是拿分词后的某个词去进行排序，排序结果往往跟我们想象的不一样。解决这个办法是在设置 mapping 的时候，多个这个字段设置一个 fields raw，让这个不进行分词，然后查询排序的时候使用这个 raw，具体看这里：https://www.elastic.co/guide/cn/elasticsearch/guide/current/multi-fields.html

```
GET /product_index/product/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "match": {
            "product_name": "PHILIPS toothbrush"
          }
        }
      ],
      "should": [
        {
          "match": {
            "product_desc": "刷头"
          }
        }
      ],
      "filter": {
        "bool": {
          "must": [
            {
              "range": {
                "price": {
                  "gte": 33.00
                }
              }
            },
            {
              "range": {
                "price": {
                  "lte": 555.55
                }
              }
            }
          ],
          "must_not": [
            {
              "term": {
                "product_name": "HX6730"
              }
            }
          ]
        }
      }
    }
  },
  "sort": [
    {
      "price": {
        "order": "desc"
      }
    }
  ]
}
```

boost 用法（默认是 1）。在搜索精准度的控制上，还有一种需求，比如搜索：PHILIPS toothbrush，要比：Braun toothbrush 更加优先，我们可以这样：

```
GET /product_index/product/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "match": {
            "product_name": "toothbrush"
          }
        }
      ],
      "should": [
        {
          "match": {
            "product_name": {
              "query": "PHILIPS",
              "boost": 4
            }
          }
        },
        {
          "match": {
            "product_name": {
              "query": "Braun",
              "boost": 3
            }
          }
        }
      ]
    }
  }
}
```

dis_max 用法，也称作：best fields 策略。
由于查询关键字是会被分词的，默认 query bool 查询多个字段的语法时候，每个字段匹配到一个或多个的时候分数比：一个字段匹配到查询分词的所有结果的分数来的大。但是对于我们来讲这样的不够精准的。所以我们希望查询字段中，匹配的关键字越多排序越靠前，而不是每个字段查询了一个分词就排前，我们可以使用 dis_max。
但是使用 dis_max，一般还不够，建议再加上 tie_breaker。
tie_breaker 是一个小数值，在 0~1 之间用来将其他查询结果分数，乘以 tie_breaker 的值，然后再综合与 dis_max 最高分数的的分数一起进行计算。除了取 dis_max 的最高分以外，还会考虑其他的查询结果的分数。
在 dis_max 基础上，为了增加精准，我们还可以加上：boost、minimum_should_match 等相关参数。其中 minimum_should_match 比较常用，因为查询字段的分词中如果只有一个分词查询上了这种结果基本是没啥用的。
官网资料：https://www.elastic.co/guide/en/elasticsearch/guide/current/_best_fields.html

```
GET /product_index/product/_search
{
  "query": {
    "dis_max": {
      "queries": [
        {
          "match": {
            "product_name": "PHILIPS toothbrush"
          }
        },
        {
          "match": {
            "product_desc": "iphone shell"
          }
        }
      ],
      "tie_breaker": 0.2
    }
  }
}
```

```
GET /product_index/product/_search
{
  "query": {
    "dis_max": {
      "queries": [
        {
          "match": {
            "product_name": {
              "query": "PHILIPS toothbrush",
              "minimum_should_match": "50%",
              "boost": 3
            }
          }
        },
        {
          "match": {
            "product_desc": {
              "query": "iphone shell",
              "minimum_should_match": "50%"，
              "boost": 2
            }
          }
        }
      ],
      "tie_breaker": 0.3
    }
  }
}
```

prefix 前缀搜索（性能较差，扫描所有倒排索引）
比如有一个不分词字段 product_name，分别有两个 doc 是：iphone-6，iphone-7。我们搜索 iphone 这个前缀关键字就可以搜索到结果

```
GET /product_index/product/_search
{
  "query": {
    "prefix": {
      "product_name": {
        "value": "iphone"
      }
    }
  }
}
```

通配符搜索（性能较差，扫描所有倒排索引）

```
GET /product_index/product/_search
{
  "query": {
    "wildcard": {
      "product_name": {
        "value": "ipho*"
      }
    }
  }
}
```

正则搜索（性能较差，扫描所有倒排索引）

```
GET /product_index/product/_search
{
  "query": {
    "regexp": {
      "product_name": "iphone[0-9].+"
    }
  }
}
```

fuzzy 纠错查询
参数 fuzziness 默认是 2，表示最多可以纠错两次，但是这个值不能很大，不然没效果。一般 AUTO 是自动纠错。
下面的关键字漏了一个字母 o。

```
GET /product_index/product/_search
{
  "query": {
    "match": {
      "product_name": {
        "query": "PHILIPS tothbrush",
        "fuzziness": "AUTO",
        "operator": "and"
      }
    }
  }
}
```


