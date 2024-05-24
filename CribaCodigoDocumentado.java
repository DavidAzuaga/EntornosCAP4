package ENTORNOS;

import java.util.Scanner;

/**
 * Clase que implementa la Criba de Erastótenes para encontrar numeros primos.
 * Deja al usuario añadir un número y se generarán los primos hasta ese número.
 */
public class CribaDocumentado {
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

    /**
     * Genera un array de números primos hasta el paramertro 'numeroIntroducido'.
     * @param numeroIntroducido Los primos se generan hasta este número
     * @return Un Array de números que contiene los números primos encontrados hasta 'numeroIntroducido'.
     */
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

    /**
     * Rellena un array con los números primos encontrados.
     * @param esNumeroPrimo Array de booleanos que indica si el número es primo o no.
     * @param tamanoArray Tamaño del array.
     * @return Un array de números enteros que contiene todos los números primos encontrados.
     */

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

    /**
     * Realiza la criba de Erastótenes para marcar los números que no son primos, posteriormente eliminandolos con el metodo eliminarMultiplos.
     * @param tamanoArray Tamaño del array.
     * @param esNumeroPrimo Array de booleanos que indica si el número es primo o no.
     */
    private static void realizarCriba(int tamanoArray, boolean[] esNumeroPrimo) {
        for (int i = 2; i < Math.sqrt(tamanoArray) + 1; i++) {
            eliminarMultiplos(tamanoArray, esNumeroPrimo, i);
        }
    }

    /**
     * Elimina los números que no son primos en la lista.
     * @param tamanoArray Tamaño del array.
     * @param esNumeroPrimo Array de booleanos que indica si el número es primo o no.
     * @param i Sus multiplos se deben eliminar.
     */
    private static void eliminarMultiplos(int tamanoArray, boolean[] esNumeroPrimo, int i) {
        if (esNumeroPrimo[i]) {
            for (int j = 2 * i; j < tamanoArray; j += i)
                esNumeroPrimo[j] = false;
        }
    }

    /**
     * Inicializa el array de números prims.
     * @param esNumeroPrimo Array de booleanos que indica si el número es primo o no.
     */
    private static void iniciarArray(boolean[] esNumeroPrimo) {
        int i;
        for (i = 0; i < esNumeroPrimo.length; i++)
            esNumeroPrimo[i] = true;
        esNumeroPrimo[0] = esNumeroPrimo[1] = false;
    }

    /**
     *Imprime en consola el array de números primos del array Vector.
     * @param vector Array de enteros que con tiene los números primos a imprimir.
     */
    private static void imprimirVectoresPrimos(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            imprimirEnGruposdeDiez(i);
            System.out.print(vector[i] + "\t");
        }
    }

    /**
     * Imprime los números del 1 al tamaño del array en la consola.
     * @param vector Array de enteros que con tiene los números primos a imprimir.
     */
    private static void imprimirArrayVector(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            imprimirEnGruposdeDiez(i);
            System.out.print(i + 1 + "\t");
        }
    }

    /**
     * Imprime una linea después de cada grupo de diez elementos
     * @param i Indice del elemento en el array
     */
    private static void imprimirEnGruposdeDiez(int i) {
        if (i % 10 == 0) System.out.println();
    }
}

