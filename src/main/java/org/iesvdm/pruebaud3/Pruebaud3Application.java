package org.iesvdm.pruebaud3;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.pruebaud3.Dao.PeliculaDAO;
import org.iesvdm.pruebaud3.Modelo.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@Slf4j
@SpringBootApplication
public class Pruebaud3Application {

    @Autowired
    private PeliculaDAO peliculaDAO;

    public static void main(String[] args) {
        SpringApplication.run(Pruebaud3Application.class, args);
    }


    public void run(String... args) throws Exception {
        log.info("*******************************");
        log.info("*Prueba de arranque PeliculaDAO*");
        log.info("*******************************");

        peliculaDAO.getAll().forEach(c -> log.info("Pelicula: {}", c));

        int idPelicula = 1;
        Optional<Pelicula> pelicula = peliculaDAO.find(idPelicula);

       /* if (pelicula.isPresent()) {
            log.info("Cliente {}: {}", idPelicula, pelicula.get());

            String tituloOld = pelicula.get().getTitulo();

            pelicula.get().setNombre("Jose M");

            clienteDAO.update(cliente.get());

            cliente = clienteDAO.find(idCliente);

            log.info("Cliente {}: {}", idCliente, cliente.get());

            //Volvemos a cargar el nombre antiguo..
            cliente.get().setNombre(nombreOld);
            clienteDAO.update(cliente.get());

        }

        // Como es un cliente nuevo a persistir, id a 0
        Cliente clienteNew = new Cliente(0, "Jose M", "Martín", null, "Málaga", 100);

        //create actualiza el id
        clienteDAO.create(clienteNew);

        log.info("Cliente nuevo con id = {}", clienteNew.getId());

        clienteDAO.getAll().forEach(c -> log.info("Cliente: {}", c));

        //borrando por el id obtenido de create
        clienteDAO.delete(clienteNew.getId());

        clienteDAO.getAll().forEach(c -> log.info("Cliente: {}", c));
*/
        log.info("************************************");
        log.info("*FIN: Prueba de arranque ClienteDAO*");
        log.info("************************************");
    }
}