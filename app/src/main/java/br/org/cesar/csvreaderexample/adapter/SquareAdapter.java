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
import br.org.cesar.csvreaderexample.database.entity.Square;

public class SquareAdapter extends ArrayAdapter<Square> {

    public SquareAdapter(Context context, List<Square> squares) {
        super(context, 0, squares);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Square square = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.logradouro_row,
                    parent, false);
        }
        TextView textView = convertView.findViewById(R.id.txtLogradouroDesc);
        textView.setText(square.getNome_oficial_equip_urbano());
        return convertView;
    }
}
