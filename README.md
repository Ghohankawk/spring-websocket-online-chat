# spring-websocket-online-chat

## 项目介绍

基于spring+websocket的java版本简单在线聊天室，支持相互之间的公屏聊天，以及管理员全局的发送通知。


### 技术选型

 后端技术
- spring
- websocket

## 项目运行

1. 下载代码用idea打开该项目

```
git clone https://github.com/Ghohankawk/spring-websocket-online-chat.git

```

2. pom.xml导入依赖的jar包

3. 配置容器如tomcat等


## 请求实例

1. 分别打开多个浏览器页面，模仿不同的用户登录，打开下面的地址
```
http://localhost:8080/springwebsocket/
```
2. 分别用不同的用户名来说话，点击发送，看各个页面收发的信息内容

3. 管理员全局发送通知，此时所有在线的用户都能收到这条信息
```
http://localhost:8080/springwebsocket/notice?content=helloworld
```
