package org.iesvdm.pruebaud3.Dao;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.pruebaud3.Modelo.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;



@Slf4j
@Repository
public class PeliculaDAOImp implements PeliculaDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public synchronized void create(Pelicula pelicula) {
        //Desde java15+ se tiene la triple quote """ para bloques de texto como cadenas.
        String sqlInsert = """
							INSERT INTO pelicula (titulo, descripcion, fecha_lanzamiento, id_idioma, duracion_alquiler,
							rental_rate, duracion, replacement_cost, ultima_actualizacion)
							VALUES  (     ?,         ?,         ?,       ?,         ?,  ?,  ?,  ?,  ?)
									""";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        //Con recuperación de id generado
        int rows = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlInsert, new String[] { "id_pelicula" });
            int idx = 1;
            ps.setString(idx++, pelicula.getTitulo());
            ps.setString(idx++, pelicula.getDescripcion());

            Date fechaLanzamiento = new Date(pelicula.getFecha_lanzamiento().getTime());
            ps.setDate(idx++, fechaLanzamiento);
            ps.setInt(idx++, pelicula.getId_idioma());
            ps.setInt(idx++, pelicula.getDuracion_alquiler());
            ps.setDouble(idx++, pelicula.getRental_rate());
            ps.setInt(idx++, pelicula.getDuracion());
            ps.setDouble(idx++, pelicula.getReplacement_cost());

            Date fechaUltimaActualizacion = new Date(pelicula.getUltima_actualizacion().getTime());
            ps.setDate(idx, fechaUltimaActualizacion);
            return ps;
        },keyHolder);

        pelicula.setId_pelicula(Objects.requireNonNull(keyHolder.getKey()).intValue());

        log.info("Insertados {} registros.", rows);
    }



    @Override
    public List<Pelicula> getAll() {
        List<Pelicula> listPel = jdbcTemplate.query(
                "SELECT * FROM pelicula",
                (rs, rowNum) -> new Pelicula(rs.getInt("id_pelicula"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getDate("fecha_lanzamiento"),
                        rs.getInt("id_idioma"),
                        rs.getInt("duracion_alquiler"),
                        rs.getDouble("rental_rate"),
                        rs.getInt("duracion"),
                        rs.getDouble("replacement_cost"),
                        rs.getDate("ultima_actualizacion")
                )
        );

        log.info("Devueltos {} registros.", listPel.size());

        return listPel;
    }


    @Override
    public Optional<Pelicula> find(int id) {
        Pelicula pel =  jdbcTemplate
                .queryForObject("SELECT * FROM pelicula WHERE id = ?"
                        , (rs, rowNum) -> new Pelicula(rs.getInt("id_pelicula"),
                                rs.getString("titulo"),
                                rs.getString("descripcion"),
                                rs.getDate("fecha_lanzamiento"),
                                rs.getInt("id_idioma"),
                                rs.getInt("duracion_alquiler"),
                                rs.getDouble("rental_rate"),
                                rs.getInt("duracion"),
                                rs.getDouble("replacement_cost"),
                                rs.getDate("ultima_actualizacion"))
                        , id
                );

        if (pel != null) {
            return Optional.of(pel);}
        else {
            log.info("Pelicula no encontrado.");
            return Optional.empty(); }
    }


    @Override
    public void update(Pelicula pelicula) {
        int rows = jdbcTemplate.update("""
										UPDATE pelicula SET
														titulo = ?,
														descripcion = ?,
														fecha_lanzamiento = ?,
														id_idioma = ?,
														duracion_alquiler = ?,
														rental_rate = ?,
														duracion = ?,
														replacement_cost = ?,
														ultima_actualizacion = ?
												WHERE id_pelicula = ?
										""", pelicula.getTitulo()
                , pelicula.getDescripcion()
                , pelicula.getFecha_lanzamiento()
                , pelicula.getId_idioma()
                , pelicula.getDuracion_alquiler()
                , pelicula.getRental_rate()
                , pelicula.getDuracion()
                , pelicula.getReplacement_cost()
                , pelicula.getUltima_actualizacion()
                , pelicula.getId_pelicula());

        log.info("Update de Cliente con {} registros actualizados.", rows);

    }

    @Override
    public void delete(long id) {
        int rows = jdbcTemplate.update("DELETE FROM pelicula WHERE id = ?", id);
        log.info("Delete de Pelicula con {} registros eliminados.", rows);
    }
}