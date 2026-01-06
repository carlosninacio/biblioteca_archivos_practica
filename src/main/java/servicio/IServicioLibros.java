package servicio;

import entidad.Libro;

import java.util.List;

public interface IServicioLibros {
    void agregarLibro(Libro libro);
    void mostrarLibros();
    List<Libro> getLibros();
}