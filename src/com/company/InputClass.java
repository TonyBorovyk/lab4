package com.company;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.io.*;
public class InputClass {
    public void InputFile1(File file,byte[] mainArrayFile,byte[] samplesArray,short[] input) {
        try {
            FileInputStream inputFile = new FileInputStream(file);
            inputFile.read(mainArrayFile);
            inputFile.read(samplesArray);
            ByteBuffer.wrap(samplesArray).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(input);//Переводить масив байтів в масив шортов а шорти це і є 8-бітові значення
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
