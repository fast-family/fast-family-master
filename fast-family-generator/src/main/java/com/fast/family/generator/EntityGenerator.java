package com.fast.family.generator;

import com.fast.family.generator.config.GeneratorConfig;
import com.fast.family.generator.db.AnalysisDB;
import com.fast.family.generator.model.TableInfo;
import com.fast.family.generator.utils.PackageDirUtils;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.Version;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 张顺
 * @version 1.0
 */
public class EntityGenerator {

    public static void generatorSingleEntity(String tableName, String className,
                                             String classComment, GeneratorConfig generatorConfig) {
        TableInfo tableInfo = AnalysisDB.getTableInfoByName(tableName, generatorConfig);
        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("className", className);
        paramMap.put("tableInfo", tableInfo);
        paramMap.put("sysTime", new Date());
        paramMap.put("classComment", classComment);
        paramMap.put("packageName", generatorConfig.getPackageName());


        Version version = new Version("2.3.27");
        Configuration configuration = new Configuration(version);
        try {
            configuration.setObjectWrapper(new DefaultObjectWrapper(version));
            configuration.setDirectoryForTemplateLoading(new File(EntityGenerator.class.getClassLoader()
                    .getResource("ftl").getPath()));
            String savePath = PackageDirUtils.getPackageEntityDir(generatorConfig.getSrcBasePath());
            String filePath = generatorConfig.getSrcBasePath() + "entity//";
            savePath = filePath + className + ".java";
            File dirPath = new File(filePath);
            if (!dirPath.exists()) {
                dirPath.mkdirs();
            }
            try (FileWriter fileWriter = new FileWriter(savePath)) {
                Template temporal = configuration.getTemplate("entity.ftl");
                temporal.process(paramMap, fileWriter);
            }
            System.out.println("************" + savePath + "************");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
