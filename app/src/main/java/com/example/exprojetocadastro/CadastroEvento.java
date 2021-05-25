package com.example.exprojetocadastro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.exprojetocadastro.modelo.Evento;

import java.util.Date;

public class CadastroEvento extends AppCompatActivity {

    private final int RESULT_CODE_NOVO_EVENTO = 10;
    private final int RESULT_CODE_EDIT_EVENTO = 11;


    private boolean triagem = false;

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

            triagem = true;
            id = evento.getId();
        }

    }

    private boolean isCampoVazio (String v){
        boolean resultado = (TextUtils.isEmpty(v) || v.trim().isEmpty());
        return resultado;
    }

    public void onClickExcluir(View v) {

        Evento evento = new Evento(id,"Cancelado!","","");
        Intent intent = new Intent();

        intent.putExtra("eventoEditado", evento);
        setResult(RESULT_CODE_EDIT_EVENTO, intent);

        EditText editTextNome = findViewById(R.id.edtNome);
        EditText editTextData = findViewById(R.id.edtData);
        EditText editTextLocal = findViewById(R.id.edtLocal);

        editTextData.setText(" ");
        editTextNome.setText(" ");
        editTextLocal.setText(" ");


        finish();
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
        Intent intent = new Intent();



        if (triagem){

            intent.putExtra("eventoEditado", evento);
            setResult(RESULT_CODE_EDIT_EVENTO, intent);

        }
        else{
            intent.putExtra("novoEvento", evento);
            setResult(RESULT_CODE_NOVO_EVENTO, intent);

        }

        if (isCampoVazio(nome) || isCampoVazio(data) || isCampoVazio(local)) {
            edtNome.requestFocus();
            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setTitle("Aviso");
            msg.setMessage("Há campos inválidos ou em branco");
            msg.setNeutralButton("Ok", null);
            msg.show();

        }else {

        finish();
    }

    }
}