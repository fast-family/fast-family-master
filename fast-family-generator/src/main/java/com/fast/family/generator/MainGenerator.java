package com.fast.family.generator;

import com.fast.family.generator.config.GeneratorConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;

/**
 * @author 张顺
 * @version 1.0
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
        GeneratorCodeEntity codeEntity = new GeneratorCodeEntity();
        codeEntity.setClassComment("用户信息");
        codeEntity.setClassName("SysUser");
        codeEntity.setTableName("sys_user");
        codeEntity.setUrl("user");
//        MainGenerator.generatorCode(codeEntity, generatorConfig());
        MainGenerator.generatorOneToOneCode(codeEntity,"sys_role",generatorConfig());
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


    public static void generatorCode(GeneratorCodeEntity masterTable,
                                     GeneratorConfig generatorConfig) {
        ControllerGenerator.genSingleController(masterTable.getClassName(),masterTable.getClassComment(),masterTable.getUrl(), generatorConfig);
        ServiceGenerator.genServiceCode(masterTable.getClassName(), masterTable.getClassComment(), generatorConfig);
        EntityGenerator.generatorSingleEntity(masterTable.getTableName(), masterTable.getClassName(),masterTable.getClassComment(),generatorConfig);
        MapperGenerator.genMapperInterface(masterTable.getClassName(),masterTable.getClassComment(), generatorConfig);
        MapperGenerator.genMapperXML(masterTable.getTableName(),masterTable.getClassName(),masterTable.getClassComment(),generatorConfig);
        DTOGenerator.genResourceCode(masterTable.getClassName(),masterTable.getClassComment(),generatorConfig);
    }

    public static void generatorOneToOneCode(GeneratorCodeEntity masterTable,String slaveTableName,
                                     GeneratorConfig generatorConfig) {
        ControllerGenerator.genOneToOneController(masterTable.getClassName(),masterTable.getClassComment(),masterTable.getUrl(),generatorConfig);
        ServiceGenerator.genOneToOneServiceInterface(masterTable.getClassName(), masterTable.getClassComment(), generatorConfig);
        ServiceGenerator.genOneToOneServiceImpl(masterTable.getClassName(), masterTable.getClassComment(), generatorConfig);
        EntityGenerator.generatorSingleEntity(masterTable.getTableName(), masterTable.getClassName(), masterTable.getClassComment(),generatorConfig);
        MapperGenerator.genOneToOneMapperInterface(masterTable.getClassName(), masterTable.getClassComment(), generatorConfig);
        MapperGenerator.genOneToOneMapperXML(masterTable.getTableName(),masterTable.getClassName(),masterTable.getClassComment(),slaveTableName,generatorConfig);
        DTOGenerator.genOneToOneResourceCode(masterTable.getClassName(), masterTable.getClassComment(),masterTable.getTableName()
                ,slaveTableName,generatorConfig);
    }

    public static void generatorOneToManyCode(GeneratorCodeEntity masterTable,String slaveTableName,
                                              GeneratorConfig generatorConfig){
        ControllerGenerator.genOneToManyControllerCode(masterTable.getClassName(),masterTable.getClassComment(),masterTable.getUrl(),generatorConfig);
        ServiceGenerator.genOneToManyServiceInterface(masterTable.getClassName(), masterTable.getClassComment(), generatorConfig);
        ServiceGenerator.genOneToManyServiceImpl(masterTable.getClassName(), masterTable.getClassComment(), generatorConfig);
        EntityGenerator.generatorSingleEntity(masterTable.getTableName(), masterTable.getClassName(), masterTable.getClassComment(),generatorConfig);
        MapperGenerator.genOneToOneMapperInterface(masterTable.getClassName(), masterTable.getClassComment(), generatorConfig);
        MapperGenerator.genOneToManyMapperXML(masterTable.getTableName(),masterTable.getClassName(),masterTable.getClassComment(),slaveTableName,generatorConfig);
        DTOGenerator.genOneToManyResourceCode(masterTable.getClassName(), masterTable.getClassComment(),masterTable.getTableName()
                ,slaveTableName,generatorConfig);
    }

    public static void generatorManyToOneCode(GeneratorCodeEntity masterTable,String slaveTableName,
                                              GeneratorConfig generatorConfig){
        ControllerGenerator.genManyToOneController(masterTable.getClassName(),masterTable.getClassComment(),masterTable.getUrl(),generatorConfig);
        ServiceGenerator.genManyToOneServiceInterface(masterTable.getClassName(), masterTable.getClassComment(), generatorConfig);
        ServiceGenerator.genManyToOneServiceImpl(masterTable.getClassName(), masterTable.getClassComment(), generatorConfig);
        EntityGenerator.generatorSingleEntity(masterTable.getTableName(), masterTable.getClassName(), masterTable.getClassComment(),generatorConfig);
        MapperGenerator.genManyToOneMapperInterface(masterTable.getClassName(), masterTable.getClassComment(), generatorConfig);
        MapperGenerator.genManyToOneMapperXML(masterTable.getTableName(),masterTable.getClassName(),masterTable.getClassComment(),slaveTableName,generatorConfig);
        DTOGenerator.genManyToOneResourceCode(masterTable.getClassName(), masterTable.getClassComment(),masterTable.getTableName()
                ,slaveTableName,generatorConfig);
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GeneratorCodeEntity{
        
        private String className;
        
        private String classComment;
        
        private String url;
        
        private String tableName;
        
    }
}
