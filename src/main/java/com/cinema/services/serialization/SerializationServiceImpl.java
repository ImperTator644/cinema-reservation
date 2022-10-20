package com.cinema.services.serialization;

import com.cinema.services.SerializationService;

import java.io.*;

public class SerializationServiceImpl implements SerializationService {

    private static SerializationServiceImpl instance;

    public static SerializationServiceImpl getInstance() {
        if (instance == null) {
            instance = new SerializationServiceImpl();
        }
        return instance;
    }

    @Override
    public void serialize(Object obj, String filename) throws IOException {
        System.out.println("Serializing data: ");
        System.out.println(obj);
        FileOutputStream fos = new FileOutputStream(filename);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.close();
        fos.close();
    }

    @Override
    public Object deserialize(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object obj = ois.readObject();
        System.out.println("Deserialized data: ");
        System.out.println(obj);
        ois.close();
        return obj;
    }
}
