package com.example.remedios.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MedicamentoHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "medicamentos.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_MEDICAMENTOS = "medicamentos";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_DESCRICAO ="descricao";
    public static final String COLUMN_HORARIO = "horario";
    public static final String COLUMN_CONSUMIDO = "consumido";

    public MedicamentoHelper(Context context) {
        super(context , DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABLE_MEDICAMENTOS + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NOME + " TEXT, " +
                        COLUMN_DESCRICAO + " TEXT, " +
                        COLUMN_HORARIO + " TEXT, " +
                        COLUMN_CONSUMIDO + " INTEGER DEFAULT 0)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICAMENTOS);
        onCreate(db);
    }

    // Adicionar um medicamento
    public long addMedicamento(String nome, String descricao, String horario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, nome);
        values.put(COLUMN_DESCRICAO, descricao);
        values.put(COLUMN_HORARIO, horario);
        values.put(COLUMN_CONSUMIDO, 0); // Consumido inicialmente é 0

        return db.insert(TABLE_MEDICAMENTOS, null, values);
    }

    // Obter todos os medicamentos
    public Cursor getAllMedicamentos() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_MEDICAMENTOS, null, null, null, null, null, null);
    }

    // Marcar medicamento como consumido
    public int marcarConsumido(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CONSUMIDO, 1); // Marcando como consumido

        return db.update(TABLE_MEDICAMENTOS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    // Excluir medicamento
    public void excluirMedicamento(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MEDICAMENTOS, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }
}
