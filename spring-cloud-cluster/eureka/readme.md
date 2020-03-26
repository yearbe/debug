###Dockerfile构建镜像

1. 创建DOCKERFILE文件
```
# FROM java:8是指Docker Hub上官方提供的java镜像，版本号是8也就是jdk1.8，有了这个基础镜像后，Dockerfile可以通过FROM指令直接获取它的状态——也就是在容器中java是已经安装的，接下来通过自定义的命令来运行Spring Boot应用
# VOLUME /tmp创建/tmp目录并持久化到Docker数据文件夹，因为Spring Boot使用的内嵌Tomcat容器默认使用/tmp作为工作目录
# ADD spring-boot-docker-0.1.0.jar app.jar 将应用jar包复制到/app.jar
# ENTRYPOINT表示容器运行后默认执行的命令
FROM java:8
VOLUME /tmp
ADD spring-boot-docker-0.1.0.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
```
2. 后运行docker构建镜像命令
```
docker build -t tmy/spring-boot-app .
```
3. 查看系统中的docker镜像：
```
docker images
docker images -q # 列出镜像ID
docker rmi $(docker images -q) # 删除所有镜像
docker image prune -f -a # 删除所有不用的镜像
docker container prune # 删除所有停止的容器
```
4. 运行Docker容器
```
# 其中-d表示后台运行容器，这也就自然地解决的Spring Boot不支持后台运行应用程序的问题。
# -p 8080:8080表示将容器内部的8080端口映射到宿主机器的8080端口，这样就可以通过宿主机器直接访问应用。
# --name sample-app给容器取一个容易记住的名字方便日后管理。
docker run -d -p 8080:8080 --name sample-app tmy/spring-boot-app
```
5. 查看运行中镜像
```
docker ps
docker ps -aq # 列出所有
```
6. 停止docker容器
```
docker stop $CONTAINER_ID
docker stop $(docker ps -aq) # 停止所有
```
7. 删除docker容器
```
docker rm $CONTAINER_ID
docker rm $(docker ps -aq) # 删除所有
```

###使用Maven/Gradle创建Docker镜像
**Maven：**
```
<!-- 
pom.xml包含了docker-maven-plugin的配置：
    imageName指定了镜像的名字
    dockerDirectory指定Dockerfile的位置
    resources是指那些需要和Dockerfile放在一起，在构建镜像时使用的文件，一般应用jar包需要纳入
-->
<properties>
    <docker.image.prefix>springio</docker.image.prefix>
</properties>
<build>
    <plugins>
        <plugin>
            <groupId>com.spotify</groupId>
            <artifactId>docker-maven-plugin</artifactId>
            <version>1.2.0</version>
            <configuration>
                <imageName>${docker.image.prefix}/${project.artifactId}</imageName>
                <dockerDirectory>src/main/docker</dockerDirectory>
                <resources>
                    <resource>
                        <targetPath>/</targetPath>
                        <directory>${project.build.directory}</directory>
                        <include>${project.build.finalName}.jar</include>
                    </resource>
                </resources>
            </configuration>
        </plugin>
    </plugins>
</build>
```
运行下列命令可以在本地Docker中创建一个镜像：
```
mvn clean package docker:build -DskipTests
```

**Gradle：**
```
buildscript {
    ...
    dependencies {
        ...
        classpath('se.transmode.gradle:gradle-docker:1.2')
    }
}

group = 'springio'

...
apply plugin: 'docker'

task buildDocker(type: Docker, dependsOn: build) {
  push = true
  applicationName = jar.baseName
  dockerfile = file('src/main/docker/Dockerfile')
  doFirst {
    copy {
      from jar
      into stageDir
    }
  }
}
```
使用以下命令构建镜像：
```
gradle build buildDocker
```