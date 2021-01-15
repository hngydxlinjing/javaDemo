package io;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @version V1.0
 * @author: zouwh
 * @description: mac
 * @date: 2021/1/10 1:41 PM
 * @Copyright: 2021 www.ztzqzg.com Ltd. All rights reserved.
 * 注意：本内容仅限于中泰证券（上海）资产管理有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Slf4j
public class MacFileList {
    public final static Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();

    @Test
    public void batch_Rename_Path_YYYY_MM_DD() throws Exception {

        String basePath  = "/Volumes/Backup Plus/照片";
        File dir = new File(basePath);
        if(!dir.exists()){
            throw new Exception("文件目录"+basePath+"不存在");
        }
        if(!dir.isDirectory()){
            throw new Exception("文件目录"+basePath+"不是路径");

        }

        File[] files=dir.listFiles();
        String newFileName = "";
        for(int i=0;i<files.length;i++){
            File file =files[i];
            String filePath = file.getAbsolutePath();
            newFileName = file.getParentFile()+"/"+getFileCreateTime(filePath,"yyyy-MM-dd")+file.getName();
            System.out.println(newFileName);
            File newFile = new File(newFileName);
            file.renameTo(newFile);
        }

    }

    @Test
    public void batch_Rename_File_Add_CrtTime_OLD() throws Exception {

        String basePath  = "/Volumes/Backup Plus/照片/";
        File dir = new File(basePath);
        if(!dir.exists()){
            throw new Exception("文件目录"+basePath+"不存在");
        }
        if(!dir.isDirectory()){
            throw new Exception("文件目录"+basePath+"不是路径");

        }

        String[] filepaths = new  String[]{
                basePath
        };

        for(int i=0;i<filepaths.length;i++){
            File tempDir = new File(filepaths[i]);
            batchRenameFileAddCrtTime(tempDir,"yyyyMMdd_HHmmss");
        }


    }


    @Test
    public void batch_Rename_File_Add_CrtTime_MAC() throws Exception {

        String basePath  = "/Volumes/Backup Plus/照片/";
        File dir = new File(basePath);
        if(!dir.exists()){
            throw new Exception("文件目录"+basePath+"不存在");
        }
        if(!dir.isDirectory()){
            throw new Exception("文件目录"+basePath+"不是路径");

        }

        String[] filepaths = new  String[]{
                "/Volumes/Backup Plus/照片/a-2-林静",
                "/Volumes/Backup Plus/照片/a-1-邹林钰"
        };

        for(int i=0;i<filepaths.length;i++){
            File tempDir = new File(filepaths[i]);
            batchRenameFileAddCrtTime(tempDir,"yyyyMMdd_HHmmss");
        }


    }




    private void batchRenameFileAddCrtTime(File dir,String datePatern) throws IOException {
        String filePath = dir.getAbsolutePath();

        File[] files=dir.listFiles();
        if(files == null){
            return ;
        }
        for(int i=0;i<files.length;i++){
            File file =files[i];
            if(file.isDirectory()){
                batchRenameFileAddCrtTime(file,datePatern);
            }else{
                String prefix;
                String fileType = getFileTypeByFile(file);
                String newFileName="";
                String parentFileName = dir.getName();

                //如果目录中包含了日期YYYY-MM-DD，则过滤日期
                if(parentFileName.matches("^\\d{4}-\\d{2}-\\d{2}.*$")){
                    parentFileName = dir.getName().substring(10,dir.getName().length());
                }

                //如果文件带有后缀，则该文件名称为 目录名（过滤日期）+文件名+ 时间戳.文件类型
                if(file.getName().lastIndexOf(".")>0){
                    try {
                        //newFileName = file.getParentFile() + "/" + parentFileName + getFileCreateTime(file.getAbsolutePath(), "yyyy-MM-dd HH-mm-ss-SSS") + "." + fileType;
                        String filePrefix = parentFileName.substring(parentFileName.indexOf("-",2)+1,parentFileName.length());

                        newFileName = file.getParentFile() + "/" +filePrefix+file.getName()
                                .replace("."+fileType,"")
                                .replace(".","")
                                .replaceAll("-\\d{4}.*$","")
                                .replaceAll(filePrefix,"")
                                .replaceAll("\\s","")
                                .replaceAll("--","-")
                                .replaceAll(".","")
                                .replaceAll("_-","_")+"-"+ getFileCreateTime(file.getAbsolutePath(), "yyyy-MM-dd HH-mm-ss-SSS") + "." + fileType;
                        System.out.println(newFileName);
                        File newFile = new File(newFileName);
                        file.renameTo(newFile);
                    }catch(Exception e){
                        log.info("异常文件名称。。。"+file.getName());
                    }
                    continue;
                }

                //如果该文件后缀已经包含时间戳，但是没有后缀，则不改名，仅补充后缀
                if(file.getName().matches(".*\\d{4}-\\d{2}-\\d{2}\\s\\d{2}-\\d{2}-\\d{2}-\\d{3}$")){
                    prefix = file.getName();
                    if(fileType == null){fileType = "MOV";}
                    newFileName = file.getParentFile()+"/"+prefix+"."+fileType;
                    System.out.println(newFileName);
                    File newFile = new File(newFileName);
                    file.renameTo(newFile);
                    continue;
                }


                if(fileType == null || StringUtils.isEmpty(fileType)){
                    log.info("程序不能解析的文件类型。。。"+file.getName());
                    continue;
                }


                log.info("程序未处理文件。。。"+file.getName());


            }

        }

    }

    static {
        getAllFileType(); // 初始化文件类型信息
    }

    /**
     * 获取文件类型,包括图片,若格式不是已配置的,则返回null
     *
     * @author Xewee.Zhiwei.Wang
     * @version 2011-9-18 下午12:36:32
     * @param file
     * @return
     */
    public static String getFileTypeByFile(File file) {
        String filetype = null;
        byte[] b = new byte[50];
        if(file.getAbsolutePath().indexOf(".")>0){
            int i = file.getAbsolutePath().lastIndexOf(".");
            return file.getAbsolutePath().substring(i+1,file.getAbsolutePath().length());
        }
        try {
            InputStream is = new FileInputStream(file);
            is.read(b);
            filetype = getFileTypeByStream(b);
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filetype;
    }

    /**
     * 将常见文件类型放入到map中
     *
     * @author Xewee.Zhiwei.Wang
     * @version 2011-9-18 下午12:35:22
     */
    private static void getAllFileType() {
        FILE_TYPE_MAP.put("jpg", "FFD8FF"); // JPEG (jpg)
        FILE_TYPE_MAP.put("png", "89504E47"); // PNG (png)
        FILE_TYPE_MAP.put("gif", "47494638"); // GIF (gif)
        FILE_TYPE_MAP.put("tif", "49492A00"); // TIFF (tif)
        FILE_TYPE_MAP.put("bmp", "424D"); // Windows Bitmap (bmp)
        FILE_TYPE_MAP.put("dwg", "41433130"); // CAD (dwg)
        FILE_TYPE_MAP.put("html", "68746D6C3E"); // HTML (html)
        FILE_TYPE_MAP.put("rtf", "7B5C727466"); // Rich Text Format (rtf)
        FILE_TYPE_MAP.put("xml", "3C3F786D6C");
        FILE_TYPE_MAP.put("zip", "504B0304");
        FILE_TYPE_MAP.put("rar", "52617221");
        FILE_TYPE_MAP.put("psd", "38425053"); // Photoshop (psd)
        FILE_TYPE_MAP.put("eml", "44656C69766572792D646174653A"); // Email
        FILE_TYPE_MAP.put("dbx", "CFAD12FEC5FD746F"); // Outlook Express (dbx)
        FILE_TYPE_MAP.put("pst", "2142444E"); // Outlook (pst)
        FILE_TYPE_MAP.put("xls", "D0CF11E0"); // MS Word
        FILE_TYPE_MAP.put("doc", "D0CF11E0"); // MS Excel 注意：word 和 excel的文件头一样
        FILE_TYPE_MAP.put("mdb", "5374616E64617264204A"); // MS Access (mdb)
        FILE_TYPE_MAP.put("wpd", "FF575043"); // WordPerfect (wpd)
        FILE_TYPE_MAP.put("eps", "252150532D41646F6265");
        FILE_TYPE_MAP.put("ps", "252150532D41646F6265");
        FILE_TYPE_MAP.put("pdf", "255044462D312E"); // Adobe Acrobat (pdf)
        FILE_TYPE_MAP.put("qdf", "AC9EBD8F"); // Quicken (qdf)
        FILE_TYPE_MAP.put("pwl", "E3828596"); // Windows Password (pwl)
        FILE_TYPE_MAP.put("wav", "57415645"); // Wave (wav)
        FILE_TYPE_MAP.put("avi", "41564920");
        FILE_TYPE_MAP.put("ram", "2E7261FD"); // Real Audio (ram)
        FILE_TYPE_MAP.put("rm", "2E524D46"); // Real Media (rm)
        FILE_TYPE_MAP.put("mpg", "000001BA"); //
        FILE_TYPE_MAP.put("mov", "6D6F6F76"); // Quicktime (mov)
        FILE_TYPE_MAP.put("asf", "3026B2758E66CF11"); // Windows Media (asf)
        FILE_TYPE_MAP.put("mid", "4D546864"); // MIDI (mid)
    }

    /**
     * 通过字节流获得文件类型
     *
     * @author Xewee.Zhiwei.Wang
     * @version 2011-9-18 下午12:37:03
     * @param b
     * @return
     */
    public static String getFileTypeByStream(byte[] b) {
        String filetypeHex = String.valueOf(getFileHexString(b));
        Iterator<Map.Entry<String, String>> entryiterator = FILE_TYPE_MAP
                .entrySet().iterator();
        while (entryiterator.hasNext()) {
            Map.Entry<String, String> entry = entryiterator.next();
            String fileTypeHexValue = entry.getValue();
            if (filetypeHex.toUpperCase().startsWith(fileTypeHexValue)) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * 获得文件的16进制数据
     *
     * @author Xewee.Zhiwei.Wang
     * @version 2011-9-18 下午12:38:26
     * @param b
     * @return
     */
    public static String getFileHexString(byte[] b) {
        StringBuilder stringBuilder = new StringBuilder();
        if (b == null || b.length <= 0) {
            return null;
        }
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    private String getFileCreateTime(String filePath,String datePattern) throws IOException {
        SimpleDateFormat simpleDateFormat  = new SimpleDateFormat(datePattern);

        /*File file = new File(filePath);
        FileTime t= Files.readAttributes(Paths.get(filePath),BasicFileAttributes.class).lastModifiedTime();
        return simpleDateFormat.format(t.toMillis());
*/
        try {
            Path path= Paths.get(filePath);
            BasicFileAttributeView basicview= Files.getFileAttributeView(path, BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS );
            BasicFileAttributes attr = basicview.readAttributes();
            System.out.println(attr.lastModifiedTime());
            return simpleDateFormat.format(new Date(attr.lastModifiedTime().toMillis()));
           // return attr.creationTime().toMillis();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
            //return simpleDateFormat.format(new Date(file.lastModified()));
            //return String.valueOf(file.lastModified());
        }
    }


}
