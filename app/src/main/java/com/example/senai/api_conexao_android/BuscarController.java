package com.example.senai.api_conexao_android;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;

import java.util.HashMap;

public class BuscarController extends AbstractController implements IEnviadorTextoSimplesPHP {
    public BuscarController(Context appContext) {
        super(appContext);
    }

    @Override
    public void enviarParaPHP(String texto) {
        HashMap<String,String> hm = new HashMap();
        hm.put("comando", "buscarNome");
        hm.put("valor", texto);
        AppModelSingleton.getInstance().
                registrarCallback(this);
        AppModelSingleton.getInstance().
                enviarRequisicao(this.getAppContext(),hm);
    }

    @Override
    public void eventoRetornouOk(String response) {
        Log.d(this.getClass().toString(), "Evento Retornou!" + response);
        this.getmListenerView().eventoRetornouOk(response);
    }

    @Override
    public void eventoRetornouErro(VolleyError error) {
        Log.d(this.getClass().toString(), "Evento Retornou erro!" + error.toString());
        this.getmListenerView().eventoRetornouErro(error);
    }



}