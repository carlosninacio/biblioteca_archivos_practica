package servicio;

import entidad.Libro;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ServicioLibros implements IServicioLibros {

    private final String NOMBRE_ARCHIVO = "biblioteca.txt";
    private List<Libro> libros = new ArrayList<>();

    public ServicioLibros() {
        var archivo = new File(NOMBRE_ARCHIVO);
        var existe = false;
        try {
            if(existe) {
                this.libros = obtenerLibros();
            } else {
                var salida = new PrintWriter(new FileWriter(archivo));
                salida.close();
                System.out.println("Se ha creado el archivo!");
            }
        } catch (Exception e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
        if(!existe) {
            cargarLibrosIniciales();
        }
    }

    private void cargarLibrosIniciales() {
        this.agregarLibro(new Libro("El principito", "Antoine de Saint-Exupéry", 1943, "Clásico"));
        this.agregarLibro(new Libro("Cien años de soledad", "Gabriel García Márquez", 1967, "Realismo mágico"));
    }

    private List<Libro> obtenerLibros() {
        var libros = new ArrayList<Libro>();
        try {
            List<String> lineas = Files.readAllLines(Paths.get(NOMBRE_ARCHIVO));
            for (String linea: lineas) {
                 String[] lineaLibro = linea.split(",");
                 var idLibro = lineaLibro[0];
                 var titulo = lineaLibro[1];
                 var autor = lineaLibro[2];
                 var anio = Integer.parseInt(lineaLibro[3]);
                 var genero = lineaLibro[4];
                 var libro = new Libro(titulo, autor, anio, genero);
                 libros.add(libro);
            }
        } catch (Exception e) {
            System.out.println("Error al leer archivo de libros: " + e.getMessage());
        }
        return libros;
    }

    @Override
    public void agregarLibro(Libro libro) {
        this.libros.add(libro);
        this.agregarLibroArchivo(libro);
    }

    private void agregarLibroArchivo(Libro libro) {
        boolean anexar = false;
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            anexar = archivo.exists();
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            salida.println(libro.escribirLibro());
            salida.close();
        } catch (Exception e) {
            System.out.println("Error al agregar libro: " + e.getMessage());
        }
    }

    @Override
    public void mostrarLibros() {
        System.out.println("--- Libros Disponibles ---");
        var inventarioLibros = "";
        for (var libro: this.libros) {
            inventarioLibros += libro.toString() + "\n";
        }
        System.out.println(inventarioLibros);
    }

    @Override
    public List<Libro> getLibros() {
        return this.libros;
    }
}