package cn.lang.nio;

import java.io.*;

public class BioDemo01 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("input.txt")), 1024*1024);
        PrintWriter printWriter = new PrintWriter(new FileWriter(new File("output.txt")));

        try {
            long start = System.currentTimeMillis();
            String line = null;
            while ((line=bufferedReader.readLine())!=null){
                printWriter.println(line);
            }
            System.out.println("耗时："+(System.currentTimeMillis()-start));
        } finally {
            bufferedReader.close();
            printWriter.close();;
        }

    }
}
