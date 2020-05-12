package com.company;
import java.nio.ByteBuffer;
import java.io.FileOutputStream;
//запис у файл
public class OutputClass {
    public void Out(ByteBuffer headerOutBuf, ByteBuffer outBuf) {
        try {
            FileOutputStream outputFile = new FileOutputStream("addSize.wav");
            outputFile.write(headerOutBuf.array());
            outputFile.write(outBuf.array());
            outputFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
