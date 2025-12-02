package com;

import com.biblioteca.util.Menu;

public class Main {
    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println("BIENVENIDO AL SISTEMA DE BIBLIOTECA");
        System.out.println("====================================");
        System.out.println("\n");

        Menu menu = new Menu();
        menu.mostrarMenuPrincipal();
    }

}