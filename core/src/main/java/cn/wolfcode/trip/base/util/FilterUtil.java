package cn.wolfcode.trip.base.util;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 敏感词过滤
 */
public class FilterUtil {

    //存放读取的敏感词
    private static List<String> forbidWord = new ArrayList<>();
    //对敏感词集合进行初始化
    static {
        readFile();
    }

    /**
     * 读取文件将词条加入集合中
     * @throws Exception
     */
    public static void readFile() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("C:\\Users\\陈晋\\Desktop\\forbid.txt")),
                    "UTF-8"));
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                forbidWord.add(lineTxt.trim());
                System.out.println(lineTxt);
            }
            br.close();
        } catch (Exception e) {
            System.err.println("read errors :" + e);
        }

    }
    public static void main(String[] args) {
        String msg = pass("我好");
        System.out.println(msg);
    }

    //敏感词过滤
    public static String pass(String word){
        System.out.println("forbidWord.size():"+forbidWord.get(0));
        System.out.println(forbidWord.contains("script"));
        for (int i = 0; i < forbidWord.size(); i++) {
            if(word.contains(forbidWord.get(i))){
                word = word.replace(forbidWord.get(i), "***");
            }
        }
        return word;

    }
}
