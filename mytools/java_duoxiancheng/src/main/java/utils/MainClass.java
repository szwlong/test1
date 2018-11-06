package utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;

public class MainClass  {
    public static void main(String[] args) throws Exception {
        FileManager a = new FileManager("C://users//lizs//Desktop//表名.txt","\n");
        FileManager b = new  FileManager("C://users//lizs//Desktop//test.txt"," |\n");
        FileWriter c = new FileWriter("C://users//lizs//Desktop//result.txt");
        String aWord = null;
        String bWord = null;
        while((aWord =a.nextWord())!=null&&(bWord= b.nextWord())!=null)
        {
            c.write(aWord + "\n");
            c.write(bWord);
            c.write("\n");
        }
        while((aWord =a.nextWord())==null&&(bWord= b.nextWord())!=null)
        {
            c.write(bWord);
            c.write("\n");
        }
        while((bWord= b.nextWord())==null&&(aWord =a.nextWord())!=null)
        {
            c.write(aWord + "\n");

        }
        c.close();

    }
}

class FileManager {
    String[] words = null;
    int pos = 0;
    public FileManager(String fileName,String seperators) throws Exception {
        File file  = new File(fileName);
        Reader reader  = new FileReader(file);
        char[] buffer = new char[(int)file.length()];
        reader.read(buffer);
        String result = new String(buffer,0,buffer.length);
        words = result.split(seperators);

    }
    public String nextWord(){
        if(pos == words.length-1)
            return null;
        return words[pos++];
    }
}
