## fast-family-generator

**说明**：

​	此模块是根据fast-family-mvc模块定制开发的。

​	只需简单配置快速生成controller,service,mapper,entity,dto等类。生成即可使用。

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

