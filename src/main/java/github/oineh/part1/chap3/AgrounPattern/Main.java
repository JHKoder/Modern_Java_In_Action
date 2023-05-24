package github.oineh.part1.chap3.AgrounPattern;


import java.io.BufferedReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Sample sample = new Sample();
        String before = sample.beforeProcessFile();

        String after1 = sample.afterProcessFile((BufferedReader br ) -> br.readLine());
        String after2 = sample.afterProcessFile((BufferedReader br ) -> br.readLine() +br.readLine());
    }
}
