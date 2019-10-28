package br.org.cesar.csvreaderexample.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.org.cesar.csvreaderexample.R;
import br.org.cesar.csvreaderexample.adapter.LogradouroAdapter;
import br.org.cesar.csvreaderexample.database.AppDatabase;
import br.org.cesar.csvreaderexample.database.entity.Logradouro;
import br.org.cesar.csvreaderexample.service.SquareService;

public class MainActivity extends AppCompatActivity {

	public static final String SQUARE_DATABASE_CREATED = "is_database_created";
	public static final String IS_DATABASE_CREATED_CORRECTLY = "is_database_created_correctly";

	private static final String TAG = MainActivity.class.getName();
	private AppDatabase mAppDatabase;
	private List<Logradouro> mListLogradouro;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//pegando instância do banco de dados
		mAppDatabase = AppDatabase.getAppDatabase(this);

		//TODO: na primeira execução, é preciso ler o arquivo csv e salvar as informações no banco
		//de dados.
		//verificando se o banco de dados com os barrios e parques já foi criado
		SharedPreferences sharedPreferences = getSharedPreferences(SQUARE_DATABASE_CREATED,
			MODE_PRIVATE);
		boolean isDatabaseCreated = sharedPreferences.getBoolean(IS_DATABASE_CREATED_CORRECTLY, false);

		SquareService squareService = new SquareService(this, sharedPreferences);
		if (!isDatabaseCreated) {
			squareService.createDatabaseTable();
		}

		//TODO: ler dados da tabela bairro e passá-los para o Adapter populando a listview
		mListLogradouro = mAppDatabase.logradouroDao().getAll();

		ListView listView = findViewById(R.id.listview);
		LogradouroAdapter logradouroAdapter = new LogradouroAdapter(this, mListLogradouro);
		listView.setAdapter(logradouroAdapter);

		//verificando click evento de click
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
				Logradouro logradouro = mListLogradouro.get(position);

				Intent squareActivity = new Intent(MainActivity.this, SquaresActivity.class);
				squareActivity.putExtra(SquaresActivity.SQUARES_ACTIVITY_LOGRADOURO_EXTRA, logradouro);
				startActivity(squareActivity);
				Toast.makeText(MainActivity.this, "setOnItemClickListener i: " + position + " l: " + l, Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override
	protected void onDestroy() {
		AppDatabase.destroyInstance();
		super.onDestroy();
	}
}
