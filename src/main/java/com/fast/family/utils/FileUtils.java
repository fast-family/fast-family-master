package com.fast.family.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isBlank;



public class FileUtils {

    public static void writeFile(File file,byte[] byteArray) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(byteArray);
        fileOutputStream.close();
    }

    /**
     * 判断当前路径下是否有文件
     * @param filePath 文件路径
     * @return
     */
    public static boolean exist(String filePath){
        File file = new File(filePath);
        return file.exists();
    }

    public static String generateUUIDFileName(){
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 生成UUID文件名
     * @param extension
     * @return
     */
    public static String generateUUIDFileName(String extension){
        return String.format("%s.%s", UUID.randomUUID().toString(),extension);
    }

    /**
     * 获取文件名
     * @param fileName
     * @return
     */
    public static String getFileExtension(String fileName){
        return fileName.lastIndexOf(".") != -1 &&
                fileName.lastIndexOf(".") != 0 ?
                fileName.substring(fileName.lastIndexOf(".") + 1) : null;
    }

    /**
     * 获取文件名
     * @param file
     * @return
     */
    public static String getFileExtension(File file){
        String fileName = file.getName();
        return getFileExtension(fileName);
    }

    /**
     * 删除文件
     * @param file
     * @throws IOException
     */
    public static void forceDelete(File file) throws IOException {
        if (file.exists()){
            org.apache.commons.io.FileUtils.forceDelete(file);
        }
    }

    public static void forceMkdirParent(File file) throws IOException {
        org.apache.commons.io.FileUtils.forceMkdir(file);
    }

    public static void transferTo(MultipartFile multipartFile, File dest) throws IOException {
        forceMkdirParent(dest);
        multipartFile.transferTo(dest);
    }
    /**
     * 转换UUID文件上传
     * @param multipartFile
     * @param destPath
     * @return
     */
    public static File transferToUUIDFile(MultipartFile multipartFile, String destPath) throws IOException {
        String extension = getFileExtension(multipartFile.getOriginalFilename());
        String uuidFileName = generateUUIDFileName(extension);
        File dest = new File(destPath,uuidFileName);
        transferTo(multipartFile,dest);
        return dest;
    }
    /**
     * 文件路径
     * @param prefix 前缀
     * @return 返回上传路径
     */
    public static String getPath(String prefix, String suffix) {
        //生成uuid
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //文件路径
        String path = DateTimeUtils.now(DateTimeUtils.TimeFormat.YYYYMMDD) + "/" + uuid;
        if (!isBlank(prefix)) {
            path = prefix + "/" + path;
        }
        return path + suffix;
    }

    /**
     * 获取图片后缀名
     * @param fileName
     * @return
     */
    public static String fileSuffix(String fileName) {
        String suffix = "";
        if (fileName.indexOf(".") > 0) {
            suffix = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
            return suffix;
        }
        suffix = ".jpg";
        return suffix;
    }
}
