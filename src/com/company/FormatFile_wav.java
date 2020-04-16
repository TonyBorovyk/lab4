package com.company;

import java.io.*;
import java.util.Scanner;
import java.nio.ByteBuffer;// во-первых позволяет в одном массиве данных смешивать разные типы данных, а во вторых позволяет выделять память за пределами обычной кучи языка Java, используя так называемые прямые буфера
import java.nio.ByteOrder;//Постоянный обратный порядок байтов обозначения. В этом порядке байты многобайтового значения упорядочиваются от старшего значащего до младшего значащего.


public class FormatFile_wav {
    FormatFile_wav(String path) {
        modFile(path);
    }

    public void modFile(String path) {
        //формат wave файлу має 44 байти
        int firstLengthFile = 44;
        File file = new File(path);
        byte[] mainArrayFile = new byte[firstLengthFile];//Дані в цьому масиві записані в 32-бітном форматі

        byte[] samplesArray = new byte[(int) file.length() - firstLengthFile];//Масив семплів 57600
        short[] Array8bit = new short[(samplesArray.length / 2)];//Записуються дані в 16-му форматі

        Scanner in = new Scanner(System.in);
        System.out.print("Введіть у скільки разів розширити аудіо-файл: ");
        int AddedLength = in.nextInt();
        InputClass InputFile = new InputClass();
        //Ввод текстового файла
        InputFile.InputFile1(file, mainArrayFile, samplesArray, Array8bit);
        //Обробка текстового файла
        int[] mainArrayFileInt = new int[mainArrayFile.length / 4];//Масив для переведення даних з 32 до 8 бітного,тому ділимо на 4

    }

}