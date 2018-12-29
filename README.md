# micro-framework
micro service base framework

# spring cloud

## config server

### 仓库配置
    application-profile-label.properties
#### Git
#### VCS (git,svn)
#### 文件系统
    spring.cloud.config.server.native.searchLocations=
    spring.profiles.active=native
    file:///${user.home}/config-repo
#### Vault
    https://www.vaultproject.io/
    于第三方存储
#### 代理（代理访问GIT/Vault）
    proxy.http
    proxy.https
#### 共享配置信息
    文件仓库
        git,svn,native
        application* (application.properties,application.yml,application-*.properties)
    vault服务器
#### JDBC
    JdbcEnvironmentRepository
#### 集成仓库
    spring:
      profiles:
        active: composite
      cloud:
        config:
          server:
            composite:
            -
              type: svn
              uri: file:///path/to/svn/repo
            -
              type: git
              uri: file:///path/to/rex/git/repo
            -
              type: git
              uri: file:///path/to/walter/git/repo
#### 属性覆盖
    spring:
      cloud:
        config:
          server:
            overrides:
              foo: bar
### 健康检测
    pring:
      cloud:
        config:
          server:
            health:
              repositories:
                myservice:
                  label: mylabel
                myservice-dev:
                  name: myservice
                  profiles: development#
### 安全
### 加密解密
### 密钥管理
### 创建测试密钥对
### 多密钥和密钥旋转
### 内置config服务
    @EnableConfigServer
    程序内部
    spring:
      application:
        name: configserver
      profiles:
        active: composite
      cloud:
        config:
          server:
            composite:
              - type: native
                search-locations: ${HOME}/Desktop/config
            bootstrap: true
## config客户端
    spring.cloud.config.uri
    spring.cloud.config.fail-fast=true

    配置重试
    spring.cloud.config.fail-fast=true
    依赖
    spring-retry
    spring-boot-starter-aop
    配置
    spring.cloud.config.retry.*


    /{name}/{profile}/{label}
    "name" = ${spring.application.name}
    "profile" = ${spring.profiles.active} (actually Environment.getActiveProfiles())
    "label" = "master"
    spring.cloud.config.label=myfeature,develop

    读取超时
    spring.cloud.config.request-read-timeout

    安全
    spring:
      cloud:
        config:
         uri: ${vcap.services.configserver.credentials.uri:http://user:password@localhost:8888}
    健康检测
    health.config.enabled=false
    health.config.time-to-live

    自定义RestTemplate
    CustomConfigServiceBootstrapConfiguration
    resources/META-INF
    org.springframework.cloud.bootstrap.BootstrapConfiguration = com.my.config.client.CustomConfigServiceBootstrapConfiguration


