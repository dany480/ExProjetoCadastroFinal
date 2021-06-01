package com.example.exprojetocadastro;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.exprojetocadastro.database.EventoDAO;
import com.example.exprojetocadastro.database.contract.EventoContract;
import com.example.exprojetocadastro.database.entity.EventoEntity;
import com.example.exprojetocadastro.modelo.Evento;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

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
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventoDAO eventoDAO = new EventoDAO(getBaseContext());
        aEvento = new ArrayAdapter<Evento>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                eventoDAO.relacionar());

        listViewEventos.setAdapter(aEvento);

    }

    public void onClickNovoEvento (View v){
        Intent intent = new Intent(MainActivity.this, CadastroEvento.class);
        startActivity(intent);
    }

}