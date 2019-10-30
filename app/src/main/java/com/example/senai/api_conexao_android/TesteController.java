package com.example.senai.api_conexao_android;

import android.content.Context;
import android.util.Log;
import android.widget.ProgressBar;

import java.util.HashMap;

/**
 * Classe do tipo CONTROLLER - liga a interface do usuário com o controle de dados
 * do tipo MODEL (AppModelSingleton.java) é a camada do \"meio\"
 */
public class TesteController implements IDadosEventListener {
    AppModelSingleton appModelSingleton;
    Context appContext;
    IDadosEventListener mListenerView;

    public TesteController(Context appContext) {
        this.appContext = appContext;
        this.appModelSingleton = AppModelSingleton.getInstance();
    }



    public void enviarParaPHP(String texto, ProgressBar pb){
        HashMap<String,String> hm = new HashMap();
        hm.put("comando", "teste");
        hm.put("valor", texto);
        AppModelSingleton.getInstance().registrarEvento(this);
        AppModelSingleton.getInstance().enviarRequisicao(this.appContext,pb,hm);
    }

    @Override
    public void eventoRetorno() {
        Log.d(this.getClass().toString(), "Evento Retornou!");


    }
}
