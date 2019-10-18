package br.org.cesar.csvreaderexample.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "logradouro")
public class Logradouro implements Serializable {

	@PrimaryKey()
	private double id;

	@ColumnInfo(name = "descricao")
	private String descricao;

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
