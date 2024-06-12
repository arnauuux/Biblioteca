import java.util.List;

public class LibroDigital extends Libro {
    private double tamano;

    // Constructor
    public LibroDigital(String titulo, String autor, String categoria, String isbn, double precio, double tamano) {
        super(titulo, autor, categoria, isbn, precio);
        this.tamano = tamano;
    }

    // Getter y Setter para el atributo 'tamano'
    public double getTamano() {
        return tamano;
    }

    public void setTamano(double tamano) {
        this.tamano = tamano;
    }

    // Implementación del método abstracto mostrarInformacion
    @Override
    public String mostrarInformacion() {
        return super.getTitulo() + " - " + super.getAutor() + " - " + super.getCategoria() + " - " + super.getIsbn() + " - " + super.getPrecio() + " - " + tamano + "MB";
    }
}
