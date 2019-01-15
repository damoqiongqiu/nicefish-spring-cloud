<p align="center">
    <img width="150" src="./doc/nice-fish.png">
</p>

<h1 align="center">NiceFish</h1>

<div align="left">
NiceFish（美人鱼） 是一个系列项目，目标是全面示范 Angular 在浏览器、移动端、Electron 环境中的用法，同时提供了一个基于 SpringCloud 的服务端。
</div>

## 系列项目

* NiceFish：美人鱼，这是一个微型 Blog 系统，前端基于 Angular 7.0 + PrimeNG 7.0.3。http://git.oschina.net/mumu-osc/NiceFish/

* nicefish-ionic：这是一个移动端的 demo，基于 ionic，此项目已支持 PWA。http://git.oschina.net/mumu-osc/nicefish-ionic

* NiceBlogElectron：https://github.com/damoqiongqiu/NiceBlogElectron ,这是一个基于 Electron 的桌面端项目，把 NiceFish 用 Electron 打包成了一个桌面端运行的程序。这是由 ZTE 中兴通讯的前端道友提供的，我 fork 了一个，有几个 node 模块的版本号老要改，如果您正在研究如何利用 Electron 开发桌面端应用，请参考这个项目。

* nicefish-spring-cloud: https://gitee.com/mumu-osc/nicefish-spring-cloud ， 这是NiceFish的服务端代码，基于SpringCloud。已经完成了一些基本的功能，如 SpringSecurity+OAuth2+JWT 实现SSO，文章、用户、评论等的分页查询等。如果你需要与这个后端代码进行对接，请检出本项目的 for-spring-cloud 分支。

## NiceFish-Spring-Cloud

本项目是NiceFish的服务端代码，已经完成的功能有：

- 利用Consul进行服务注册和发现
- 利用SpringSecurity+OAuth2+JWT实现SSO
- 文章管理（列表分页查询、新增文章）
- 评论管理（列表分页查询）
- 用户管理（注册、登录、SSO）

用到的主要模块：
- spring-cloud-oauth2（已经内置依赖了spring-cloud-starter-security、spring-security-oauth2、spring-security-jwt）
- spring-boot-starter-data-jpa
- mysql-connector-java

## 使用方法

- 安装配置好Consul（默认HTTP端口是8500），以dev的方式启动agent，官方文档：https://www.consul.io/
- 克隆项目到你的本地：git clone https://gitee.com/mumu-osc/nicefish-spring-cloud.git
- 在你本地的MySQL里面执行/doc/nicefish.sql
- 用IDEA打开项目
- 启动所有模块（无顺序）：NiceFishBlogApplication.java、NiceFishUserCenterApplication.java、NiceFishOAuthApplication.java
- 使用Postman或者NiceFish的前端项目来测试后端接口（OAuth服务起在9001端口，用户中心模块起在9002端口，blog相关的模块起在9003端口，内置了一个测试账号damoqiongqiu@126.com，密码12345678，密码对应的MD5是25d55ad283aa400af464c76d713c07ad）
-  NiceFish的代码在这里，里面有完整的使用说明：https://gitee.com/mumu-osc/NiceFish ，NiceFish与本项目对接的代码位于for-spring-cloud分支上。

## Maven Module 模块功能和依赖关系

- nicefish-spring-cloud：这是root项目，通用的依赖都定义在这个项目的pom.xml中，子Module会自动继承这里的依赖关系。
- nicefish-user-center：这是用户中心模块，它是独立的不依赖其它子模块。
- nicefish-blog：这里实现blog相关的功能，如文章和评论等，依赖nicefish-user-center模块中的配置和UserEntity等。
- nicefish-oauth2-jwt：这里实现OAuth和JWT相关的功能，依赖nicefish-user-center模块中的UserEntity和Repository等。
- nicefish-swagger2-api：这里是所有API文档的总入口，依赖nicefish-user-center、nicefish-blog、nicefish-oauth2-jwt模块，解析并生成API文档，访问地址是 http://localhost:9004/swagger-ui.html#/

## 特别注意

- ** 此项目在 SpringBoot 1.5.9.RELEASE 和 SpringCloud Edgware.RELEASE 测试通过，其它所有版本都未经测试。（SpringBoot和SpringCloud存在版本对应关系，版本升级可能需要修改非常多的琐碎细节。如果您需要升级版本，请仔细查阅Spring官方的文档，以免浪费大量时间。）**
- ** 项目本身的代码是独立的，没有与任何前端技术绑定，因此您可以使用任意前端技术接入。 **

## 效果截图

<img width="80%" src="./doc/1.png">

<img width="80%" src="./doc/2.png">

<img width="80%" src="./doc/3.png">

<img width="80%" src="./doc/4.png">

<img width="80%" src="./doc/5.png">

<img width="80%" src="./doc/6.png">

## TODO

已经列入计划的功能：
- 尝试升级SpringBoot到2.x，升级SpringCloud到F版本
- 继续修改前端NiceFish的代码，美化界面、完善功能
- 引入Redis缓存
- 引入Consul进行服务注册和发现
- 引入Feign进行负载均衡

## 关联 QQ 群

<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=8db5ed802cbddbf6432d7ba7dc4f2a316be020442491eb41cbfb1a12434e8cc7" class="list-group-item"><i class="fa fa-qq" aria-hidden="true"></i> Angular-1区</a>

<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=cbfcd79e7e90939b0e2c519f475fac4792985ce2abc5ad45ec5e06ffcfe944dd" class="list-group-item"><i class="fa fa-qq" aria-hidden="true"></i> Angular-2区</a>

<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=639229c8b6ad0c3a9a8f381dddf5d7785780b20d8c37eb25c91ac73ea7d37a5f" class="list-group-item"><i class="fa fa-qq" aria-hidden="true"></i> Angular-3区</a>

<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=12add102af3f67910bdc0de753dee10ebada08ab485af7e38f4dfa0ee27476f7" class="list-group-item"><i class="fa fa-qq" aria-hidden="true"></i> Angular-4区</a>

<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=1293a6494fb306ea29d281e320a8f4ef82285fa5300f73118e6ff7a79ce76036" class="list-group-item"><i class="fa fa-qq" aria-hidden="true"></i>Angular-5区</a>

<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=fcd880ba919983dc85690642d48cf00ad0affd8d35de5f30542c895e622a8ab8" class="list-group-item"><i class="fa fa-qq" aria-hidden="true"></i>Angular-6区
</a>

<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=5d6b8c5296e4806142b8422ae7abca6f27b9b9b992a4dac80dc1392644e8970a"><i class="fa fa-qq" aria-hidden="true"></i>脚本娃娃-桃花岛</a>

## License

MIT

（你可以随意使用此项目，无需通知我，本人不对您使用此代码产生的任何收益或者风险承担任何责任。）
