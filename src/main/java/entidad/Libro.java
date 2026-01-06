package entidad;

import java.io.Serializable;
import java.util.Objects;

public class Libro implements Serializable {

    private static int contadorLibros = 0;
    private int idLibro;
    private String titulo;
    private String autor;
    private int anio;
    private String genero;

    public Libro() {
        this.idLibro = ++Libro.contadorLibros;
    }

    public Libro (String titulo, String autor, int anio, String genero) {
        this();
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.genero = genero;
    }

    public static int getContadorLibros() {
        return contadorLibros;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "idLibro=" + idLibro +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", anio=" + anio +
                ", genero='" + genero + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return idLibro == libro.idLibro && anio == libro.anio && Objects.equals(titulo, libro.titulo) && Objects.equals(autor, libro.autor) && Objects.equals(genero, libro.genero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLibro, titulo, autor, anio, genero);
    }
}