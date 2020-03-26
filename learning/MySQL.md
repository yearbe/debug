# `MySQL`

### 事务

- 在 MySQL 中只有使用了 Innodb 数据库引擎的数据库或表才支持事务。
- 事务处理可以用来维护数据库的完整性，保证成批的 SQL 语句要么全部执行，要么全部不执行。
- 事务用来管理 insert,update,delete 语句
- 事务是必须满足4个条件（ACID）：：原子性（**A**tomicity，或称不可分割性）、一致性（**C**onsistency）、隔离性（**I**solation，又称独立性）、持久性（**D**urability）
  - **原子性：**一个事务（transaction）中的所有操作，要么全部完成，要么全部不完成，不会结束在中间某个环节。事务在执行过程中发生错误，会被回滚（Rollback）到事务开始前的状态，就像这个事务从来没有执行过一样。
  - **一致性：**在事务开始之前和事务结束以后，数据库的完整性没有被破坏。这表示写入的资料必须完全符合所有的预设规则，这包含资料的精确度、串联性以及后续数据库可以自发性地完成预定的工作。
  - **隔离性：**数据库允许多个并发事务同时对其数据进行读写和修改的能力，隔离性可以防止多个事务并发执行时由于交叉执行而导致数据的不一致。事务隔离分为不同级别，包括读未提交（Read uncommitted）、读提交（read committed）、可重复读（repeatable read）和串行化（Serializable）。
  - **持久性：**事务处理结束后，对数据的修改就是永久的，即便系统故障也不会丢失。

#### 事务控制语句

- BEGIN 或 START TRANSACTION 显式地开启一个事务；
- COMMIT 也可以使用 COMMIT WORK，不过二者是等价的。COMMIT 会提交事务，并使已对数据库进行的所有修改成为永久性的；
- ROLLBACK 也可以使用 ROLLBACK WORK，不过二者是等价的。回滚会结束用户的事务，并撤销正在进行的所有未提交的修改；
- SAVEPOINT identifier，SAVEPOINT 允许在事务中创建一个保存点，一个事务中可以有多个 SAVEPOINT；
- RELEASE SAVEPOINT identifier 删除一个事务的保存点，当没有指定的保存点时，执行该语句会抛出一个异常；
- ROLLBACK TO identifier 把事务回滚到标记点；
- SET TRANSACTION 用来设置事务的隔离级别。InnoDB 存储引擎提供事务的隔离级别有READ UNCOMMITTED、READ COMMITTED、REPEATABLE READ 和 SERIALIZABLE。

**`MYSQL`事务处理：**

1. 用 BEGIN, ROLLBACK, COMMIT来实现
   - BEGIN 开始一个事务
   - ROLLBACK 事务回滚
   - COMMIT 事务确认
2. 直接用 SET 来改变 MySQL 的自动提交模式:
   - SET AUTOCOMMIT=0 禁止自动提交
   - SET AUTOCOMMIT=1 开启自动提交



### **`my.cnf`配置文件 **

```mysql
[client]
default-character-set = utf8mb4

[mysql]
#开启 tab 补全
#auto-rehash
default-character-set = utf8mb4

[mysqld]
port=3306
basedir=/data/server/mysql57/
datadir=/data/server/mysql57/data/
socket=/data/server/mysql57/data/mysql.sock
symbolic-links=0
log-error=/data/logs/mysql57/mysqld.log
pid-file=/data/server/mysql57/data/mysqld57.pid

# 禁用主机名解析
skip-name-resolve
# 默认的数据库引擎
default-storage-engine = InnoDB
innodb-file-per-table=1innodb_force_recovery = 1#一些坑
group_concat_max_len = 10240sql_mode=expire_logs_days = 7memlock

### 字符集配置
character-set-client-handshake = FALSE
character-set-server = utf8mb4
collation-server = utf8mb4_unicode_ci
init_connect='SET NAMES utf8mb4'### GTID
server_id = 330759# 为保证 GTID 复制的稳定，行级日志
binlog_format = row
# 开启 gtid 功能
gtid_mode = on
# 保障 GTID 事务安全
# 当启用enforce_gtid_consistency功能的时候,
# MySQL只允许能够保障事务安全，并且能够被日志记录的SQL语句被执行,
# 像create table ... select 和 create temporarytable语句，
# 以及同时更新事务表和非事务表的SQL语句或事务都不允许执行
enforce-gtid-consistency = true# 以下两条配置为主从切换，数据库高可用的必须配置
# 开启 binlog 日志功能
log_bin = mysql57-bin 
# 开启从库更新 binlog 日志
log-slave-updates = on
#slave复制进程不随mysql启动而启动
skip_slave_start=1### 慢查询日志
# 打开慢查询日志功能
slow_query_log = 1# 超过2秒的查询记录下来
long_query_time = 2# 记录下没有使用索引的查询
log_queries_not_using_indexes = 0slow_query_log_file =/data/logs/mysql57/slow.log
#log=/data/logs/mysql57/all.log
### 自动修复
# 记录 relay.info 到数据表中
relay_log_info_repository = TABLE
# 记录 master.info 到数据表中 
master_info_repository = TABLE
# 启用 relaylog 的自动修复功能
relay_log_recovery = on
# 在 SQL 线程执行完一个 relaylog 后自动删除
relay_log_purge = 1### 数据安全性配置
# 为关闭 master 创建 function 的功能
log_bin_trust_function_creators = on
# 每执行一个事务都强制写入磁盘
sync_binlog = 1# timestamp 列如果没有显式定义为 not null，则支持null属性
# 设置 timestamp 的列值为 null，不会被设置为 current timestamp
explicit_defaults_for_timestamp=true### 优化配置
# 优化中文全文模糊索引
ft_min_word_len = 1# 默认库名表名保存为小写，不区分大小写
lower_case_table_names = 1# 单条记录写入最大的大小限制
# 过小可能会导致写入(导入)数据失败
max_allowed_packet = 256M
# 半同步复制开启
#rpl_semi_sync_master_enabled = 1#rpl_semi_sync_slave_enabled = 1# 半同步复制超时时间设置
#rpl_semi_sync_master_timeout = 1000# 复制模式(保持系统默认)
#rpl_semi_sync_master_wait_point = AFTER_SYNC
# 后端只要有一台收到日志并写入 relaylog 就算成功
#rpl_semi_sync_master_wait_slave_count = 1# 多线程复制
# 基于组提交的并行复制方式
slave_parallel_type = logical_clock
#并行的SQL线程数量，此参数只有设置   1<N的情况下才会才起N个线程进行SQL重做。
#经过测试对比发现， 如果主库的连接线程为M， 只有M < N的情况下， 备库的延迟才可以完全避免。
slave_parallel_workers = 4### 连接数限制
max_connections = 1500# 验证密码超过20次拒绝连接
max_connect_errors = 200# back_log值指出在mysql暂时停止回答新请求之前的短时间内多少个请求可以被存在堆栈中
# 也就是说，如果MySql的连接数达到max_connections时，新来的请求将会被存在堆栈中
# 以等待某一连接释放资源，该堆栈的数量即back_log，如果等待连接的数量超过back_log
# 将不被授予连接资源
back_log = 500open_files_limit = 65535# 服务器关闭交互式连接前等待活动的秒数
interactive_timeout = 3600# 服务器关闭非交互连接之前等待活动的秒数
wait_timeout = 3600### 内存分配
# 指定表高速缓存的大小。每当MySQL访问一个表时，如果在表缓冲区中还有空间
# 该表就被打开并放入其中，这样可以更快地访问表内容
table_open_cache = 1024# 为每个session 分配的内存，在事务过程中用来存储二进制日志的缓存
binlog_cache_size = 4M
# 在内存的临时表最大大小
tmp_table_size = 128M
# 创建内存表的最大大小(保持系统默认，不允许创建过大的内存表)
# 如果有需求当做缓存来用，可以适当调大此值
max_heap_table_size = 16M
# 顺序读，读入缓冲区大小设置
# 全表扫描次数多的话，可以调大此值
read_buffer_size = 1M
# 随机读，读入缓冲区大小设置
read_rnd_buffer_size = 8M
# 高并发的情况下，需要减小此值到64K-128K
sort_buffer_size = 1M
# 每个查询最大的缓存大小是1M，最大缓存64M 数据
query_cache_size = 64M
query_cache_limit = 1M
# 提到 join 的效率
join_buffer_size = 16M
# 线程连接重复利用
thread_cache_size = 64### InnoDB 优化
## 内存利用方面的设置
# 数据缓冲区
innodb_buffer_pool_size=2G
## 日志方面设置
# 事务日志大小
innodb_log_file_size = 256M
# 日志缓冲区大小
innodb_log_buffer_size = 4M
# 事务在内存中的缓冲
innodb_log_buffer_size = 3M
# 主库保持系统默认，事务立即写入磁盘，不会丢失任何一个事务
innodb_flush_log_at_trx_commit = 1# mysql 的数据文件设置，初始100，以10M 自动扩展
#innodb_data_file_path = ibdata1:100M:autoextend
# 为提高性能，MySQL可以以循环方式将日志文件写到多个文件
innodb_log_files_in_group = 3##其它设置
# 如果库里的表特别多的情况，请增加此值
#innodb_open_files = 800# 为每个 InnoDB 表分配单独的表空间
innodb_file_per_table = 1# InnoDB 使用后台线程处理数据页上写 I/O（输入）请求的数量
innodb_write_io_threads = 8# InnoDB 使用后台线程处理数据页上读 I/O（输出）请求的数量
innodb_read_io_threads = 8# 启用单独的线程来回收无用的数据
innodb_purge_threads = 1# 脏数据刷入磁盘(先保持系统默认，swap 过多使用时，调小此值，调小后，与磁盘交互增多，性能降低)
 innodb_max_dirty_pages_pct = 90# 事务等待获取资源等待的最长时间
innodb_lock_wait_timeout = 120# 开启 InnoDB 严格检查模式, 不警告, 直接报错 1开启 0关闭
innodb_strict_mode=1# 允许列索引最大达到3072
 innodb_large_prefix = on

[mysqldump]
# 开启快速导出
quick
default-character-set = utf8mb4
max_allowed_packet = 256M
```

查询配置编码：`show variables like 'character%';`

查询数据库编码（通过创建语句查看）：`show create database <数据库名>;`

查询表编码（通过创建语句查看）：`show create table <表名>;`



### `MySQL`分层

- 连接层：

  ​	提供与客户端连接的服务

- 服务层：

  ​	1、提供各种用户使用的接口（`select`, `insert`, `update`, `delete`...）

  ​	2、提供`SQL`优化器（`MySQL Query Optimizer`）

- 引擎层：

  ​	提供了各种存储数据的方式（`InnoDB`, `MyISAM`...）

  ​	`InnoDB`：事务优先（适合高并发操作；行锁）

  ​	`MyISAM`：性能优先（表锁）

- 存储层：

  ​	存储数据

查询支持的数据库引擎：`show engines;`

查询当前的数据库引擎：`show variables like '%storage_engine%';`

指定数据库对象的引擎：

```
create table test(
	id bigint auto_increment primary key
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
```



### `SQL`优化

原因：性能低、执行时间太长、等待时间太长、`SQL`语句欠佳（连接查询）、索引失效、服务器参数设置不合理（缓存区，线程数...）。

1. `SQL`

   编写过程：

   ```
   select (distinct) ..from ..join..on.. where.. group by.. having.. order by.. limit..
   ```

   解析过程：

   ```
   from.. on.. join.. where.. groub by.. having.. select (distinct).. order by.. limit..
   ```

2. `SQL`优化

   主要就是优化索引

   索引：相当于书的目录，是帮助`MySQL`高效获取数据的数据结构。

   ​			索引是数据结构，B+树（树：B树、Hash树...）。

   弊端：索引本身很大，可以存放在内存/硬盘（通常为硬盘）

   ​			索引不是所有情况均适用：a. 少量数据，b. 频繁更新的字段，c. 很少使用的字段

   ​			索引增加查询效率，但是会降低增删改的效率

   优势：提高查询效率（降低`IO`使用率）

   ​			降低`CPU`使用率（`...order by .. desc`，因为B树索引本身就是一个排好序的结构，因此在排序时可以直接使用）



### 索引

分类：

​	主键索引：不能重复，不能为空

​	单值索引：单列，一个表可以有多个单值索引

​	唯一索引：不能重复

​	复合索引：多个列构成的索引（相当于多级目录）

创建：

```mysql
# 方式一
create <索引类型> <索引名> on <表名(字段名)>
# 单值
create index name_index on test(name);
# 唯一
create unique index name_index on test(name);
# 复合
create index name_age_index on test(name, age);
```

```mysql
# 方式二
alter table <表名> add <索引类型> <索引名(字段名)>
# 单值
alter table test add index name_index(name);
# 唯一
alter table test add unique index name_index(name);
# 复合
alter table test add index name_age_index(name, age);
```

​	注意：如果一个字段是primary key，则该字段默认就是主键索引。

删除：

```mysql
drop index <索引名> on <表名>
# 例：
drop index name_age_index on test;
```

查询：

```mysql
show index from <表名>;
# 例：
show index from test;
```



### 约束

- 非空约束(not null)
- 唯一性约束(unique)
- 主键约束(primary key) PK
- 外键约束(foreign key) FK

**`NOT NULL`非空约束：**

1. 建表时直接添加

   ```mysql
   CREATE TABLE t_user(user_id INT(10) NOT NULL);
   ```

2. 通过ALTER 语句

   ```mysql
   ALTER TABLE t_user MODIFY user_id INT(10) NOT NULL;
   ALTER TABLE t_user CHANGE user_id user_id INT(10) NOT NULL;
   ```

3. 删除

   ```mysql
   ALTER TABLE t_user MODIFY user_id INT(10);
   ALTER TABLE t_user CHANGE user_id user_id INT(10);
   ```

   

**`UNIQUE`唯一约束：**

1. 建表语句直接添加

   ```mysql
   CREATE TABLE t_user(user_id INT(10) UNIQUE);
   
   CREATE TABLE t_user(
       user_id INT(10),
       user_name VARCHAR(30),
       CONSTRAINT UN_PHONE_EMAIL UNIQUE(user_id,user_name)#复合约束
   );
   
   CREATE TABLE t_user(
       user_id INT(10),
       UNIQUE KEY(user_id)
   );
   ```

2. 通过`alter`语句

   ```mysql
   ALTER TABLE t_user MODIFY user_id INT(10) UNIQUE;
   ALTER TABLE t_user CHANGE user_id user_id INT(10) UNIQUE;
   ALTER TABLE t_user ADD UNIQUE(user_id);
   ALTER TABLE t_user ADD UNIQUE KEY(user_id);
   ALTER TABLE t_user ADD CONSTRAINT UN_ID UNIQUE(user_id);
   ALTER TABLE t_user ADD CONSTRAINT UN_ID UNIQUE KEY(user_id);
   ```

3. 删除

   ```mysql
   # 删除唯一性约束
   ALTER TABLE t_user DROP INDEX user_id;
   # 注：唯一但是可以为空(空和空不相等)
   ```

**`PRIMARY KEY`主键约束：**

1. 建表时直接添加

   ```mysql
   CREATE TABLE t_user(user_id INT(10) PRIMARY KEY);
   
   CREATE TABLE t_user(
       user_id INT(10),
       user_name VARCHAR(30),
       CONSTRAINT PK_ID_NAME PRIMARY KEY(user_id,user_name)#复合约束
   );
   
   CREATE TABLE t_user(
       user_id INT(10),
       PRIMARY KEY(user_id)
   );
   ```

2. 通过ALTER语句

   ```mysql
   ALTER TABLE t_user MODIFY user_id INT(10) PRIMARY KEY;
   ALTER TABLE t_user CHANGE user_id user_id INT(10) PRIMARY KEY;
   ALTER TABLE t_user ADD PRIMARY KEY(user_id);
   ALTER TABLE t_user ADD CONSTRAINT PK_ID PRIMARY KEY(user_id);
   ```

3. 删除

   ```mysql
   ALTER TABLE t_user DROP PRIMARY KEY;
   /* 
   注：主键约束相当于(唯一约束+非空约束)
   一张表中最多有一个主键约束,如果设置多个主键,就会出现如下提示：
   Multiple primary key defined!!!
   删除主键约束前，如果有自增长需要先删除自增长,如果不删除自增长就无法删除主键约束
   */
   ```

**`FOREIGN KEY`外键约束：**

对应的字段只能是主键或唯一约束修饰的字段

1. 建表添加

   ```mysql
   # 主表
   CREATE TABLE class(
       cla_id INT(6) AUTO_INCREMENT PRIMARY KEY,
       cla_name VARCHAR(30) NOT NULL UNIQUE
   );
   # 从表
   CREATE TABLE students(
       stu_id INT(10) AUTO_INCREMENT PRIMARY KEY,
       stu_name VARCHAR(30) NOT NULL,
       stu_score FLOAT(5,2) DEFAULT 0.0,
       cla_id INT(10),
       CONSTRAINT FK_CLA_ID FOREIGN KEY(cla_id) REFERENCES class(cla_id)#添加外键约束
   );
   ```

2. 通过ALTER语句

   ```mysql
   ALTER TABLE students ADD CONSTRAINT FK_CLA_ID FROEIGN KEY(cla_id) REFERENCES class(cla_id);
   ```

3. 删除

   ```mysql
   ALTER TABLE students DROP FOREIGN KEY FK_CLA_ID;
   #外键中的级联关系有以下几种情况：
   #ON DELETE CASCADE 删除主表中的数据时，从表中的数据随之删除
   #ON UPDATE CASCADE 更新主表中的数据时，从表中的数据随之更新
   #ON DELETE SET NULL 删除主表中的数据时，从表中的数据置为空
   #默认 删除主表中的数据前需先删除从表中的数据，否则主表数据不会被删除
   
   CREATE TABLE students(
       stu_id INT(10) AUTO_INCREMENT PRIMARY KEY,
       stu_name VARCHAR(30) NOT NULL,
       stu_score FLOAT(5,2) DEFAULT 0.0,
       cla_id INT(10),
       CONSTRAINT FK_CLA_ID FOREIGN KEY(cla_id) REFERENCES class(cla_id) ON DELETE CASCADE
   );
   CREATE TABLE students(
       stu_id INT(10) AUTO_INCREMENT PRIMARY KEY,
       stu_name VARCHAR(30) NOT NULL,
       stu_score FLOAT(5,2) DEFAULT 0.0,
       cla_id INT(10),
       CONSTRAINT FK_CLA_ID FOREIGN KEY(cla_id) REFERENCES class(cla_id) ON UPDATE CASCADE
   );
   CREATE TABLE students(
       stu_id INT(10) AUTO_INCREMENT PRIMARY KEY,
       stu_name VARCHAR(30) NOT NULL,
       stu_score FLOAT(5,2) DEFAULT 0.0,
       cla_id INT(10),
       CONSTRAINT FK_CLA_ID FOREIGN KEY(cla_id) REFERENCES class(cla_id) ON DELETE SET NULL
   );
   /* 注：插入数据时，先插入主表中的数据，再插入从表中的数据。
      删除数据时，先删除从表中的数据，再删除主表中的数据。*/
   ```

**`AUTO_INCREMENT`自增长：**

1. 在创建表的时候添加

   ```mysql
   CREATE TABLE t_user(user_id INT(10) AUTO_INCREMENT PRIMARY KEY);
   ```

2. 通过ALTER语句

   ```mysql
   ALTER TABLE t_user MODIFY user_id INT(10) AUTO_INCREMENT;
   ALTER TABLE t_user CHANGE user_id user_id INT(10) AUTO_INCREMENT;
   ```

3. 删除自增长

   ```mysql
   ALTER TABLE t_user MODIFY user_id INT(10);
   ALTER TABLE t_user CHANGE user_id user_id INT(10);
   /*
   注：There can be only one auto column and it must be defined as a key.
   一张表只能有一个自增长列，并且该列必须定义了约束(可以是主键约束,也可以是唯一约束,也可以是外键约束,但是不可以是非空和检查约束)
   不过自增长一般配合主键使用，并且只能在数字类型中使用
   */
   ```

**`ZEROFILL`零填充：**

1. 在创建表的时候添加

   ```mysql
   CREATE TABLE t_user(user_id INT(10) ZEROFILL);
   ```

2. 通过ALTER语句

   ```mysql
   ALTER TABLE t_user MODIFY user_id INT(10) ZEROFILL;
   ALTER TABLE t_user CHANGE user_id user_id INT(10) ZEROFILL;
   ```

3. 删除零填充

   ```mysql
   ALTER TABLE t_user MODIFY user_id INT(10);
   ALTER TABLE t_user CHANGE user_id user_id INT(10);
   /*
   注：零填充会将未将有效位以外的位用零来显示,比如某字段数据类型为INT(5),而插入的值为2,那么零填充会显示00002
   但是，这个效果在Navicat for MySQL中显示不出来,只有在DOS窗口下才能显示
   */
   ```

**`DEFAULT`默认：**

1. 在创建表的时候添加

   ```mysql
   CREATE TABLE t_user(user_id INT(10) DEFAULT  3);
   ```

2. 通过ALTER语句

   ```mysql
   ALTER TABLE t_user MODIFY user_id INT(10) DEFAULT  2;
   ALTER TABLE t_user CHANGE user_id user_id INT(10) DEFAULT  2;
   ```

3. 删除默认约束

   ```mysql
   ALTER TABLE t_user MODIFY user_id INT(10);
   ALTER TABLE t_user CHANGE user_id user_id INT(10);
   ```

**`UNSIGNED`无符号位：**

1. 在创建表的时候添加

   ```mysql
   CREATE TABLE t_user(user_id INT(10) UNSIGNED);
   ```

2. 通过ALTER语句

   ```mysql
   ALTER TABLE t_user MODIFY user_id INT(10) UNSIGNED;
   ALTER TABLE t_user CHANGE user_id user_id INT(10) UNSIGNED;
   ```

3. 删除无符号

   ```mysql
   ALTER TABLE t_user MODIFY user_id INT(10);
   ALTER TABLE t_user CHANGE user_id user_id INT(10);
   # 注：无符号作用于数值类型
   ```

**从查询information_schema中查询指定表中的约束：**

```mysql
USE INFORMATION_SCHEMA;
SELECT CONSTRAINT_NAME FROM TABLE_CONSTRAINTS WHERE TABLE_NAME='student';
```



### `SQL`性能问题

分析`SQL`的执行计划

```mysql
# 可以模拟SQL优化器执行SQL语句，从而让开发人员知道自己编写的SQL状况
explain <SQL语句>
```

`MySQL`查询优化器会干扰优化

查询执行计划：`explain + SQL语句`

| 字段          | 解释                                                         |
| ------------- | ------------------------------------------------------------ |
| id            | 编号，越大优先级越高                                         |
| select_type   | 查询类型                                                     |
| table         | 表，会根据表数据量优化查询顺序（一般为数据量小的优先级高）   |
| partitions    | 匹配的分区                                                   |
| type          | 在表中找到所需行的方式，又称“访问类型”                       |
| possible_keys | 预测可能会用到的索引                                         |
| key           | 实际使用的索引                                               |
| key_len       | 索引中使用的字节数，可通过该列计算查询中使用的索引的长度     |
| ref           | 表之间的引用关系，哪个字段或常数与 key 一起被使用            |
| rows          | 根据表统计信息及索引选用情况，估算的找到所需的记录所需要读取的行数（越少越好） |
| filtered      | 表示此查询条件所过滤的数据的百分比                           |
| Extra         | 包含解决查询的详细信息                                       |

**id:**

- id相同时，执行顺序由上至下
- 如果是子查询，id的序号会递增，id值越大优先级越高，越先被执行
- id如果相同，可以认为是一组，从上往下顺序执行；在所有组中，id值越大，优先级越高，越先执行

**select_type：**

- SIMPLE(简单SELECT,不使用UNION或子查询等)
- PRIMARY(查询中若包含任何复杂的子部分,最外层的select被标记为PRIMARY)
- UNION(UNION中的第二个或后面的SELECT语句)
- DEPENDENT UNION(UNION中的第二个或后面的SELECT语句，取决于外面的查询)
- UNION RESULT(UNION的结果)
- SUBQUERY(子查询中的第一个SELECT)
- DEPENDENT SUBQUERY(子查询中的第一个SELECT，取决于外面的查询)
- DERIVED(派生表的SELECT, FROM子句的子查询，衍生查询：用到了临时表)
- UNCACHEABLE SUBQUERY(一个子查询的结果不能被缓存，必须重新评估外链接的第一行)

**type:**

```mysql
system > count > eq_ref > ref > fulltext > ref_or_null > index_merge > unique_subquery > index_subquery > range > index > ALL
# 常用类型（system, const 是理想情况；实际能达到ref, range就满足需求）：
system > const > eq_ref > ref > range > index >all
```

- `system`：表中只有一条数据或衍生表（临时表）只有一条数据的主查询。这个类型是特殊的 `const` 类型。
- `const`：针对主键Primary Key或唯一索引unique index的等值查询扫描，最多只返回一行数据。`const` 查询速度非常快，因为它仅仅读取一次即可。
- `eq_ref`：此类型通常出现在多表的 join 查询，表示对于前表的每一个结果，都只能匹配到后表的一行结果。并且查询的比较操作通常是 `=`，查询效率较高。
- `ref`：此类型通常出现在多表的 join 查询，针对于非唯一或非主键索引，或者是使用了 `最左前缀` 规则索引的查询。
- `range`：表示使用索引范围查询，通过索引字段范围获取表中部分数据记录。这个类型通常出现在 =，<>，>，>=，<，<=，IS NULL，<=>，BETWEEN，IN（部分操作中IN会走`ALL`查询） 操作中。
  当 `type` 是 `range` 时，那么 EXPLAIN 输出的 `ref` 字段为 NULL，并且 `key_len` 字段是此次查询中使用到的索引的最长的那个。
- `index`：表示全索引扫描(full index scan)，和 ALL 类型类似，只不过 ALL 类型是全表扫描，而 index 类型则仅仅扫描所有的索引，而不扫描数据。
  `index` 类型通常出现在: 所要查询的数据直接在索引树中就可以获取到，而不需要扫描数据。当是这种情况时，Extra 字段 会显示 `Using index`。
- `ALL`：表示全表扫描，这个类型的查询是性能最差的查询之一。通常来说，我们的查询不应该出现 ALL 类型的查询，因为这样的查询在数据量大的情况下，对数据库的性能是巨大的灾难。如一个查询是 ALL 类型查询，那么一般来说可以对相应的字段添加索引来避免。
  下面是一个全表扫描的例子，可以看到，在全表扫描时，possible_keys 和 key 字段都是 NULL，表示没有使用到索引，并且 rows 十分巨大，因此整个查询效率是十分低下的。
- `NULL`：MySQL在优化过程中分解语句，执行时甚至不用访问表或索引，例如从一个索引列里选取最小值可以通过单独索引查找完成。

**key_len:**

表示查询优化器使用了索引的字节数。这个字段可以评估组合索引是否完全被使用，或只有最左部分字段被使用到。
key_len 的计算规则如下:

- 字符串
  
  utf8：1个字符3个字节；utf8mb4：1个字符4个字节；gbk：1个字符2个字节；latin：1个字符1个字节。
  
  - char(n)：如果是 utf8 编码, 则是 3 \* n字节；如果是 utf8mb4 编码, 则是 4 * n字节。
  - varchar(n)：如果是 utf8 编码, 则是 3 \* n + 2字节；如果是 utf8mb4 编码, 则是 4 * n + 2 字节。
  
- 数值类型:
  - TINYINT: 1字节
  - SMALLINT: 2字节
  - MEDIUMINT: 3字节
  - INT: 4字节
  - BIGINT: 8字节
  
- 时间类型
  - DATE: 3字节
  - TIMESTAMP: 4字节
  - DATETIME: 8字节
  
- 字段属性: NULL 属性占用一个字节（即字段可以为NULL时，长度为类型长度+1）。如果一个字段是 NOT NULL 的，则没有此属性。

**ref：**

指明当前表所参照的字段（索引字段）或常量。

```mysql
select ... from a.c = b.x and a.y = 1
# a.c引用了b.x(需要都为索引字段)
# a.y引用了常量
```

**Extra:**

- `Using index`："覆盖索引扫描"，表示查询在索引树中就可查找所需数据，不用扫描表数据文件，往往说明性能不错。

  性能提升，索引覆盖。不需要检索原文件，只从索引文件获取数据（不需要回表查询）。

  如果用到了索引覆盖（`using index`）时，会对`possible_keys`和`key`造成影响：

  ​	a. 如果没有`where`，则索引只出现在`key`中

  ​	b. 如果有`where`，则索引出现在`key`和`possible_keys`中

- `Using where`：列数据是从仅仅使用了索引中的信息而没有读取实际的行动的表返回的，这发生在对表的全部的请求列都是同一个索引的部分的时候，表示mysql服务器将在存储引擎检索行后再进行过滤。

  需要回表查询（从原文件中检索数据）

  例：`select name，age from user where age = 33`，假设`age`是索引字段，`age`字段使用索引，但`name`字段需要回原文件查询数据。

- `Using temporary`：表示`MySQL`需要使用临时表来存储结果集，常见于排序、分组和多表`join`查询，查询效率不高，建议优化。

  性能损耗大，用到了临时表（已经有表但不适用，必须再来一张表），一般常见于`group by`。

  例：`select a1 ... where a1 ... group by a2`

  建议：查询哪些列，就是用哪些列`group by`。

- `Using filesort`：`MySQL`中无法利用索引完成的排序操作称为“文件排序”，当` Extra `中有 `Using filesort` 时，表示 `MySQL` 需额外的排序操作，不能通过索引顺序达到排序效果（需要一次“额外”的排序/查询）。 一般有 `Using filesort`，都建议优化去掉，因为这样的查询 `CPU `资源消耗大。

  一般常见于`order by`。对于单索引，如果排序和查找是同一个字段，则不会出现`using filesort`；如果排序和查询不是同一个字段则会出现`using filesort`。

  对于复合索引，不能跨列或无序使用（最佳左前缀，`where ... order by ...`需要从左往右使用索引字段，不能跨过左边的字段）。例：有复合索引`(a1, a2, a3)`，查询时`where a1 ... order by a3`时会出现`using filesort`;查询时`where a1 .. a2 .. order by a3`不会出现。

- `Using join buffer`：改值强调了在获取连接条件时没有使用索引，并且需要连接缓冲区来存储中间结果。如果出现了这个值，那应该注意，根据查询的具体情况可能需要添加索引来改进能。

- `Impossible where`：这个值强调了`where`语句会导致没有符合条件的行（`where`语句永远为`false`）。

- `Select tables optimized away`：这个值意味着仅通过使用索引，优化器可能仅从聚合函数结果中返回一行。

#### 强制索引

```mysql
select * from table force index(PRI) limit 2;(强制使用主键)

select * from table force index(ziduan1_index) limit 2;(强制使用索引"ziduan1_index")

select * from table force index(PRI,ziduan1_index) limit 2;(强制使用索引"PRI和ziduan1_index")
```

#### 禁止索引

```mysql
select * from table ignore index(PRI) limit 2;(禁止使用主键)

select * from table ignore index(ziduan1_index) limit 2;(禁止使用索引"ziduan1_index")

select * from table ignore index(PRI,ziduan1_index) limit 2;(禁止使用索引"PRI,ziduan1_index")
```

### 索引失效情况

- like是以%开头的查询语句
- 使用多列索引没有按最佳左前缀原则的查询语句
- 使用OR关键字查询语句
- 如果列类型是字符串，但是查询时没有使用引号括起来的查询条件，如：字符型字段为数字时在where条件里不添加引号
- 没有查询条件，或者查询条件没有建立索引
- 在查询条件上没有使用引导列
- 查询的数量是大表的大部分，应该是30％以上
- 索引本身失效
- 查询条件使用函数在索引列上，或者对索引列进行运算，运算包括(+，-，*，/，! 等) 错误的例子：select* from test where id-1=9; 正确的例子：select * from test where id=10
- 对小表查询
- 提示不使用索引
- 统计数据不真实
- CBO计算走索引花费过大的情况。其实也包含了上面的情况，这里指的是表占有的block要比索引小。
- 1,<> 2,单独的>,<,(有时会用到，有时不会)
- 表没分析
- 单独引用复合索引里非第一位置的索引列
- not in ,not exist
- 当变量采用的是times变量，而表的字段采用的是date变量时，或相反情况
- B-tree索引 is null不会走,is not null会走,位图索引 is null,is not null 都会走
- 联合索引 is not null 只要在建立的索引列（不分先后）都会走, in null时 必须要和建立索引第一列一起使用,当建立索引第一位置条件是is null 时,其它建立索引的列可以是is null（但必须在所有列 都满足is null的时候）,或者=一个值； 当建立索引的第一位置是=一个值时,其它索引列可以是任何情况（包括is null =一个值）,以上两种情况索引都会走。其它情况不会走。
- 部分查询优化器优化后不走

#### 使用索引覆盖

```mysql
# 如果必须使用like '%x%'查询，可以使用查询字段为索引字段覆盖，避免全表，扫描挽救一部分性能
select tname from test where tname like '%xx%';
```

#### 其它方法

- 如果主查询的数据集大，则使用`in`。
- 如果子查询的数据集大，则使用`exists`。
- `using filesort`两种算法：双路排序（`MySQL4.1`前默认）、单路排序（`MySQL4.1`后默认，根据IO的次数）
  - 双路排序：扫描2次磁盘，第一次从磁盘读取排序字段，在`Buffer`缓冲区中对字段进行排序；第二次扫描其它字段。2次IO比较消耗性能。
  - 单路排序：只读取一次（全部字段），在`Buffer`中进行排序。但此种单路排序会有一定的隐患，不一定真的是单次IO，有可能出现多次IO（如果数据量特别大，则无法将所有字段的数据一次性读完，因此会进行分片读取，多次读取数据进行单次排序）。
  - 单路排序比双路排序占用理多的`buffer`，单路排序在使用时，如果数据量大，可以考虑调大`buffer`的容量大小（`set max_length_for_sort_data = 1024`，单位byte） 。
  - 如果`max_length_for_sort_data`值太低，则`MySQL`会自动从单路切换至双路（需要排序的列的总大小超过了`max_length_for_sort_data`定义的字节数）。
- 提高order by查询的策略：
  - 选择使用单路、双路排序
  - 调整`buffer`的容量大小
  - 避免`select * from ...`（使用`*`需要计算字段，影响索引使用），使用`select a, b, c from ...`
  - 复合索引不要跨列使用，避免`using filesort`
  - 保证全部排序字段的排序一致性（都是升序或降序）

#### 慢查询日志

`MySQL`提供的一种日志记录，用于记录`MySQL`中响应时间超过阈值的`SQL`语句。

- 建议：开发调优时打，最终部署时关闭。

- 时间阈值配置项为`long_query_time`，默认10秒，默认关闭。
  
  - 查询配置项
    
    ```mysql
    show variables like 'long_query_time';
    ```
    
  - 临时设置：
  
    ```mysql
    # 修改完成后，需要重新登录mysql client
    set global long_query_time = 5;
    ```
  
  - 永久设置
  
    ```mysql
    # 修改my.cnf文件
    [mysqld]
    long_query_time=3
    ```
  
- 慢查询日志开关

  - 检查是否开启：

    ```mysql
    show variables like 'slow_query_log%';
    ```

  - 临时开启：

    ```mysql
    # 在内存中开启，mysql server关闭后关闭
    set global slow_query_log = 1;
    ```
    
  - 永久开启

    ```mysql
    # 修改my.cnf文件
    [mysqld]
    slow_query_log=1
    slow_query_log_file=/var/lib/mysql/localhost-slow.log
    ```

- 查询超过阈值的`SQL`

  ```mysql
  # 显示慢查询SQL语句的数量
  show global status like '%slow_queries%';
  ```

  通过慢查询日志文件查看慢查询`SQL`语句（可以通过工具`mysqldumpslow`查找快速定位）。

  - `mysqldumpslow --help`

    - s：排序方式
    - r：逆序
    - l：锁定时间
    - g：正则匹配模式

  - 获取返回记录最多的3个`SQL`

    ```shell
    mysqldumpslow -s r -t 3 <慢SQL日志文件>
    ```

  - 获取访问次数最多的3个`SQL`

    ```mysql
    mysqldumpslow -s c -t 3 <慢SQL日志文件>
    ```

  - 按照时间排序，前10条包含`left join`查询语句的`SQL`

    ```mysql
    mysqldumpslow -s t -t 10 -g "left join" <慢SQL日志文件>
    ```

#### 分析海量数据

- 查询是否开启：

  ```mysql
  show variables like '%profiling%';
  ```

- 打开分析器：

  ```mysql
  set profiling = on;
  ```

- 查看分析器：

  ```mysql
  show profiles; -- 默认关闭
  ```

  会记录所有`profilling`打开之后的`SQL`查询语句。

  - `Query_ID`：查询ID。

  - `Duration`：消耗时间。只能看到总的消耗时间，不能看到各个硬件消耗的时间。
  - `Query`：查询语句。

- 精确分析（`SQL`诊断）

  ```mysql
  # 查询所有项
  show profile all for query <对应的查询ID（Query_ID）>
  # 查询cpu, io消耗时间
  show profile cup, block io for query <Query_ID>
  ```

- 全局查询日志：开启后会记录所有`SQL`语句，一般在开发或调优时才选择开启，最终实施时选择关闭。

  ```mysql
  # 查看配置项
  show variables like '%general_log%';
  # 开启
  set global general_log = 1;
  # 开启该项后会被记录至mysql.general_log表中，默认记录到配置项general_log_file文件中（table|file）
  set global log_output = 'table';
  ```



### 锁机制

解决因资源共享而造成的并发问题。

**根据操作类型分类**

- 读锁（共享锁）：对同一个数据，多个读操作可以同时进行，互不干扰。
- 写锁（互斥锁）：如果当前写操作没有完毕，则无法进行其它的读、写操作。

**根据操作范围分类**

- 表锁：一次性对一张表加锁。如`MyISAM`存储引擎使用表锁，开销小、加锁快、无死锁；但锁的范围大，容易发生锁冲突，并发度低。
  - 如果某一个会话`session`对A表加了读锁，则该会话可以对A表执行读操作、不能执行写操作；且该会不能对其它表执行读、写操作。
  - 会话1给A表加了读锁，其它会话可以对其它表（A表以外的表）进行读、写操作；对A表可以进行读操作，写操作需要等待A表读锁释放。
  - *当前会话*可以对加了写锁的表进行任何操作（增删改查）；但是不能操作其它表。
  - *其它会话*对*当前会话*中加写锁的表可以进行增删改查的前提是：等待*当前会话*释放写锁。
- 行锁：对一行数据加锁。如`InnoDB`存储引擎使用行锁，开销大、加锁慢、容易出现死锁；锁的范围小，不易发生锁冲突，并发度高。
  - 如果*当前会话*对某条数据进行`DML`操作（`set autocommit = 0`关闭了自动提交情况下），则*其它会话*必须等待*当前会话*结束事务（`commit/rollback`）后，才能对数据进行操作。
  - 表锁是通过`unlock tables`解锁，也可以通过事务解锁（行锁转为表锁的情况下）；行锁通过事务解锁（`commit/rollback`）。
  - 行锁操作不同的行数据，互不干扰。
  - 如果没有索引或索引失效，则行锁会转为表锁。
  - 行锁的一种特殊情况，间隙锁：值在范围内，但却不存在的数据。例：当前会话操作1 < id < 9的数据，但是数据中没有id = 7的数据，也会为id = 7的数据增加一个间隙锁；其它会话若要操作id = 7的数据，也要等当前会话事务结束。
- 页锁：开销和加锁速度介于表锁和行锁之间；会出现死锁；锁定粒度介于表锁和行锁之间，并发度一般。

增加锁：

```mysql
lock table <表1> read/write, <表2> read/write, ...
```

释放锁：

```mysql
unlock tables;
```

**分析表锁定**

查看加锁的表：

```mysql
# 1代表被加了锁
show open tables;
```

表锁分析：

```mysql
# Table_locks_immediate：要加锁时，立刻可以加上表锁的数量
# Table_locks_waited：需要等待释放的表锁数量
show status like 'table%';
```

​	如果`Table_locks_immediate / Table_locks_waited > 5000 `，一般建议采用`InnoDB`引擎，否则使用`MyISAM`引擎。

行锁分析：

```mysql
/*
  Innodb_row_lock_current_waits: 当前正在等待的锁数量
  Innodb_row_lock_time：等待锁的总时长
  Innodb_row_lock_time_avg：等待锁的平均时长
  Innodb_row_lock_time_max：等待锁的最大时长
  Innodb_row_lock_waits：等待锁的总次数
*/
show status like '%innodb_row_lock%';
```

查询语句加锁：

```mysql
set autocommit = 0;
# 该行数据会增加写锁，其它操作会等待该事务
select * from test where id = 1 for update;
commit;
```



### 主从复制 

1. `master`将改变的数据记录在本地的二进制日志中（`binary log`），该过程称为二进制日志事件。
2. `slave`将`master`的`binary log`拷贝到自己的中继日志文件中（`relay log`）。
3. 中继日志事件，将数据读取到自己的数据库中。
4. `MySQL`主从复制是异步的，串行化的，有延迟。
5. `master:slave = 1:n`，一对多的关系。

**主：**

```mysql
[mysqld]
# 设置主机ID
server-id=1
# 设置二进制文件路径或名称
log-bin=mysql-bin
# 错误记录文件
log-error=mysql-error
# 主从同步时，忽略的数据库
binlog-ignore-db=test,mysql
# 主从同步时，同步哪些数据库
binlog-do-db=userdata
```

**主机授权：**

```mysql
# 授权远程访问
grant all privileges on *.* to 'root'@'%' identified by '123456' with grant option;
flush privileges;

# 授权哪个数据库作为自己的从库
grant replication slave,reload,super on *.* to 'root'@'192.168.8.%' identified by '123456';
flush privileges;

# 查看主数据库的状态，每次在做主从同步前，需要观察主机状态的最新值
/*
  File：二进制文件名（mysql-bin.000001）
  Position: 起点位置（107）
  Binlog-Do-DB：同步的表名
  Binlog-Ignore-DB：不同步的表名
*/
show master status;
```

**从：**

```mysql
[mysqld]
# 设置从机ID（每台机的ID不能相同）
server-id=2
log-bin=mysql-bin
# 需要同步的表
replicate-do-db=userdata
```

**从机授权：**

```mysql
# 如果非第一次授权，需要先关闭先前配置
stop slave;
# 修改主机信息
change master to 
	master_host = '192.168.8.134',
	master_user = 'root',
	master_password = '123456',
	master_port = 3306,
	master_log_file = 'mysql-bin.000001',
	master_log_pos = 107;
# 开启从机
start slave;
# 查询从机状态
/*
  观察Slave_IO_Running和Slave_SQL_Running，确保二者都是yes
  如果不都是yes，看下方的Last_IO_Error内容解决相应问题
*/
show salve status;
```

**其它：**

```mysql
# 查询数据库配置的机器ID
show variables like 'server_id';
# 设置全局机器ID
set global server_id = 2;
```



### 数据库备份与还原

#### 数据备份

**使用`mysqldump`命令备份**

`mysqldump`命令可以将数据库中的数据备份成一个文本文件。表的结构和表中的数据将存储在生成的文本文件中。

```shell
# 备份一个库中多个表
# dbname参数表示数据库的名称；
# table1和table2参数表示需要备份的表的名称，为空则整个数据库备份；
# BackupName.sql参数是备份文件的名称，文件名前面可以加上一个绝对路径。通常将数据库被分成一个后缀名为sql的文件；
mysqldump -u username -p dbname table1 table2 ...-> BackupName.sql

# 备份多个库
mysqldump -u username -p --databases dbname1 dbname2 > Backup.sql

# 备份所有库
mysqldump -u username -p -all-databases > /home/BackupName.sql
```

**直接复制整个数据库目录**

MySQL有一种非常简单的备份方法，就是将MySQL中的数据库文件直接复制出来。这是最简单，速度最快的方法。

不过在此之前，要先将服务器停止，这样才可以保证在复制期间数据库的数据不会发生数据冲突。如果在复制数据库的过程中还有数据写入，就会造成数据不一致。这种情况在开发环境可以，但是在生产环境中很难允许备份服务器。

注意：此方法不适用于InnoDB存储引擎的表，而对于MyISAM存储引擎的表很方便。同时，还原时MySQL的版本最好相同。

**使用mysqlhotcopy工具快速备份**

mysqlhotcopy是一种热备份，支持不停止MySQL服务器的备份，而且比mysqldump快。mysqlhotcopy是一个perl脚本，主要在Linux系统下使用。其使用LOCK TABLES、FLUSH TABLES和cp来进行快速备份。

原理：先将需要备份的数据库加上一个读锁，然后用FLUSH TABLES将内存中的数据写回到硬盘上的数据库，最后，把需要备份的数据库文件复制到目标目录。

mysqlhotcopy并非mysql自带，需要安装Perl的数据库接口包；下载地址为:`http://dev.mysql.com/downloads/dbi.html`。目前，该工具也仅仅能够备份MyISAM类型的表。

命令格式如下：

```shell
mysqlhotcopy [option] dbname1 dbname2 backupDir/
```

- dbname：数据库名称；
- backupDir：备份到哪个文件夹下；

常用选项：

- --help：查看帮助；
- --allowold：如果备份目录下存在相同的备份文件，将旧的备份文件加上_old；
- --keepold：如果备份目录下存在相同的备份文件，不删除旧的备份文件，而是将旧的文件更名；
- --flushlog：备份之后，将对数据库的更新记录到日志中；
- --noindices：只备份数据文件，不备份索引文件；
- --user=用户名：指定用户名，可以用-u代替；
- --password=密码：指定密码，可以用-p代替。使用-p时，密码与-p之间没有空格；
- --port=端口号：指定访问端口，可以用-P代替；
- --socket=socket文件：指定socket文件，可以用-S代替；

#### 数据还原

**还原使用mysqldump命令备份的数据库**

```shell
mysql -u root -p [dbname] < backup.sql
```

**还原直接复制目录的备份**

通过这种方式还原时，必须保证两个MySQL数据库的版本号是相同的。对于MyISAM类型的表有效，对于InnoDB类型的表不可用，InnoDB表的表空间不能直接复制。