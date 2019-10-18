package br.org.cesar.csvreaderexample.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.org.cesar.csvreaderexample.database.entity.Logradouro;
import br.org.cesar.csvreaderexample.database.entity.LogradouroDao;

@Database(entities = {Logradouro.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

	private static AppDatabase INSTANCE;
	public abstract LogradouroDao logradouroDao();

	public static AppDatabase getAppDatabase(Context context) {
		if (INSTANCE == null) {
			synchronized (AppDatabase.class) {
				if (INSTANCE == null) {
					INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
						AppDatabase.class, "squares")
						.allowMainThreadQueries() //rodando na main apenas para facilitar o entendimento
						.build();
				}
			}
		}
		return INSTANCE;
	}

	public static void destroyInstance() {
		INSTANCE = null;
	}
}
