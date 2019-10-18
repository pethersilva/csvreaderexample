package br.org.cesar.csvreaderexample.database.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LogradouroDao {

	@Query("SELECT * FROM logradouro")
	List<Logradouro> getAll();

	@Query("SELECT * FROM logradouro where descricao LIKE :descricao")
	Logradouro findByName(String descricao);

	@Query("SELECT * FROM logradouro where id = :id")
	Logradouro findById(double id);

	@Query("SELECT COUNT(*) from logradouro")
	int countUsers();

	@Insert
	void insertAll(Logradouro... logradouros);

	@Delete
	void delete(Logradouro logradouro);
}
