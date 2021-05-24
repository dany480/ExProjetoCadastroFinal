package com.example.exprojetocadastro;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.exprojetocadastro.modelo.Evento;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE_NOVO_EVENTO = 1;
    private final int REQUEST_CODE_EDIT_EVENTO = 2;

    private final int RESULT_CODE_NOVO_EVENTO = 10;
    private final int RESULT_CODE_EDIT_EVENTO = 11;

    private int id = 0;


    private ListView listViewEventos;
    private ArrayAdapter <Evento> aEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Eventos");

        listViewEventos = findViewById(R.id.listView_Eventos);

        ArrayList <Evento> eventos = new ArrayList<Evento>();

        aEvento = new ArrayAdapter<Evento>(MainActivity.this,
                android.R.layout.simple_list_item_1, eventos);

        listViewEventos.setAdapter(aEvento);
        registerForContextMenu(listViewEventos);

        definirOnClickListenerListView();

    }

    private void definirOnClickListenerListView(){
        listViewEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Evento eventoClicado = aEvento.getItem(position);
                Intent intent = new Intent(MainActivity.this, CadastroEvento.class);
                intent.putExtra("eventoEdicao",eventoClicado);
                startActivityForResult(intent,REQUEST_CODE_EDIT_EVENTO);
            }
        });
    }

    public void onClickNovoEvento (View v){
        Intent intent = new Intent(MainActivity.this, CadastroEvento.class);
        startActivityForResult(intent, REQUEST_CODE_NOVO_EVENTO);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_CODE_NOVO_EVENTO &&
                resultCode == RESULT_CODE_NOVO_EVENTO){

            Evento evento = (Evento) data.getExtras().getSerializable("novoEvento");
            evento.setId(++id);
            this.aEvento.add(evento);
        }

        else if(requestCode == REQUEST_CODE_EDIT_EVENTO &&
                resultCode == RESULT_CODE_EDIT_EVENTO){

            Evento eventoEdt = (Evento) data.getExtras().getSerializable("eventoEditado");
            for (int i = 0; i < aEvento.getCount(); i++){

                Evento evento = aEvento.getItem(i);
                if(evento.getId() == eventoEdt.getId()){
                    aEvento.remove(evento);
                    aEvento.insert(eventoEdt,i);
                    break;
                }

            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}