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
public class DTOGenerator {

    public static void genResourceCode(String className,String classComment,GeneratorConfig generatorConfig){
        Map<String,Object> paramMap = new HashMap<>();

        paramMap.put("className",className);
        paramMap.put("classComment",classComment);
        paramMap.put("sysTime",new Date());
        paramMap.put("packageName",generatorConfig.getPackageName());

        Version version = new Version("2.3.27");
        Configuration configuration = new Configuration(version);
        try {
            URL url = ControllerGenerator.class.getClassLoader().getResource("ftl");
            configuration.setDirectoryForTemplateLoading(new File(url.getPath()));
            configuration.setObjectWrapper(new DefaultObjectWrapper(version));
            String filePath = generatorConfig.getSrcBasePath() + "dto//";
            String savePath = filePath +className + "DTO.java";
            File dirPath = new File(filePath);
            if (!dirPath.exists()) {
                dirPath.mkdirs();
            }
            try (FileWriter fileWriter = new FileWriter(new File(savePath))) {
                Template template = configuration.getTemplate("dto.ftl");
                template.process(paramMap, fileWriter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
