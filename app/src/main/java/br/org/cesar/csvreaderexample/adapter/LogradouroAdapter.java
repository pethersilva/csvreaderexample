package br.org.cesar.csvreaderexample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import br.org.cesar.csvreaderexample.R;
import br.org.cesar.csvreaderexample.database.entity.Logradouro;

public class LogradouroAdapter extends ArrayAdapter<Logradouro> {

    public LogradouroAdapter(Context context, List<Logradouro> logradouros) {
        super(context, 0, logradouros);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Logradouro logradouro = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.logradouro_row,
                    parent, false);
        }
        TextView textView = convertView.findViewById(R.id.txtLogradouroDesc);
        textView.setText(logradouro.getDescricao());
        return convertView;
    }
}
