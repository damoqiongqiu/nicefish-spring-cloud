# NiceFish-Spring-Cloud

### 简介
-----------------

这是NiceFish的服务端代码，已经完成的功能有：

- 利用SpringSecurity+OAuth2+JWT实现SSO
- 文章管理
- 评论管理
- 用户管理（注册、登录、SSO）

用到的主要模块：
- spring-cloud-oauth2（已经内置依赖了spring-cloud-starter-security、spring-security-oauth2、spring-security-jwt）
- spring-boot-starter-data-jpa
- mysql-connector-java

### 使用方法
-----------------

- 克隆项目到你的本地：git clone https://gitee.com/mumu-osc/nicefish-spring-cloud.git
- 在你本地的MySQL里面执行/doc/nicefish.sql
- 用IDEA打开项目
- 启动3个模块（任意顺序）：NiceFishBlogApplication.java、NiceFishUserCenterApplication.java、NiceFishOAuthApplication.java
- 使用Postman或者NiceFish的前端项目来测试后端接口（OAuth服务起在9001端口，用户中心模块起在9002端口，blog相关的模块起在9003端口）
-  NiceFish的代码在这里，里面有完整的使用说明：https://gitee.com/mumu-osc/NiceFish ，NiceFish与本项目对接的代码位于for-spring-cloud分支上。

### 特别注意
-----------------

- 此项目在 SpringBoot 1.5.9.RELEASE 和 SpringCloud Edgware.RELEASE 测试通过，其它所有版本都未经测试。（SpringBoot和SpringCloud存在版本对应关系，大版本升级可能需要修改非常多的琐碎细节。如果您需要升级版本，请仔细查阅Spring官方的文档，以免浪费大量时间。）
- 项目本身的代码是独立的，没有与任何前端技术绑定，因此您可以使用任意前端技术接入。

### 效果截图
-----------------

<img src="./doc/1.png">

<img src="./doc/2.png">

<img src="./doc/3.png">

<img src="./doc/4.png">

### TODO
-----------------

已经列入计划的功能：
- 尝试升级SpringBoot到2.x，升级SpringCloud到F版本
- 继续修改前端NiceFish的代码，美化界面、完善功能
- 引入Redis缓存
- 引入Consul进行服务注册和发现
- 引入Feign进行负载均衡

### 关联 QQ 群
-----------------
<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=8db5ed802cbddbf6432d7ba7dc4f2a316be020442491eb41cbfb1a12434e8cc7" class="list-group-item"><i class="fa fa-qq" aria-hidden="true"></i> Angular-1区</a>

<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=cbfcd79e7e90939b0e2c519f475fac4792985ce2abc5ad45ec5e06ffcfe944dd" class="list-group-item"><i class="fa fa-qq" aria-hidden="true"></i> Angular-2区</a>

<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=639229c8b6ad0c3a9a8f381dddf5d7785780b20d8c37eb25c91ac73ea7d37a5f" class="list-group-item"><i class="fa fa-qq" aria-hidden="true"></i> Angular-3区</a>

<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=12add102af3f67910bdc0de753dee10ebada08ab485af7e38f4dfa0ee27476f7" class="list-group-item"><i class="fa fa-qq" aria-hidden="true"></i> Angular-4区</a>

<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=1293a6494fb306ea29d281e320a8f4ef82285fa5300f73118e6ff7a79ce76036" class="list-group-item"><i class="fa fa-qq" aria-hidden="true"></i>Angular-5区</a>

<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=fcd880ba919983dc85690642d48cf00ad0affd8d35de5f30542c895e622a8ab8" class="list-group-item"><i class="fa fa-qq" aria-hidden="true"></i>Angular-6区
</a>

<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=5d6b8c5296e4806142b8422ae7abca6f27b9b9b992a4dac80dc1392644e8970a"><i class="fa fa-qq" aria-hidden="true"></i>脚本娃娃-桃花岛</a>

### License
-----------------
MIT

（你可以随意使用此项目，无需通知我，本人不对您使用此代码产生的任何收益或者风险承担任何责任。）
