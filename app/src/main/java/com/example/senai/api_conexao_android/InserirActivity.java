package com.example.senai.api_conexao_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.VolleyError;

import java.util.HashMap;

public class InserirActivity extends AppCompatActivity implements  IDadosEventListener{

    private EditText etNome, etCpf, etEndereco, etTelefone;
    private ProgressBar pb;
    private TextView tvResultado;

    private InserirController inserirController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir);
        this.inserirController = new InserirController(this);
        this.etNome = findViewById(R.id.et_nome);
        this.etCpf = findViewById(R.id.et_cpf);
        this.etEndereco = findViewById(R.id.et_endereco);
        this.etTelefone = findViewById(R.id.et_telefone);
        this.pb = findViewById(R.id.pb_inserir);
        this.tvResultado = findViewById(R.id.tv_resultado);
    }

    public void enviar(View view) {
        HashMap<String,String> hm = new HashMap<>();
        hm.put("nome", etNome.getText().toString());
        hm.put("cpf", etCpf.getText().toString());
        hm.put("endereco", etEndereco.getText().toString());
        hm.put("telefone", etTelefone.getText().toString());

        this.pb.setVisibility(ProgressBar.VISIBLE);
        this.inserirController.enviarParaPHP(hm);
    }
    @Override
    public void eventoRetornouOk(String response) {
        this.pb.setVisibility(ProgressBar.GONE);
        this.tvResultado.setText(response);
    }

    @Override
    public void eventoRetornouErro(VolleyError error) {
        this.pb.setVisibility(ProgressBar.GONE);
        this.tvResultado.setText(error.toString());
    }


}
