package cn.wolfcode.trip.base.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class SpiderUtil {
    /**
     * 建立http连接,返回网页内容
     */
    public static String Connect(String address) {
        HttpURLConnection conn = null;
        URL url = null;
        InputStream in = null;
        BufferedReader reader = null;
        StringBuffer stringBuffer = null;
        try {
            url = new URL(address);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setDoInput(true);
            conn.connect();
            in = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in));
            stringBuffer = new StringBuffer();
            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuffer.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
            try {
                in.close();
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 用于将内容写入到磁盘文件
     */
    public static void writeToFile(String allText,String path) {
        System.out.println("正在写入。。。");
        BufferedOutputStream bos = null;
        try {
            File targetFile = new File(path);
            File fileDir = targetFile.getParentFile();
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            if (!targetFile.exists()) {
                targetFile.createNewFile();
            }
            bos = new BufferedOutputStream(new FileOutputStream(targetFile, true));
            System.out.println("allText:"+allText);
            bos.write(allText.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bos) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("写入完毕。。。");
    }
}
