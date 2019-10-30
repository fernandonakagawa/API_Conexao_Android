package com.example.senai.api_conexao_android;

import com.android.volley.VolleyError;

public interface IDadosEventListener {
    void eventoRetornouOk(String response);
    void eventoRetornouErro(VolleyError error);
}
