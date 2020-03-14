## Redis

**Redis是一个简单的，高效的，分布式的，基于内存的缓存工具。**

- 特点

  性能极高：Redis能读的速度是110000次/秒，写的速度是81000次/秒，单个key存入512M大小数据。

  丰富的数据类型：Redis支持的类型String，List，Hash，Set及Ordered Set数据类型操作

  原子性：Redis的所有操作都是原子性的，意思就是要么成功执行要么失败完全不执行。单个操作是原子性的，多个操作也支持事务（即原子性），通过MULTI和EXEC指令包起来。可持久化，使用RDB和AOF机制。

  丰富的特性：Redis还支持publish/subscribe，通知，key过期等等特性。支持集群，支持（0-15）16个库。

- 优缺点

  优点：丰富的数据结构。高速读写，使用自己实现的分离器，代码量很短，没有使用Lock（单线程），因为效率非常高。

  缺点：持久化过程消耗大。持久化方式有两种，一是定时快照（snapshot），每隔一段时间将整个数据库写到磁盘上，每次均是写全部数据，代价非常高。二是基于语句追加（AOF），只追踪变化的数据，但是追求的Log可能过大，同时所有的操作均重新执行一遍，回复速度慢。数据存储在内存中，占用内存过高。

------

### 命令

1. keys [pattern]

   检索所有符合条件的key

   通配符：

   ​	* -> 匹配所有

   ​	? -> 匹配单个字符

2. del [key [key ...]]

   删除key

3. dump [key]

   序列化给定key，并返回被序列化的值

4. exists [key [key ...]]

   检查key是否存在

5. expire [key] [seconds] 

   指定key过期时间（单位：秒）

6. ttl [key]

   返回key过期时间（单位：秒），-1：不过期，-2：已过期或不存在

7. pexpire [key] [milliseconds]

   指定key过期时间（单位：毫秒） 

8. pttl [key]

   返回key过期时间（单位：毫秒）

9. persist [key]

   移除key的过期时间，将持久保留

10. randomkey

    从当前数据库中随机返回一个key

11. rename [key] [newkey]

    修改key名称

12. move [key] [dbindex]

    移动key到指定数据库中

13. type key

    返回key所存储值的数据类型

------

### String

string是redis最基本的类型， 一个key对应一个value。

string类型是二进制安全的，意思是redis的string可以包含任何数据。比如jpg图片或者序列化的对象。一个键最大能存储512MB。

注：二进制安全是指，在传输数据时，保证二进制数据的信息安全，也就是不被篡改、破译等，如果被攻击，能够及时检测出来。

二进制安全的特点：

		1. 编码、解码发生在客户端完成，执行效率高。
  		2. 不需要频繁的编解码，不会出现乱码

**设置**

1. set key value

   用于设置给定的key的值。如果key已经存储值，set就覆写旧值，且无视类型。

2. setnx key value

   只有在key不存在时设置key的值。setnx（set if not exists）命令在指定的key不存在时为key设置指定的值。

3. mset key1 value1 [key2 value2...]

   同时设置一个或多个key-value对。

**读取**

4. get key

   get命令用于取指定key的值。如果key不存在，返回nil。如果key存储的值不是字符串类型，返回一个错误。

5. getrange key start end

   用于获取存储在指定key中字符串的子字符串。截取范围由start和end两个偏移量决定（包括start和end在内）。

6. getbit key offset

   对key所存储的字符串值，获取指定偏移量的位（bit）。

7. mget key1 [key2...]

   获取所有（一个或多个）给定key的值。

8. getset key value

   用于设置指定key的值，并返回key的旧值，当key不存在时，返回nil。

9. strlen key

   返回key所存储的字符串值的长度。

**删除**

10. del key [key...]

    删除指定的key，如果存在，返回值数字类型。

**自增自减**

11. incr key

    将key中存储的数字值增加1。如果key不存在，那么key的值会先初始化为0，然后再执行incr操作。

12. incrby key 增量值

    将key中存储的数字加上指定的增量值。

13. decr key

​	将key中存储的数字减1。

14. decrby key 减量值

    将key中存储的数字减去指定的减量值。

15. append key value

    为指定的key追加值至末尾，如果key不存在，为其赋值为value。

------

### 哈希（Hash）

Redis hash是一个string类型的field和value的映射表，hash特别适用于存储对象。每个hash可以存储2^32-1键值对（40多亿）。该类型的数据仅占用很少的磁盘空间（相比于JSON），非常适合于存储值对象的信息。

**赋值**

1. hset key field value

   为指定的key，设定field/value。

2. hmset key field value [field1, value1...]

   同时将多个field-value（域-值）对设置到哈希表key中。

**取值**

3. hget key field

   获取存储在hash中的值，根据field得到value。

4. hmget key field [field1...]

   获取key所有给定字段的值。

5. hgetall key

   返回hash表中所有的字段和值。

6. hkeys key

   获取所有哈希表中的字段。

7. hlen key

   获取哈希表中字段的数量。

**删除**

8. hdel key field [field1...]

   删除一个或多个hash表字段。

**其它**

9. hsetnx key field value

   只在field字段不存在时，设置哈希表字段的值。

10. hincrby key field increment

    为哈希表key中的指定字段的整数值加上增加increment。

11. hincrbyfloat key field increment

    为哈希表key中的指定字段的浮点数值加上增加increment。

12. hexists key field

    查看哈希表key中，指定的字段是否存在。

------

### List

redis列表是简单的字符串列表，按照插入顺序排序，可以添加一个元素到列表的头部或尾部，一个列表最多可以包含2^32-1个元素。

**赋值**

1. lpush key value1 [value2...]

   将一个或多个值插入列表头部。

2. rpush key value1 [value2...]

   将一个或多个值添加到列表尾部。

3. lpushx key value

   将一个值插入已存在的列表头部。如果列表不存在，操作无效。

4. rpushx key value

   将一个值插入已存在的列表尾部。如果列表不存在，操作无效。

**取值**

5. llen key

   获取列表长度。

6. lindex key index

   通过索引获取列表中的元素。

7. lrange key start stop

   获取列表指定范围内的元素。0表示第一个元素，1表示第一个，-1表示最后一个元素，-2表示倒数第二个元素，以此类推。

   **删除**

8. lpop key

   移出并返回列表的第一个元素。

9. rpop key

   移除并返回列表的最后一个元素。

10. blpop key [key1...] timeout

    移出并返回列表的第一个元素，如果列表没有元素会阻塞列表直到等待超时或发现可操作元素为止。若超时则返回nil。

11. brpop key [key1...] timeout

    移出并返回列表的最后一个元素，如果列表没有元素会阻塞列表直到等待超时或发现可操作元素为止。若超时则返回nil。

12. ltrim key start stop

    对一个列表进行修剪，让列表保留指定区间内的元素。

**修改**

13. lset key index value

通过索引设置列表元素的值。

14. linsert key before|after world value

在列表的元素前或后插入元素。将值value插入到列表key当中，位于值world之前或之后。

**高级语法**

15. rpoplpush source destination

    移除列表source的最后一个元素，并将该元素添加到列表destination头部后返回。

16. brpoplpush source destination timeout

    移除列表source的最后一个元素，并将该元素添加到列表destination头部后返回。若source不存在，会进入等待。等待超时后返回nil。

------

### Set

Redis的set是string类型的无序集合。集合成员是唯一的，这就意味着集合中不能出现重复的数据。集合是通过哈希表实现，所以添加、删除、查找的复杂度都是O(1)。集合最大的成员数为2^32-1。

**赋值**

1. sadd key member1 [member2...]

   向集合添加一个或多个成员。

**取值**

2. scard key

   获取集合的成员数。

3. smembers key

   返回集合中的所有成员。

4. sismember key member

   判断member元素是否是集合key的成员。

5. srandmember key [count]

   返回集合中一个或多个随机数。

**删除**

6. srem key member1 [member2...]

   移除集合中一个或多个成员。

7. spop key [count]

   移除并返回集合中的count个随机元素。

8. smove source destination member

   将member元素从source集合移动到destination集合。

**差集**

9. sdiff key1 [key2...]

   返回给定的所有集合的差集，以key1为基础。

10. sdiffstore destination key1 [key2...]

    返回给定所有集合的差集并存储在destination中。

**交集**

11. sinter key1 [key2...]

    返回给定所有集合的交集（共有数据）。

12. sinterstore destination key1 [key2...]

    返回给定所有集合的交集并存储在destination中。

**并集**

13. sunion key1 [key2...]

    返回所有给定集合的并集。

14. sunionstore destination key1 [key2...]

    返回给定所有集合的并集并存储在destination中。

------

### ZSet

- Redis有序集合和集合一样也是String类型元素的集合，且不允许重复的成员。
- 不同的是每个元素都会关联一个Double类型的分数。Redis正是通过分数来为集合中的成员进行从小到大的排序。
- 有序集合的成员是唯一的，但分数（Score）却可以重复。
- 集合是通过哈希表实现的，所以添加、删除、查找的复杂度都是O(1)。集合中最大的成员数为2^32-1。

**赋值**

1. zadd key score1 member1 [score2 member2...]

   向有序集合添加一个或多个成员，或者更新已存在的成员分数。

**取值**

2. zcard key

   获取有序集合的成员数。

3. zcount key min max

   计算在有序集合中指定区间分数的成员数。

4. zrank key member

   返回有序集合中指定成员的索引。

5. zrange key start stop [withscores]

   通过索引区间返回有序集合指定区间内的成员（低到高）。

6. zrevrange key start stop [withscores]

   返回有序集合中指定区间内的成员，通过索引，分数从高到低。

**删除**

7. del key

   移除集合。

8. zrem key member [member1...]

   移除有序集合中的一个或多个成员。

9. zremrangebyrank key start stop

   移除有序集体中给定的排名区间（位置下标）的所有成员（第一名是0）。

10. zremrangebyscore key min max

    移除有序集合中给定分数区间的成员。

------

### Redis发布订阅

Redis发布订阅（pub/sub）是一种消息通信模式：发送者（pub）发送信息，订阅者（sub）接收消息。Redis客户端可以订阅任意数量的频道。

**订阅**

1. subscribe channel [channel1...]

   订阅给定的一个或多个频道的信息。

2. psubscribe pattern [pattern1...]

   订阅一个或多个符合给定模式的频道。

**发布**

3. publish channel message 将信息发送到指定的频道。

**退订**

4. unsubscribe [channel [channel1...]]

   退订给定的频道。

5. punsubscribe [pattern [pattern1...]]

   退订所有给定模式的频道。

------

### Redis多数据库

Reids数据库是由一个整数索引标识，而不是由一个数据库名称。默认情况下，一个客户端连接到数据库0。

Redis配置文件中下面的参数来控制数据库总数：

```
database 16 # 索引标识从0开始，0～15
```

1. select [数据库]

   切换数据库。

2. move key 数据库

   移动数据（将当前key移动到指定库）。

3. flushdb

   清除当前数据库。

4. flushall

   清除整个Redis的所有数据库。

------

### Redis事务

Redis事务可以一次执行多个命令，按顺序地串行化执行，执行中不会被其它命令插入，不许加塞。

- 事务在multi命令后开启，批量操作在发送exec命令前被放入队列缓存。
- 收到exec命令后进入事务执行，事务中任意命令执行失败，其它的命令依然被执行。
- 在事实执行过程中，其它客户端提交的命令请求不会插入到事务执行命令序列中。
- 队列中的命令出现了报告错误（在加入队列缓存时提示的错误，如：语法错误），执行exec时会直接返回错误，整个队列都会被取消。

1. multi

   标记一个事务块的开始。

2. exec

   执行所有事务块内的命令。

3. discard

   取消事务，放弃执行事务块内的所有命令。

4. watch key [key...]

   监视一个（或多个）key，如果在事务执行之前这个（或这些）key被其他命令所改动，那么事务将被打断。

5. unwatch

   取消watch命令对所有key的监视。

------

### Redis数据淘汰策略

当内存不足时，Redis会根据配置的缓存策略淘汰部分key，以保证写入成功。当无淘汰策略或没有找到适合淘汰的key时，Redis会直接返回out of memory错误。配置文件默认为redis.conf。

**最大缓存配置**

```
maxmemory 512G 	# 在redis中，允许用户设置最大使用内存大小。
```

**淘汰策略**

1. volatile-lru：从已设置过期时间的数据集中挑选最近最少使用的数据淘汰。
2. volatile-lfu：从已设置过期的Keys中，删除一段时间内使用次数最少使用的数据。
3. volatile-ttl：从已设置过期时间的数据集中挑选最近将要过期的数据淘汰。
4. volatile-random：从已设置过期时间的数据集中随机选择数据淘汰。
5. allkeys-lru：从数据集中挑选最少使用的数据淘汰。
6. allkeys-lfu：从所有Keys中，删除一段时间内使用次数最少的数据。
7. allkeys-random：从数据集中随机选择数据淘汰。
8. no-enviction（驱逐）：禁止驱逐数据（不采用任何淘汰策略，默认配置），针对写操作返回错误信息。

------

### Redis持久化

数据存放于：

- 内存：高效，断电（关机）内存数据会丢失。
- 硬盘：读写速度慢于内存，断电数据不会丢失。

**RDB**

Redis的默认持久化机制。

相当于快照，保存的是一种状态。几十G数据 --> 几KB快照。

快照是默认的持久化方式。这种方式就是将内存中的数据以快照的方式写入到二进制文件中，默认的文件名为dump.rdb。

优点：快照保存数据极快、还原数据极快。适用于空难备份。

缺点：小内存机器不适合使用，RDB机制符合要求就会照快照。

快照条件：

1. 服务器正常关闭

   ```
   ./bin/redis-cli shutdown
   ```

2. key满足一定条件（redis.conf中的save配置），会进行快照

   ```
   save 900 1 # 每900秒（15分钟）至少1个key发生变化
   save 300 10 # 每300秒（5分钟）至少10个key发生变化
   save 60 10000 # 每60秒（1分钟）至少10000个key发生变化
   ```

**AOF**

由于快照方式是在一定时间间隔做一次的，所以如果Redis意思down掉的话，就会丢失最后一次快照后的所有修改。如果应用要求不能丢失任何修改的话，可以采用aof持久化方式。

Append-only file：aof比rdb方式有更好的持久化，是由于在使用aof持久化方式时，Redis会将每一个收到的写命令都通过write函数追加到文件中（默认是appendonly.aof）。当Redis重启时会通过重新执行文件中保存的写命令来在内存中重建整个数据库的内容。

有三种方式（默认是：每秒fsync一次）：

```
appendonly yes # 启用aof持久化方式

appendfsync always # 收到写命令就立即写入磁盘，最慢，但是保证完成的持久化
appendfsync everysec # 每秒钟写入磁盘一次，在性能和持久化方面做了很好的折中
appendfsync no # 完成依赖os，性能最好，持久化没保证
```

缺点：aof的方式也同时带来了另一个问题。持久化文件会越来越大。例如我们调用incr test命令100次，文件中必须保存全部的100条命令，其实有99条都是多余的。

------

### Redis主从复制

一个Redis服务可以有多个该服务的复制品，这个Redis服务称为Master，其它复制称为Slaves。主库负责写数据，每次有数据更新都将更新的数据同步到它所有的从库，而从库只负责读数据。这样做的好处：

1. 读写分离，不仅可以提高服务的负载能力，并且可以根据读请求的规模自由增加或者减少从库的数量。
2. 数据被复制成了好几份，就算有一台机器出现故障，也可以使用其它机器的数据快速恢复。

注意：一台主库可以拥有多个从库，但是一个从库只能隶属于一个主库。

```
# 启动主库
redis-server ./redis.conf
# 创建从库：指定端口6380，slaveof指定为6379库的从库
redis-server ./redis.conf --port 6380 --slaveof 127.0.0.1 6379
```

```
slaveof on one # 变回主库
slaveof [ip] [port] # 变回从库
```

------

### Redis Cluster集群

为了在大流量访问下提供稳定的业务，集群化是存储的必然形态。

Redis集群搭建的方式有多种，但从redis 3.0之后版本支持redis-cluster集体，至少需要3（Master）+3（Slave）才能建立集群。Redis-Cluster采用无中心结构，每个节点保存数据和整个集群状态，每个节点和其它所有节点连接。

**特点**

1. 所有的redis节点彼此互联（PING-PONG机制），内部使用二进制协议优化传输速度和带宽。
2. 节点的fail是通过集群中超过半数节点检测失效时才生效。
3. 客户端与redis节点直连，不需要中间proxy层。客户端不需要连接集群所有节点，连接集群中任何一个可用节点即可。
4. redis-cluster把所有的物理节点映射到[0-16383]slot上（不一定是平均分配），cluster负责维护。
5. Redis集群预分好16384个哈希槽，当需要在Redis集群中放置一个key-value时，redis先对key使用crc16算法算出一个结果，然后把结果对16384求余数，这样每个key都会对应一个编号在0-16383之间的哈希槽，redis会根据节点数量大致均等的将哈希槽映射到不同的节点。

**容错**

是指软件检测应用程序所运行的软件或硬件中发生的错误中恢复的能力。通常可以从系统的可靠性、可用性、可测性等几个方面来衡量。

Redis-Cluster投票：

1. 投票过程是集群中所有master参与，如果半数以上master节点与master节点通信过时（cluster-node-timeout），认为当前master节点挂掉。
2. 什么时候整个集群不可用（cluster_state:fail）？如果集群任意master挂掉，且当前master没有slave，集群进入fail状态，也可以理解成集群的slot映射[0-16383]不完整时进入fail状态。如果集群超过半数以上master挂掉，无论是否有slave，集群进入fail状态。

**节点分配**

（官方推荐）三个主节点分别是：A、B、C三个节点，它们可以是一台机器上的三个端口，也可以是三台不同的服务器。那么，采用哈希槽（hash slot）的方式来分配16384个slot的话，它们三个节点分别承担的slot区间分别是：

- 节点A覆盖0-5460
- 节点B覆盖5461-10922
- 节点C覆盖10923-16383

新增一个节点D，redis-cluster的这种做法是从各个节点的前面各拿取一部分slot到D上：

- 节点A覆盖1365-5460
- 节点B覆盖6827-10922
- 节点C覆盖12288-16383
- 节点D覆盖0-1364,5461-6826,10923-12287

**集群搭建**

群集中至少应该有奇数个节点，所以搭建集群最少需要3台主机。同时每个节点至少有一个备份节点，所以下面最少需要创建6台机器，才能完成Redis Cluster集群（主节点、备份节点由redis-cluster集群确定）。

1. 创建Redis节点安装目录

   ```
   mkdir /usr/local/redis_cluster
   ```

2. 在redis_cluster目录下，创建7001-7006六个文件夹

   ```
   cd /usr/local/redis_cluster
   mkdir 7001 7002 7003 7004 7005 7006
   # 或 ####################
   mkdir -p /usr/local/redis_cluster/{7001,7002,7003,7004,7005,7006}
   ```

3. 将redis-conf分别拷贝到7001-7006文件夹下

   ```
   cp /etc/redis/redis.conf /usr/local/redis_cluster/7001
   cp /etc/redis/redis.conf /usr/local/redis_cluster/7002
   cp /etc/redis/redis.conf /usr/local/redis_cluster/7003
   cp /etc/redis/redis.conf /usr/local/redis_cluster/7004
   cp /etc/redis/redis.conf /usr/local/redis_cluster/7005
   cp /etc/redis/redis.conf /usr/local/redis_cluster/7006
   # 或 ####################
   for dir in /data/redis-cluster/700* ; do cp -v redis.conf $dir ; done
   ```

4. 分别修改如下配置文件，修改如下内容

   同时protected-mode：是为禁止公网访问redis cache，加强redis安全。启用条件有两个：

   1) 没有bind ip

   2) 没有设置访问密码

   由于Linux上的redis处于安全保护模式，这就无法从服务器外部去轻松建立连接。

   若需要外部访问：redis.conf中设置保护模式为protected-mode no

5. 修改配置文件

   ```
   bind [ip]	# 绑定服务器ip地址
   port 7001	# 绑定端口号，以此区分redis实例，与创建的文件夹名一致
   daemonize yes 	# 后台运行
   pidfile /var/run/redis-7001.pid	# 修改pid进程文件名，以端口号命名
   logfile [日志文件名]	# 日志文件名，最好以端口号作为文件夹区分
   dir [数据文件存放地址]	# 文件夹，最好以端口号作为文件夹区分
   cluster-enabled yes	# 启用集群
   cluster-config-file nodes-7001.conf	# 配置每个节点的文件，以端口号为名称，这个文件不能删除也不能修改
   cluster-node-timeout 15000	# 配置集群节点的超时时间
   appendonly yes	# 启用AOF增量持久化策略
   appendfsync always	# 发生改变就记录日志
   # slaveof <masterip> <masterport> 可以配置中指定为某个主库的从库
   
   # 或 ###########################
   for dir in /data/redis-cluster/700*; do
   sed -r -i "
   s@^\s*port.*@port ${dir##*/}@ ;
   s@^\s*daemonize.*@daemonize yes@ ;
   s@^\s*pidfile.*@pidfile /var/run/redis_${dir##*/}.pid@ ;
   s@^\s*logfile.*@logfile /var/log/redis_${dir##*/}.log@;
   s@^\s*#?\s*cluster-enabled.*@cluster-enabled yes@;
   s@^\s*#?\s*cluster-config-file.*@cluster-config-file $dir/nodes-${dir##*/}.conf@;
   s@^\s*#?\s*cluster-node-timeout.*@cluster-node-timeout 15000@;
   s@^\s*dir.*@dir $dir@;
   " $dir/redis.conf
   done
   ```

6. 启动redis服务

   ```
   redis-server /usr/local/redis_cluster/7001/redis.conf
   redis-server /usr/local/redis_cluster/7002/redis.conf
   redis-server /usr/local/redis_cluster/7003/redis.conf
   redis-server /usr/local/redis_cluster/7004/redis.conf
   redis-server /usr/local/redis_cluster/7005/redis.conf
   redis-server /usr/local/redis_cluster/7006/redis.conf
   # 或 ###########################
   for dir in /data/redis-cluster/700*; do /usr/local/redis/bin/redis-server $dir/redis.conf ; done
   ```

**创建集群**

Redis官方提供了redis-trib.rb这个工具创建集群，这个工具使用Ruby实现，若使用需要安装Ruby环境。

可以直接在命令行中执行：

```
# --replicas 1 = 主/从（个数） 默认将前三个作主，后三个作从
redis-trib.rb create --replicas 1 127.0.0.1:7001 127.0.0.1:7002 127.0.0.1:7003 127.0.0.1:7004 127.0.0.1:7005 127.0.0.1:7006
```

