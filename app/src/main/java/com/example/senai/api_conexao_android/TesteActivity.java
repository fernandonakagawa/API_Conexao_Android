package com.example.senai.api_conexao_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.HashMap;

public class TesteActivity extends AppCompatActivity {

    private EditText etTeste;
    private TextView tvTeste;
    private ProgressBar pbTeste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);
        this.etTeste = findViewById(R.id.etTeste);
        this.tvTeste = findViewById(R.id.tvTexto);
        this.pbTeste = findViewById(R.id.pbTeste);
    }

    public void enviarParaPHP(View view) {
        String s = etTeste.getText().toString();
        HashMap<String,String> hm = new HashMap();
        hm.put("comando", "teste");
        hm.put("valor", s);
        ControleDadosSingleton.getInstance().enviarRequisicao(this,this.pbTeste,hm);
    }
}
