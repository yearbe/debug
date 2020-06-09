# MySQL JDBC URL参数

| **属性名**                                          | **定义**                                                     | **要求** | **默认值**                           | **版本**     |
| --------------------------------------------------- | ------------------------------------------------------------ | -------- | ------------------------------------ | :----------- |
| Connection/Authentication（连接／鉴定）             |                                                              |          |                                      |              |
| user                                                | 连接的用户                                                   | No       |                                      | 全部         |
| password                                            | 连接时使用的密码。                                           | No       |                                      | 全部         |
| socketFactory                                       | 驱动程序用于创建与服务器套接字连接的类的名称。该类必须实现了接口“com.mysql.jdbc.SocketFactory”，并有公共无参量构造函数。 | No       | com.mysql.jdbc.StandardSocketFactory | 3.0.3        |
| connectTimeout                                      | 套接字连接的超时（单位为毫秒），0表示无超时。仅对JDK-1.4或更新版本有效。默认值为“0”。 | No       | 0                                    | 3.0.1        |
| socketTimeout                                       | 网络套接字连接的超时（默认值0表示无超时）。                  | No       | 0                                    | 3.0.1        |
| useConfigs                                          | 在解析URL属性或应用用户指定的属性之前，加载由逗号“,”分隔的配置属性列表。在文档的“配置”部分中解释了这些配置。 | No       |                                      | 3.1.5        |
| interactiveClient                                   | 设置CLIENT_INTERACTIVE标志，根据INTERACTIVE_TIMEOUT而不是WAIT_TIMEOUT向MySQL通报超时连接。 | No       | false                                | 3.1.0        |
| propertiesTransform                                 | com.mysql.jdbc.ConnectionPropertiesTransform的1个实施实例，在尝试连接之前，驱动程序将使用它来更改传递给驱动的URL属性。 | No       |                                      | 3.1.4        |
| useCompression                                      | 与服务器进行通信时采用zlib压缩（真／假）？ 默认值为“假”。    | No       | false                                | 3.0.17       |
| High Availability and Clustering （高可用性和簇集） |                                                              |          |                                      |              |
| autoReconnect                                       | 驱 动程序是否应尝试再次建立失效的和／或死连接？ 如果允许，对于在失效或死连接上发出的查询（属于当前事务），驱动程序将抛出异常，但在新事务的连接上发出下一个查询时，将尝试再连接。不推荐使用该特 性，这是因为，当应用程序不能恰当处理SQLExceptions时，它会造成与会话状态和数据一致性有关的副作用，设计它的目的仅用于下述情况，即，当 你无法配置应用程序来恰当处理因死连接和／或无效连接导致的SQLExceptions时。作为可选方式，可将MySQL服务器变量 “wait_timeout”设置为较高的值，而不是默认的8小时。 | No       | false                                | 1.1          |
| autoReconnectForPools                               | 使用适合于连接池的再连接策略（默认值为“假”）。               | No       | false                                | 3.1.3        |
| failOverReadOnly                                    | 在autoReconnect模式下出现故障切换时，是否应将连接设置为“只读”？ | No       | true                                 | 3.0.12       |
| reconnectAtTxEnd                                    | 如果将autoReconnect设置为“真”，在每次事务结束后驱动程序是否应尝试再连接？ | No       | false                                | 3.0.10       |
| roundRobinLoadBalance                               | 启用了autoReconnect而且failoverReadonly为“假”时，是否应按照循环方式挑选要连接的主机？ | No       | false                                | 3.1.2        |
| queriesBeforeRetryMaster                            | 出现故障切换（使用多主机故障切换）并返回主机之前发出的查询数。无论首先满足了哪个条件，“queriesBeforeRetryMaster”或“secondsBeforeRetryMaster”，均会再次与主机进行连接。默认值为“50”。 | No       | 50                                   | 3.0.2        |
| secondsBeforeRetryMaster                            | 出现故障切换后，在尝试再次连接到主服务器之前，驱动程序应等待的时间？ 无论首先满足了哪个条件，“queriesBeforeRetryMaster”或“secondsBeforeRetryMaster”，均会再次与主 机进行连接。单位为秒，默认值为30。 | No       | 30                                   | 3.0.2        |
| enableDeprecatedAutoreconnect                       | 自3.2版开始，自动再连接功能受到冷落，在3.3版中将删除该功能。将该属性设置为“真”可禁止检查配置的特性。 | No       | false                                | 3.2.1        |
| Security （安全）                                   |                                                              |          |                                      |              |
| allowMultiQueries                                   | 在一条语句中，允许使用“;”来分隔多条查询（真／假，默认值为“假”）。 | No       | false                                | 3.1.1        |
| useSSL                                              | 与服务器进行通信时使用SSL（真／假），默认值为“假”。          | No       | false                                | 3.0.2        |
| requireSSL                                          | 要求SSL连接，useSSL=true？ 默认值为“假”。                    | No       | false                                | 3.1.0        |
| allowUrlInLocalInfile                               | 驱动程序在是“LOAD DATA LOCAL INFILE”语句中否允许URL？        | No       | false                                | 3.1.4        |
| paranoid                                            | 采取措施，防止在错误信息中泄漏敏感信息，并可可能时清除保存敏感数据的数据结构？ 默认值为“假”。 | No       | false                                | 3.0.1        |
| Performance Extensions （性能扩展）                 |                                                              |          |                                      |              |
| metadataCacheSize                                   | 如果将cacheResultSetMetaData设置为“真”，对cacheResultSetMetadata的查询次数（默认值为50）。 | No       | 50                                   | 3.1.1        |
| prepStmtCacheSize                                   | 如果允许预处理语句缓冲功能，应缓冲处理多少条预处理语句？     | No       | 25                                   | 3.0.10       |
| prepStmtCacheSqlLimit                               | 如果允许预处理语句缓冲功能，驱动程序将执行解析缓冲处理的最大SQL是什么？ | No       | 256                                  | 3.0.10       |
| maintainTimeStats                                   | 驱动程序是否应维持各种内部定时器，以允许空闲时间计算，以及与服务器的连接失败时允许提供更详细的错误消息？ 将该属性设置为“假”，对于每次查询，至少能减少两次对System.getCurrentTimeMillis()的调用。 | No       | true                                 | 3.1.9        |
| blobSendChunkSize                                   | 组块，当通过ServerPreparedStatements发送BLOB/CLOB时使用。    | No       | 1048576                              | 3.1.9        |
| cacheCallableStmts                                  | 驱动程序是否应对CallableStatements的解析过程执行缓冲处理。   | No       | false                                | 3.1.2        |
| cachePrepStmts                                      | 驱动程序是否应对客户端预处理语句的PreparedStatements的解析过程执行缓冲处理，是否应检查服务器端预处理语句的适用性以及服务器端预处理语句本身？ | No       | false                                | 3.0.10       |
| cacheResultSetMetadata                              | 驱动程序是否应对用于Statements和PreparedStatements的ResultSetMetaData执行缓冲处理？ 要求 JDK-1.4+，真／假，默认为“假”。 | No       | false                                | 3.1.1        |
| cacheServerConfiguration                            | 驱动程序是否应根据每条URL对“HOW VARIABLES”和“SHOW COLLATION”的结果执行缓冲处理？ | No       | false                                | 3.1.5        |
| dontTrackOpenResources                              | JDBC规范要求驱动程序自动跟踪和关闭资源，但是，如果你的应用程序不能明确调用作用在语句或结果集上的close()，可能会导致内存泄漏。将该属性设置为“真”，可放宽该限制，对于某些应用程序，会提供更高的内存效率。 | No       | false                                | 3.1.7        |
| dynamicCalendars                                    | 需要时，驱动程序是否应检索默认日历，或根据连接／会话对其进行缓冲处理？ | No       | false                                | 3.1.5        |
| elideSetAutoCommits                                 | 如果使用MySQL-4.1或更高版本，当服务器的状态与Connection.setAutoCommit(boolean)请求的状态不匹配时，驱动程序是否仅应发出“set autocommit=n”查询？ | No       | false                                | 3.1.3        |
| holdResultsOpenOverStatementClose                   | 驱动程序是否应按照JDBC规范的要求关闭Statement.close()上的结果集？ | No       | false                                | 3.1.7        |
| locatorFetchBufferSize                              | 如果将“emulateLocators”配置为“真”，当获取关于getBinaryInputStream的BLOB数据时，缓冲区的大小应是多少？ | No       | 1048576                              | 3.2.1        |
| useFastIntParsing                                   | 是否使用内部“String->Integer”转换子程序来避免创建过多对象？  | No       | true                                 | 3.1.4        |
| useLocalSessionState                                | 驱动程序是否应引用autocommit的内部值，以及由Connection.setAutoCommit()和Connection.setTransactionIsolation()设置的事务隔离，而不是查询数据库？ | No       | false                                | 3.1.7        |
| useNewIO                                            | 驱动程序是否应将java.nio.* interfaces用于网络通信（真／假），默认为“假”。 | No       | false                                | 3.1.0        |
| useReadAheadInput                                   | 从服务器读取数据时，是否使用较新的、优化的非成组缓冲输入流？ | No       | true                                 | 3.1.5        |
| Debuging/Profiling （调试/ 仿形）                   |                                                              |          |                                      |              |
| logger                                              | 实现了com.mysql.jdbc.log.Log的类的名称，com.mysql.jdbc.log.Log用于记录消息（默认为“com.mysql.jdbc.log.StandardLogger”，它会将日志记录到STDERR）。 | No       | com.mysql.jdbc.log.StandardLogger    | 3.1.1        |
| profileSQL                                          | 跟踪查询以及它们对已配制记录器的执行/获取次数（真／假），默认为“假”。 | No       | false                                | 3.1.0        |
| reportMetricsIntervalMillis                         | 如果允许“gatherPerfMetrics”，记录它们的频率是多少（单位毫秒）？ | No       | 30000                                | 3.1.2        |
| maxQuerySizeToLog                                   | 调试或仿形时，控制将记录的查询的最大长度／大小。             | No       | 2048                                 | 3.1.3        |
| packetDebugBufferSize                               | 当“enablePacketDebug”为“真”时，需要保留的最大信息包数目。    | No       | 20                                   | 3.1.3        |
| slowQueryThresholdMillis                            | 如果允许“logSlowQueries”，在将查询记录为“慢”之前的查询时间是多少（毫秒）？ | No       | 2000                                 | 3.1.2        |
| useUsageAdvisor                                     | 驱动程序是否应发出“使用情况”警告，就DBC和MySQL Connector/J的恰当和高效使用给出建议（真／假，默认为“假”）？ | No       | false                                | 3.1.1        |
| autoGenerateTestcaseScript                          | 驱动程序是否应将正在执行的SQL（包括服务器端预处理语句）转储到STDERR？ | No       | false                                | 3.1.9        |
| dumpQueriesOnException                              | 驱动程序是否应将发送至服务器的查询内容转储到SQLExceptions中？ | No       | false                                | 3.1.3        |
| enablePacketDebug                                   | 允许时，将保留“packetDebugBufferSize”信息包的环形缓冲区，并当在驱动程序代码的关键区域抛出异常时进行转储。 | No       | false                                | 3.1.3        |
| explainSlowQueries                                  | 如果允许了“logSlowQueries”，驱动程序是否应在服务器上自动发出“EXPLAIN”，并以WARN级别将结果发送给配置好的日志？ | No       | false                                | 3.1.2        |
| logSlowQueries                                      | 是否要记录时间长于“slowQueryThresholdMillis”的查询？         | No       | false                                | 3.1.2        |
| traceProtocol                                       | 是否应记录跟踪级网络协议？                                   | No       | false                                | 3.1.2        |
| Miscellaneous （其他）                              |                                                              |          |                                      |              |
| useUnicode                                          | 处理字符串时，驱动程序是否应使用Unicode字符编码？ 仅应在驱动程序无法确定字符集映射，或你正在强制驱动程序使用MySQL不是固有支持的字符集时（如UTF-8）才应使用。真／假，默认为“真”。 | No       | false                                | 1.1g         |
| characterEncoding                                   | 如果“useUnicode”被设置为“真”，处理字符串时，驱动程序应使用什么字符编码？ 默认为“autodetect”。 | No       |                                      | 1.1g         |
| characterSetResults                                 | 字符集，用于通知服务器以何种字符集返回结果。                 | No       |                                      | 3.0.13       |
| connectionCollation                                 | 如果设置了它，将通知服务器通过“set collation_connection”使用该校对。 | No       |                                      | 3.0.13       |
| sessionVariables                                    | 以逗号隔开的“名称／值”对列表，当驱动程序建立了连接后，以“SET SESSION ...”的方式将其发送给服务器。 | No       |                                      | 3.1.8        |
| allowNanAndInf                                      | 驱动程序是否应在PreparedStatement.setDouble()中允许NaN或+/- INF值？ | No       | false                                | 3.1.5        |
| autoDeserialize                                     | 驱动程序是否应自动检测并串并转换保存在BLOB字段中的对象？     | No       | false                                | 3.1.5        |
| capitalizeTypeNames                                 | 是否将DatabaseMetaData中的类型名转换为大写？ 通常仅在使用WebObjects时有用，真／假。默认为“假”。 | No       | false                                | 2.0.7        |
| clobberStreamingResults                             | 这会使“流式”结果集被自动关闭，如果在所有数据尚未从服务器中读取完之前，执行了另一查询，正在从服务器流出的任何未完成数据均将丢失。 | No       | false                                | 3.0.9        |
| continueBatchOnError                                | 如果一条语句失败，驱动程序是否应继续处理批命令？ JDBC规范允许任何一种方式（默认为“真”）。 | No       | true                                 | 3.0.3        |
| createDatabaseIfNotExist                            | 如果不存在，创建URL中给定的数据库。假定用户具有创建数据库的权限。 | No       | false                                | 3.1.9        |
| emptyStringsConvertToZero                           | 驱动程序是否应允许从空字符串字段到数值“0”的转换？            | No       | true                                 | 3.1.8        |
| emulateLocators                                     | N/A                                                          | No       | false                                | 3.1.0        |
| emulateUnsupportedPstmts                            | 驱动程序是否应检测不被服务器支持的预处理语句，并用客户端模拟版替换它们？ | No       | true                                 | 3.1.7        |
| ignoreNonTxTables                                   | 是否忽略关于回退的非事务表？ 默认值为“假”。                  | No       | false                                | 3.0.9        |
| jdbcCompliantTruncation                             | 连接到支持告警的服务器时（MySQL 4.1.0和更高版本），当按照JDBC的要求截短数据时，驱动程序是否应抛出java.sql.DataTruncation异常？ | No       | true                                 | 3.1.2        |
| maxRows                                             | 返回的最大行数（0，默认值表示返回所有行）。                  | No       | -1                                   | all versions |
| noDatetimeStringSync                                | 不保证ResultSet.getDatetimeType().toString().equals(ResultSet.getString()。 | No       | false                                | 3.1.7        |
| nullCatalogMeansCurrent                             | 当DatabaseMetadataMethods请求“目录”参数时，值“Null”是否意味着使用当前目录？ 它不兼容JDBC，但符合驱动程序早期版本的传统行为。 | No       | true                                 | 3.1.8        |
| nullNamePatternMatchesAll                           | 接受*pattern参数的DatabaseMetaData方法是否应将null按对待“％”的相同方式处理（不兼容JDBC，但驱动程序的早期版本能接受与规范的这类偏离）。 | No       | true                                 | 3.1.8        |
| pedantic                                            | 严格遵守JDBC规范。                                           | No       | false                                | 3.0.0        |
| relaxAutoCommit                                     | 如果驱动程序所连接的MySQL服务器的版本不支持事务，仍允许调用commit()、rollback()和setAutoCommit()？真／假，默认为“假”。 | No       | false                                | 2.0.13       |
| retainStatementAfterResultSetClose                  | 调用ResultSet.close()后，驱动程序是否应将语句引用保存在结果集中？ 在JDBC-4.0后，与JDBC不兼容。 | No       | false                                | 3.1.11       |
| rollbackOnPooledClose                               | 当连接池中的逻辑连接关闭时，驱动程序是否应发出rollback()？   | No       | true                                 | 3.0.15       |
| runningCTS13                                        | 允许在Sun与JDBC兼容的testsuite 1.3版中处理缺陷。             | No       | false                                | 3.1.7        |
| serverTimezone                                      | 覆盖时区的检测/映射。当服务器的时区为映射到Java时区时使用。  | No       |                                      | 3.0.2        |
| strictFloatingPoint                                 | 仅在兼容性测试的早期版本中使用。                             | No       | false                                | 3.0.0        |
| strictUpdates                                       | 驱动程序是否应对可更新结果集进行严格检查（选择所有的主键）？真／假，默认为“真”。 | No       | true                                 | 3.0.4        |
| tinyInt1isBit                                       | 驱动程序是否应将数据类型TINYINT(1)当作BIT类型对待？创建表时，服务器会执行BIT -> TINYINT(1)操作。 | No       | true                                 | 3.0.16       |
| transformedBitIsBoolean                             | 如果驱动程序将TINYINT(1)转换为不同的类型，为了与MySQL-5.0兼容，驱动程序是否应使用BOOLEAN取代BIT？这是因为MySQL-5.0具有BIT类型。 | No       | false                                | 3.1.9        |
| ultraDevHack                                        | 由于UltraDev已损坏，并为所有语句发出了prepareCall()，需要时，是否要为prepareCall()创建PreparedStatements? 真／假，默认值为“假”。 | No       | false                                | 2.0.3        |
| useHostsInPrivileges                                | 在DatabaseMetaData.getColumn/TablePrivileges()中为用户添加“@hostname”。真／假，默认为“真”。 | No       | true                                 | 3.0.2        |
| useOldUTF8Behavior                                  | 与4.0和更早版本的服务器进行通信时，使用UTF-8。               | No       | false                                | 3.1.6        |
| useOnlyServerErrorMessages                          | 对服务器返回的错误消息，不事先设定“标准的”SQLState错误消息。 | No       | true                                 | 3.0.15       |
| useServerPrepStmts                                  | 如果服务器支持，是否使用服务器端预处理语句？ 默认值为“真”。  | No       | true                                 | 3.1.0        |
| useSqlStateCodes                                    | 使用SQL标准状态码取代“传统的”X/Open/SQL状态码，真／假，默认为“真”。 | No       | true                                 | 3.1.3        |
| useStreamLengthsInPrepStmts                         | 是否采用PreparedStatement/ResultSet.setXXXStream()方法调用中的流长度参数？真／假，默认为“真”。 | No       | true                                 | 3.0.2        |
| useTimezone                                         | 是否在客户端和服务器时区间转换时间／日期类型（真／假，默认为“假”）？ | No       | false                                | 3.0.2        |
| useUnbufferedInput                                  | 不使用BufferedInputStream来从服务器读取数据。                | No       | true                                 | 3.0.11       |
| yearIsDateType                                      | JDBC驱动程序是否应将MySQL类型“YEAR”当作java.sql.Date或SHORT对待？ | No       | true                                 | 3.1.9        |
| zeroDateTimeBehavior                                | 当驱动程序遇到全由0组成的DATETIME值时，应出现什么？MySQL使用它来表示无效日期。有效值是“exception”、“round”和“convertToNull”。 | No       | exception                            | 3.1.4        |