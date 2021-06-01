package com.example.exprojetocadastro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.exprojetocadastro.database.EventoDAO;
import com.example.exprojetocadastro.modelo.Evento;

import java.util.Date;

public class CadastroEvento extends AppCompatActivity {

    private  int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        setTitle("Add Evento");

        carregarEvento();

    }

    private void carregarEvento(){

        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null &&
                intent.getExtras().get("eventoEdicao")!=null){

            Evento evento = (Evento) intent.getExtras().get("eventoEdicao");
            EditText edtNome = findViewById(R.id.edtNome);
            EditText edtData = findViewById(R.id.edtData);
            EditText edtLocal = findViewById(R.id.edtLocal);

            edtNome.setText(evento.getNome());
            edtData.setText(evento.getData());
            edtLocal.setText(evento.getLocal());


            id = evento.getId();
        }

    }

    private boolean isCampoVazio (String v){
        boolean resultado = (TextUtils.isEmpty(v) || v.trim().isEmpty());
        return resultado;
    }

    public void onClickExcluir(View v) {

        EditText editTextNome = findViewById(R.id.edtNome);
        EditText editTextData = findViewById(R.id.edtData);
        EditText editTextLocal = findViewById(R.id.edtLocal);

        String nome = editTextNome.getText().toString();
        String data = editTextData.getText().toString();
        String local = editTextLocal.getText().toString();

        Evento evento = new Evento(id,nome,data,local);
        EventoDAO eventoDAO = new EventoDAO(getBaseContext());

        boolean ex = eventoDAO.deletar(evento);

        if (ex){
            finish();
        }
        else{
            Toast.makeText(CadastroEvento.this,"Erro na exclusão!",
                    Toast.LENGTH_LONG).show();
        }
    }


    public void onClickVoltar(View v){finish();}

    public void onClickSalvar(View v){


        EditText edtNome = findViewById(R.id.edtNome);
        EditText edtData = findViewById(R.id.edtData);
        EditText edtLocal = findViewById(R.id.edtLocal);

        String nome = edtNome.getText().toString();
        String data = edtData.getText().toString();
        String local = edtLocal.getText().toString();

        Evento evento = new Evento(id,nome,data,local);

        EventoDAO eventoDAO = new EventoDAO(getBaseContext());

        boolean salvo = eventoDAO.salvar(evento);

        if (salvo){
            finish();
        }
        else {
            Toast.makeText(CadastroEvento.this,"Erro ao executar!",Toast.LENGTH_LONG).show();
        }
    }
}