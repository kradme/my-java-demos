package cn.lang.test;

import java.io.*;

public class StringTest {

    public static void main(String[] args) throws IOException {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\lang\\Desktop\\test16.txt"))) {
            String a = null,b=null;
            a = bufferedReader.readLine();
            b = bufferedReader.readLine();
            System.out.println(a==b);
        }finally {

        }
    }
}
