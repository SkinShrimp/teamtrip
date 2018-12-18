import cn.wolfcode.trip.app.util.SpiderUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class SpiderTest {

   /* @Test
    public void test1() throws Exception {
        HttpClient client = new HttpClient();
        // 设置代理服务器地址和端口
        //client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);
        // 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https
        HttpMethod method= new PostMethod("https://www.baidu.com");
        //使用POST方法
        //HttpMethod method = new PostMethod("http://java.sun.com");
        client.executeMethod(method);
        //状态码
        //System.out.println(method.getStatusLine());
        //内容
        String content= method.getResponseBodyAsString();
        content = new String(content.getBytes("UTF-8"),"UTF-8");
        System.out.println(content);
        //释放连接
        method.releaseConnection();


        //jsoup解析网页数据
        //第一步，将字符内容解析成一个Document类
        Document doc = Jsoup.parse(content);
        //第二步，根据我们需要得到的标签，选择提取相应标签的内容
        Elements elements = doc.select("a");
        for( Element element : elements ){
            String title = element.text();
            System.out.println(title);
        }

    }

    @Test
    public void test2() throws Exception{
        URL url = new URL("https://www.zhihu.com/search?type=content&q=%E6%97%85%E6%B8%B8");
        URLConnection connection = url.openConnection();
        InputStream in = connection.getInputStream();
        byte[] buffer = new byte[1024];
        int index=-1;
        String content = "";
        while((index=in.read(buffer))!=-1){
            String msg = new String(buffer,"UTF-8");
            content+=msg;
        }
        System.out.println(content);

        //jsoup解析网页数据
        //第一步，将字符内容解析成一个Document类
        Document doc = Jsoup.parse(content);
        //第二步，根据我们需要得到的标签，选择提取相应标签的内容
        Elements elements = doc.select("a");
        for( Element element : elements ){
            String title = element.text();
            //System.out.println(title);
        }
    }
    
    @Test
    public void test3() throws Exception{

        String path = "https://www.toutiao.com";// 实验环境中使用pc的ip，不能用localhost或127.0.0.1
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("contentType", "UTF-8");
        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        InputStream inStream = conn.getInputStream();

        BufferedReader in = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = in.readLine()) != null){
            buffer.append(line);
        }
        String msg = buffer.toString();
        System.out.println(msg);
    }
    
    
    @Test
    public void test4() throws Exception{
        StringBuilder allText = new StringBuilder();
        for (int i = 1; i <= 3; i++) {
            System.out.println("正在爬取第" + i + "页内容。。。");
            // 建立连接，获取网页内容
            String html = SpiderUtil.Connect("https://www.pengfu.com/xiaohua_" + i + ".html");
            // 将内容转换成dom格式，方便操作
            Document doc = Jsoup.parse(html);
            // 获取网页内所有标题节点
            Elements titles = doc.select("h1.dp-b");
            for (Element titleEle : titles) {
                Element parent = titleEle.parent();
                // 标题内容
                String title = titleEle.getElementsByTag("a").text();
                // 标题对应的作者
                //String author = parent.select("p.user_name_list > a").text();
                // 标题对应的正文
                String content = parent.select("div.content-img").text();
                System.out.println("content:"+content);
                // 将内容格式化
                allText.append(title)
                        .append("\r\n").append(content)
                        .append("\r\n").append("\r\n");
            }
        }

        //将内容写入磁盘
        SpiderUtil.writeToFile(allText.toString(),"C:\\Users\\陈晋\\Desktop\\xx\\a.txt");
    }*/
}
