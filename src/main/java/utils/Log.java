package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Log {
    public static BufferedWriter bufferedWriter;

    static {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter("D:/log.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(String s) {
        if (null == s) return;
        try {
            bufferedWriter.write(s + "\n");
            bufferedWriter.flush();
        } catch  (IOException e) {
            e.printStackTrace();
        }
    }
}
