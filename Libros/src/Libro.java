import java.util.*;

public abstract class Libro {
    private String titulo;
    private String autor;
    private String categoria;
    private String isbn;
    private double precio;

    // Constructor
    public Libro(String titulo, String autor, String categoria, String isbn, double precio) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.isbn = isbn;
        this.precio = precio;
    }

    // Getters y Setters
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getIsbn() {
        return isbn;
    }

    public double getPrecio() {
        return precio;
    }

    // Método abstracto para mostrar información
    public abstract String mostrarInformacion();
}
