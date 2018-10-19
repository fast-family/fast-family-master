# fast-family-master

fast-family-master是一个快速开发框架,在框架内部提供大量基类，从而为了减少开发人员工作量，以及降低开发难度，让开发人员更关注与业务，绝对是接私活以及中小型项目的福音。

# 特点

1. 良好的项目模块和API设计
2. 快速生成单表相关CRUD（无BUG）
3. 请求响应日志完善，能够帮助你快速定位问题
4. 安全可靠且能够对于一般性能问题有提升

# 模块

fast-family-security (认证模块)

fast-family-mvc (mvc模块)

fast-family-datasource(多数据源模块)

fast-family-generator(代码生成器模块)

fast-family-commons (通用模块)

fast-family-log (日志模块)



# 使用文档



## fast-family-mvc

**说明:**

​	此模块是对传统springmvc的增强，内部涵盖功能有：

1. ​	传统controller,service,mapper,entity,dto等基类
2. ​        分布式主键ID生成器
3. ​        swagger2增强（基于简单yml配置即可）
4. ​        tomcat优雅停机
5. ​        全局异常处理器
6. ​        请求响应日志打印



- **mvc层基类示例**

  controller：

    ```
    @RestController
    @RequestMapping("/user")
    @Slf4j
    @Api(tags = "用户信息接口")
    public class SysUserController extends GenericController<SysUser,Long>{

        @Autowired
        private SysUserService sysUserService;


        @Override
        public GenericService<SysUser, Long> getService() {
            return this.sysUserService;
        }
    }
    ```

  service:

  ```
  /**
  * <p>
  * 描述: 用户信息业务接口
  * <p>
  *
  * @created 2018-10-06 14:17:30
  */
  public interface SysUserService extends GenericService<SysUser,Long>{


  }
  ```
  ```
  @Service
  @Slf4j
  public class SysUserServiceImpl extends GenericServiceImpl<SysUser,Long> implements SysUserService{

      @Autowired
      private SysUserMapper sysuserMapper;


      @Override
      public GenericMapper<SysUser, Long> getMapper() {
          return sysuserMapper;
      }
  }
  ```
  mapper:

  ```
  /**
  * <p>
  * 描述: 用户信息Mapper接口
  * <p>
  *
  * @created 2018-10-06 14:17:30
  */
  public interface SysUserMapper extends GenericMapper<SysUser,Long> {

  }
  ```


  entity:

  ```
  /**
  * <p>
  * 描述: 用户信息实体
  * <p>
  * @created 2018-10-06 14:17:30
  */
  @Builder
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Entity
  @Table(name = "sys_user")
  @ApiModel(value = "用户信息实体")
  public class SysUser extends GenericEntity<Long> {

      
      @Column(name = "user_name")
      @ApiModelProperty(required = true, dataType = "String", name = "用户名")
      private String userName;


      @Column(name = "password")
      @ApiModelProperty(required = true, dataType = "String", name = "密码")
      private String password;
      
  }
  ```
  dto:

  ```
  /**
  * <p>
  * 描述: 用户信息Dto实体
  * <p>
  *
  * @created 2018-10-06 14:17:30
  */
  public class SysUserDTO extends AbstractPersistableBusinessObjectAdapter<SysUser>{


      public SysUserDTO(Class<SysUser> persistableEntityClass) {
          super(persistableEntityClass);
      }

      @Override
      protected void customizeConvert(SysUser sysuser) {

      }

      @Override
      protected void customizeFill(SysUser sysuser) {

      }
  }
  ```


- **swagger2示例**

    ```
    fast:
      family:
        swagger2:
          restapi:
            back-package: com.fast.family.mvc.example
          apiinfo:
            version: 1.0
            title: fast-family-swagger构建restful api
            description: 欢迎关注fast-family快速开发框架
            terms-of-service-url: https://github.com/fast-family/fast-family-master
            contact-email: 616394599@qq.com
            contact-name: 张顺
    ```



- **请求响应日志示例**

    ```
    	@Bean
    	public Filter logFilter(){
    		return new AccessLogFilter();
    	}
    ```



## fast-family-security

**说明：**

此模块是对spring security增强，具有如下功能：

1.jwt生成和验证

2.验证码生成和验证（提供redis和内存存储）

**示例：**

```
fast:
  family:
    security:
      enabled: true //开启security配置
      jwt:
        authoritiesKey: "fast-family-auth-key"
        secret: "fast-family-token-secret";
        tokenValidityInseconds: 1800 //jwt有效时间
        tokenValidityInSecondsForRememberMe: 2592000 //记住我JWT有效时间
      validate:
        code:
          repository: imMemory //存储方式为内存方式
          sms: true //开启短信验证码
```

```
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private JWTHelper jwtHelper;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
            .disable()
            .headers()
            .frameOptions()
            .disable()
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .authorizeRequests()
            .antMatchers("/system/index").permitAll()
        .and()
            .apply(jwtConfigurer())
        .and()
            .apply(smsCodeAuthenticationConfigurer())
        .and()
            .apply(smsValidateCodeConfigurer());

    }


    private SmsValidateCodeConfigurer smsValidateCodeConfigurer(){
        return new SmsValidateCodeConfigurer();
    }

    /**
     * jwt配置
     * @return
     */
    private JWTConfigurer jwtConfigurer(){
        return new JWTConfigurer(jwtHelper,securityProperties);
    }

    /**
     * 短信验证码配置
     * @return
     */
    private SmsCodeAuthenticationConfigurer smsCodeAuthenticationConfigurer(){
        return new SmsCodeAuthenticationConfigurer(userDetailsService);
    }
}
```

## fast-family-datasource

**说明：**

此模块是多数据源模块，支持注解切换数据源，以及具备XA分布式事务功能.

注意：暂时只支持druid数据源

**示例：**

```
fast:
  family:
    type: com.alibaba.druid.pool.xa.DruidXADataSource //配置DruidDataSource则不具备XA分布式事务
    datasource:
      master:
        druid:
          username: root
          password: root
          url: jdbc:mysql://localhost:3306/master-db?useUnicode=true&characterEncoding=utf8
          driverClassName: com.mysql.jdbc.Driver
        atomikos:
          uniqueResourceName: master-db
      slave_0:
        druid:
          username: root
          password: root
          url: jdbc:mysql://localhost:3306/slave-db?useUnicode=true&characterEncoding=utf8
          driverClassName: com.mysql.jdbc.Driver
        atomikos:
          uniqueResourceName: slave-db
```

```
@DataSourceAnnotation(name = "slave_0")
@Override
public List<User> selectAlldb1() {
    return userDao.selectAll();
}

@DataSourceAnnotation(name = "master")
@Override
public List<User> selectAlldb2() {
    return userDao.selectAll();
}
```

## fast-family-log

**说明：**

此模块利用spring aop来扫描操作日志，只需在方法上加入一个注解即可，支持个性化扩展.

注意：一定要设置spring.application.name(因为有可能是多系统搜集)

**示例**：

```
@LogAnnotation(methodName = "helloword",methodType = LogConstant.SELECT,desc = "helloword")
@GetMapping("/helloword")
public String helloword(String name){
    return "helloword";
}
```

```
/**
 * @author 张顺
 * @version 1.0
 * @created 2018/10/19-17:30
 */
public class SimpleAccessLogInterceptor  implements AccessLogInterceptor {

    @Override
    public void before(AccessLogInfo accessLogInfo) {
        
    }

    @Override
    public void after(AccessLogInfo accessLogInfo) {

    }
}
```

```
@Bean
public AccessLogMethodInterceptor accessLogMethodInterceptor(){
   AccessLogMethodInterceptor accessLogMethodInterceptor = new AccessLogMethodInterceptor();
   accessLogMethodInterceptor.add(new SimpleAccessLogInterceptor());
   return accessLogMethodInterceptor;
}
```

## fast-family-generator

**说明：**

此模块是配套fast-family-mvc做的代码生成器

**示例：**

- ​	全局配置信息：

```
    private static GeneratorConfig generatorConfig(){
        GeneratorConfig generatorConfig = new GeneratorConfig();
        generatorConfig.setDbHost("localhost");//数据库路径
        generatorConfig.setDbName("fast-family-example");//数据库名称
        generatorConfig.setDbPassword("root");//数据库密码
        generatorConfig.setDbUser("root");//数据库用户名
        generatorConfig.setPort(3306);//数据库端口
        generatorConfig.setPackageName("com.fast.family.example");//生成包路径
        generatorConfig.setSrcBasePath("src//main//java//com//fast//family//example//");//生成文件路径
        return generatorConfig;
    }
```

- ​        表生成配置：

```
    public static void main(String[] args) throws IOException {

        /**
         * 1.类名称
         * 2.类描述
         * 3.类路径
         * 4.表名
         * 5.全局配置信息
         */
        MainGenerator.generatorCode( "SysUser", 
                "用户信息", "user",
                "sys_user", generatorConfig());
    }
```


**备注：**

如果您对此项目感兴趣请star或fork。

想和作者交流？联系方式如下：

QQ群：390295286

作者qq：616394599





# 鸣谢

感谢通用mapper

https://github.com/abel533/Mapper
