import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        int saldo = 100;

        while (saldo > 0) {

            // Cuánto quiere apostar en esta ronda
            System.out.println("Tu saldo es de: " + saldo + ". ¿Cuánto quieres apostar?");
            int apuesta = sc.nextInt();
            while (apuesta <= 0 || apuesta > saldo) {
                System.out.println("Cantidad no válida. Introduce entre 1 y " + saldo + ":");
                apuesta = sc.nextInt();
            }
            saldo -= apuesta;

            // Qué quiere apostar el usuario
            System.out.println("Introduce el numero que quieres apostar: (0-36)");
            int num_apostado = sc.nextInt();
            while (num_apostado < 0 || num_apostado > 36) {
                System.out.println("Número no válido. Introduce uno del 0 al 36:");
                num_apostado = sc.nextInt();
            }
            sc.nextLine();
            System.out.println("Introduce el color: (Rojo o Negro)");
            String color_apostado = sc.nextLine().toUpperCase();

            // Cuánto apuesta a cada cosa
            System.out.println("De tu apuesta: " + apuesta + " cuánto quieres apostar por el numero " + num_apostado);
            int cantidad_num = sc.nextInt();
            while (cantidad_num < 0 || cantidad_num > apuesta) {
                System.out.println("Introduce entre 0 y " + apuesta + ":");
                cantidad_num = sc.nextInt();
            }
            apuesta -= cantidad_num;

            System.out.println("De tu apuesta: " + apuesta + " cuánto quieres apostar por el color " + color_apostado);
            int cantidad_color = sc.nextInt();
            while (cantidad_color < 0 || cantidad_color > apuesta) {
                System.out.println("Introduce entre 0 y " + apuesta + ":");
                cantidad_color = sc.nextInt();
            }
            apuesta -= cantidad_color;

            // Tirada: primero el número y luego su color
            int numero = (int) (Math.random() * 37);

            String resultado_color;
            if (numero == 0) {
                resultado_color = "VERDE";
            } else if ((numero >= 1 && numero <= 10) || (numero >= 19 && numero <= 28)) {
                if (numero % 2 != 0) resultado_color = "ROJO";
                else resultado_color = "NEGRO";
            } else {
                if (numero % 2 == 0) resultado_color = "ROJO";
                else resultado_color = "NEGRO";
            }

            System.out.println("La bola gira...");
            Thread.sleep(1500);
            System.out.println("Ha salido el " + numero + " " + resultado_color);
            Thread.sleep(1000);

            // Comparaciones con lo introducido por el usuario
            if (color_apostado.equals(resultado_color)) {
                System.out.println("Has acertado con: " + color_apostado);
                Thread.sleep(500);
                cantidad_color *= 2;
            } else {
                System.out.println("Has fallado el color");
                Thread.sleep(500);
                cantidad_color = 0;
            }
            if (numero == num_apostado) {
                System.out.println("¡Pleno!");
                Thread.sleep(500);
                cantidad_num *= 36;
            } else {
                System.out.println("Has fallado el número");
                Thread.sleep(500);
                cantidad_num = 0;
            }

            // Lo que no se aposto y ganancias
            apuesta += cantidad_color + cantidad_num;
            saldo += apuesta;
            System.out.println("Ahora tu saldo es de: " + saldo);
            Thread.sleep(1000);
        }

        System.out.println("Game over, te has quedado sin saldo.");
    }
}