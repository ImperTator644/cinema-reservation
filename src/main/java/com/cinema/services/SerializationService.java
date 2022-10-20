package com.cinema.services;

import java.io.IOException;
public interface SerializationService {
    void serialize(Object obj, String filename) throws IOException;
    Object deserialize(String filename) throws IOException, ClassNotFoundException;
}
