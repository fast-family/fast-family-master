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

        generatorCode( "SysUser",
                "用户信息", "user", "sys_user",
                generatorConfig());

    }

    private static GeneratorConfig generatorConfig(){
        GeneratorConfig generatorConfig = new GeneratorConfig();
        generatorConfig.setDbHost("localhost");
        generatorConfig.setDbName("fast-family-example");
        generatorConfig.setDbPassword("root");
        generatorConfig.setDbUser("root");
        generatorConfig.setPort(3306);
        generatorConfig.setSrcBasePath("src//main//java//com//fast//family//example//");
        return generatorConfig;
    }

    public static void generatorCode(String className,
                                      String classComment,
                                      String url, String tableName, GeneratorConfig generatorConfig) {
        ControllerGenerator.genResourceCode( className, classComment, url, generatorConfig);
        ServiceGenerator.genServiceCode(className, classComment, generatorConfig);
        EntityGenerator.generatorSingleEntity(tableName, className, classComment,generatorConfig);
        MapperGenerator.genMapperInterface(className, classComment, generatorConfig);
//        MapperGenerator.genResourceXML(moduleName, className, classComment, tableName, versionNo, packageName);
        DTOGenerator.genResourceCode(className, classComment,generatorConfig);
    }
}
