package com.example.senai.api_conexao_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TableLayout;

import com.android.volley.VolleyError;

public class BuscarActivity extends AppCompatActivity implements IDadosEventListener{

    private BuscarController buscarController;

    private EditText etBusca;
    private ProgressBar pbBusca;
    private TableLayout tbResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        this.buscarController = new BuscarController(this);
        this.etBusca = findViewById(R.id.et_buscar_nome);
        this.pbBusca = findViewById(R.id.pbBusca);
        this.tbResultado = findViewById(R.id.tb_buscar);
    }

    public void enviar(View view) {
        String s = etBusca.getText().toString();
        this.pbBusca.setVisibility(ProgressBar.VISIBLE);
        this.buscarController.enviarParaPHP(s);
    }

    @Override
    public void eventoRetornouOk(String response) {
        this.pbBusca.setVisibility(ProgressBar.GONE);
    }

    @Override
    public void eventoRetornouErro(VolleyError error) {
        this.pbBusca.setVisibility(ProgressBar.GONE);
    }
}
