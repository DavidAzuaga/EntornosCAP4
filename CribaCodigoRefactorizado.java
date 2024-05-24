package ENTORNOS;

import java.util.Scanner;

public class CribaRefactorizado {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el número para la criba de Erastótenes:");
        int numeroIntroducido = sc.nextInt();
        int[] vector = new int[numeroIntroducido];
        System.out.println("\nVector inicial hasta: " + numeroIntroducido);
        imprimirArrayVector(vector);
        vector = generarPrimos(numeroIntroducido);
        System.out.println("\nVector de primos hasta: " + numeroIntroducido);
        imprimirVectoresPrimos(vector);
    }

    public static int[] generarPrimos(int numeroIntroducido) {
        if (numeroIntroducido < 2) {
            return new int[0]; //Si el número es menor que 2, se devolvemos un vector vacío
        }

        int tamanoArray = numeroIntroducido + 1; //Definimos el tamaño del array
        boolean[] esNumeroPrimo = new boolean[tamanoArray];
        int[] listaNumerosPrimos;

        iniciarArray(esNumeroPrimo);
        realizarCriba(tamanoArray, esNumeroPrimo);
        listaNumerosPrimos = rellenarPrimos(esNumeroPrimo, tamanoArray);
        return listaNumerosPrimos;
    }

    private static int[] rellenarPrimos(boolean[] esNumeroPrimo, int tamanoArray) {
        int[] listaNumerosPrimos;
        int contadorPrimos = 0;
        for (boolean b : esNumeroPrimo) { //Contamos los números primos
            if (b) contadorPrimos++;
        }
        listaNumerosPrimos = new int[contadorPrimos]; //Rellenamos el array con los números primos
        for (int i = 0, j = 0; i < tamanoArray; i++) {
            if (esNumeroPrimo[i]) listaNumerosPrimos[j++] = i;
        }
        return listaNumerosPrimos;
    }

    private static void realizarCriba(int tamanoArray, boolean[] esNumeroPrimo) {
        for (int i = 2; i < Math.sqrt(tamanoArray) + 1; i++) {
            eliminarMultiplos(tamanoArray, esNumeroPrimo, i);
        }
    }

    private static void eliminarMultiplos(int tamanoArray, boolean[] esNumeroPrimo, int i) {
        if (esNumeroPrimo[i]) {
            for (int j = 2 * i; j < tamanoArray; j += i)
                esNumeroPrimo[j] = false;
        }
    }

    private static void iniciarArray(boolean[] esNumeroPrimo) {
        int i;
        for (i = 0; i < esNumeroPrimo.length; i++)
            esNumeroPrimo[i] = true;
        esNumeroPrimo[0] = esNumeroPrimo[1] = false;
    }

    private static void imprimirVectoresPrimos(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            imprimirEnGruposdeDiez(i);
            System.out.print(vector[i] + "\t");
        }
    }

    private static void imprimirArrayVector(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            imprimirEnGruposdeDiez(i);
            System.out.print(i + 1 + "\t");
        }
    }

    private static void imprimirEnGruposdeDiez(int i) {
        if (i % 10 == 0) System.out.println();
    }
}
