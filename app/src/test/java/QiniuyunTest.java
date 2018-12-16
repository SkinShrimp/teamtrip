import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.Test;

public class QiniuyunTest {

    @Test
    public void test() {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
//        String accessKey = "Lg8Bkem0U00Xnd4luspXqR9V3s-OJyxY__emhlcn";
        String accessKey = "zd890ccts9Eb4SrpTwgPYdwbX5LlE4ITMgY18Y9h";
//        String secretKey = "gkWMmkSMPvt_nNpB5xsJ8TTPhohhymwWE7w57kSk";
        String secretKey = "ev3-K2NtK0EMKagn0SrnWNgSvJeCNnwTKa4j7djA";
        String bucket = "trip";//存储空间名称
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "E:\\上课资料\\Chen\\骡窝窝\\图片\\1.jpeg";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;//指定文件名称
        Auth auth = Auth.create(accessKey, secretKey);//凭证
        String upToken = auth.uploadToken(bucket);//凭证
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);//上传图片
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
