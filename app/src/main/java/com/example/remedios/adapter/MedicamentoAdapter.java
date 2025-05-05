package com.example.remedios.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.remedios.R;
import com.example.remedios.database.MedicamentoHelper;
import com.example.remedios.model.Medicamento;

import java.util.List;

public class MedicamentoAdapter extends ArrayAdapter<Medicamento> {

    private final Context context;
    private final List<Medicamento> medicamentos;
    private final MedicamentoHelper dbHelper;

    public MedicamentoAdapter(Context context, List<Medicamento> medicamentos) {
        super(context, 0, medicamentos);
        this.context = context;
        this.medicamentos = medicamentos;
        this.dbHelper = new MedicamentoHelper(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Medicamento medicamento = medicamentos.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_medicamento, parent, false);
        }

        TextView txtNome = convertView.findViewById(R.id.txtNome);
        TextView txtHorario = convertView.findViewById(R.id.txtHorario);
        TextView txtStatus = convertView.findViewById(R.id.txtStatus);
        Button btnExcluir = convertView.findViewById(R.id.btnExcluir); // ✅ novo

        txtNome.setText(medicamento.nome);
        txtHorario.setText("Horário: " + medicamento.horario);
        txtStatus.setText("Status: " + (medicamento.consumido == 1 ? "Consumido" : "Não consumido"));

        btnExcluir.setOnClickListener(v -> {
            dbHelper.excluirMedicamento(medicamento.id);
            medicamentos.remove(position);
            notifyDataSetChanged();
        });

        return convertView;
    }
}
