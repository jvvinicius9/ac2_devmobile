package com.example.remedios;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.Manifest;

import com.example.remedios.database.MedicamentoHelper;
import com.example.remedios.model.Medicamento;

public class CadastroActivity extends Activity {

    EditText edtNome, edtDescricao, edtHorario;
    Button btnSalvar;
    MedicamentoHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        // Solicita permissão para notificações
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        100
                );
            }
        }

        edtNome = findViewById(R.id.edtNome);
        edtDescricao = findViewById(R.id.edtDescricao);
        edtHorario = findViewById(R.id.edtHorario);
        btnSalvar = findViewById(R.id.btnSalvar);
        dbHelper = new MedicamentoHelper(this);

        btnSalvar.setOnClickListener(v -> {
            String nome = edtNome.getText().toString();
            String descricao = edtDescricao.getText().toString();
            String horario = edtHorario.getText().toString();

            Medicamento medicamento = new Medicamento(nome, descricao, horario, 0);
            long id = dbHelper.addMedicamento(nome, descricao, horario);

            //Chama o serviço que dispara a notificação
            Intent intent = new Intent(this, com.example.remedios.service.NotificationService.class);
            intent.putExtra("med_nome", nome);
            startService(intent);

            Toast.makeText(this, "Medicamento salvo!", Toast.LENGTH_SHORT).show();
            finish();
        });

    };
}
