package com.example.remedios;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.remedios.adapter.MedicamentoAdapter;
import com.example.remedios.database.MedicamentoHelper;
import com.example.remedios.model.Medicamento;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listaMedicamentos;
    MedicamentoHelper dbHelper;
    ArrayList<Medicamento> lista;
    MedicamentoAdapter adapter;
    Button btnAdicionar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new MedicamentoHelper(this);
        listaMedicamentos = findViewById(R.id.listaMedicamentos);
        btnAdicionar = findViewById(R.id.btnAdicionar);

        btnAdicionar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
            startActivity(intent);
        });

        carregarLista();

        listaMedicamentos.setOnItemClickListener((parent, view, position, id) -> {
            Medicamento med = lista.get(position);
            dbHelper.marcarConsumido(med.id);
            carregarLista();
        });

        listaMedicamentos.setOnItemLongClickListener((parent, view, position, id) -> {
            Medicamento med = lista.get(position);
            dbHelper.excluirMedicamento(med.id);
            carregarLista();
            return true;
        });
    }

    private void carregarLista() {
        lista = new ArrayList<>();
        Cursor cursor = dbHelper.getAllMedicamentos();

        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
            String nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"));
            String descricao = cursor.getString(cursor.getColumnIndexOrThrow("descricao"));
            String horario = cursor.getString(cursor.getColumnIndexOrThrow("horario"));
            int consumido = cursor.getInt(cursor.getColumnIndexOrThrow("consumido"));

            Medicamento m = new Medicamento(id, nome, descricao, horario, consumido);
            lista.add(m);
        }

        adapter = new MedicamentoAdapter(this, lista);
        listaMedicamentos.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }
}