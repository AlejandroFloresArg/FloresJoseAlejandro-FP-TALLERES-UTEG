import java.awt.*; // Librería AWT para crear ventanas y componentes gráficos
import java.awt.event.*; // Para manejar eventos (clics, cierre de ventana, etc.)
import java.io.*; // Archivos de textos.
import java.util.ArrayList; // Listas dinamicas

public class Taller4FundamentosAWT_ALE extends Frame {

    // Cada factura guarda: [0]=número, [1]=cliente, [2]=producto, [3]=cantidad, [4]=total
    ArrayList<String[]> facturas = new ArrayList<>();
    int contador = 1; 
    TextArea area = new TextArea(); 

    public Taller4FundamentosAWT_ALE() {
        setTitle("Facturas AWT");
        setSize(600, 400);
        setLayout(new BorderLayout()); 

        area.setEditable(false); 
        add(area, BorderLayout.CENTER); 

        // Creo los 4 botones y los agrega al panel inferior
        Panel botones = new Panel(new GridLayout(1, 4)); // 1 fila, 4 columnas
        String[] nombres = {"1.Registrar", "2.Consultar", "3.Exportar", "4.Salir"};
        for (String nombre : nombres) {
            Button btn = new Button(nombre);
            btn.addActionListener(e -> accion(nombre.charAt(0))); // Envía '1','2','3' o '4' al método accion()
            botones.add(btn);
        }
        add(botones, BorderLayout.SOUTH); // Los botones van en la parte inferior

        // Permite cerrar la ventana con la X
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });

        setVisible(true); // Sin esto la ventana no aparece en pantalla
    }

    // Metodo que ejecuta la acción según el botón presionado
    void accion(char opcion) {
        switch (opcion) {

            case '1': // REGISTRAR
                Dialog d = new Dialog(this, "Nueva Factura", true); // true = bloquea la ventana principal hasta cerrar este diálogo
                d.setSize(300, 200);
                d.setLayout(new GridLayout(5, 2, 5, 5)); // 5 filas, 2 columnas (etiqueta + campo)

                // Campos donde el usuario ingresa los datos
                TextField tCliente  = new TextField();
                TextField tProducto = new TextField();
                TextField tCantidad = new TextField();
                TextField tPrecio   = new TextField();

                d.add(new Label("Cliente:"));   d.add(tCliente);
                d.add(new Label("Producto:"));  d.add(tProducto);
                d.add(new Label("Cantidad:"));  d.add(tCantidad);
                d.add(new Label("Precio $:"));  d.add(tPrecio);

                Button guardar = new Button("Guardar");
                d.add(guardar);

                guardar.addActionListener(ev -> {
                    try {
                        // Convertimos texto a número para poder calcular el total
                        double precio   = Double.parseDouble(tPrecio.getText());
                        int    cantidad = Integer.parseInt(tCantidad.getText());
                        double total    = precio * cantidad;

                        // Guardamos todos los datos de la factura en la lista
                        facturas.add(new String[]{
                            String.valueOf(contador++), // Asigna el número actual y luego suma 1
                            tCliente.getText(),
                            tProducto.getText(),
                            String.valueOf(cantidad),
                            String.format("%.2f", total) // %.2f muestra el número con 2 decimales
                        });

                        actualizarArea(); // Refresca la lista visible en pantalla
                        d.dispose();     // Cierra el diálogo
                    } catch (NumberFormatException ex) {
                        // Se activa si el usuario escribe letras en cantidad o precio
                        area.append(">> Error: ingresa números válidos.\n");
                    }
                });

                // Permite cerrar el diálogo con la X sin que el programa falle
                d.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) { d.dispose(); }
                });
                d.setVisible(true);
                break;

            case '2': // CONSULTAR
                Dialog dc = new Dialog(this, "Consultar", true);
                dc.setSize(260, 100);
                dc.setLayout(new FlowLayout()); // Los elementos se acomodan uno al lado del otro
                TextField tNum = new TextField(10);
                Button buscar  = new Button("Buscar");
                dc.add(new Label("N° Factura:")); dc.add(tNum); dc.add(buscar);

                buscar.addActionListener(ev -> {
                    String num = tNum.getText().trim(); // .trim() elimina espacios accidentales
                    boolean encontrada = false;

                    // Recorre la lista buscando la factura con ese numero
                    for (String[] f : facturas) {
                        if (f[0].equals(num)) { // f[0] es el número de factura
                            area.append("\n>> Factura #" + f[0]
                                + " | Cliente: "  + f[1]
                                + " | Producto: " + f[2]
                                + " | Cantidad: " + f[3]
                                + " | Total: $"   + f[4] + "\n");
                            encontrada = true;
                            break; 
                        }
                    }

                    // Mensaje requerido por el enunciado si no existe la factura
                    if (!encontrada)
                        area.append(">> Factura no se encuentra registrada.\n");

                    dc.dispose();
                });

                dc.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) { dc.dispose(); }
                });
                dc.setVisible(true);
                break;

            case '3': // EXPORTAR
                // try-with-resources: abre el archivo y lo cierra automáticamente al terminar
                try (PrintWriter pw = new PrintWriter("facturas.txt")) {
                    for (String[] f : facturas) // Escribe una línea por cada factura
                        pw.println("Factura #" + f[0] + " | " + f[1] + " | "
                            + f[2] + " | Cant: " + f[3] + " | Total: $" + f[4]);

                    area.append(">> Archivo 'facturas.txt' generado.\n");
                } catch (IOException ex) {
                    area.append(">> Error al exportar.\n");
                }
                break;

            case '4': // SALIR
                System.exit(0); // Cierra completamente el programa
        }
    }

    // Limpia el area y vuelve a mostrar todas las facturas actualizadas
    void actualizarArea() {
        area.setText("=== FACTURAS REGISTRADAS ===\n"); // Borra lo anterior y pone el titulo
        for (String[] f : facturas)
            area.append("#" + f[0] + " | " + f[1] + " | " + f[2]
                + " | Cant: " + f[3] + " | Total: $" + f[4] + "\n");
    }

    public static void main(String[] args) {
        new Taller4FundamentosAWT_ALE(); // Crea y lanza la ventana principal
    }
}