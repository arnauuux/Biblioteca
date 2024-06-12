import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorFicheroSecuencial {
    private String filename; // Nombre del archivo

    public GestorFicheroSecuencial(String filename) {
        this.filename = filename; // Asignar el nombre del archivo proporcionado al atributo filename
    }

    // Método para agregar un libro al archivo secuencial
    public void agregarLibro(Libro libro) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(libro.toString() + "\n"); // Escribir la representación en cadena del libro en el archivo
        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero: " + e.getMessage()); // Manejo de excepciones en caso de error
        }
    }

    // Método para leer los libros desde el archivo secuencial
    public List<String> leerLibros() {
        List<String> libros = new ArrayList<>(); // Lista para almacenar los libros leídos
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String linea;
            while ((linea = reader.readLine()) != null) { // Leer línea por línea hasta llegar al final del archivo
                libros.add(linea); // Agregar la línea (representando un libro) a la lista de libros
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage()); // Manejo de excepciones en caso de error
        }
        return libros; // Devolver la lista de libros leídos
    }

    // Método para modificar un libro en el archivo secuencial
    public void modificarLibro(String isbn, Libro nuevoLibro) {
        List<String> libros = leerLibros(); // Obtener la lista actual de libros
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String libro : libros) {
                if (libro.contains(isbn)) { // Si el libro actual contiene el ISBN a modificar
                    writer.write(nuevoLibro.toString() + "\n"); // Escribir el nuevo libro en lugar del antiguo
                } else {
                    writer.write(libro + "\n"); // Escribir el libro sin modificar
                }
            }
        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero: " + e.getMessage()); // Manejo de excepciones en caso de error
        }
    }

    // Método para eliminar un libro del archivo secuencial
    public void eliminarLibro(String isbn) {
        List<String> libros = leerLibros(); // Obtener la lista actual de libros
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String libro : libros) {
                if (!libro.contains(isbn)) { // Si el libro actual no contiene el ISBN a eliminar
                    writer.write(libro + "\n"); // Escribir el libro en el nuevo archivo
                }
            }
        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero: " + e.getMessage()); // Manejo de excepciones en caso de error
        }
    }
}
