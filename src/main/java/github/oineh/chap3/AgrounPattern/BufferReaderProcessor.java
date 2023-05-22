package github.oineh.chap3.AgrounPattern;

import java.io.BufferedReader;
import java.io.IOException;

public interface BufferReaderProcessor {
    String process(BufferedReader b) throws IOException;
}
