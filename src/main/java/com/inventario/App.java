package com.inventario;

import java.util.Scanner;

import com.inventario.modelos.Inventary;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Inventary inventary = new Inventary();

        int option = 0;

        inventary.initialize(option, scanner);
    }
}

// inventario
// productos
// categoria
// entradas
// salidas