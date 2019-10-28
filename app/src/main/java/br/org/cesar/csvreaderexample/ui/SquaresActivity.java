package br.org.cesar.csvreaderexample.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import br.org.cesar.csvreaderexample.R;
import br.org.cesar.csvreaderexample.adapter.LogradouroAdapter;
import br.org.cesar.csvreaderexample.adapter.SquareAdapter;
import br.org.cesar.csvreaderexample.database.AppDatabase;
import br.org.cesar.csvreaderexample.database.entity.Logradouro;
import br.org.cesar.csvreaderexample.database.entity.Square;

public class SquaresActivity extends AppCompatActivity {

    public final static String SQUARES_ACTIVITY_LOGRADOURO_EXTRA = "squares_activity_logradouro_extra";
    private final static String TAG = "SquaresActivity";
    private AppDatabase mAppDatabase;
    private List<Square> mSquareList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squares);

        Intent intent = getIntent();
        Logradouro logradouro = (Logradouro) intent.getSerializableExtra(SQUARES_ACTIVITY_LOGRADOURO_EXTRA);

        if (logradouro != null) {
            Log.d(TAG, logradouro.toString());

            //retornando os squares por logradouro
            mAppDatabase = AppDatabase.getAppDatabase(this);
            mSquareList = mAppDatabase.squareDao().getSquaresByLogradouro(logradouro.getId());
            Log.d(TAG, "squareList");


            ListView listView = findViewById(R.id.listview);
            if (mSquareList != null) {
                SquareAdapter squareAdapter = new SquareAdapter(this, mSquareList);
                listView.setAdapter(squareAdapter);
            }
        }
    }
}