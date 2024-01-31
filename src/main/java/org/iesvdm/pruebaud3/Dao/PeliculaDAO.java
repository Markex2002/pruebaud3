package org.iesvdm.pruebaud3.Dao;

import org.iesvdm.pruebaud3.Modelo.Pelicula;

import java.util.List;
import java.util.Optional;

public interface PeliculaDAO {
    public void create(Pelicula pelicula);

    public List<Pelicula> getAll();
    public Optional<Pelicula> find(int id);

    public void update(Pelicula pelicula);

    public void delete(long id);
}