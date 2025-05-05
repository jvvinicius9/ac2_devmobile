package com.example.remedios.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
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
        TextView txtDescricao = convertView.findViewById(R.id.txtDescricao);
        Button btnConsumido = convertView.findViewById(R.id.btnConsumido);
        Button btnExcluir = convertView.findViewById(R.id.btnExcluir);
        ImageButton btnExpandir = convertView.findViewById(R.id.btnExpandir);

        txtNome.setText(medicamento.nome);
        txtHorario.setText(context.getString(R.string.label_horario, medicamento.horario));
        txtStatus.setText(medicamento.consumido == 1
                ? context.getString(R.string.status_consumido)
                : context.getString(R.string.status_nao_consumido));
        txtDescricao.setText(medicamento.descricao);

        // Foco para o ListView funcionar corretamente
        btnConsumido.setFocusable(false);
        btnConsumido.setFocusableInTouchMode(false);
        btnExcluir.setFocusable(false);
        btnExcluir.setFocusableInTouchMode(false);
        btnExpandir.setFocusable(false);
        btnExpandir.setFocusableInTouchMode(false);

        // Alternar visibilidade da descrição
        btnExpandir.setOnClickListener(v -> {
            if (txtDescricao.getVisibility() == View.GONE) {
                txtDescricao.setVisibility(View.VISIBLE);
                btnExpandir.setImageResource(android.R.drawable.arrow_up_float);
            } else {
                txtDescricao.setVisibility(View.GONE);
                btnExpandir.setImageResource(android.R.drawable.arrow_down_float);
            }
        });

        // Lógica do botão "Consumido"
        if (medicamento.consumido == 1) {
            btnConsumido.setEnabled(false);
            btnConsumido.setAlpha(0.5f);
        } else {
            btnConsumido.setEnabled(true);
            btnConsumido.setAlpha(1f);

            btnConsumido.setOnClickListener(v -> {
                dbHelper.marcarConsumido(medicamento.id);
                medicamento.consumido = 1;
                notifyDataSetChanged();
            });
        }

        // Lógica do botão "Excluir"
        btnExcluir.setOnClickListener(v -> {
            dbHelper.excluirMedicamento(medicamento.id);
            medicamentos.remove(position);
            notifyDataSetChanged();
        });

        return convertView;
    }
}
