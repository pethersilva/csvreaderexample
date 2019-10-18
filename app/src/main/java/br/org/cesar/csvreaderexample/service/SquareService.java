package br.org.cesar.csvreaderexample.service;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import br.org.cesar.csvreaderexample.database.AppDatabase;
import br.org.cesar.csvreaderexample.database.entity.Logradouro;
import br.org.cesar.csvreaderexample.model.Square;
import de.siegmar.fastcsv.reader.CsvParser;
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRow;

public class SquareService {

	private Context mContext;
	private final int NOME_EQUIP_URBANO_INDEX = 0;
	private final int TIPO_EQUIP_URBANO_INDEX = 1;
	private final int ENDERECO_EQUIP_URBANO_INDEX = 2;
	private final int CODIGO_LOGRADOURO_INDEX = 3;
	private final int LEI_EQUIP_URBANO_INDEX = 4;

	private final int NOME_BAIRRO_INDEX = 5;
	private final int CODIGO_BAIRRO_INDEX = 6;
	private final int NOME_OFICIAL_EQUIP_URBANO_INDEX = 7;
	private final int AREA_INDEX = 8;

	private final int PERIMETRO_INDEX = 9;
	private final int LATITUDE_INDEX = 10;
	private final int LONGITUDE_INDEX = 11;
	private final static String TAG = "SquareService";

	public SquareService(Context context) {
		mContext = context;
	}

	public void createDatabaseTable() {

		AppDatabase.getAppDatabase(mContext);
		ArrayList<Square> squareList = readCSVFile();
	}

	private ArrayList<Square> readCSVFile() {
		ArrayList<Square> squareList = null;

		if (mContext != null) {
			try {
				InputStream ins = mContext.getResources().openRawResource(
					mContext.getResources().getIdentifier("squares",
						"raw", mContext.getPackageName()));
				BufferedReader readerInput = new BufferedReader(new InputStreamReader(ins));
				CsvReader csvReader = new CsvReader();
				csvReader.setFieldSeparator(';');
				try (CsvParser csvParser = csvReader.parse(readerInput)) {
					CsvRow row;
					csvParser.nextRow(); //ignoring the first line
					Square square;
					squareList = new ArrayList<>();
					while ((row = csvParser.nextRow()) != null) {
						square = new Square();

						square.setmNome_equip_urbano(row.getField(NOME_EQUIP_URBANO_INDEX));
						square.setmTipo_equip_urbano(row.getField(TIPO_EQUIP_URBANO_INDEX));
						square.setmEndereco_equip_urbano(row.getField(ENDERECO_EQUIP_URBANO_INDEX));
						square.setmCodigo_logradouro(parseStringToDouble(
							row.getField(CODIGO_LOGRADOURO_INDEX), 0));
						square.setmLei_equip_urbano(row.getField(LEI_EQUIP_URBANO_INDEX));

						double id_bairro = parseStringToDouble(row.getField(CODIGO_BAIRRO_INDEX),0);
						String desc_bairro = row.getField(NOME_BAIRRO_INDEX);

						square.setmNome_bairro(desc_bairro);
						square.setmCcodigo_bairro(id_bairro);

						square.setmNome_oficial_equip_urbano(
							row.getField(NOME_OFICIAL_EQUIP_URBANO_INDEX));
						square.setmArea(parseStringToDouble(
							row.getField(AREA_INDEX), 0));
						square.setmPerimetro(parseStringToDouble(
							row.getField(PERIMETRO_INDEX), 0));
						square.setmLatitude(parseStringToDouble(
							row.getField(LATITUDE_INDEX), 0));
						square.setmLongitude(parseStringToDouble(
							row.getField(LONGITUDE_INDEX), 0));

						//verificar se já existe um bairro com o id cadastrado
						if (id_bairro > 0) {
							AppDatabase appDatabase = AppDatabase.getAppDatabase(mContext);
							Logradouro logradouro = appDatabase.logradouroDao().findById(id_bairro);

							if (logradouro == null) { //então esse logradouro nunca foi inserido no banco
								logradouro = new Logradouro();
								logradouro.setDescricao(desc_bairro);
								logradouro.setId(id_bairro);
								appDatabase.logradouroDao().insertAll(logradouro);
							}
						}
						squareList.add(square);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return squareList;
	}

	private double parseStringToDouble(String value, double defaultValue) {
		double result = defaultValue;
		if (value != null && !value.isEmpty()) {
			try {
				result = Double.parseDouble(value.replace(',', '.'));
			} catch (Exception e) {
				Log.d(TAG, "Error trying to convert " + value + " to double");
			}
		}
		return result;
	}
}
