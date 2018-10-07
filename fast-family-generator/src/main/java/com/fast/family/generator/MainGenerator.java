package com.fast.family.generator;

import com.fast.family.generator.config.GeneratorConfig;

import java.io.File;
import java.io.IOException;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/9/29-0:07
 */
public class MainGenerator {


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

    private static GeneratorConfig generatorConfig(){
        GeneratorConfig generatorConfig = new GeneratorConfig();
        generatorConfig.setDbHost("localhost");
        generatorConfig.setDbName("fast-family-example");
        generatorConfig.setDbPassword("root");
        generatorConfig.setDbUser("root");
        generatorConfig.setPort(3306);
        generatorConfig.setPackageName("com.fast.family.mvc.example");
        generatorConfig.setSrcBasePath("F://github//fast-family-example//fast-family-mvc-example//src//main//java//com//fast//family//mvc//example//");
        return generatorConfig;
    }

    public static void generatorCode(String className,
                                      String classComment,
                                      String url, String tableName, GeneratorConfig generatorConfig) {
        ControllerGenerator.genResourceCode( className, classComment, url, generatorConfig);
        ServiceGenerator.genServiceCode(className, classComment, generatorConfig);
        EntityGenerator.generatorSingleEntity(tableName, className, classComment,generatorConfig);
        MapperGenerator.genMapperInterface(className, classComment, generatorConfig);
        MapperGenerator.genMapperXML(tableName,className,classComment,generatorConfig);
        DTOGenerator.genResourceCode(className, classComment,generatorConfig);
    }
}
