## Docker

Docker包括三个基本概念

- 镜像（Image）
- 容器（Container）
- 仓库（Repository）

#### Docker镜像

Docker镜像就是一个只读的模板（可以是一个完整的操作系统）。

镜像可以用来创建Docker容器。

Docker提供了一个很简单的机制来创建镜像或者更新现有的镜像，用户甚至可以直接从其他人那里下载一个已经做好的镜像来直接使用。

Docker运行容器前需要本地存在对应的镜像，如果镜像不存在本地，Docker会从镜像仓库下载（默认是Docker Hub公共注册服务器中的仓库）。

#### Docker容器

Docker是利用容器来运行应用的。

容器是从镜像创建的运行实例。它可以被启动、开始、停止、删除。每个容器都是相互隔离的、保证安全的平台 。

可以把容器看做是一个简易版的Linux环境（包括Root用户权限、进程空间、用户空间和网络空间等）和运行在其中的应用程序 。

**注：镜像是只读的，容器在启动的时候创建一层可写层作为最上层。**

#### Docker仓库

Dokcer仓库是集中存放镜像文件的场所。有时候会把仓库和仓库注册服务器（Registry）混为一谈，并不严格区分。实际上，仓库注册服务器上往往存放着多个仓库，每个仓库中又包含了多个镜像，每个镜像有不同的标签（Tag）。

仓库分为公开仓库（Public）和私有仓库（Private）两种形式。

最大的公开仓库是Docker Hub，存放了数量庞大的镜像供用户下载。 国内的公开仓库包括Docker Pool
等，可以提供大陆用户更稳定快速的访问。

当然，用户也可以在本地网络内创建一个私有仓库。

当用户创建了自己的镜像之后就可以使用 push命令将它上传到公有或者私有仓库，这样下次在另外一台
机器上使用这个镜像时候，只需要从仓库上pull下来就可以了。
**注：Docker仓库的概念跟Git类似，注册服务器可以理解为GitHub这样的托管服务。**

------

##### Docker镜像

1. 获取镜像

   ```sh
   docker pull <镜像名>
   如：docker pull ubuntu:12.04
   ```

   若使用其它仓库下载：

   ```sh
   docker pull <域名>/<镜像名>
   如：docker pull dl.dockerpool.com:5000/ubuntu:12.04
   ```

2.  列出本地镜像

   ```sh
   docker images
   ```

   列表中几个字段信息

   REPOSITORY : 来自于哪个仓库。

   TAG : 镜像的标记。如果运行不指定标记，则默认使用latest标记信息。

   IMAGE ID : 它的ID号（唯一）。不同的TAG，只要ID一致，就是同一镜像。

   CREATED : 创建时间。

   VIRTURAL SIZE : 镜像大小。



### 附录：

**管理命令:**

 container  管理容器

 image    管理镜像

 network   管理网络

**命令：**

 attach    介入到一个正在运行的容器

 build    根据 Dockerfile 构建一个镜像

 commit    根据容器的更改创建一个新的镜像

 cp      在本地文件系统与容器中复制 文件/文件夹

 create    创建一个新容器

 exec     在容器中执行一条命令

```bash
# 进入容器shell
docker exec -it <container-id> /bin/bash
```

 images    列出镜像

 kill     杀死一个或多个正在运行的容器   

 logs     取得容器的日志

 pause    暂停一个或多个容器的所有进程

 ps      列出所有容器

 pull     拉取一个镜像或仓库到 registry

 push     推送一个镜像或仓库到 registry

 rename    重命名一个容器

 restart   重新启动一个或多个容器

 rm      删除一个或多个容器

 rmi     删除一个或多个镜像

 run     在一个新的容器中执行一条命令

 search    在 Docker Hub 中搜索镜像

 start    启动一个或多个已经停止运行的容器

 stats    显示一个容器的实时资源占用

 stop     停止一个或多个正在运行的容器

 tag     为镜像创建一个新的标签

 top     显示一个容器内的所有进程

 unpause   恢复一个或多个容器内所有被暂停的进程

**更详细的功能参数配置**

| 参数                                      | 解释                                                         |
| ----------------------------------------- | ------------------------------------------------------------ |
| --api-enable-cors=false                   | 开放远程API调用的 CORS 头信息。这个接口开关对想进行二次开发的上层应用提供了支持. |
| -b, --bridge=""                           | 挂载已经存在的网桥设备到 Docker 容器里。注意，使用 none可以停用容器里的网络. |
| --bip=""                                  | 使用 CIDR 地址来设定网络桥的 IP。注意，此参数和 -b 不能一起使用. |
| -D, --debug=false                         | 开启Debug模式。例如：docker -d -D                            |
| -d, --daemon=false                        | 开启Daemon模式.                                              |
| --dns=[]                                  | 强制容器使用DNS服务器.例如： docker -d --dns 8.8.8.8         |
| --dns-search=[]                           | 强制容器使用指定的DNS搜索域名.例如： docker -d --dns-searchexample.com |
| -e, --exec-driver="native"                | 强制容器使用指定的运行时驱动.例如：docker -d -e lxc          |
| -G, --group="docker"                      | 在后台运行模式下，赋予指定的Group到相应的unix socket上。注意，当此参数 --group 赋予空字符串时，将去除组信息。 |
| -g, --graph="/var/lib/docker"             | 配置Docker运行时根目录                                       |
| -H, --host=[]                             | 在后台模式下指定socket绑定，可以绑定一个或多个 tcp://host:port, unix:///path/to/socket, fd://* 或 fd://socketfd。例如：$ docker -H tcp://0.0.0.0:2375 ps或者 $ export DOCKER_HOST="tcp://0.0.0.0:2375" $ docker ps |
| --icc=true                                | 启用内联容器的通信.                                          |
| --ip="0.0.0.0"                            | 容器绑定IP时使用的默认IP地址.                                |
| --ip-forward=true                         | 启动容器的 net.ipv4.ip_forward.                              |
| --iptables=true                           | 启动Docker容器自定义的iptable规则.                           |
| --mtu=0                                   | 设置容器网络的MTU值，如果没有这个参数，选用默认 route MTU，如果没有默认route，就设置成常量值 1500. |
| -p, --pidfile="/var/run/docker.pid"       | 后台进程PID文件路径.                                         |
| -r, --restart=true                        | 重启之前运行中的容器.                                        |
| -s, --storage-driver=""                   | 强制容器运行时使用指定的存储驱动，例如,指定使用devicemapper,可以这样：docker -d -s devicemapper |
| --selinux-enabled=false                   | 启用selinux支持                                              |
| --storage-opt=[]                          | 配置存储驱动的参数                                           |
| --tls=false                               | 启动TLS认证开关                                              |
| --tlscacert="/Users/dxiao/.docker/ca.pem" | 通过CA认证过的的certificate文件路径                          |
| --tlscert="/Users/dxiao/.docker/cert.pem" | TLS的certificate文件路径                                     |
| --tlskey="/Users/dxiao/.docker/key.pem"   | TLS的key文件路径                                             |
| --tlsverify=false                         | 使用TLS并做后台进程与客户端通讯的验证                        |
| -v, --version=false                       | 显示版本信息                                                 |

 

*注意：其中带有[] 的启动参数可以指定多次，例如

docker run -a stdin -a stdout -a stderr -i -t ubuntu /bin/bash

### docker基本

· 查看系统内核

· uname -r

· 启动docker 境像

· systemctl start docker

· 3.查看docker版本

· docker verison

· 4.显示docker系统的信息

· docker info

### 操作docker镜像

1.检索image

docker search image-name

2.下载image

docker pull image-name

3.列出镜像列表

docker images

4.删除一个或者多个镜像

docker rmi image-name

5.显示一个镜像的历史

docker history image-name  

6.通过容器创建镜像

*从已经创建的容器中更新镜像，并且提交这个镜像 *使用 Dockerfile 指令来创建一个新的镜像 下面通过已存在的容器创建一个新的镜像。

docker commit -m="First Image" -a="keke" 7a15f99695c0 keke/unbantu:17.10.0

上面命令参数说明：

\* -m 提交的描述信息

\* -a 指定镜像作者

\* 7a15f99695c0 记住这个是容器id，不是镜像id

\* keke/unbantu:17.10.0 创建的目标镜像名

\1. 在[Docker](https://www.docker.com/) 注册账户，发布的镜像都在[这个页面里](https://cloud.docker.com/repository/list)展示

\2. 将上面做的镜像unbantu，起个新的名字unbantu-test

docker tag keke/unbantu:17.10.0 keke/unbantu-test:lastest

\1. 登录docker

docker login

4.上传unbantu镜像

docker push keke/unbantu-test:lastest

### 启动容器

docker容器可以理解为在沙盒中运行的进程。这个沙盒包含了该进程运行所必须的资源，包括文件系统、系统类库、shell 环境等等。但这个沙盒默认是不会运行任何程序的。你需要在沙盒中运行一个进程来启动某一个容器。这个进程是该容器的唯一进程，所以当该进程结束的时候，容器也会完全的停止。

1.在容器中安装新的程序

docker run image-name apt-get install -y -name

2.在容器中运行"echo"命令，输出"hello word"

docker run image-name echo "hello word"

3.交互式进入容器中

docker run -i -t image_name /bin/bash  

注意:在执行apt-get 命令的时候，要带上-y参数。如果不指定-y参数的话，apt-get命令会进入交互模式，需要用户输入命令来进行确认，但在docker环境中是无法响应这种交互的。apt-get 命令执行完毕之后，容器就会停止，但对容器的改动不会丢失.

### 查看容器

1.列出当前所有正在运行的container

docker ps

2.列出所有的container

docker ps -a  

3.列出最近一次启动的container

docker ps -l  

4.保存对容器的修改 当你对某一个容器做了修改之后（通过在容器中运行某一个命令），可以把对容器的修改保存下来，这样下次可以从保存后的最新状态运行该容器。

1.保存对容器的修改; -a, --author="" Author; -m, --message="" Commit message

docker commit ID new-image-name

5.操作容器

1.删除所有容器

docker rm `docker ps -a -q`

2.删除单个容器; -f, --force=false; -l, --link=false Remove the specified link and not the underlying container; -v, --volumes=false Remove the volumes associated to the container

docker rm Name/ID

3.停止、启动、杀死一个容器

docker stop Name/ID  

docker start Name/ID  

docker kill Name/ID

4.从一个容器中取日志; -f, --follow=false Follow log output; -t, --timestamps=false Show timestamps

docker logs Name/ID  

5.列出一个容器里面被改变的文件或者目录，list列表会显示出三种事件，A 增加的，D 删除的，C 被改变的

docker diff Name/ID

6.显示一个运行的容器里面的进程信息

docker top Name/ID  

7.从容器里面拷贝文件/目录到本地一个路径

docker cp Name:/container-path to-path  

docker cp ID:/container-path to-path

8.重启一个正在运行的容器; -t, --time=10 Number of seconds to try to stop for before killing the container, Default=10

docker restart Name/ID

9.附加到一个运行的容器上面; --no-stdin=false Do not attach stdin; --sig-proxy=true Proxify all received signal to the process

docker attach ID #重新启动并运行一个交互式会话shell

注意：attach命令允许你查看或者影响一个运行的容器。你可以在同一时间attach同一个容器。你也可以从一个容器中脱离出来，是从CTRL-C.

### 保存和加载镜像

当需要把一台机器上的镜像迁移到另一台机器的时候，需要保存镜像与加载镜像。

1.保存镜像到一个tar包; -o, --output="" Write to an file

docker save image-name -o file-path

2.加载一个tar包格式的镜像; -i, --input="" Read from a tar archive file

docker load -i file-path

3.从机器A拷贝到机器B

docker save image-name > /home/keke/main.tar

*使用scp将main.tar拷到机器A上:

docker load < /home/keke/main.tar

### 登录

1.登陆registry server; -e, --email="" Email; -p, --password="" Password; -u, --username="" Username

docker login

### 发布docker镜像

docker push new-image-name

### 构建镜像(Dockerfile + docker build)

FROM ...

RUN ...

\# 指定容器内的程序将会使用容器的指定端口

\# 配合 docker run -p

EXPOSE ...

· RUN: 指定镜像被构建时要运行的命令

· CMD: 指定容器被启动时要运行的命令

· ENTRYPOINT: 同 CMD ，但不会被 docker run -t 覆盖

· WORKDIR: CMD/ENTRYPOINT 会在这个目录下执行

· VOLUME

· ADD

· COPY

docker history images-name

1.从新镜像启动容器

docker run -d -p 4000:80 --name [name] #可以在 Dokcer 宿主机上指定一个具体的端口映射到容器的80端口上

### 守护容器

docker run -d container-name #创建守护容器

docker top container-name #查看容器内进程

docker exec container-name touch a.txt #在容器内部运行进程

docker stop container-name #停止容器



## Docker 安装Mysql 5.7

1. 拉取镜像

```sh
docker pull mysql:5.7
```

2. 在/etc下新建my.cnf配置文件，如果有的话先删除原来的，再加入创建新的

```php
[mysql]
#设置mysql客户端默认字符集
default-character-set=utf8
socket=/var/lib/mysql/mysql.sock

[mysqld]
#mysql5.7以后的不兼容问题处理
sql_mode=NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES
datadir=/var/lib/mysql
socket=/var/lib/mysql/mysql.sock
# Disabling symbolic-links is recommended to prevent assorted security risks
symbolic-links=0
# Settings user and group are ignored when systemd is used.
# If you need to run mysqld under a different user or group,
# customize your systemd unit file for mariadb according to the
# instructions in http://fedoraproject.org/wiki/Systemd
#允许最大连接数
max_connections=200
#服务端使用的字符集默认为8比特编码的latin1字符集
character-set-server=utf8
#创建新表时将使用的默认存储引擎
default-storage-engine=INNODB
lower_case_table_names=1
max_allowed_packet=16M 
#设置时区
default-time_zone='+8:00'
[mysqld_safe]
log-error=/var/log/mariadb/mariadb.log
pid-file=/var/run/mariadb/mariadb.pid

#
# include all files from the config directory
#
!includedir /etc/mysql/conf.d/
!includedir /etc/mysql/mysql.conf.d/
```

3. 运行

```jsx
docker run --name="mysql-5.7" -p 3306:3306 --privileged=true -v /usr/local/docker/mysql/logs:/logs -v /usr/local/docker/mysql/data:/var/lib/mysql -v /etc/my.cnf:/etc/mysql/mysql.conf.d/mysqld.cnf -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7
```

--privileged=true 获取临时的selinux的权限
 -v 宿主机目录挂在到容器的地址，映射
 -e MYSQL_ROOT_PASSWORD 初始化密码

---

```sh
# 查询镜像
docker search mysql
# 拉取镜像
docker pull mysql:latest
# 查看本地镜像
docker images
# 运行容器
docker run -itd --name mysql-test -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 mysql
# 查看容器
docker ps
# 进入容器
docker exec -it mysql-test bash
```

