package com.fast.family.generator;

import com.fast.family.generator.config.GeneratorConfig;
import com.fast.family.generator.db.AnalysisDB;
import com.fast.family.generator.model.TableInfo;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.Version;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 张顺
 * @version 1.0
 */
public class MapperGenerator {



    public static void genMapperInterface(String className,
                                          String classComment,
                                          GeneratorConfig generatorConfig){
        genMapper(className,classComment,"ftl",generatorConfig);
    }



    public static void genMapperXML(String tableName,
                                    String className,
                                    String classComment,
                                    GeneratorConfig generatorConfig){
        TableInfo tableInfo = AnalysisDB.getTableInfoByName(tableName,generatorConfig);
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("className",className);
        paramMap.put("classComment",classComment);
        paramMap.put("sysTime",new Date());
        paramMap.put("packageName",generatorConfig.getPackageName());
        paramMap.put("tableInfo",tableInfo);
        genMapperXml(paramMap,className,"ftl",generatorConfig);

    }


    public static void genOneToOneMapperInterface(String className,
                                          String classComment,
                                          GeneratorConfig generatorConfig){
        genMapper(className,classComment,"ftl/onetoone",generatorConfig);
    }



    public static void genOneToOneMapperXML(String tableName,
                                    String className,
                                    String classComment,
                                    GeneratorConfig generatorConfig){
        TableInfo tableInfo = AnalysisDB.getTableInfoByName(tableName,generatorConfig);
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("className",className);
        paramMap.put("classComment",classComment);
        paramMap.put("sysTime",new Date());
        paramMap.put("packageName",generatorConfig.getPackageName());
        paramMap.put("tableInfo",tableInfo);
        genMapperXml(paramMap,className,"ftl/onetoone",generatorConfig);

    }

    public static void genOneToManyMapperInterface(String className,
                                          String classComment,
                                          GeneratorConfig generatorConfig){
        genMapper(className,classComment,"ftl/onetomany",generatorConfig);
    }



    public static void genOneToManyMapperXML(String tableName,
                                    String className,
                                    String classComment,
                                    GeneratorConfig generatorConfig){
        TableInfo tableInfo = AnalysisDB.getTableInfoByName(tableName,generatorConfig);
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("className",className);
        paramMap.put("classComment",classComment);
        paramMap.put("sysTime",new Date());
        paramMap.put("packageName",generatorConfig.getPackageName());
        paramMap.put("tableInfo",tableInfo);


        genMapperXml(paramMap,className,"ftl/onetomany",generatorConfig);

    }


    public static void genManyToOneMapperInterface(String className,
                                          String classComment,
                                          GeneratorConfig generatorConfig){
        genMapper(className,classComment,"ftl/manytoone",generatorConfig);
    }



    public static void genManyToOneMapperXML(String tableName,
                                    String className,
                                    String classComment,
                                    GeneratorConfig generatorConfig){
        TableInfo tableInfo = AnalysisDB.getTableInfoByName(tableName,generatorConfig);
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("className",className);
        paramMap.put("classComment",classComment);
        paramMap.put("sysTime",new Date());
        paramMap.put("packageName",generatorConfig.getPackageName());
        paramMap.put("tableInfo",tableInfo);

        genMapperXml(paramMap,className,"ftl/manytoone",generatorConfig);
    }


    private static void genMapperXml(Map<String,Object> params,String className,String resourcePath,
                                    GeneratorConfig generatorConfig){

        Version version = new Version("2.3.27");
        Configuration configuration = new Configuration(version);
        try {
            URL url = MapperGenerator.class.getClassLoader().getResource(resourcePath);
            configuration.setDirectoryForTemplateLoading(new File(url.getPath()));
            configuration.setObjectWrapper(new DefaultObjectWrapper(version));
            String filePath = generatorConfig.getSrcBasePath() + "mapper//";
            String savePath = filePath + className + "Mapper.xml";
            File dirPath = new File(filePath);
            if (!dirPath.exists()) {
                dirPath.mkdirs();
            }
            try (FileWriter out = new FileWriter(new File(savePath))) {
                Template template = configuration.getTemplate("mapper_xml.ftl");
                template.process(params, out);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void genMapper(String className,
                                 String classComment,
                                 String resourcePath,
                                 GeneratorConfig generatorConfig){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("className",className);
        paramMap.put("classComment",classComment);
        paramMap.put("sysTime",new Date());
        paramMap.put("packageName",generatorConfig.getPackageName());;

        Version version = new Version("2.3.27");
        Configuration configuration = new Configuration(version);
        try {
            URL url = MapperGenerator.class.getClassLoader().getResource(resourcePath);
            configuration.setDirectoryForTemplateLoading(new File(url.getPath()));
            configuration.setObjectWrapper(new DefaultObjectWrapper(version));
            String filePath = generatorConfig.getSrcBasePath() + "mapper//";
            String savePath = filePath + className + "Mapper.java";
            File dirPath = new File(filePath);
            if (!dirPath.exists()) {
                dirPath.mkdirs();
            }
            try (FileWriter out = new FileWriter(new File(savePath))) {
                Template template = configuration.getTemplate("mapper.ftl");
                template.process(paramMap, out);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
