package com.fast.family.generator;

import com.fast.family.generator.config.GeneratorConfig;
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
public class ServiceGenerator {

    public static void genServiceCode(String className,String classComment,GeneratorConfig generatorConfig){
        genServiceInterface(className,classComment,generatorConfig);
        genServiceImpl(className,classComment,generatorConfig);
    }

    public static void genServiceInterface(String className,String classComment,GeneratorConfig generatorConfig){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("className",className);
        paramMap.put("classComment",classComment);
        paramMap.put("sysTime",new Date());
        paramMap.put("packageName",generatorConfig.getPackageName());
        Version version = new Version("2.3.27");
        Configuration configuration = new Configuration(version);
        try {
            URL url = ServiceGenerator.class.getClassLoader().getResource("ftl");
            configuration.setDirectoryForTemplateLoading(new File(url.getPath()));
            configuration.setObjectWrapper(new DefaultObjectWrapper(version));
            String filePath = generatorConfig.getSrcBasePath() + "service//";
            String savePath = filePath +className + "Service.java";
            File dirPath = new File(filePath);
            if (!dirPath.exists()) {
                dirPath.mkdirs();
            }
            try (FileWriter fileWriter = new FileWriter(new File(savePath))) {
                Template template = configuration.getTemplate("service.ftl");
                template.process(paramMap, fileWriter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void genServiceImpl(String className,String classComment,GeneratorConfig generatorConfig){
        Map<String,Object> paramMap = new HashMap<>();

        paramMap.put("className",className);
        paramMap.put("classComment",classComment);
        paramMap.put("sysTime",new Date());
        paramMap.put("packageName",generatorConfig.getPackageName());

        Version version = new Version("2.3.27");
        Configuration configuration = new Configuration(version);
        try {
            URL url = ServiceGenerator.class.getClassLoader().getResource("ftl");
            configuration.setDirectoryForTemplateLoading(new File(url.getPath()));
            configuration.setObjectWrapper(new DefaultObjectWrapper(version));
            String filePath = generatorConfig.getSrcBasePath() + "service//impl//";
            String savePath = filePath +className + "ServiceImpl.java";
            File dirPath = new File(filePath);
            if (!dirPath.exists()) {
                dirPath.mkdirs();
            }
            try (FileWriter fileWriter = new FileWriter(new File(savePath))) {
                Template template = configuration.getTemplate("service_impl.ftl");
                template.process(paramMap, fileWriter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
