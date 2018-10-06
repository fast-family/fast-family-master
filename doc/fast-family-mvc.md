### fast-family-mvc

**说明:**

​	此模块是对传统springmvc的增强，内部涵盖功能有：

1. ​	传统controller,service,mapper,entity,dto等基类

2. ​        分布式主键ID生成器

3. ​        swagger2增强（基于简单yml配置即可）

4. ​        tomcat优雅停机

5. ​        全局异常处理器

6. ​        请求响应日志打印

   ​                     

**使用示例：**

- controller等基类示例

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

  ​

- swagger2示例

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

  ​

- 请求响应日志示例


  ```
  	@Bean
  	public Filter logFilter(){
  		return new AccessLogFilter();
  	}
  ```

  ​

**使用效果：**

​	继承controller基类效果图

​	![](C:\Users\hp\Desktop\QQ图片20181006152936.png)

​	swagger效果图

![QQ图片20181006153026](C:\Users\hp\Desktop\QQ图片20181006153026.png)

​	请求响应日志效果图

![QQ图片20181006201326](C:\Users\hp\Desktop\QQ图片20181006201326.png)


