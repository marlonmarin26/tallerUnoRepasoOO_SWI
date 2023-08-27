package ucaldas;

import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("BIENVENIDO AL CONTADOR DE PALABRAS");

        System.out.print("Ingrese la ruta de la carpeta: ");
        String ruta = scanner.nextLine();

        System.out.print("Ingrese la palabra a buscar: ");
        String palabra = scanner.nextLine();

        ContadorPalabras app = new ContadorPalabras();
        try {
            app.leerCarpeta(ruta);
            app.contarPalabra(palabra);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
