package com.example.BlogApplication.utils;

import java.io.ByteArrayOutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtils {

    public static byte[] compressImage(byte[] input){
        int size =-1;
        Deflater deflater = new Deflater();
        deflater.setInput(input);
        deflater.setLevel(Deflater.BEST_COMPRESSION);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(input.length);
        byte[] tmp = new byte[4*1024];
        while(!deflater.finished()){
           size = deflater.deflate(tmp);
           outputStream.write(tmp,0,size);
        }
        return outputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] input) throws DataFormatException {
        int size = -1;
        Inflater inflater = new Inflater();
        inflater.setInput(input);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(input.length);
        byte[] bytes = new byte[1024];

        while(inflater.finished()){
            size = inflater.inflate(bytes);
            outputStream.write(bytes,0,size);
        }
        return outputStream.toByteArray();
    }
}
