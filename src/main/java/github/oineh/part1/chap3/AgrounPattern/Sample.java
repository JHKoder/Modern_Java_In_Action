package github.oineh.part1.chap3.AgrounPattern;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Sample implements BufferReaderProcessor {
    public String beforeProcessFile() throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
            return br.readLine();
        }
    }

    public String afterProcessFile(BufferReaderProcessor p ) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
            return p.process(br);
        }
    }

    @Override
    public String process(BufferedReader b) throws IOException {
        return b.readLine();
    }
}
