package org.ttscoupe.common.system.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.repository.core.support.PropertiesBasedNamedQueries;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.PropertiesPersister;
import org.ttscoupe.common.SystemProperties;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * 文件处理类
 */
public class FileUtils {

    /**
     * 拷贝文件到正式地址
     * @return absPath
     */
    public  String convertFile(String pathName,String inPath,String outPath){
        //文件原地址
        String srcPath = inPath + File.separator + pathName;
        File srcfile = new File(srcPath);
        //文件正式地址
        String absPath = inPath + File.separator + outPath + File.separator + pathName;
        File absfile = new File(absPath);
        try {
//            //检查正式地址是否存在
            File file = new File(absPath.substring(0,absPath.lastIndexOf("/")));
            if (!file.exists()) {
                file.mkdirs();// 创建文件根目录
            }
            FileCopyUtils.copy(srcfile,absfile);
            FileUtils.deleFile(srcPath);
            //返回值需要省略根路径，不然查询时会多拼接一次根路径。
            return outPath + "/" + pathName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 删除文件
     * @param pathName
     * @return
     */
    public static boolean deleFile(String pathName){
        File file = new File(pathName);
        FileSystemUtils.deleteRecursively(file);
        return true;
    }
}
