Finchley.SR2
## 服务发现：Eureka

### Eureka Clients
    spring-cloud-starter-netflix-eureka-client
    注册
    @SpringBootApplication
    @RestController
    public class Application {

        @RequestMapping("/")
        public String home() {
            return "Hello world";
        }

        public static void main(String[] args) {
            new SpringApplicationBuilder(Application.class).web(true).run(args);
        }

    }
    配置
    eureka:
      client:
        serviceUrl:
          defaultZone: http://localhost:8761/eureka/
        默认程序名：spring.application.name ID
        虚拟HOST：virtual host
        non-secure port: 8761
    禁用
        eureka.client.enabled=false
    Eureka Server 验证
    eureka.client.serviceUrl.defaultZone
    状态页和健康检测
    /info
    /health
    server.servletPath=/custom
    eureka:
      instance:
        statusPageUrlPath: ${server.servletPath}/info
        healthCheckUrlPath: ${server.servletPath}/health
    注册一个安全程序
    eureka.instance.[nonSecurePortEnabled]=[false]
    eureka.instance.[securePortEnabled]=[true]
    eureka:
      instance:
        statusPageUrl: https://${eureka.hostname}/info
        healthCheckUrl: https://${eureka.hostname}/health
        homePageUrl: https://${eureka.hostname}/
    心疼检测客户端是否还存活
        eureka:
          client:
            healthcheck:
              enabled: true
        不能设置在bootstrap.yml中
    eureka元数据
### eureka 服务器
    spring-cloud-starter-netflix-eureka-server
    @SpringBootApplication
    @EnableEurekaServer
    public class Application {

        public static void main(String[] args) {
            new SpringApplicationBuilder(Application.class).web(true).run(args);
        }

    }
    eureka:
      client:
        serviceUrl:
          defaultZone: http://peer1/eureka/,http://peer2/eureka/,http://peer3/eureka/

    ---
    spring:
      profiles: peer1
    eureka:
      instance:
        hostname: peer1

    ---
    spring:
      profiles: peer2
    eureka:
      instance:
        hostname: peer2

    ---
    spring:
      profiles: peer3
    eureka:
      instance:
        hostname: peer3

    eureka.instance.preferIpAddress
    安全

    spring-boot-starter-security

    @EnableWebSecurity
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().ignoringAntMatchers("/eureka/**");
            super.configure(http);
        }
    }

## 熔断机制：Hystrix
    spring-cloud-starter-netflix-hystrix

    @SpringBootApplication
    @EnableCircuitBreaker
    public class Application {

        public static void main(String[] args) {
            new SpringApplicationBuilder(Application.class).web(true).run(args);
        }

    }

    @Component
    public class StoreIntegration {

        @HystrixCommand(fallbackMethod = "defaultStores")
        public Object getStores(Map<String, Object> parameters) {
            //do stuff that might fail
        }

        public Object defaultStores(Map<String, Object> parameters) {
            return /* something useful */;
        }
    }


    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
        })
    public User getUserById(String id) {
        return userResource.getUserById(id);
    }

     @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
        },
                threadPoolProperties = {
                        @HystrixProperty(name = "coreSize", value = "30"),
                        @HystrixProperty(name = "maxQueueSize", value = "101"),
                        @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                        @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
                        @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                        @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
        })
    public User getUserById(String id) {
        return userResource.getUserById(id);
    }


### hystrix metrics
    spring-boot-starter-actuator
    management.endpoints.web.exposure.include: hystrix.stream

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>

### Hystrix Dashboard
    spring-cloud-starter-netflix-hystrix-dashboard
    @EnableHystrixDashboard

### Turbine (涡轮)
    整合 hystrix.stream 到 turbine.stream
    turbine.instanceInsertPort=false
    eureka:
      instance:
        metadata-map:
          management.port: ${management.port:8081}
    http://my.turbine.server:8080/turbine.stream?cluster=CLUSTERNAME
    turbine:
      aggregator:
        clusterConfig: SYSTEM,USER
      appConfig: customers,stores,ui,admin
      clusterNameExpression: metadata['cluster']

      spring-cloud-netflix-hystrix-stream

      http://my.turbine.sever:8989/turbine.stream?cluster=customers
      http://my.turbine.sever:8989/turbine.stream?cluster=products
      http://my.turbine.sever:8989/turbine.stream?cluster=default
      http://my.turbine.sever:8989/turbine.stream
      spring-cloud-starter-netflix-turbine-stream
## 智能路由：Zuul
    路由和过滤
    spring-cloud-starter-netflix-zuul

### Zuul Http Client
    RestClient
    OkHttpClient
    ribbon.restclient.enabled=true
    ribbon.okhttp.enabled=true
    zuul.ignoredHeaders
    zuul:
      routes:
        users:
          path: /myusers/**
          sensitiveHeaders: Cookie,Set-Cookie,Authorization
          url: https://downstream

## 客户端负载均衡：Ribbon
    @FeignClient
    spring-cloud-starter-netflix-ribbon

    @RibbonClients(defaultConfiguration = DefaultRibbonConfig.class)
    public class RibbonClientDefaultConfigurationTestsConfig {

    	public static class BazServiceList extends ConfigurationBasedServerList {
    		public BazServiceList(IClientConfig config) {
    			super.initWithNiwsConfig(config);
    		}
    	}
    }

    @Configuration
    class DefaultRibbonConfig {

    	@Bean
    	public IRule ribbonRule() {
    		return new BestAvailableRule();
    	}

    	@Bean
    	public IPing ribbonPing() {
    		return new PingUrl();
    	}

    	@Bean
    	public ServerList<Server> ribbonServerList(IClientConfig config) {
    		return new RibbonClientDefaultConfigurationTestsConfig.BazServiceList(config);
    	}

    	@Bean
    	public ServerListSubsetFilter serverListFilter() {
    		ServerListSubsetFilter filter = new ServerListSubsetFilter();
    		return filter;
    	}
    }

    ribbon:
      eureka:
       enabled: false

    zuul:
      threadPool:
        useSeparateThreadPools: true