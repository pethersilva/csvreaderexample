package br.org.cesar.csvreaderexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.org.cesar.csvreaderexample.model.Square;
import br.org.cesar.csvreaderexample.service.SquareService;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SquareService squareService = new SquareService(this);
		squareService.getSquare();
	}
}
