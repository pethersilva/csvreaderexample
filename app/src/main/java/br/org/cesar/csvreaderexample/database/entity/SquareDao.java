package br.org.cesar.csvreaderexample.database.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SquareDao {

    @Query("SELECT * FROM square")
    List<Square> getAll();

    @Query("SELECT * FROM square where codigo_bairro = :cod_bairro")
    List<Square> getSquaresByLogradouro(double cod_bairro);

    @Insert
    void insertAll(Square... squares);

    @Delete
    void delete(Square square);
}