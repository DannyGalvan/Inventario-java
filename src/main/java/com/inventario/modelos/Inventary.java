package com.inventario.modelos;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Inventary {
    private final ArrayList<Products> product = new ArrayList<>();
    private final ArrayList<Category> categories = new ArrayList<>();
    private final ArrayList<Entry> registers = new ArrayList<>();

    // constructor
    public Inventary() {
        this.addInitialCategories();
        this.addInitialProducts();
        System.out.println("--- BIENVENIDO A SU INVENTARIO ---");
        this.printMenu();
    }

    public void initialize(int option, Scanner scanner) {
        while (option != 9) {
            System.out.print("Ingrese una opcion: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    this.createEntry(scanner);
                    this.printMenu();
                    break;
                case 2:
                    this.createOutput(scanner);
                    this.printMenu();
                    break;
                case 3:
                    this.createProduct();
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
                    this.printFilter(true);
                    this.printMenu();
                    break;
                case 7:
                    this.printFilter(false);
                    this.printMenu();
                    break;
                case 8:
                    this.printRegisters();
                    this.printMenu();
                    break;
                case 9:
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
        System.out.println("6.- Listar Entradas");
        System.out.println("7.- Listar Salidas");
        System.out.println("8.- Listar Todos los Registros");
        System.out.println("9.- Salir");
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

    private void createEntry(Scanner scanner) {
        System.out.println("Entradas de productos");
        Entry in = new Output();
        System.out.println("Ingrese el codigo de la entrada");
        int code = scanner.nextInt();
        in.setCodeEntry(code);
        System.out.println("Ingrese la cantidad que desea meter");
        int quantity = scanner.nextInt();
        in.setPartialQuantity(quantity);
        System.out.println("Ingrese codigo de producto");
        int codeProduct = scanner.nextInt();
        in.setCodeProduct(codeProduct);
        Products prod = product.stream()
                .filter(p -> p.getCodeProduct() == codeProduct)
                .findFirst().get();
        in.setProduct(prod);
        in.setIsEntriy(true);

        int result = prod.getQuantity() + in.getPartialQuantity();

        prod.setQuantity(result);

        int index = product.indexOf(prod);

        product.set(index, prod);

        registers.add(in);
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

        registers.add(out);
    }

    private void createProduct() {

    }

    private void printFilter(boolean IsEntry) {
        System.out.println("");
        System.out.println("Lista de Entradas");
        ArrayList<Entry> ins = new ArrayList<>(
                registers.stream().filter(r -> r.isIsEntriy() == IsEntry).collect(Collectors.toList()));

        for (Entry in : ins) {
            System.out.println(String.format(
                    "No: %s | Producto: %s | Cantidad: %s", in.getCodeEntry(), in.getProduct().getDescription(),
                    in.getPartialQuantity()));
        }
    }

    private void printRegisters() {
        System.out.println("");
        System.out.println("Lista de Todos los Registros");

        for (Entry in : registers) {
            System.out.println(String.format(
                    "No: %s | Producto: %s | Cantidad: %s | Tipo: %s", in.getCodeEntry(),
                    in.getProduct().getDescription(),
                    in.getPartialQuantity(), in.isIsEntriy() ? "Entrada" : "Salida"));
        }
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