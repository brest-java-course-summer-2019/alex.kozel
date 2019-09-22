package com.epam.brest.summer.courses2019.web_app;

public class ServerDataAccessExeption extends RuntimeException {

    /**
     * Constructor with parameter
     * @param message
     */
    public ServerDataAccessExeption(String message) {
        super(message);
    }
}
