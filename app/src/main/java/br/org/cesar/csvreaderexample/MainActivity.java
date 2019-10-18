package br.org.cesar.csvreaderexample;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.org.cesar.csvreaderexample.adapter.LogradouroAdapter;
import br.org.cesar.csvreaderexample.database.AppDatabase;
import br.org.cesar.csvreaderexample.database.entity.Logradouro;
import br.org.cesar.csvreaderexample.service.SquareService;

public class MainActivity extends AppCompatActivity {

	public static final String SQUARE_DATABASE_CREATED = "is_database_created";
	public static final String IS_DATABASE_CREATED_CORRECTLY = "is_database_created_correctly";

	private static final String TAG = MainActivity.class.getName();
	private AppDatabase mAppDatabase;

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

		SquareService squareService = new SquareService(this);
		if (!isDatabaseCreated) {
			squareService.createDatabaseTable();
		}

		//TODO: ler dados da tabela bairro e passá-los para o Adapter populando a listview
		List<Logradouro> listLogradouro = mAppDatabase.logradouroDao().getAll();

		ListView listView = findViewById(R.id.listview);
		LogradouroAdapter logradouroAdapter = new LogradouroAdapter(this, listLogradouro);
		listView.setAdapter(logradouroAdapter);
	}

	@Override
	protected void onDestroy() {
		AppDatabase.destroyInstance();
		super.onDestroy();
	}
}
