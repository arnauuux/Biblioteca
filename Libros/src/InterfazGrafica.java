import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class InterfazGrafica extends JFrame {
    private JTextField tituloField, autorField, categoriaField, isbnField, precioField, tamanoField, pesoField;
    private GestorFicheroSecuencial gestorSecuencial;
    private GestorFicheroBinario gestorBinario;

    // Constructor de la interfaz gráfica
    public InterfazGrafica() {
        setTitle("Gestión de Libros"); // Establece el título de la ventana
        setSize(400, 500); // Establece el tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define el comportamiento de cierre de la ventana
        gestorSecuencial = new GestorFicheroSecuencial("libros.txt"); // Inicializa el gestor de archivos secuenciales
        gestorBinario = new GestorFicheroBinario("libros.dat"); // Inicializa el gestor de archivos binarios

        JPanel panel = new JPanel(); // Crea un panel para organizar los componentes
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Establece un diseño de caja vertical para el panel

        // Agrega etiquetas y campos de texto para cada atributo del libro
        panel.add(new JLabel("Título:"));
        tituloField = new JTextField(20);
        panel.add(tituloField);

        panel.add(new JLabel("Autor:"));
        autorField = new JTextField(20);
        panel.add(autorField);

        panel.add(new JLabel("Categoría:"));
        categoriaField = new JTextField(20);
        panel.add(categoriaField);

        panel.add(new JLabel("ISBN:"));
        isbnField = new JTextField(20);
        panel.add(isbnField);

        panel.add(new JLabel("Precio:"));
        precioField = new JTextField(20);
        panel.add(precioField);

        panel.add(new JLabel("Tamaño (MB) - Solo para libros digitales:"));
        tamanoField = new JTextField(20);
        panel.add(tamanoField);

        panel.add(new JLabel("Peso (kg) - Solo para libros físicos:"));
        pesoField = new JTextField(20);
        panel.add(pesoField);

        // Agrega botones para realizar acciones
        JButton agregarDigitalButton = new JButton("Agregar Libro Digital");
        agregarDigitalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarLibroDigital();
            }
        });
        panel.add(agregarDigitalButton);

        JButton agregarFisicoButton = new JButton("Agregar Libro Físico");
        agregarFisicoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarLibroFisico();
            }
        });
        panel.add(agregarFisicoButton);

        JButton mostrarLibrosButton = new JButton("Mostrar Libros");
        mostrarLibrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarLibros();
            }
        });
        panel.add(mostrarLibrosButton);

        add(panel); // Agrega el panel a la ventana
    }

    // Método para agregar un libro digital
    private void agregarLibroDigital() {
        // Manejo de errores al intentar convertir campos de texto en valores numéricos
        try {
            // Extrae los datos de los campos de texto
            String titulo = tituloField.getText();
            String autor = autorField.getText();
            String categoria = categoriaField.getText();
            String isbn = isbnField.getText();
            double precio = Double.parseDouble(precioField.getText());
            double tamano = Double.parseDouble(tamanoField.getText());

            // Crea un objeto LibroDigital con los datos y lo agrega a los gestores de archivos
            LibroDigital libro = new LibroDigital(titulo, autor, categoria, isbn, precio, tamano);
            gestorSecuencial.agregarLibro(libro);
            gestorBinario.agregarLibro(libro);
            JOptionPane.showMessageDialog(this, "Libro Digital agregado"); // Muestra un mensaje de confirmación
        } catch (NumberFormatException ex) {
            // Manejo de error si los campos de precio o tamaño no son números válidos
            JOptionPane.showMessageDialog(this, "Error: Precio o tamaño no son números válidos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para agregar un libro físico
    private void agregarLibroFisico() {
        // Manejo de errores al intentar convertir campos de texto en valores numéricos
        try {
            // Extrae los datos de los campos de texto
            String titulo = tituloField.getText();
            String autor = autorField.getText();
            String categoria = categoriaField.getText();
            String isbn = isbnField.getText();
            double precio = Double.parseDouble(precioField.getText());
            double peso = Double.parseDouble(pesoField.getText());

            // Crea un objeto LibroFisico con los datos y lo agrega a los gestores de archivos
            LibroFisico libro = new LibroFisico(titulo, autor, categoria, isbn, precio, peso);
            gestorSecuencial.agregarLibro(libro);
            gestorBinario.agregarLibro(libro);
            JOptionPane.showMessageDialog(this, "Libro Físico agregado"); // Muestra un mensaje de confirmación
        } catch (NumberFormatException ex) {
            // Manejo de error si los campos de precio o peso no son números válidos
            JOptionPane.showMessageDialog(this, "Error: Precio o peso no son números válidos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para mostrar la lista de libros almacenados
    private void mostrarLibros() {
        // Lee los libros almacenados en los gestores de archivos
        List<String> librosSecuencial = gestorSecuencial.leerLibros();
        JOptionPane.showMessageDialog(this, "Libros (Fichero Secuencial):\n" + String.join("\n", librosSecuencial));

        List<Libro> librosBinario = gestorBinario.leerLibros();
        StringBuilder librosBinarioStr = new StringBuilder("Libros (Fichero Binario):\n");
        for (Libro libro : librosBinario) {
            librosBinarioStr.append(libro.mostrarInformacion()).append("\n");
        }
        JOptionPane.showMessageDialog(this, librosBinarioStr.toString());
    }

    // Método main para iniciar la aplicación
    public static void main(String[] args) {
        // Crear una instancia de la interfaz gráfica y hacerla visible
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                InterfazGrafica app = new InterfazGrafica();
                app.setVisible(true);
            }
        });
    }
}
