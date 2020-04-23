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

        ByteBuffer.wrap(mainArrayFile).order(ByteOrder.LITTLE_ENDIAN).asIntBuffer().get(mainArrayFileInt);//Переводить масив байтів в масив шортів а шорти це і є 8-бітові значення
        /////////////////////////////////////////////////
        int newSize = (int) Math.ceil(samplesArray.length * AddedLength);


        mainArrayFileInt[1] = 36 + newSize;
        //Додаємо 36 бо це інформація про весь файл крім  RIFF .Тобто це блок fmt, що вказує формат даних, і блок даних, що містить фактичні дані выбірки.Ці 2 блоки містять основну інфу
        mainArrayFileInt[mainArrayFileInt.length - 1] = newSize;
        //Метод allocate () класу java.nio.ByteBuffer використовується для виділення нового байтового буфера.
        ByteBuffer headerOutBuf = ByteBuffer.allocate(mainArrayFile.length);
        //Цей метод повертає порядок байтів цього буфера
        headerOutBuf.order(ByteOrder.LITTLE_ENDIAN);

        //Порядок знову створеного байтового буфера завжди LITTLE_ENDIAN.
        //використовується для запису чотирьох байтів, що використовують дане значення int, в поточному порядку байтів, в цей буфер в поточній позиції, а потім збільшує позицію на чотири.
        for (int i = 0; i < mainArrayFileInt.length; i++){
            headerOutBuf.putInt(mainArrayFileInt[i]);}

        //Створюємо масив куди будемо записувати дані у форматі 0 2 4 6,якщо ввсели збільшити в два рази
        int[] newArrayX = new int[Array16bit.length];
        for (int i = 0; i < newArrayX.length; i++) {
            newArrayX[i] = Math.round(i * AddedLength);

        }
        //Тому кожен семпл збільшив свою тривалість у задану к-сть разів
        //Метод allocate () класса java.nio.ByteBuffer используется для выделения нового байтового буфера.
        ByteBuffer outBuf = ByteBuffer.allocate(4*newArrayX[newArrayX.length - 1]);
        outBuf.order(ByteOrder.LITTLE_ENDIAN);
        short lastValue = 0;
        for (int i = 0; i < Array16bit.length; i++) {
            short sample = Array16bit[i];
            for (int j = 0; j < AddedLength-1; j++) {
                short tempValue = (short) Math.floor(lastValue + ((j + 1) / AddedLength ));
                outBuf.putShort(tempValue);
            }
            outBuf.putShort(sample);
            lastValue = sample;
        }
        //Запис файла
        OutputClass printInfile = new OutputClass();
        printInfile.Out(headerOutBuf, outBuf);

    }
    }

}