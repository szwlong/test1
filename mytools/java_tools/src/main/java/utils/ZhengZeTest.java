package utils;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZhengZeTest {
    public static void testZhengze() {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            String str = null;
            fis = new FileInputStream("C:\\Users\\lizs\\Desktop\\1.txt");
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

            //fw = new FileWriter("C:\\Users\\lizs\\Desktop\\2.txt");
            //bw = new BufferedWriter(fw);

            while((str = br.readLine()) != null){
                //System.out.println(str);
                // 正则表达式规则
                String regEx = "^\\d{5,12}$";
                // 编译正则表达式
                //Pattern pattern = Pattern.compile(regEx);
                // 忽略大小写的写法
                Pattern pattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(str);
                // 查找字符串中是否有匹配正则表达式的字符/字符串
                if(matcher.find()){
                    System.out.println(matcher.group(0));

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static void test2(){
        String Regex="\\w(\\d\\d)(\\w+)";
        String TestStr="A22happy";
        Pattern p=Pattern.compile(Regex);
        Matcher matcher=p.matcher(TestStr);
        if (matcher.find()) {
            int gc=matcher.groupCount();
            System.out.println(gc);
            for (int i = 0; i <= gc; i++) {
                System.out.println("group "+i+" :"+matcher.group(i));
            }
        }
    }

    public static void main(String[] args) {
        //test2();
        testZhengze();
    }
}
