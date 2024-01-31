package org.iesvdm.pruebaud3.Service;


import lombok.extern.slf4j.Slf4j;
import org.iesvdm.pruebaud3.Dao.PeliculaDAO;
import org.iesvdm.pruebaud3.Modelo.Pelicula;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PeliculaService {

    private PeliculaDAO peliculaDAO;

    public PeliculaService(PeliculaDAO peliculaDAO) {
        this.peliculaDAO = peliculaDAO;
    }

    public List<Pelicula> listAll() {
        return peliculaDAO.getAll();
    }


    //Método que comprueba si la Pelicula que hemos buscado existe
    public Pelicula one(Integer id) {
        Optional<Pelicula> optCli = peliculaDAO.find(id);
        return optCli.orElse(null);
    }

    //Editar una Pelicula existente
    public void replacePelicula(Pelicula pelicula) {
        peliculaDAO.update(pelicula);
    }


    //Crear Pelicula
    public void crearPelicula(Pelicula pelicula){
        peliculaDAO.create(pelicula);
        log.info("ID cliente creado {}", pelicula.getId_pelicula());
    }

    //Borrar Cliente
    public void deletePelicula(int id) {
        peliculaDAO.delete(id);
    }









}