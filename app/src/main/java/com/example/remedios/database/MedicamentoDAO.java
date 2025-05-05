package com.example.remedios.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.remedios.model.Medicamento;

import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAO {
    private MedicamentoHelper dbHelper;

    public MedicamentoDAO(Context context){
        dbHelper = new MedicamentoHelper(context);
    }

    //Inserir
    public void inserir(Medicamento medicamento) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", medicamento.nome);
        values.put("descricao", medicamento.descricao);
        values.put("horario", medicamento.horario);
        values.put("consumido", medicamento.consumido);

        db.insert("medicamento", null, values);
        db.close();
    }

    //Listar
    public List<Medicamento> listar() {
        List<Medicamento> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM medicamentos", null);

        if (cursor.moveToFirst()) {
            do {
                Medicamento medicamento = new Medicamento(
                        cursor.getLong(0),           // id
                        cursor.getString(1),         // nome
                        cursor.getString(2),         // descricao
                        cursor.getString(3),         // horario
                        cursor.getInt(4)             // consumido
                );
                lista.add(medicamento);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lista;
    }

    //Atualizar
    public void atualizar(Medicamento medicamento) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", medicamento.nome);
        values.put("descricao", medicamento.descricao);
        values.put("horario", medicamento.horario);
        values.put("consumido", medicamento.consumido);

        db.update("medicamentos", values, "id = ?", new String[]{String.valueOf(medicamento.id)});
        db.close();
    }

    //Deletar
    public void deletar(long id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("medicamentos", "id = ?", new String[]{String.valueOf( id)});
        db.close();
    }
}
