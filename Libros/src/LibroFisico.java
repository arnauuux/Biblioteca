import java.util.List;

public class LibroFisico extends Libro {
    private double peso;

    // Constructor
    public LibroFisico(String titulo, String autor, String categoria, String isbn, double precio, double peso) {
        super(titulo, autor, categoria, isbn, precio);
        this.peso = peso;
    }

    // Getter y Setter para el atributo 'peso'
    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    // Implementación del método abstracto mostrarInformacion
    @Override
    public String mostrarInformacion() {
        return super.getTitulo() + " - " + super.getAutor() + " - " + super.getCategoria() + " - " + super.getIsbn() + " - " + super.getPrecio() + " - " + peso + "kg";
    }
}
