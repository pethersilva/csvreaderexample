package br.org.cesar.csvreaderexample.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "square")
public class Square {

	@PrimaryKey(autoGenerate = true)
	private int id;

	@ColumnInfo(name = "nome_equip_urbano")
	private String nome_equip_urbano;

	@ColumnInfo(name = "tipo_equip_urbano")
	private String tipo_equip_urbano;

	@ColumnInfo(name = "endereco_equip_urbano")
	private String endereco_equip_urbano;

	@ColumnInfo(name = "codigo_logradouro")
	private double codigo_logradouro;

	@ColumnInfo(name = "lei_equip_urbano")
	private String lei_equip_urbano;

	@ColumnInfo(name = "nome_bairro")
	private String nome_bairro;

	@ColumnInfo(name = "codigo_bairro")
	private double codigo_bairro;

	@ColumnInfo(name = "nome_oficial_equip_urbano")
	private String nome_oficial_equip_urbano;

	@ColumnInfo(name = "area")
	private double area;

	@ColumnInfo(name = "perimetro")
	private double perimetro;

	@ColumnInfo(name = "latitude")
	private double latitude;

	@ColumnInfo(name = "longitude")
	private double longitude;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome_equip_urbano() {
		return nome_equip_urbano;
	}

	public void setNome_equip_urbano(String nome_equip_urbano) {
		this.nome_equip_urbano = nome_equip_urbano;
	}

	public String getTipo_equip_urbano() {
		return tipo_equip_urbano;
	}

	public void setTipo_equip_urbano(String tipo_equip_urbano) {
		this.tipo_equip_urbano = tipo_equip_urbano;
	}

	public String getEndereco_equip_urbano() {
		return endereco_equip_urbano;
	}

	public void setEndereco_equip_urbano(String endereco_equip_urbano) {
		this.endereco_equip_urbano = endereco_equip_urbano;
	}

	public double getCodigo_logradouro() {
		return codigo_logradouro;
	}

	public void setCodigo_logradouro(double codigo_logradouro) {
		this.codigo_logradouro = codigo_logradouro;
	}

	public String getLei_equip_urbano() {
		return lei_equip_urbano;
	}

	public void setLei_equip_urbano(String lei_equip_urbano) {
		this.lei_equip_urbano = lei_equip_urbano;
	}

	public String getNome_bairro() {
		return nome_bairro;
	}

	public void setNome_bairro(String nome_bairro) {
		this.nome_bairro = nome_bairro;
	}

	public double getCodigo_bairro() {
		return codigo_bairro;
	}

	public void setCodigo_bairro(double codigo_bairro) {
		this.codigo_bairro = codigo_bairro;
	}

	public String getNome_oficial_equip_urbano() {
		return nome_oficial_equip_urbano;
	}

	public void setNome_oficial_equip_urbano(String nome_oficial_equip_urbano) {
		this.nome_oficial_equip_urbano = nome_oficial_equip_urbano;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public double getPerimetro() {
		return perimetro;
	}

	public void setPerimetro(double perimetro) {
		this.perimetro = perimetro;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
