package com.example.senai.api_conexao_android;

import android.content.Context;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

class AppModelSingleton implements Response.Listener, Response.ErrorListener{

    private final String URL =
            "http://10.0.2.2:8080/android/processaandroid.php";
    private ProgressBar pb;
    private Context ctx;
    private static final AppModelSingleton ourInstance = new AppModelSingleton();

    private IDadosEventListener mListener;

    static AppModelSingleton getInstance() {
        return ourInstance;
    }

    private AppModelSingleton() {
    }

    public void registrarEvento(IDadosEventListener mListener){
        this.mListener = mListener;
        Log.d(this.getClass().toString(), "Evento Registrado " + mListener.getClass());
    }

    public void enviarRequisicao(Context ctx, ProgressBar pb, final HashMap<String,String> dados){
        this.ctx = ctx;
        this.pb = pb;
        this.pb.setVisibility(ProgressBar.VISIBLE);
        StringRequest sr = new StringRequest(Request.Method.POST, URL,
                this, this) {
            @Override
            protected Map<String, String> getParams() {
                //ENVIAR DADOS PARA O PHP VIA POST!
                Map<String, String> params = dados;
                return params;
            }
        };
        AppController.getInstancia().adicionarParaFila(sr);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        if(this.pb != null){
            this.pb.setVisibility(ProgressBar.GONE);
        }
        if(this.ctx != null) {
            Toast.makeText(ctx, "Erro na conexão!",
                    Toast.LENGTH_SHORT).show();
        }
        Log.e(this.getClass().toString(), "Erro na conexão!" + error.toString() +
                " " + error.getNetworkTimeMs());
    }

    @Override
    public void onResponse(Object response) {
        if(this.pb != null){
            this.pb.setVisibility(ProgressBar.GONE);
        }
        if(this.ctx != null) {
            Toast.makeText(ctx, String.valueOf(response),
                    Toast.LENGTH_SHORT).show();
        }
        Log.d(this.getClass().toString(), String.valueOf(response));

        //callback síncrono do método registrado
        if(this.mListener != null){
            mListener.eventoRetorno();
            Log.d(this.getClass().toString(), "Executando mListener" + mListener.toString());
        }
    }
}
