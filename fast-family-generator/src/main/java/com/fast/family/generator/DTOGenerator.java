package com.fast.family.generator;

import com.fast.family.generator.config.GeneratorConfig;
import com.fast.family.generator.db.AnalysisDB;
import com.fast.family.generator.model.ColumnInfo;
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
import java.util.List;
import java.util.Map;

/**
 * @author 张顺
 * @version 1.0
 */
public class DTOGenerator {




    public static void genResourceCode(String className,
                                        String classComment,
                                        GeneratorConfig generatorConfig){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("className",className);
        paramMap.put("classComment",classComment);
        paramMap.put("sysTime",new Date());
        paramMap.put("packageName",generatorConfig.getPackageName());
        genDTO(paramMap,"ftl",generatorConfig);
    }


    public static void genOneToOneResourceCode(String className,
                                               String classComment,
                                               String masterTableName,
                                               String slaveTableName,
                                               GeneratorConfig generatorConfig){
        TableInfo masterTableInfo = AnalysisDB.getTableInfoByName(masterTableName,generatorConfig);
        TableInfo slaveTableInfo = AnalysisDB.getTableInfoByName(slaveTableName,generatorConfig);
        for (int i = 0;i < masterTableInfo.getColumnInfoList().size();i++){
            ColumnInfo masterColumnInfo = masterTableInfo.getColumnInfoList().get(i);
            for (int j = 0;j < slaveTableInfo.getColumnInfoList().size();j++){
                ColumnInfo slaveColumnInfo = slaveTableInfo.getColumnInfoList().get(j);
                if (masterColumnInfo.getColumnJavaName().equals(slaveColumnInfo.getColumnJavaName())){
                    masterTableInfo.getColumnInfoList().remove(i);
                }
            }
        }
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("className",className);
        paramMap.put("classComment",classComment);
        paramMap.put("sysTime",new Date());
        paramMap.put("packageName",generatorConfig.getPackageName());
        paramMap.put("masterTableInfo",masterTableInfo);
        paramMap.put("slaveTableInfo",slaveTableInfo);
        genDTO(paramMap,"ftl/onetoone",generatorConfig);
    }


    public static void genOneToManyResourceCode(String className,
                                                String classComment,
                                                String masterTableName,
                                                String slaveTableName,
                                                GeneratorConfig generatorConfig){
        TableInfo masterTableInfo = AnalysisDB.getTableInfoByName(masterTableName,generatorConfig);
        TableInfo slaveTableInfo = AnalysisDB.getTableInfoByName(slaveTableName,generatorConfig);
        for (int i = 0;i < masterTableInfo.getColumnInfoList().size();i++){
            ColumnInfo masterColumnInfo = masterTableInfo.getColumnInfoList().get(i);
            for (int j = 0;j < slaveTableInfo.getColumnInfoList().size();j++){
                ColumnInfo slaveColumnInfo = slaveTableInfo.getColumnInfoList().get(j);
                if (masterColumnInfo.getColumnJavaName().equals(slaveColumnInfo.getColumnJavaName())){
                    masterTableInfo.getColumnInfoList().remove(i);
                }
            }
        }
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("className",className);
        paramMap.put("classComment",classComment);
        paramMap.put("sysTime",new Date());
        paramMap.put("packageName",generatorConfig.getPackageName());
        paramMap.put("masterTableInfo",masterTableInfo);
        paramMap.put("slaveTableInfo",slaveTableInfo);
        genDTO(paramMap,"ftl/onetomany",generatorConfig);
    }


    public static void genManyToOneResourceCode(String className,
                                                String classComment,
                                                String masterTableName,
                                                String slaveTableName,
                                                GeneratorConfig generatorConfig){
        TableInfo masterTableInfo = AnalysisDB.getTableInfoByName(masterTableName,generatorConfig);
        TableInfo slaveTableInfo = AnalysisDB.getTableInfoByName(slaveTableName,generatorConfig);
        for (int i = 0;i < masterTableInfo.getColumnInfoList().size();i++){
            ColumnInfo masterColumnInfo = masterTableInfo.getColumnInfoList().get(i);
            for (int j = 0;j < slaveTableInfo.getColumnInfoList().size();j++){
                ColumnInfo slaveColumnInfo = slaveTableInfo.getColumnInfoList().get(j);
                if (masterColumnInfo.getColumnJavaName().equals(slaveColumnInfo.getColumnJavaName())){
                    masterTableInfo.getColumnInfoList().remove(i);
                }
            }
        }
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("className",className);
        paramMap.put("classComment",classComment);
        paramMap.put("sysTime",new Date());
        paramMap.put("packageName",generatorConfig.getPackageName());
        paramMap.put("masterTableInfo",masterTableInfo);
        paramMap.put("slaveTableInfo",slaveTableInfo);
        genDTO(paramMap,"ftl/manytoone",generatorConfig);
    }

    private static void genDTO(Map<String,Object> paramMap,
                               String reousrcePath,
                               GeneratorConfig generatorConfig){


        Version version = new Version("2.3.27");
        Configuration configuration = new Configuration(version);
        try {
            URL url = ControllerGenerator.class.getClassLoader().getResource(reousrcePath);
            configuration.setDirectoryForTemplateLoading(new File(url.getPath()));
            configuration.setObjectWrapper(new DefaultObjectWrapper(version));
            String filePath = generatorConfig.getSrcBasePath() + "dto//";
            String savePath = filePath + paramMap.get("className") + "DTO.java";
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
