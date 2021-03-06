package cn.wolfcode.trip.base.util;

import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.io.Resources;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

/**
 * 文件上传工具
 */
public class UploadUtil {


    private static Properties properties = new Properties();
    public static String QI_PATH = null;
    public static String PATH = null;

    static {
        try {
            InputStream in = Resources.getResourceAsStream("upload.properties");
            properties.load(in);
            //七牛云的域名
            QI_PATH = properties.getProperty("QI_PATH");
            //存放图片的路径
            PATH = properties.getProperty("imagesLocalPath");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理文件上传
     *
     * @param file
     * @param basePath
     * @return 123.png
     */
    public static String upload(MultipartFile file, String basePath) {//c:/trip/upload
        //获取随机字符串
        String uuid = UUID.randomUUID().toString();
        //获取上传的文件的名称
        String orgFileName = file.getOriginalFilename();
        //获取文件后缀名
        String ext = "." + FilenameUtils.getExtension(orgFileName);
        //文件名称
        String fileName = uuid + ext;
        try {
            File targetFile = new File(basePath, fileName); //c:/trip/upload/sssss.jpg
            FileUtils.writeByteArrayToFile(targetFile, file.getBytes());

            return "/upload/" + fileName;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 存储到七牛云
     *
     * @param file
     * @return
     */
    public static String uploadQiniuyun(MultipartFile file) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = properties.getProperty("accessKey");
        String secretKey = properties.getProperty("secretKey");
        String bucket = properties.getProperty("bucketName");//存储空间名称
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;//指定文件名称
        Auth auth = Auth.create(accessKey, secretKey);//凭证
        String upToken = auth.uploadToken(bucket);//凭证
        try {
            Response response = uploadManager.put(file.getBytes(), key, upToken);//上传图片
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            return putRet.key;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
























