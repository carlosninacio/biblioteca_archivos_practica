package presentacion;

import entidad.Libro;
import servicio.IServicioLibros;
import servicio.ServicioLibros;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Biblioteca {
    public static void main(String[] args) {
        bibliotecaFuncionamiento();
    }

    public static void bibliotecaFuncionamiento() {
        var salir = false;
        var consola = new Scanner(System.in);
        IServicioLibros servicioLibros = new ServicioLibros();
        List<Libro> carritoPrestamo = new ArrayList<>();
        System.out.println("*** BIENVENIDO A LA BIBLIOTECA ***");
        servicioLibros.mostrarLibros();
        while (!salir) {
            try {
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(opcion, consola, carritoPrestamo, servicioLibros);
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }
        }
    }

    private static int mostrarMenu(Scanner consola) {
        System.out.println("""
                Menú:
                1. Elegir libro para prestamo
                2. Mostrar Ticket
                3. Agregar Nuevo Libro
                4. Inventario Libros
                5. Salir
                
                Elige una opción:\s""");
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(int opcion, Scanner consola, List<Libro> carritoPrestamo, IServicioLibros servicioLibros) {
        var salir = false;
        switch (opcion) {
            case 1 -> comprarLibro(consola, carritoPrestamo, servicioLibros);
            case 2 -> mostrarTicket(carritoPrestamo);
            case 3 -> agregarLibro(consola, servicioLibros);
            case 4 -> listarInventarioLibros(servicioLibros);
            case 5 -> {
                System.out.println("Regresa pronto!");
                salir = true;
            }
            default -> System.out.println("Opción inválida: " + opcion);
        }
        return salir;
    }

    private static void comprarLibro(Scanner consola, List<Libro> carritoPrestamo, IServicioLibros servicioLibros) {
        System.out.print("Qué libro deseas alquilar (ID)?: ");
        var idLibro = Integer.parseInt(consola.nextLine());
        var libroEncontrado = false;
        for (var libro: servicioLibros.getLibros()) {
            if(idLibro == libro.getIdLibro()) {
                carritoPrestamo.add(libro);
                System.out.println("Libro agregado: " + libro);
                libroEncontrado = true;
                break;
            }
        }
        if (!libroEncontrado) {
            System.out.println("Id de libro no encontrado: " + idLibro);
        }
    }

    private static void mostrarTicket(List<Libro> carritoPrestamo) {
        var ticket = "*** Factura de Préstamo";
        for (var libro: carritoPrestamo) {
            ticket += "\n\t-" + libro.getTitulo() + " - " + libro.getAnio() + " - " + libro.getGenero();
        }
        System.out.println(ticket);
    }

    private static void agregarLibro(Scanner consola, IServicioLibros servicioLibros) {
        System.out.print("Nombre del libro: ");
        var titulo = consola.nextLine();
        System.out.print("Autor del libro: ");
        var autor = consola.nextLine();
        System.out.print("Año de publicación del libro: ");
        var anio = Integer.parseInt(consola.nextLine());
        System.out.print("Genero del libro: ");
        var genero = consola.nextLine();
        servicioLibros.agregarLibro(new Libro(titulo,autor,anio,genero));
        servicioLibros.mostrarLibros();
    }

    private static void listarInventarioLibros(IServicioLibros servicioLibros) {
        servicioLibros.mostrarLibros();
    }
}