import java.util.Scanner;

public class CalculadoraNutrientesUTEG1 {
    
    // Funcion para calcular calorias segun el grupo (por cada 100g)
    public static double calcularCalorias(int grupo, double gramos) {
        double calorias[] = {0, 200, 350, 700, 40, 60}; // Indice 0 no se usa, 1-5 son los grupos
        return (calorias[grupo] * gramos) / 100;
    }
    
    // Funcion para calcular proteinas segun el grupo (por cada 100g)
    public static double calcularProteinas(int grupo, double gramos) {
        double proteinas[] = {0, 25, 8, 15, 2, 1};
        return (proteinas[grupo] * gramos) / 100;
    }
    
    // Funcion para verificar si los valores son aceptables
    public static boolean esAceptable(double cal, double prot) {
        return (cal >= 400 && cal <= 800 && prot >= 20 && prot <= 40);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Variables para acumular totales
        double totalCalorias = 0, totalProteinas = 0;
        
        System.out.println("--- CALCULADORA DE NUTRIENTES - UTEG ---\n");
        System.out.print("Cuantos ingredientes consumiste? ");
        int cantidad = sc.nextInt();
        sc.nextLine();
        
        // Procesar cada ingrediente con for
        for(int i = 1; i <= cantidad; i++) {
            System.out.println("\n--- INGREDIENTE " + i + " ---");
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Gramos: ");
            double gramos = sc.nextDouble();
            
            System.out.println("\nGrupos: 1-Proteinas 2-Carbohidratos 3-Grasas 4-Verduras 5-Frutas");
            System.out.print("Grupo (1-5): ");
            int grupo = sc.nextInt();
            sc.nextLine();
            
            // Calcular nutrientes usando las funciones
            double calorias = calcularCalorias(grupo, gramos);
            double proteinas = calcularProteinas(grupo, gramos);
            
            // Acumular totales
            totalCalorias += calorias;
            totalProteinas += proteinas;
            
            System.out.printf("-> %s aporta: %.1f kcal, %.1f g proteinas\n", nombre, calorias, proteinas);
        }
        
        // Mostrar resumen
        System.out.println("\n========================================");
        System.out.printf("TOTAL: %.1f kcal | %.1f g proteinas\n", totalCalorias, totalProteinas);
        System.out.println("========================================");
        
        // Verificar si es aceptable y fin
        if(esAceptable(totalCalorias, totalProteinas)) {
            System.out.println("QUE BIEN!, VALOR NUTRICIONAL: ACEPTABLE");
        } else {
            System.out.println("QUE MAL, VALOR NUTRICIONAL: NO ACEPTABLE");
            System.out.println("(Rangos: 400-800 kcal, 20-40g proteinas)");
        }
        
        sc.close();
    }
}
