package org.iesvdm.pruebaud3.controlador;

import org.iesvdm.pruebaud3.Modelo.Pelicula;
import org.iesvdm.pruebaud3.Service.PeliculaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class PeliculaController {

    private PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    //@RequestMapping(value = "/clientes", method = RequestMethod.GET)
    //equivalente a la siguiente anotación
    @GetMapping({"/peliculas", "/films"}) //Al no tener ruta base para el controlador, cada método tiene que tener la ruta completa
    public String listar(Model model) {
        List<Pelicula> listaPeliculas =  peliculaService.listAll();
        model.addAttribute("listaPeliculas", listaPeliculas);

        return "peliculas";
    }

    //Editar una Pelicula
    @GetMapping("/peliculas/editar/{id}")
    public String editar(Model model, @PathVariable Integer id) {
        Pelicula pelicula = peliculaService.one(id);
        model.addAttribute("pelicula", pelicula);

        return "editar-pelicula";
    }
    @PostMapping("/peliculas/editar/{id}")
    public RedirectView submitEditar(@ModelAttribute("pelicula") Pelicula pelicula) {
        peliculaService.replacePelicula(pelicula);

        return new RedirectView("/peliculas");
    }

    //Mostrar detalles de Pelicula
    @GetMapping("/peliculas/{id}")
    public String detalle(Model model, @PathVariable Integer id ) {

        Pelicula pelicula = peliculaService.one(id);
        model.addAttribute("pelicula", pelicula);

        return "detalle-pelicula";
    }

    //Crear nueva Pelicula
    @GetMapping("/peliculas/crear")
    public String crear(Model model) {

        Pelicula pelicula = new Pelicula();
        model.addAttribute("pelicula", pelicula);

        return "crear-pelicula";
    }

    @PostMapping("/peliculas/crear")
    public RedirectView submitCrear(@ModelAttribute("pelicula") Pelicula pelicula) {

        peliculaService.crearPelicula(pelicula);

        return new RedirectView("/peliculas") ;
    }

    //Borrar Cliente
    @PostMapping("/peliculas/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id) {
        peliculaService.deletePelicula(id);

        return new RedirectView("/peliculas");
    }
}