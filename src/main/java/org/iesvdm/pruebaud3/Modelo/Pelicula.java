package org.iesvdm.pruebaud3.Modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
//Para generar un constructor con lombok con todos los args
@AllArgsConstructor
public class Pelicula {

    private int id_pelicula;
    private String titulo;
    private String descripcion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_lanzamiento;
    private int id_idioma;
    private int duracion_alquiler;
    private double rental_rate;
    private int duracion;
    private double replacement_cost;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ultima_actualizacion;

    public Pelicula(){

    }
}