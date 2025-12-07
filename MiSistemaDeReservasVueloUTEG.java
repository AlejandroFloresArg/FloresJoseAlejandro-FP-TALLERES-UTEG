import java.util.Scanner;

public class MiSistemaDeReservasVueloUTEG {

    public static void main(String[] args) {
        // Mis variables iniciales para iniciar el sistema
        int asientosDisponibles = 100;
        double precioBoleto = 500.00; // Precio base para calcular multas
        
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        // Ciclo del Menú Principal
        do {
            System.out.println("\n--- SISTEMA DE RESERVAS UTEG ---");
            System.out.println("1. Reservar vuelo");
            System.out.println("2. Cancelar vuelo");
            System.out.println("3. Salir");
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();

            
            scanner.nextLine(); 

            switch (opcion) {
                case 1: // RESERVAR VUELO 
                    System.out.println("\n--- RESERVA DE VUELO ---");
                    
                    // 1. Solicitar datos del viaje 
                    System.out.print("Ingrese Origen (Ej: GYE): ");
                    String origen = scanner.nextLine();
                    System.out.print("Ingrese Destino (Ej: BUE): ");
                    String destino = scanner.nextLine();
                    
                    // Buscar Vuelos y restricción de ruta
                    if (origen.equalsIgnoreCase("GYE") && destino.equalsIgnoreCase("BUE")) {
                        System.out.println("¡Vuelo Disponible!");
                        
                        // Verificar asientos y tiempo 
                        System.out.print("¿Cuántas horas faltan para el vuelo?: ");
                        int horasParaVuelo = scanner.nextInt();
                        scanner.nextLine(); // limpiar buffer

                        if (asientosDisponibles > 0 && horasParaVuelo > 24) {
                            
                            // Solicitar datos del pasajero
                            System.out.print("Ingrese Nombre del Pasajero: ");
                            String nombre = scanner.nextLine();
                            System.out.print("Ingrese Cédula/Pasaporte: ");
                            String id = scanner.nextLine();

                            // Solicitar tipo de boleto y equipaje
                            System.out.println("Tipo de boleto: (1) Económico - (2) Ejecutivo");
                            int tipoBoleto = scanner.nextInt();
                            
                            System.out.print("Cantidad de maletas: ");
                            int cantidadMaletas = scanner.nextInt();
                            System.out.print("Peso total de maletas (kg): ");
                            double pesoMaletas = scanner.nextDouble();

                            // Validar Equipaje segun restricciones
                            boolean equipajeValido = false;

                            if (tipoBoleto == 1) { // Económico
                                // Max 1 maleta de 23 kg
                                if (cantidadMaletas <= 1 && pesoMaletas <= 23) {
                                    equipajeValido = true;
                                }
                            } else if (tipoBoleto == 2) { // Ejecutivo
                                // Max 2 maletas de 32 kg
                                if (cantidadMaletas <= 2 && pesoMaletas <= 32) {
                                    equipajeValido = true;
                                }
                            }

                            // Resultado de la Reserva
                            if (equipajeValido) {
                                asientosDisponibles--; // Restamos 1 asiento
                                System.out.println("\n*** ¡RESERVA EXITOSA! ***");
                                System.out.println("Pasajero: " + nombre);
                                System.out.println("Asientos restantes: " + asientosDisponibles);
                            } else {
                                System.out.println("Error: El equipaje excede el límite permitido para su boleto.");
                            }

                        } else {
                            System.out.println("Lo sentimos: Vuelo lleno o ya no es posible reservar (menos de 24h)."); 
                        }
                    } else {
                        System.out.println("No hay vuelos disponibles para esa ruta."); 
                    }
                    break;

                case 2: // CANCELAR VUELO 
                    System.out.println("\n--- CANCELACIÓN DE VUELO ---");
                    
                    // 1. Pedir código
                    System.out.print("Ingrese código de reserva: ");
                    String codigo = scanner.nextLine(); 

                    // 2. Verificar tiempo para multa
                    System.out.print("¿Cuántas horas faltan para el vuelo?: ");
                    int horasRestantes = scanner.nextInt();

                    double reembolso = 0;
                    
                    if (horasRestantes < 24) {
                        // Multa del 20%, devuelve 80%
                        reembolso = precioBoleto * 0.80;
                        System.out.println("Aplica MULTA del 20%.");
                    } else {
                        // Devolución del 100%
                        reembolso = precioBoleto * 1.00;
                        System.out.println("Sin multa. Devolución completa.");
                    }

                    asientosDisponibles++; // Sumamos 1 asiento
                    System.out.println("Reserva " + codigo + " cancelada.");
                    System.out.println("Monto a reembolsar: $" + reembolso);
                    break;

                case 3:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 3);
        
        scanner.close();
    }
}