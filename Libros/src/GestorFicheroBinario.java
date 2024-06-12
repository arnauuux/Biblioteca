import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorFicheroBinario {
    private String filename; // Nombre del archivo

    public GestorFicheroBinario(String filename) {
        this.filename = filename; // Asignar el nombre del archivo proporcionado al atributo filename
    }

    // Método para agregar un libro al archivo binario
    public void agregarLibro(Libro libro) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename, true))) {
            oos.writeObject(libro); // Escribir el objeto libro en el archivo
        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero: " + e.getMessage()); // Manejo de excepciones en caso de error
        }
    }

    // Método para leer los libros desde el archivo binario
    public List<Libro> leerLibros() {
        List<Libro> libros = new ArrayList<>(); // Lista para almacenar los libros leídos
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            while (true) {
                try {
                    libros.add((Libro) ois.readObject()); // Leer un objeto Libro y agregarlo a la lista
                } catch (EOFException eof) {
                    break; // Terminar el bucle cuando se alcance el final del archivo
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage()); // Manejo de excepciones en caso de error
        }
        return libros; // Devolver la lista de libros leídos
    }
}
