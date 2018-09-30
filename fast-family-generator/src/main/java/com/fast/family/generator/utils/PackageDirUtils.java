package com.fast.family.generator.utils;

import java.io.File;
import java.io.IOException;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/9/29-0:07
 */
public class PackageDirUtils {

    public static String getPackageControllerDir(String className,String srcBasePath)  {
        String modelPath = srcBasePath + "." + className + "Controller";
        String packPath = null;
        try {
            packPath = covertPackagePath(modelPath);
            makeDirs(packPath);
            return packPath;
        } catch (IOException e) {
            throw new RuntimeException("根据模块名称获取controller路径失败");
        }

    }

    public static String getPackageEntityDir(String srcBasePath){
        String modelPath = srcBasePath;
        String packPath = null;

        try {
            packPath = covertPackagePath(modelPath);
            makeDirs(packPath);
            return packPath;
        } catch (IOException e) {
            throw new RuntimeException("根据模块名称获取entity路径失败");
        }

    }

    public static String getPackageDaoDir(String moduleName,String srcBasePath){
        String modelPath = srcBasePath + "." + moduleName + "Dao";
        String packPath = null;
        try {
            packPath = covertPackagePath(moduleName);
            makeDirs(packPath);
            return packPath;
        } catch (IOException e) {
            throw new RuntimeException("根据模块名称获取dao路径失败");
        }
    }


    public static String getPackageServiceDir(String moduleName,String srcBasePath){
        String modelPath = srcBasePath + "." + moduleName + "Service";
        String packPath = null;
        try {
            packPath = covertPackagePath(moduleName);
            makeDirs(packPath);
            return packPath;
        } catch (IOException e) {
            throw new RuntimeException("根据模块名称获取service路径失败");
        }
    }

    public static String getPackageServiceImplDir(String moduleName,String srcBasePath){
        String modelPath = srcBasePath + "." + moduleName + "ServiceImpl";
        String packPath = null;
        try {
            packPath = covertPackagePath(moduleName);
            makeDirs(packPath);
            return packPath;
        } catch (IOException e) {
            throw new RuntimeException("根据模块名称获取serviceImpl路径失败");
        }
    }



    private static void makeDirs(String dir){
        File file = new File(dir);
        if (!file.exists() && !file.isDirectory()){
            file.mkdirs();
        }
    }

    private static String covertPackagePath(String packageName) throws IOException {
        File file = new File(packageName);
        String basePath = file.getCanonicalPath();
        return basePath;
    }
}
