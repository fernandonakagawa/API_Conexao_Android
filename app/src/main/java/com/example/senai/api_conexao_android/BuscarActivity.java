package com.example.senai.api_conexao_android;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import java.util.ArrayList;

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
    public void eventoRetornouOk(Object response) {
        this.pbBusca.setVisibility(ProgressBar.GONE);
        ArrayList<Cliente> clientes = new ArrayList<>();
        TableRow tr;
        Log.d(this.getClass().toString(), "Clientes: " + clientes.toString());
        for(Cliente c: clientes){
            Log.d(this.getClass().toString(), "Cliente: " + c.getNome());
            tr = new TableRow(this);
            TextView tvCpf = new TextView(this);
            tvCpf.setText(c.getCpf());
            tvCpf.setPadding(2, 0, 5, 0);
            tvCpf.setTextColor(Color.BLACK);
            tr.addView(tvCpf);
            TextView tvNome = new TextView(this);
            tvNome.setText(c.getNome());
            tvNome.setPadding(2, 0, 5, 0);
            tvNome.setTextColor(Color.BLACK);
            tr.addView(tvNome);
            TextView tvEndereco = new TextView(this);
            tvEndereco.setText(c.getEndereco());
            tvEndereco.setPadding(2, 0, 5, 0);
            tvEndereco.setTextColor(Color.BLACK);
            tr.addView(tvEndereco);
            TextView tvTelefone = new TextView(this);
            tvTelefone.setText(c.getTelefone());
            tvTelefone.setPadding(2, 0, 5, 0);
            tvTelefone.setTextColor(Color.BLACK);
            tr.addView(tvTelefone);
            tbResultado.addView(tr);
        }

    }

    @Override
    public void eventoRetornouErro(VolleyError error) {
        this.pbBusca.setVisibility(ProgressBar.GONE);
        Toast.makeText(this, R.string.erro_busca, Toast.LENGTH_LONG);
    }
}
