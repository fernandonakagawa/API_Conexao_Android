package com.example.senai.api_conexao_android;

class ControleDadosSingleton {
    private static final ControleDadosSingleton ourInstance = new ControleDadosSingleton();

    static ControleDadosSingleton getInstance() {
        return ourInstance;
    }

    private ControleDadosSingleton() {
    }
}
