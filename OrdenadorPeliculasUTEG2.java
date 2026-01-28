import java.util.Scanner;

public class OrdenadorPeliculasUTEG2 {
    
    // Funcion para ordenar peliculas por año (metodo burbuja)
    public static void ordenarPorAnio(String[] titulos, int[] anios, String[] generos, int n) {
        for(int i = 0; i < n - 1; i++) {
            for(int j = 0; j < n - i - 1; j++) {
                if(anios[j] > anios[j + 1]) {
                    // Intercambiar anios
                    int tempAnio = anios[j];
                    anios[j] = anios[j + 1];
                    anios[j + 1] = tempAnio;
                    
                    // Intercambiar titulos
                    String tempTitulo = titulos[j];
                    titulos[j] = titulos[j + 1];
                    titulos[j + 1] = tempTitulo;
                    
                    // Intercambiar generos
                    String tempGenero = generos[j];
                    generos[j] = generos[j + 1];
                    generos[j + 1] = tempGenero;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("--- ORDENADOR DE PELICULAS - UTEG ---\n");
        System.out.print("Cuantas peliculas desea ingresar? ");
        int n = sc.nextInt();
        sc.nextLine();
        
        // Arreglos unidimensionales paralelos
        String[] titulos = new String[n];
        int[] anios = new int[n];
        String[] generos = new String[n];
        
        // Ingresar datos con for
        for(int i = 0; i < n; i++) {
            System.out.println("\n--- PELICULA " + (i + 1) + " ---");
            System.out.print("Titulo: ");
            titulos[i] = sc.nextLine();
            System.out.print("Anio: ");
            anios[i] = sc.nextInt();
            sc.nextLine();
            System.out.print("Genero: ");
            generos[i] = sc.nextLine();
        }
        
        // Ordenar usando la funcion
        ordenarPorAnio(titulos, anios, generos, n);
        
        // Mostrar peliculas ordenadas
        System.out.println("\n--- PELICULAS ORDENADAS (MAS ANTIGUA al MAS RECIENTE) ---\n");
        for(int i = 0; i < n; i++) {
            System.out.println((i + 1) + ". " + titulos[i] + " (" + anios[i] + ") - " + generos[i]);
        }
        
        sc.close();
    }
}
