## JVM

1. 堆设置

   - **-Xms**:初始堆大小
   - **-Xmx**:最大堆大小
   - **-XX:NewSize=n**:设置年轻代大小
   - **-XX:NewRatio=n:**设置年轻代和年老代的比值。如:为3，表示年轻代与年老代比值为1：3，年轻代占整个年轻代年老代和的1/4
   - **-XX:SurvivorRatio=n**:年轻代中Eden区与两个Survivor区的比值。注意Survivor区有两个。如：3，表示Eden：Survivor=3：2，一个Survivor区占整个年轻代的1/5
   - **-XX:MaxPermSize=n**:设置持久代大小
   - **XX:MetaspaceSize** 是分配给类元数据空间（以字节计）的初始大小(Oracle逻辑存储上的初始高水位，*the initial high-water-mark* )，此值为估计值。MetaspaceSize的值设置的过大会延长垃圾回收时间。垃圾回收过后，引起下一次垃圾回收的类元数据空间的大小可能会变大。
   - **-XX:MaxMetaspaceSize** 是分配给类元数据空间的最大值，超过此值就会触发Full GC，此值默认没有限制，但应取决于系统内存的大小。JVM会动态地改变此值。
   - **-XX:MinMetaspaceFreeRatio** 表示一次GC以后，为了避免增加元数据空间的大小，空闲的类元数据的容量的最小比例，不够就会导致垃圾回收。
   - **-XX:MaxMetaspaceFreeRatio** 表示一次GC以后，为了避免增加元数据空间的大小，空闲的类元数据的容量的最大比例，不够就会导致垃圾回收。

2. 收集器设置

   - **-XX:+UseSerialGC**:设置串行收集器
   - **-XX:+UseParallelGC**:设置并行收集器
   - **-XX:+UseParalledlOldGC**:设置并行年老代收集器
   - **-XX:+UseConcMarkSweepGC**:设置并发收集器

3. 垃圾回收统计信息
   - **-verbose:gc** 会被-XX中的参数覆盖
   - **-XX:+PrintGC**
   - **-XX:+PrintGCDetails**
   - **-XX:+PrintGCTimeStamps**
   - **-Xloggc:filename**

4. 并行收集器设置

   - **-XX:ParallelGCThreads=n**:设置并行收集器收集时使用的CPU数。并行收集线程数。
   - **-XX:MaxGCPauseMillis=n**:设置并行收集最大暂停时间
   - **-XX:GCTimeRatio=n**:设置垃圾回收时间占程序运行时间的百分比。公式为1/(1+n)

5. 并发收集器设置

   - **-XX:+CMSIncrementalMode**:设置为增量模式。适用于单CPU情况。
   - **-XX:ParallelGCThreads=n**:设置并发收集器年轻代收集方式为并行收集时，使用的CPU数。并行收集线程数。

6. 异常收集器
   - **-XX:+HeapDumpOnOutOfMemoryError**: 设置当发生oom时，保存内存快照日志文件。

---

---

---

##  jdk8 jvm配置参数说明 		

这些选项是特定于Java HotSpot虚拟机的通用选项。-X

显示所有可用`-X`选项的帮助。

-Xbatch

禁用后台编译。默认情况下，JVM将该方法编译为后台任务，以解释器模式运行该方法，直到后台编译完成。该`-Xbatch`标志禁用后台编译，以便所有方法的编译作为前台任务继续进行，直到完成。

此选项相当于`-XX:-BackgroundCompilation`。

-Xbootclasspath：*path*

指定以冒号（:)分隔的目录，JAR文件和ZIP存档的列表，以搜索引导类文件。这些用于代替JDK中包含的引导类文件。

不要部署使用此选项的应用程序覆盖类`rt.jar`，因为这违反了JRE二进制代码许可证。

-Xbootclasspath / a：*path*

指定以冒号（:)分隔的目录，JAR文件和ZIP存档的列表，以附加到默认引导类路径的末尾。

不要部署使用此选项的应用程序覆盖类`rt.jar`，因为这违反了JRE二进制代码许可证。

-Xbootclasspath / p：*path*

指定以冒号（:)分隔的目录，JAR文件和ZIP存档的列表，以预先添加到默认引导程序类路径的前面。

不要部署使用此选项的应用程序覆盖类`rt.jar`，因为这违反了JRE二进制代码许可证。

-Xcheck：JNI

对Java Native Interface（JNI）函数执行其他检查。具体来说，它在处理JNI请求之前验证传递给JNI函数的参数和运行时环境数据。遇到的任何无效数据都表明本机代码存在问题，在这种情况下，JVM将以无法恢复的错误终止。使用此选项时，预计性能会下降。

-Xcomp

在第一次调用时强制编译方法。默认情况下，客户端VM（`-client`）执行1,000个已解释的方法调用，Server VM（`-server`）执行10,000个已解释的方法调用以收集有效编译的信息。指定该`-Xcomp`选项会禁用已解释的方法调用，从而以牺牲效率为代价来提高编译性能。

您还可以使用该`-XX:CompileThreshold`选项在编译之前更改已解释的方法调用的数量。

-Xdebug

什么也没做。提供向后兼容性。

-Xdiag

显示其他诊断消息。

-Xfuture

启用严格的类文件格式检查，以强制与类文件格式规范紧密一致。鼓励开发人员在开发新代码时使用此标志，因为更严格的检查将成为未来版本中的默认值。

-Xint

以仅解释模式运行应用程序。禁用对本机代码的编译，并且解释器将执行所有字节码。暂时（JIT）编译器提供的性能优势在此模式下不存在。

-Xinternalversion

显示比该`-version`选项更详细的JVM版本信息，然后退出。

-Xloggc：*filename*

设置应将重定向的GC事件信息重定向到的文件以进行日志记录。写入此文件的信息类似于`-verbose:gc`自每个记录事件之前的第一个GC事件以来经过的时间的输出。如果两者都使用相同的命令，则该`-Xloggc`选项将覆盖。`-verbose:gc``java`

例：

```
-Xloggc：垃圾collection.log
```

-Xmaxjitcodesize = *size*

指定JIT编译代码的最大代码高速缓存大小（以字节为单位）。附加字母`k`或`K`表示千字节，`m`或`M`指示兆字节，`g`或`G`指示千兆字节。默认的最大代码缓存大小为240 MB; 如果使用该选项禁用分层编译`-XX:-TieredCompilation`，则默认大小为48 MB：

```
-Xmaxjitcodesize =240米
```

此选项相当于`-XX:ReservedCodeCacheSize`。

-Xmixed

除了热方法之外，解释器执行所有字节码，热方法被编译为本机代码。

-Xmn *尺寸*

设置年轻代（托儿所）的堆的初始和最大大小（以字节为单位）。附加字母`k`或`K`表示千字节，`m`或`M`指示兆字节，`g`或`G`指示千兆字节。

堆的年轻代区域用于新对象。GC在该区域比在其他区域更频繁地进行。如果年轻一代的规模太小，那么将会进行大量的小型垃圾收集。如果大小太大，则只执行完整的垃圾收集，这可能需要很长时间才能完成。Oracle建议您将年轻代的大小保持在整个堆大小的一半到四分之一之间。

以下示例显示如何使用各种单位将年轻代的初始和最大大小设置为256 MB：

```
-Xmn256m 
-Xmn262144k 
-Xmn268435456
```

取而代之的是的`-Xmn`，为年轻一代都设置堆的初始和最大大小选项，您可以使用`-XX:NewSize`设置初始大小和`-XX:MaxNewSize`设置的最大尺寸。

-Xms *大小*

设置堆的初始大小（以字节为单位）。该值必须是1024的倍数且大于1 MB。附加字母`k`或`K`表示千字节，`m`或`M`指示兆字节，`g`或`G`指示千兆字节。

以下示例显示如何使用各种单位将分配的内存大小设置为6 MB：

```
-Xms6291456 
-Xms6144k 
-Xms6m
```

如果未设置此选项，则初始大小将设置为为旧代和年轻代分配的大小的总和。可以使用`-Xmn`选项或`-XX:NewSize`选项设置年轻代的堆的初始大小。

-Xmx *尺寸*

### 高级运行时选项

这些选项控制Java HotSpot VM的运行时行为。

-XX：+ CheckEndorsedAndExtDirs

`java`如果命令使用了endorsed-standards override机制或扩展机制，则启用该选项以阻止命令运行Java应用程序。此选项通过检查以下内容来检查应用程序是否正在使用这些机制之一：

- 该`java.ext.dirs`或`java.endorsed.dirs`系统属性设置。
- 该`lib/endorsed`目录存在且不为空。
- 该`lib/ext`目录包含除JDK之外的任何JAR文件。
- 系统范围的特定于平台的扩展目录包含任何JAR文件。

-XX：+ DisableAttachMechanism

启用禁用允许工具附加到JVM的机制的选项。默认情况下，该选项被禁用，这意味着连接机制启用，您可以使用工具，例如`jcmd`，`jstack`，`jmap`，和`jinfo`。

-XX：ErrorFile = *filename*

指定发生不可恢复的错误时写入错误数据的路径和文件名。默认情况下，此文件在当前工作目录中创建，并命名为`hs_err_pid`*pid*`.log`，其中*pid*是导致错误的进程的标识符。以下示例显示如何设置默认日志文件（请注意，进程的标识符指定为`%p`）：

```
-XX：错误文件= / hs_err_pid％p.log
```

以下示例显示如何将错误日志设置为`/var/log/java/java_error.log`：

```
-XX：错误文件= /无功/日志/ JAVA / java_error.log
```

如果无法在指定目录中创建文件（由于空间不足，权限问题或其他问题），则会在操作系统的临时目录中创建该文件。临时目录是`/tmp`。

-XX：+ FailOverToOldVerifier

当新类型检查程序失败时，启用对旧验证程序的自动故障转移。默认情况下，此选项被禁用，并且对于具有最新字节码版本的类，它将被忽略（即，视为已禁用）。您可以为具有旧版字节码的类启用它。

-XX：+ FlightRecorder

允许在应用程序运行时使用Java Flight Recorder（JFR）。这是一个商业功能，与`-XX:+UnlockCommercialFeatures`选项一起使用如下：

```
java -XX：+ UnlockCommercialFeatures -XX：+ FlightRecorder
```

如果未提供此选项，则仍可通过提供相应的`jcmd`诊断命令在正在运行的JVM中启用Java Flight Recorder 。

-XX：-FlightRecorder

在应用程序运行时禁用Java Flight Recorder（JFR）。这是一个商业功能，与`-XX:+UnlockCommercialFeatures`选项一起使用如下：

```
java -XX：+ UnlockCommercialFeatures -XX：-FlightRecorder
```

如果提供此选项，则无法在正在运行的JVM中启用Java Flight Recorder。

-XX：FlightRecorderOptions = *parameter**value*

设置控制JFR行为的参数。这是一个与`-XX:+UnlockCommercialFeatures`选项配合使用的商业功能。仅当启用JFR时（即`-XX:+FlightRecorder`指定选项），才能使用此选项。

以下列表包含所有可用的JFR参数：

- defaultrecording = {真|假}

   指定录制是连续背景录制还是在有限时间内运行。默认情况下，此参数设置为`false`（记录在限定时间内运行）。要使录制连续运行，请将参数设置为`true`。 

- 磁盘= {真|假}

   指定JFR是否应将连续记录写入磁盘。默认情况下，此参数设置为`false`（禁用连续录制到磁盘）。要启用它，请将参数设置为`true`，并设置`defaultrecording=true`。 

- dumponexit = {真|假}

   指定当JVM以受控方式终止时是否应生成JFR数据的转储文件。默认情况下，此参数设置为`false`（不生成退出时的转储文件）。要启用它，请将参数设置为`true`，并设置`defaultrecording=true`。 转储文件将写入`dumponexitpath`参数定义的位置。 

- dumponexitpath = *path*

   如果设置`dumponexit=true`参数，则使用JVM以受控方式退出时创建的JFR数据指定转储文件的路径和名称。只有在设置的情况下才能设置路径`defaultrecording=true`。 如果指定的路径是目录，则JVM会分配一个显示创建日期和时间的文件名。如果指定的路径包含文件名，并且该文件已存在，则JVM通过将日期和时间戳附加到指定的文件名来创建新文件。 

- globalbuffersize = *size*

   指定用于数据保留的主内存总量（以字节为单位）。追加`k`或`K`到指定KB大小，`m`或`M`以MB为单位指定大小，`g`或`G`到指定GB的大小。默认情况下，大小设置为462848字节。 

- 日志等级= {安静|错误|警告|信息|调试|跟踪}

   指定JFR写入日志文件的数据量。默认情况下，它设置为`info`。 

- maxage = *时间*

   指定要为默认录制保留的磁盘数据的最长期限。附加`s`以指定以秒`m`为单位的时间，分钟，`h`小时或`d`天数（例如，指定`30s`表示30秒）。默认情况下，最大年龄设置为15分钟（`15m`）。 仅当您设置`disk=true`参数时，此参数才有效。 

- maxchunksize = *size*

   指定记录中数据块的最大大小（以字节为单位）。追加`k`或`K`到指定KB大小，`m`或`M`以MB为单位指定大小，`g`或`G`到指定GB的大小。默认情况下，数据块的最大大小设置为12 MB。 

- maxsize = *size*

   指定要为默认记录保留的磁盘数据的最大大小（以字节为单位）。追加`k`或`K`到指定KB大小，`m`或`M`以MB为单位指定大小，`g`或`G`到指定GB的大小。默认情况下，磁盘数据的最大大小不受限制，此参数设置为0。 仅当您设置`disk=true`参数时，此参数才有效。 

- repository = *path*

   指定临时磁盘存储的存储库（目录）。默认情况下，使用系统的临时目录。 

- samplethreads = {真|假}

   指定是否启用线程采样。仅当采样事件与此参数一起启用时，才会进行线程采样。默认情况下，启用此参数。 

- settings = *path*

   指定事件设置文件的路径和名称（类型为JFC）。默认情况下，使用该`default.jfc`文件，该文件位于`JAVA_HOME/jre/lib/jfr`。 

- stackdepth = *深度*

   JFR的堆栈跟踪堆栈深度。默认情况下，深度设置为64个方法调用。最大值为2048，最小值为1。 

- threadbuffersize = *size*

   指定每线程本地缓冲区大小（以字节为单位）。追加`k`或`K`到指定KB大小，`m`或`M`以MB为单位指定大小，`g`或`G`到指定GB的大小。此参数的较高值允许更多数据收集而不会争用将其刷新到全局存储。它可以在线程丰富的环境中增加应用程序占用空间。默认情况下，本地缓冲区大小设置为5 KB。 

您可以通过用逗号分隔多个参数的值来指定它们。例如，要指示JFR将连续记录写入磁盘，并将数据块的最大大小设置为10 MB，请指定以下内容：

```
-XX：FlightRecorderOptions = defaultrecording = TRUE，磁盘= TRUE，maxchunksize = 10M
```

-XX：LargePageSizeInBytes = *size*

在Solaris上，设置用于Java堆的大页面的最大大小（以字节为单位）。的*尺寸*参数必须是2的幂（2，4，8，16，...）。附加字母`k`或`K`表示千字节，`m`或`M`指示兆字节，`g`或`G`指示千兆字节。默认情况下，大小设置为0，这意味着JVM会自动选择大页面的大小。

以下示例说明如何将大页面大小设置为4兆字节（MB）：

```
-XX：LargePageSizeInBytes =4米
```

-XX：MaxDirectMemorySize = *size*

设置新I / O（`java.nio`包）直接缓冲区分配的最大总大小（以字节为单位）。附加字母`k`或`K`表示千字节，`m`或`M`指示兆字节，`g`或`G`指示千兆字节。默认情况下，大小设置为0，这意味着JVM会自动选择NIO直接缓冲区分配的大小。

以下示例说明如何以不同单位将NIO大小设置为1024 KB：

```
-XX：MaxDirectMemorySize = 1m 
-XX：MaxDirectMemorySize = 1024k 
-XX：MaxDirectMemorySize = 1048576
```

-XX：NativeMemoryTracking = *mode*

指定用于跟踪JVM本机内存使用情况的模式。此选项的可能*模式*参数包括以下内容：

- 离

   不跟踪JVM本机内存使用情况。如果未指定该`-XX:NativeMemoryTracking`选项，则这是默认行为。 

- 摘要

   仅跟踪JVM子系统的内存使用情况，例如Java堆，类，代码和线程。 

- 详情

   除了跟踪JVM子系统的内存使用情况外，还可以跟踪`CallSite`各个虚拟内存区域及其已提交区域的内存使用情况。 

-XX：ObjectAlignmentInBytes = *alignment*

设置Java对象的内存对齐方式（以字节为单位）。默认情况下，该值设置为8个字节。指定的值应为2的幂，并且必须在8和256（含）的范围内。此选项可以使用具有大Java堆大小的压缩指针。

堆大小限制（以字节为单位）计算如下：

```
4GB * ObjectAlignmentInBytes
```

注意：随着对齐值的增加，对象之间未使用的空间也会增加。因此，您可能没有意识到使用具有大型Java堆大小的压缩指针会带来任何好处。

-XX：OnError = *string*

设置自定义命令或一系列以分号分隔的命令，以便在发生不可恢复的错误时运行。如果字符串包含空格，则必须用引号括起来。

以下示例显示了如何使用该`-XX:OnError`选项运行`gcore`命令来创建核心映像，并且在出现无法恢复的错误（`%p`指定当前进程）时，调试器将开始附加到进程：

```
-XX：OnError =“gcore％p; dbx  - ％p”
```

-XX：OnOutOfMemoryError = *string*

设置自定义命令或一系列以分号分隔的命令，以便在`OutOfMemoryError`首次引发异常时运行。如果字符串包含空格，则必须用引号括起来。有关命令字符串的示例，请参阅该`-XX:OnError`选项的说明。

-XX：+ PerfDataSaveToFile

- -XX：+ PrintCommandLineFlags

   允许打印出现在命令行上的符合人体工程学选择的JVM标志。了解JVM设置的人体工程学值（例如堆空间大小和选定的垃圾收集器）可能很有用。默认情况下，禁用此选项并且不打印标志。 

- -XX：+ PrintNMTStatistics

   当启用本机内存跟踪时，允许在JVM出口处打印收集的本机内存跟踪数据（请参阅参考资料`-XX:NativeMemoryTracking`）。默认情况下，禁用此选项并且不打印本机内存跟踪数据。 

- -XX：+ RelaxAccessControlCheck

   减少验证程序中访问控制检查的数量。默认情况下，此选项被禁用，对于具有最新字节码版本的类，它将被忽略（即，视为已禁用）。您可以为具有旧版字节码的类启用它。 

- -XX：+ ResourceManagement

   在应用程序的运行时期间启用资源管理。 这是一项商业功能，需要您还指定`-XX:+UnlockCommercialFeatures`选项，如下所示： `java -XX:+UnlockCommercialFeatures -XX:+ResourceManagement` 

- -XX：ResourceManagementSampleInterval = *value*（毫秒）

   设置控制资源管理测量的采样间隔的参数，以毫秒为单位。 仅当启用资源管理（即`-XX:+ResourceManagement`指定选项）时，才能使用此选项。 

- -XX：SharedArchiveFile = *path*

   指定类数据共享（CDS）归档文件的路径和名称 

- -XX：SharedClassListFile = *file_name*

   指定包含要存储在类数据共享（CDS）存档中的类文件名称的文本文件。此文件包含每行一个类文件的全名，但斜杠（`/`）替换dots（`.`）除外。例如，指定类`java.lang.Object`和`hello.Main`，创建一个包含以下两行的文本文件： `java / lang / Object  hello / Main ` 您在此文本文件中指定的类文件应包含应用程序常用的类。它们可以包括应用程序，扩展或引导类路径中的任何类。 

- -XX：+ ShowMessageBoxOnError

   当JVM遇到无法恢复的错误时，允许显示对话框。这可以防止JVM退出并使进程保持活动状态，以便您可以将调试器附加到它以调查错误原因。默认情况下，禁用此选项。 

- -XX：StartFlightRecording = *parameter* = *value*

   启动Java应用程序的JFR记录。这是一个与`-XX:+UnlockCommercialFeatures`选项配合使用的商业功能。此选项等同于`JFR.start`在运行时启动记录的诊断命令。您可以在开始JFR录制时设置以下参数： 压缩= {真|假} 指定是否使用`gzip`文件压缩实用程序压缩磁盘上的JFR记录日志文件（JFR类型）。仅当`filename`指定参数时，此参数才有效。默认设置为`false`（记录未压缩）。要启用压缩，请将参数设置为`true`。 defaultrecording = {真|假} 指定录制是连续背景录制还是在有限时间内运行。默认情况下，此参数设置为`false`（记录在限定时间内运行）。要使录制连续运行，请将参数设置为`true`。 延迟= *时间* 指定Java应用程序启动时间和记录开始之间的延迟。附加`s`以指定以秒`m`为单位的时间，分钟，`h`小时或`d`天数（例如，指定`10m`表示10分钟）。默认情况下，没有延迟，此参数设置为0。 dumponexit = {真|假} 指定当JVM以受控方式终止时是否应生成JFR数据的转储文件。默认情况下，此参数设置为`false`（不生成退出时的转储文件）。要启用它，请将参数设置为`true`。 转储文件将写入`filename`参数定义的位置。 例： `-XX：StartFlightRecording =名=测试中，文件名= d：\ test.jfr，dumponexit =真 ` 持续时间= *时间* 指定录制的持续时间。附加`s`以指定以秒`m`为单位的时间，分钟，`h`小时或`d`天数（例如，指定`5h`表示5小时）。默认情况下，持续时间不受限制，此参数设置为0。 filename = *path* 指定JFR记录日志文件的路径和名称。 name = *标识符* 指定JFR记录的标识符。默认情况下，它设置为`Recording x`。 maxage = *时间* 指定要为默认录制保留的磁盘数据的最长期限。附加`s`以指定以秒`m`为单位的时间，分钟，`h`小时或`d`天数（例如，指定`30s`表示30秒）。默认情况下，最大年龄设置为15分钟（`15m`）。 maxsize = *size* 指定要为默认记录保留的磁盘数据的最大大小（以字节为单位）。追加`k`或`K`到指定KB大小，`m`或`M`以MB为单位指定大小，`g`或`G`到指定GB的大小。默认情况下，磁盘数据的最大大小不受限制，此参数设置为0。 settings = *path* 指定事件设置文件的路径和名称（类型为JFC）。默认情况下，使用该`default.jfc`文件，该文件位于`JAVA_HOME/jre/lib/jfr`。  您可以通过用逗号分隔多个参数的值来指定它们。例如，要将记录保存到当前工作目录中的test.jfr，并指示JFR压缩日志文件，请指定以下内容： `-XX：StartFlightRecording =文件名= test.jfr，压缩= TRUE ` 

- -XX：ThreadStackSize = *size*

   设置线程堆栈大小（以字节为单位）。附加字母`k`或`K`表示千字节，`m`或`M`指示兆字节，`g`或`G`指示千兆字节。默认值取决于平台：   Linux / ARM（32位）：320 KB   Linux / i386（32位）：320 KB   Linux / x64（64位）：1024 KB   OS X（64位）：1024 KB   Oracle Solaris / i386（32位）：320 KB   Oracle Solaris / x64（64位）：1024 KB   以下示例显示如何以不同单位将线程堆栈大小设置为1024 KB： `-XX：ThreadStackSize = 1m  -XX：ThreadStackSize = 1024k  -XX：ThreadStackSize = 1048576 ` 此选项相当于`-Xss`。 

- -XX：+ TraceClassLoading

   允许在加载类时跟踪类。默认情况下，禁用此选项并且不跟踪类。 

- -XX：+ TraceClassLoadingPreorder

   允许按引用顺序跟踪所有已加载的类。默认情况下，禁用此选项并且不跟踪类。 

- -XX：+ TraceClassResolution

   允许跟踪常量池分辨率。默认情况下，禁用此选项并且不跟踪常量池分辨率。 

- -XX：+ TraceClassUnloading

   允许在卸载类时跟踪类。默认情况下，禁用此选项并且不跟踪类。 

- -XX：+ TraceLoaderConstraints

   允许跟踪加载器约束记录。默认情况下，禁用此选项并且不跟踪加载程序约束记录。 

- -XX：+ UnlockCommercialFeatures

   允许使用商业功能。Oracle Java SE Advanced或Oracle Java SE Suite软件包中包含商业功能，如*Java SE产品*页面中所定义`http://www.oracle.com/technetwork/java/javase/terms/products/index.html` 默认情况下，此选项被禁用，JVM在没有商业功能的情况下运行。一旦为JVM进程启用了它们，就无法禁用它们用于该进程。 如果未提供此选项，则仍可使用相应的`jcmd`诊断命令在正在运行的JVM中解锁商业功能。 

- -XX：+ UseAltSigs

   使得能够使用替代信号，而不是`SIGUSR1`和`SIGUSR2`用于JVM内部信号。默认情况下，此选项被禁用，并且不使用替代信号。此选项相当于`-Xusealtsigs`。 

- -XX：+ UseAppCDS

   启用应用程序类数据共享（AppCDS）。要使用AppCDS，还必须指定选项的值`-XX:SharedClassListFile`和`-XX:SharedArchiveFile`两个CDS在转储时间（见选项`-Xshare:dump`）和应用程序运行时间。 这是一项商业功能，需要您同时指定`-XX:+UnlockCommercialFeatures`选项。这也是一个实验性的特征; 它可能在将来的版本中发生变化 请参阅[“应用程序类数据共享”](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/java.html#app_class_data_sharing)。 

- -XX：-UseBiasedLocking

   禁用使用偏置锁定。一些具有大量无竞争同步的应用程序可以在启用此标志的情况下获得显着的加速，而具有某些锁定模式的应用程序可能会看到减速。有关偏置锁定技术的更多信息，请参阅Java调优白皮书中的示例`http://www.oracle.com/technetwork/java/tuning-139912.html#section4.2.5` 默认情况下，启用此选项。 

- -XX：-UseCompressedOops

   禁用压缩指针的使用。默认情况下，启用此选项，并在Java堆大小小于32 GB时使用压缩指针。启用此选项时，对象引用表示为32位偏移而不是64位指针，这通常会在运行Java堆大小小于32 GB的应用程序时提高性能。此选项仅适用于64位JVM。 当Java堆大小大于32GB时，也可以使用压缩指针。请参阅`-XX:ObjectAlignmentInBytes`选项。 

- -XX：+ UseHugeTLBFS

   Linux的此选项相当于指定`-XX:+UseLargePages`。默认情况下禁用此选项。当保留内存时，此选项预先分配所有大页面; 因此，JVM无法动态增长或缩小大页面内存区域; 看看`-XX:UseTransparentHugePages`你是否想要这种行为。 有关更多信息，请参阅[“大页面”](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/java.html#large_pages)。 

- -XX：+ UseLargePages

   允许使用大页面内存。默认情况下，禁用此选项并且不使用大页面内存。 有关更多信息，请参阅[“大页面”](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/java.html#large_pages)。 

- -XX：+ UseMembar

   允许在线程状态转换上发布membars。默认情况下，在除ARM服务器之外的所有平台上都禁用此选项。（建议您不要在ARM服务器上禁用此选项。） 

- -XX：+ UsePerfData

   启用该`perfdata`功能。默认情况下启用此选项以允许JVM监视和性能测试。禁用它会禁止创建`hsperfdata_userid`目录。要禁用该`perfdata`功能，请指定`-XX:-UsePerfData`。 

- -XX：+ UseTransparentHugePages

   在Linux上，允许使用可以动态增长或缩小的大页面。默认情况下禁用此选项。您可能会遇到透明大页面的性能问题，因为操作系统会移动其他页面以创建大页面; 此选项可用于实验。 有关更多信息，请参阅[“大页面”](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/java.html#large_pages)。 

- -XX：+ AllowUserSignalHandlers

   允许应用程序安装信号处理程序。默认情况下，禁用此选项，并且不允许应用程序安装信号处理程序。 

### 高级JIT编译器选项

这些选项控制Java HotSpot VM执行的动态即时（JIT）编译。

- -XX：+ AggressiveOpts

   允许使用积极的性能优化功能，这些功能有望在即将发布的版本中成为默认功能。默认情况下，禁用此选项并且不使用实验性能功能。 

- -XX：AllocateInstancePrefetchLines = *lines*

   设置在实例分配指针之前预取的行数。默认情况下，预取的行数设置为1： `-XX：AllocateInstancePrefetchLines = 1 ` 只有Java HotSpot Server VM支持此选项。 

- -XX：AllocatePrefetchDistance = *size*

   设置对象分配的预取距离的大小（以字节为单位）。将从最后分配的对象的地址开始预取将要使用新对象的值写入的内存。每个Java线程都有自己的分配点。 负值表示基于平台选择预取距离。正值是预取的字节数。附加字母`k`或`K`表示千字节，`m`或`M`指示兆字节，`g`或`G`指示千兆字节。默认值设置为-1。 以下示例显示如何将预取距离设置为1024字节： `-XX：AllocatePrefetchDistance = 1024 ` 只有Java HotSpot Server VM支持此选项。 

- -XX：AllocatePrefetchInstr = *指令*

   将预取指令设置为在分配指针之前预取。只有Java HotSpot Server VM支持此选项。可能的值为0到3.值后面的实际指令取决于平台。默认情况下，预取指令设置为0： `-XX：AllocatePrefetchInstr = 0 ` 只有Java HotSpot Server VM支持此选项。 

- -XX：AllocatePrefetchLines = *lines*

   使用编译代码中生成的预取指令设置在最后一次对象分配后要加载的高速缓存行数。如果最后分配的对象是实例，则默认值为1;如果是数组，则默认值为3。 以下示例显示如何将加载的缓存行数设置为5： `-XX：AllocatePrefetchLines = 5 ` 只有Java HotSpot Server VM支持此选项。 

- -XX：AllocatePrefetchStepSize = *size*

   设置顺序预取指令的步长（以字节为单位）。附加字母`k`或`K`表示千字节，`m`或`M`指示兆字节，`g`或`G`指示千兆字节。默认情况下，步长设置为16个字节： `-XX：AllocatePrefetchStepSize = 16 ` 只有Java HotSpot Server VM支持此选项。 

- -XX：AllocatePrefetchStyle = *style*

   为预取指令设置生成的代码样式。的*风格*参数是从0至3的整数： 0 不要生成预取指令。 1 每次分配后执行预取指令。这是默认参数。 2 使用线程局部分配块（TLAB）水印指针来确定何时执行预取指令。 3 在SPARC上使用BIS指令进行分配预取。  只有Java HotSpot Server VM支持此选项。 

- -XX：+ BackgroundCompilation

   启用后台编译。默认情况下启用此选项。要禁用后台编译，请指定`-XX:-BackgroundCompilation`（这相当于指定`-Xbatch`）。 

- -XX：CICompilerCount = *threads*

   设置用于编译的编译器线程数。默认情况下，服务器JVM的线程数设置为2，客户端JVM的线程数设置为1，如果使用分层编译，则会扩展为核心数。以下示例显示如何将线程数设置为2： `-XX：CICompilerCount = 2 ` 

- -XX：CodeCacheMinimumFreeSpace = *size*

   设置编译所需的最小可用空间（以字节为单位）。附加字母`k`或`K`表示千字节，`m`或`M`指示兆字节，`g`或`G`指示千兆字节。当剩余小于最小可用空间时，编译停止。默认情况下，此选项设置为500 KB。以下示例显示如何将最小可用空间设置为1024 MB： `-XX：CodeCacheMinimumFreeSpace =1024米 ` 

- -XX：CompileCommand = *command*，*method* [，*option* ]

   指定要对方法执行的命令。例如，要排除编译类的`indexOf()`方法`String`，请使用以下命令： `-XX：CompileCommand =排除，爪哇/郎/ String.indexOf ` 请注意，指定了完整的类名，包括由斜杠（`/`）分隔的所有包和子包。为了便于剪切和粘贴操作，还可以使用`-XX:+PrintCompilation`和`-XX:+LogCompilation`选项生成的方法名称格式： `-XX：CompileCommand =排除，java.lang.String中的indexOf :: ` 如果在没有签名的情况下指定方法，则该命令将应用于具有指定名称的所有方法。但是，您也可以在类文件格式中指定方法的签名。在这种情况下，您应该将参数括在引号中，否则shell会将分号视为命令end。例如，如果要仅排除编译类的`indexOf(String)`方法`String`，请使用以下命令： `-XX：CompileCommand = “排除，爪哇/郎/ String.indexOf，（Ljava /郎/字符串;）I” ` 您还可以使用星号（*）作为类和方法名称的通配符。例如，要排除`indexOf()`编译所有类中的所有方法，请使用以下命令： `-XX：CompileCommand =排除*的indexOf。 ` 逗号和句点是空格的别名，使得通过shell传递编译器命令更容易。您可以通过`-XX:CompileCommand`将参数括在引号中来将参数传递给使用空格作为分隔符： `-XX：CompileCommand =“exclude java / lang / String indexOf” ` 请注意，在使用`-XX:CompileCommand`选项解析在命令行上传递的命令之后，JIT编译器会从`.hotspot_compiler`文件中读取命令。您可以向此文件添加命令，也可以使用该`-XX:CompileCommandFile`选项指定其他文件。 要添加多个命令，请`-XX:CompileCommand`多次指定该选项，或使用换行符分隔符（`\n`）分隔每个参数。可以使用以下命令： 打破 在调试JVM时设置断点，以便在编译指定方法的开始时停止。 compileonly 除了指定的方法之外，从编译中排除所有方法。作为替代方法，您可以使用该`-XX:CompileOnly`选项，该选项允许指定多种方法。 dontinline 防止内联指定的方法。 排除 从编译中排除指定的方法。 救命 打印该`-XX:CompileCommand`选项的帮助消息。 排队 尝试内联指定的方法。 日志 排除`-XX:+LogCompilation`除指定方法之外的所有方法的编译日志记录（带选项）。默认情况下，对所有已编译的方法执行日志记录。 选项 此命令可用于将JIT编译选项传递给指定的方法以代替最后一个参数（*选项*）。编译选项在方法名称后面的末尾设置。例如，要启用类方法的`BlockLayoutByFrequency`选项，请使用以下命令：`append()``StringBuffer` `-XX：CompileCommand =选项，爪哇/郎/的StringBuffer.append，BlockLayoutByFrequency ` 您可以指定多个编译选项，以逗号或空格分隔。 打印 在编译指定方法后打印生成的汇编代码。 安静 不要打印编译命令。默认情况下，使用 - `XX:CompileCommand`选项指定的命令将被打印; 例如，如果从编译中排除类的`indexOf()`方法`String`，则以下内容将打印到标准输出： `CompilerOracle：排除java / lang / String.indexOf ` 您可以通过`-XX:CompileCommand=quiet`在其他选项之前指定选项来抑制此操作`-XX:CompileCommand`。 

- -XX：CompileCommandFile = *filename*

   设置从中读取JIT编译器命令的文件。默认情况下，该`.hotspot_compiler`文件用于存储JIT编译器执行的命令。 命令文件中的每一行代表一个命令，一个类名和一个使用该命令的方法名。例如，此行打印类的`toString()`方法的汇编代码`String`： `print java / lang / String toString ` 有关为JIT编译器指定要对方法执行的命令的更多信息，请参阅该`-XX:CompileCommand`选项。 

- -XX：CompileOnly = *方法*

   设置应限制编译的方法列表（以逗号分隔）。仅编译指定的方法。使用完整的类名（包括包和子包）指定每个方法。例如，为了仅编译`length()`所述的方法`String`类和`size()`所述的方法`List`类，使用以下： `-XX：CompileOnly =爪哇/郎/ string.length减，JAVA / UTIL /则为list.size ` 请注意，指定了完整的类名，包括由斜杠（`/`）分隔的所有包和子包。为了便于剪切和粘贴操作，还可以使用`-XX:+PrintCompilation`和`-XX:+LogCompilation`选项生成的方法名称格式： `-XX：CompileOnly = java.lang.String中::长度，java.util.List中::大小 ` 虽然不支持通配符，但您只能指定类或包名称来编译该类或包中的所有方法，并且只指定在任何类中使用此名称编译方法的方法： `-XX：CompileOnly = java / lang / String  -XX：CompileOnly = java / lang  -XX：CompileOnly = .length ` 

- -XX：CompileThreshold = *调用*

   设置编译前解释的方法调用的数量。默认情况下，在服务器JVM中，JIT编译器执行10,000次解释方法调用以收集有效编译的信息。对于客户端JVM，默认设置为1,500次调用。启用分层编译时，将忽略此选项; 看到选项`-XX:+TieredCompilation`。以下示例显示如何将解释的方法调用数设置为5,000： `-XX：CompileThreshold = 5000 ` 您可以通过指定`-Xcomp`选项在编译之前完全禁用Java方法的解释。 

- -XX：+ DoEscapeAnalysis

   允许使用转义分析。默认情况下启用此选项。要禁用转义分析，请指定`-XX:-DoEscapeAnalysis`。只有Java HotSpot Server VM支持此选项。 

- -XX：InitialCodeCacheSize = *size*

   设置初始代码高速缓存大小（以字节为单位）。附加字母`k`或`K`表示千字节，`m`或`M`指示兆字节，`g`或`G`指示千兆字节。默认值设置为500 KB。初始代码高速缓存大小应不小于系统的最小内存页大小。以下示例显示如何将初始代码高速缓存大小设置为32 KB： `-XX：InitialCodeCacheSize = 32K ` 

- -XX：+内嵌

   启用方法内联。默认情况下启用此选项以提高性能。要禁用方法内联，请指定`-XX:-Inline`。 

- -XX：InlineSmallCode = *size*

   设置应内联的已编译方法的最大代码大小（以字节为单位）。附加字母`k`或`K`表示千字节，`m`或`M`指示兆字节，`g`或`G`指示千兆字节。只有内联小于指定大小的编译方法才会被内联。默认情况下，最大代码大小设置为1000字节： `-XX：InlineSmallCode = 1000 ` 

- -XX：+ LogCompilation

   允许将编译活动记录到`hotspot.log`当前工作目录中指定的文件。您可以使用该`-XX:LogFile`选项指定其他日志文件路径和名称。 默认情况下，禁用此选项并且不记录编译活动。该`-XX:+LogCompilation`选项必须与`-XX:UnlockDiagnosticVMOptions`解锁诊断JVM选项的选项一起使用。 每次使用该`-XX:+PrintCompilation`选项编译方法时，都可以启用详细诊断输出，并在控制台上打印一条消息。 

- -XX：MaxInlineSize = *size*

   设置要内联的方法的最大字节码大小（以字节为单位）。附加字母`k`或`K`表示千字节，`m`或`M`指示兆字节，`g`或`G`指示千兆字节。默认情况下，最大字节码大小设置为35个字节： `-XX：MaxInlineSize = 35 ` 

- -XX：MaxNodeLimit = *节点*

   设置单个方法编译期间要使用的最大节点数。默认情况下，最大节点数设置为65,000： `-XX：MaxNodeLimit = 65000 ` 

- -XX：MaxTrivialSize = *size*

   设置要内联的简单方法的最大字节码大小（以字节为单位）。附加字母`k`或`K`表示千字节，`m`或`M`指示兆字节，`g`或`G`指示千兆字节。默认情况下，一个简单方法的最大字节码大小设置为6个字节： `-XX：MaxTrivialSize = 6 ` 

- -XX：+ OptimizeStringConcat

   启用`String`串联操作的优化。默认情况下启用此选项。要禁用`String`串联操作的优化，请指定`-XX:-OptimizeStringConcat`。只有Java HotSpot Server VM支持此选项。 

- -XX：+ PrintAssembly

   通过使用外部`disassembler.so`库，可以为字节编码和本机方法打印汇编代码。这使您可以查看生成的代码，这可以帮助您诊断性能问题。 默认情况下，禁用此选项并且不打印汇编代码。该`-XX:+PrintAssembly`选项必须与`-XX:UnlockDiagnosticVMOptions`解锁诊断JVM选项的选项一起使用。 

- -XX：+ PrintCompilation

   每次编译方法时，通过向控制台打印消息，从JVM启用详细诊断输出。这使您可以查看实际编译的方法。默认情况下，禁用此选项并且不打印诊断输出。 您还可以使用该`-XX:+LogCompilation`选项将编译活动记录到文件中。 

- -XX：+ PrintInlining

   允许打印内联决策。这使您可以查看哪些方法被内联。 默认情况下，禁用此选项并且不打印内联信息。该`-XX:+PrintInlining`选项必须与`-XX:+UnlockDiagnosticVMOptions`解锁诊断JVM选项的选项一起使用。 

- -XX：ReservedCodeCacheSize = *size*

   设置JIT编译代码的最大代码缓存大小（以字节为单位）。附加字母`k`或`K`表示千字节，`m`或`M`指示兆字节，`g`或`G`指示千兆字节。默认的最大代码缓存大小为240 MB; 如果使用该选项禁用分层编译`-XX:-TieredCompilation`，则默认大小为48 MB。此选项的限制为2 GB; 否则，会产生错误。最大代码缓存大小不应小于初始代码缓存大小; 看到选项`-XX:InitialCodeCacheSize`。此选项相当于`-Xmaxjitcodesize`。 

- -XX：RTMAbortRatio = *abort_ratio*

   RTM中止比率指定为所有已执行RTM事务的百分比（％）。如果许多中止事务变得大于此比率，则编译后的代码将被去优化。`-XX:+UseRTMDeopt`启用该选项时将使用此比率。此选项的默认值为50.这意味着如果50％的所有事务都被中止，则编译后的代码将被去优化。 

- -XX：RTMRetryCount = *number_of_retries*

   RTM锁定代码将在中止或忙碌时重试此选项指定的次数，然后再回退到正常锁定机制。此选项的默认值为5. `-XX:UseRTMLocking`必须启用该选项。 

- -XX：-TieredCompilation

   禁用分层编译。默认情况下，启用此选项。只有Java HotSpot Server VM支持此选项。 

- -XX：+ UseAES

   为Intel，AMD和SPARC硬件启用基于硬件的AES内在函数。Intel Westmere（2010及更新版本），AMD Bulldozer（2011及更新版本）以及SPARC（T4及更新版本）均为支持的硬件。UseAES与UseAESIntrinsics一起使用。 

- -XX：+ UseAESIntrinsics

   默认情况下启用UseAES和UseAESIntrinsics标志，仅支持Java HotSpot Server VM 32位和64位。要禁用基于硬件的AES内在函数，请指定`-XX:-UseAES -XX:-UseAESIntrinsics`。例如，要启用硬件AES，请使用以下标志： `-XX：+ UseAES -XX：+ UseAESIntrinsics ` 支持使用32位和64位的UseAES和UseAESIntrinsics标志`-server`选项来选择Java HotSpot Server VM。客户端VM不支持这些标志。 

- -XX：+ UseCodeCacheFlushing

   在关闭编译器之前启用刷新代码缓存。默认情况下启用此选项。要在关闭编译器之前禁用刷新代码缓存，请指定`-XX:-UseCodeCacheFlushing`。 

- -XX：+ UseCondCardMark

   在更新卡表之前，可以检查卡是否已经标记。默认情况下禁用此选项，并且只应在具有多个套接字的计算机上使用此选项，从而提高严重依赖并发操作的Java应用程序的性能。只有Java HotSpot Server VM支持此选项。 

- -XX：+ UseRTMDeopt

   根据中止率自动调谐RTM锁定。该比率由`-XX:RTMAbortRatio`选项指定。如果中止事务的数量超过中止率，则包含锁定的方法将被取消优化并重新编译，并将所有锁定为正常锁定。默认情况下禁用此选项。`-XX:+UseRTMLocking`必须启用该选项。 

- -XX：+ UseRTMLocking

   为所有膨胀的锁生成受限制的事务性内存（RTM）锁定代码，使用正常的锁定机制作为回退处理程序。默认情况下禁用此选项。与RTM相关的选项仅适用于支持事务同步扩展（TSX）的x86 CPU上的Java HotSpot Server VM。 RTM是英特尔TSX的一部分，它是x86指令集扩展，有助于创建多线程应用程序。RTM引入了新的指示`XBEGIN`，`XABORT`，`XEND`，和`XTEST`。该`XBEGIN`和`XEND`说明附上一组指令作为一个事务中运行。如果在运行事务时未发现冲突，则内存和寄存器修改将在`XEND`指令处一起提交。该`XABORT`指令可用于显式中止事务以及`XEND`检查是否在事务中运行一组指令的指令。 当另一个线程尝试访问同一事务时，对事务的锁定会膨胀，从而阻止最初未请求访问该事务的线程。RTM要求在事务中止或失败时指定后备操作集。RTM锁是一种委托给TSX系统的锁。 RTM提高了在关键区域中具有低冲突的高竞争锁的性能（这是不能同时由多个线程访问的代码）。RTM还提高了粗粒度锁定的性能，这在多线程应用程序中通常表现不佳。（粗粒度锁定是长时间保持锁定以最小化获取和释放锁定的开销的策略，而细粒度锁定是通过仅在必要时锁定并尽快解锁来尝试实现最大并行性的策略。此外，对于不同线程使用的轻度争用锁，RTM可以减少错误的缓存行共享，也称为缓存行乒乓。当来自不同处理器的多个线程访问不同的资源时会发生 但资源共享相同的缓存行。结果，处理器重复地使其他处理器的高速缓存行无效，这迫使它们从主存储器而不是它们的高速缓存读取。 

- -XX：+ UseSHA

   为SPARC硬件启用SHA加密散列函数的基于硬件的内在函数。`UseSHA`与结合使用`UseSHA1Intrinsics`，`UseSHA256Intrinsics`和`UseSHA512Intrinsics`选项。 在`UseSHA`和`UseSHA*Intrinsics`标志默认情况下启用，并且仅适用于SPARC T4和新的Java HotSpot的服务器虚拟机的64位支持。 此功能仅在使用`sun.security.provider.Sun`SHA操作的提供程序时适用。 要禁用所有基于硬件的SHA内在函数，请指定`-XX:-UseSHA`。要仅禁用特定的SHA内在函数，请使用相应的相应选项。例如：`-XX:-UseSHA256Intrinsics`。 

- -XX：+ UseSHA1Intrinsics

   为SHA-1加密哈希函数启用内在函数。 

- -XX：+ UseSHA256Intrinsics

   为SHA-224和SHA-256加密哈希函数启用内在函数。 

- -XX：+ UseSHA512Intrinsics

   为SHA-384和SHA-512加密散列函数启用内在函数。 

- -XX：+ UseSuperWord

   允许将标量操作转换为超级字操作。默认情况下启用此选项。要禁用将标量操作转换为超级字操作，请指定`-XX:-UseSuperWord`。只有Java HotSpot Server VM支持此选项。 





### 高级可维护性选项

这些选项提供了收集系统信息和执行大量调试的功能。

- -XX：+ ExtendedDTraceProbes

   启用`dtrace`影响性能的其他工具探测。默认情况下，此选项已禁用，`dtrace`仅执行标准探测。 

- -XX：+ HeapDumpOnOutOfMemoryError

   在`java.lang.OutOfMemoryError`抛出异常时，通过使用堆分析器（HPROF）将Java堆转储到当前目录中的文件。您可以使用该`-XX:HeapDumpPath`选项显式设置堆转储文件路径和名称。默认情况下，禁用此选项，并在`OutOfMemoryError`抛出异常时不转储堆。 

- -XX：HeapDumpPath = *path*

   设置`-XX:+HeapDumpOnOutOfMemoryError`选项设置时，设置用于写入堆分析器（HPROF）提供的堆转储的路径和文件名。默认情况下，该文件在当前工作目录中创建，并且名为`java_pid`*pid*`.hprof`，其中*pid*是导致错误的进程的标识符。以下示例显示如何显式设置默认文件（`%p`表示当前进程标识符）： `-XX：HeapDumpPath = / java_pid％p.hprof ` 以下示例显示如何将堆转储文件设置为`/var/log/java/java_heapdump.hprof`： `-XX：HeapDumpPath = /无功/日志/ JAVA / java_heapdump.hprof ` 

- -XX：LogFile = *path*

   设置写入日志数据的路径和文件名。默认情况下，该文件在当前工作目录中创建，并以其命名`hotspot.log`。 以下示例显示如何将日志文件设置为`/var/log/java/hotspot.log`： `-XX：日志文件= /无功/日志/ JAVA / hotspot.log ` 

- -XX：+ PrintClassHistogram

   在`Control+C`事件（`SIGTERM`）之后启用类实例直方图的打印。默认情况下，禁用此选项。 设置此选项等同于运行`jmap -histo`命令或`jcmd` *pid* `GC.class_histogram`命令，其中*pid*是当前Java进程标识符。 

- -XX：+ PrintConcurrentLocks

   `java.util.concurrent`在`Control+C`事件（`SIGTERM`）之后启用锁的打印。默认情况下，禁用此选项。 设置此选项等同于运行`jstack -l`命令或`jcmd` *pid* `Thread.print -l`命令，其中*pid*是当前Java进程标识符。 

- -XX：+ UnlockDiagnosticVMOptions

   解锁用于诊断JVM的选项。默认情况下，此选项已禁用，诊断选项不可用。 





### 高级垃圾收集选项

这些选项控制Java HotSpot VM如何执行垃圾收集（GC）。

-XX：+ AggressiveHeap

启用Java堆优化。根据计算机的配置（RAM和CPU），这会将各种参数设置为具有密集内存分配的长时间运行作业的最佳选择。默认情况下，禁用该选项并且不优化堆。

-XX：+ AlwaysPreTouch

在JVM初始化期间允许触摸Java堆上的每个页面。这会在进入`main()`方法之前将所有页面放入内存中。该选项可用于测试以模拟长时间运行的系统，其中所有虚拟内存都映射到物理内存。默认情况下，禁用此选项，并将所有页面作为JVM堆空间填充提交。

-XX：+ CMSClassUnloadingEnabled

使用并发标记清除（CMS）垃圾收集器时启用类卸载。默认情况下启用此选项。要禁用CMS垃圾收集器的类卸载，请指定`-XX:-CMSClassUnloadingEnabled`。

-XX：CMSExpAvgFactor = *百分比*

设置在计算并发收集统计信息的指数平均值时用于加权当前样本的时间百分比（0到100）。默认情况下，指数平均值因子设置为25％。以下示例显示如何将因子设置为15％：

```
-XX：CMSExpAvgFactor = 15
```

-XX：CMSInitiatingOccupancyFraction = *百分比*

设置启动CMS收集周期的旧代占用率（0到100）的百分比。默认值设置为-1。任何负值（包括默认值）都意味着`-XX:CMSTriggerRatio`用于定义初始占用率的值。

以下示例显示如何将占用率设置为20％：

```
-XX：CMSInitiatingOccupancyFraction = 20
```

-XX：+ CMSScavengeBeforeRemark

在CMS备注步骤之前启用清理尝试。默认情况下，禁用此选项。

-XX：CMSTriggerRatio = *百分比*

设置在`-XX:MinHeapFreeRatio`CMS收集周期开始之前分配的值所指定的值的百分比（0到100）。默认值设置为80％。

以下示例显示如何将占用率设置为75％：

```
-XX：CMSTriggerRatio = 75
```

-XX：ConcGCThreads = *threads*

设置用于并发GC的线程数。默认值取决于JVM可用的CPU数。

例如，要将并发GC的线程数设置为2，请指定以下选项：

```
-XX：ConcGCThreads = 2
```

-XX：+ DisableExplicitGC

启用禁用处理呼叫的选项`System.gc()`。默认情况下禁用此选项，这意味着`System.gc()`将处理调用。如果`System.gc()`禁用了对调用的处理，则JVM在必要时仍会执行GC。

-XX：+ ExplicitGCInvokesConcurrent

允许使用`System.gc()`请求调用并发GC 。默认情况下禁用此选项，并且只能与该`-XX:+UseConcMarkSweepGC`选项一起启用。

-XX：+ ExplicitGCInvokesConcurrentAndUnloadsClasses

通过`System.gc()`在并发GC周期期间使用请求和卸载类，可以调用并发GC。默认情况下禁用此选项，并且只能与该`-XX:+UseConcMarkSweepGC`选项一起启用。

-XX：G1HeapRegionSize = *size*

设置使用垃圾优先（G1）收集器时Java堆所细分的区域的大小。该值可以介于1 MB和32 MB之间。默认区域大小根据堆大小以符合人体工程学的方式确定。

以下示例显示如何将细分的大小设置为16 MB：

```
-XX：G1HeapRegionSize =16米
```

-XX：+ G1PrintHeapRegions

允许打印有关哪些区域已分配以及哪些区域由G1收集器回收的信息。默认情况下，禁用此选项。

-XX：G1ReservePercent = *百分比*

设置保留为false上限的堆的百分比（0到50），以减少G1收集器升级失败的可能性。默认情况下，此选项设置为10％。

以下示例显示如何将保留堆设置为20％：

```
-XX：G1ReservePercent = 20
```

-XX：InitialHeapSize = *size*

### 已弃用和已删除的选项

这些选项包含在之前的版本中，但后来被认为是不必要的。

- -Xincgc

   启用增量垃圾收集。此选项在JDK 8中已弃用，无需替换。 

- -Xrun *libname*

   加载指定的调试/分析库。此选项已被该选项取代`-agentlib`。 

- -XX：CMSIncrementalDutyCycle = *百分比*

   设置允许并发收集器运行的次要集合之间的时间百分比（0到100）。在弃用选项后，此选项在JDK 8中已弃用，没有替换`-XX:+CMSIncrementalMode`。 

- -XX：CMSIncrementalDutyCycleMin = *百分比*

   设置次要集合之间的时间百分比（0到100），它`-XX:+CMSIncrementalPacing`是启用时占空比的下限。在弃用选项后，此选项在JDK 8中已弃用，没有替换`-XX:+CMSIncrementalMode`。 

- -XX：+ CMSIncrementalMode

   启用CMS收集器的增量模式。此选项在JDK 8中已弃用，没有替换，以及其他选项`CMSIncremental`。 

- -XX：CMSIncrementalOffset = *百分比*

   设置增量模式占空比在次要集合之间的时间段内向右移动的时间百分比（0到100）。在弃用选项后，此选项在JDK 8中已弃用，没有替换`-XX:+CMSIncrementalMode`。 

- -XX：+ CMSIncrementalPacing

   根据JVM运行时收集的统计信息，启用增量模式占空比的自动调整。在弃用选项后，此选项在JDK 8中已弃用，没有替换`-XX:+CMSIncrementalMode`。 

- -XX：CMSIncrementalSafetyFactor = *百分比*

   设置计算占空比时用于添加保守性的时间百分比（0到100）。在弃用选项后，此选项在JDK 8中已弃用，没有替换`-XX:+CMSIncrementalMode`。 

- -XX：CMSInitiatingPermOccupancyFraction = *百分比*

   设置启动GC的永久生成占用率（0到100）的百分比。此选项在JDK 8中已弃用，无需替换。 

- -XX：MaxPermSize = *size*

   设置最大永久生成空间大小（以字节为单位）。此选项在JDK 8中已弃用，并由该`-XX:MaxMetaspaceSize`选项取代。 

- -XX：PermSize = *size*

   设置分配给永久生成的空间（以字节为单位），如果超出则会触发垃圾回收。此选项在JDK 8中已弃用，并被该`-XX:MetaspaceSize`选项取代。 

- -XX：+ UseSplitVerifier

   允许拆分验证过程。默认情况下，此选项在先前版本中已启用，验证分为两个阶段：类型引用（由编译器执行）和类型检查（由JVM运行时执行）。此选项在JDK 8中已弃用，现在默认情况下会对验证进行拆分，而无法将其禁用。 

- -XX：+ UseStringCache

   启用常用分配字符串的缓存。此选项已从JDK 8中删除，无需替换。 