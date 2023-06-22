package com.inventario.modelos;

import java.util.ArrayList;
import java.util.Scanner;

public class Inventary {
    private ArrayList<Products> product = new ArrayList<>();
    private ArrayList<Category> categories = new ArrayList<>();
    private ArrayList<Entry> entries = new ArrayList<>();
    private ArrayList<Output> outputs = new ArrayList<>();

    // constructor
    public Inventary() {
        this.addInitialCategories();
        this.addInitialProducts();
        System.out.println("--- BIENVENIDO A SU INVENTARIO ---");
        this.printMenu();
    }

    public void initialize(int option, Scanner scanner) {
        while (option != 6) {
            System.out.print("Ingrese una opcion: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    this.createEntry();
                    this.printMenu();
                    break;
                case 2:
                    this.createOutput(scanner);
                    this.printMenu();
                    break;
                case 3:
                    this.printMenu();
                    break;
                case 4:
                    this.printProducts();
                    this.printMenu();
                    break;
                case 5:
                    this.printCategories();
                    this.printMenu();
                    break;
                case 6:
                    this.salir();
                    scanner.close();
                    break;

                default:
                    this.errorMenu();
                    this.printMenu();
                    break;
            }
        }
    }

    private void printMenu() {
        System.out.println("");
        System.out.println("----------------------------------");
        System.out.println("1.- Realizar una Entrada");
        System.out.println("2.- Realizar una Salida");
        System.out.println("3.- Crear nuevo Producto");
        System.out.println("4.- Listar Productos");
        System.out.println("5.- Listar Categorias");
        System.out.println("6.- Salir");
        System.out.println("----------------------------------");
        System.out.println("");
    }

    private void errorMenu() {
        System.out.println("");
        System.out.println("Elija una opcion valida");
    }

    private void salir() {
        System.out.println("");
        System.out.println("Ha Salido del sistema exitosamente");
        System.out.println("");
    }

    private void printProducts() {
        System.out.println("");
        System.out.println("Lista de Productos");
        for (Products product : product) {
            System.out.println(String.format(
                    "No: %s | Descripcion: %s | Precio: %s | Cantidad: %s | No.Categoria: %s | Categoria: %s ",
                    product.getCodeProduct(), product.getDescription(), product.getPrice(), product.getQuantity(),
                    product.getCategory().getCodeCategory(), product.getCategory().getDescription()));
        }
    }

    private void printCategories() {
        System.out.println("");
        System.out.println("Lista de Categorias");
        for (Category category : categories) {
            System.out.println(String.format("No: %s | Descripcion: %s",
                    category.getCodeCategory(), category.getDescription()));
        }
    }

    private void createEntry() {

    }

    private void createOutput(Scanner scanner) {
        System.out.println("Salidas de productos");
        Output out = new Output();
        System.out.println("Ingrese el codigo de la salida");
        int code = scanner.nextInt();
        out.setCodeEntry(code);
        System.out.println("Ingrese la cantidad que desea sacar");
        int quantity = scanner.nextInt();
        out.setPartialQuantity(quantity);
        System.out.println("Ingrese codigo de producto");
        int codeProduct = scanner.nextInt();
        out.setCodeProduct(codeProduct);
        Products prod = product.stream()
                .filter(p -> p.getCodeProduct() == codeProduct)
                .findFirst().get();
        out.setProduct(prod);
        out.setIsEntriy(false);

        if (out.getPartialQuantity() > prod.getQuantity()) {
            System.out.println("no hay suficientes existencias para este producto");
            return;
        }

        int result = prod.getQuantity() - out.getPartialQuantity();

        prod.setQuantity(result);

        int index = product.indexOf(prod);

        product.set(index, prod);

        outputs.add(out);
    }

    private void createProduct() {

    }

    private void addInitialCategories() {
        Category comida = new Category();
        comida.setCodeCategory(001);
        comida.setDescription("Consumo");
        categories.add(comida);
    }

    private void addInitialProducts() {
        Category category = categories.stream()
                .filter(c -> c.getCodeCategory() == 001)
                .findFirst()
                .get();

        Products manzana = new Products();
        manzana.setCodeProduct(002);
        manzana.setDescription("Manzana");
        manzana.setPrice(5.00f);
        manzana.setQuantity(0);
        manzana.setCodeCategory(001);
        manzana.setCategory(category);
        product.add(manzana);

        Products agua = new Products();
        agua.setCodeProduct(001);
        agua.setDescription("Bolsas de Agua");
        agua.setPrice(1.00f);
        agua.setQuantity(10);
        agua.setCodeCategory(001);
        agua.setCategory(category);
        product.add(agua);
    }

}