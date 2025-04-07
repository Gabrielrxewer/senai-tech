package poo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CarRentalSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Criação dos carros iniciais
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(new Car("Fiat", "Uno"));
        cars.add(new Car("Chevrolet", "Onix"));
        cars.add(new Car("Volkswagen", "Gol"));

        int option;
        
        do {
            // Menu interativo
            System.out.println("\nMenu:");
            System.out.println("1. Ver todos os carros");
            System.out.println("2. Alugar um carro");
            System.out.println("3. Devolver um carro");
            System.out.println("4. Adicionar um novo carro");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            
            switch (option) {
                case 1:
                    displayCars(cars);
                    break;
                    
                case 2:
                    rentCar(scanner, cars);
                    break;
                    
                case 3:
                    displayCars(cars);
                    System.out.print("Digite o modelo do carro que deseja devolver: ");
                    String returnModel = scanner.next();
                    boolean returned = false;
                    for (Car car : cars) {
                        if (car.getModel().equalsIgnoreCase(returnModel) && !car.isAvailable()) {
                            car.returnCar();
                            returned = true;
                            System.out.println("Carro devolvido com sucesso!");
                            break;
                        }
                    }
                    if (!returned) {
                        System.out.println("Carro não encontrado ou não foi alugado.");
                    }
                    break;
                    
                case 4:
                    System.out.print("Digite a marca do novo carro: ");
                    String newCarBrand = scanner.next();
                    System.out.print("Digite o modelo do novo carro: ");
                    String newCarModel = scanner.next();
                    Car newCar = new Car(newCarBrand, newCarModel);
                    cars.add(newCar);
                    System.out.println("Novo carro adicionado com sucesso!");
                    break;

                case 5:
                    System.out.println("Saindo do sistema...");
                    break;
                    
                default:
                    System.out.println("Opção inválida.");
            }
        } while (option != 5);

        scanner.close();
    }

    // Função para exibir os carros disponíveis
    public static void displayCars(ArrayList<Car> cars) {
        System.out.println("\nCarros disponíveis:");
        for (Car car : cars) {
            System.out.print("Marca: " + car.getBrand() + " | Modelo: " + car.getModel());
            if (car.isAvailable()) {
                System.out.println(" (Disponível)");
            } else {
                System.out.println(" (Alugado)");
            }
        }
    }

    // Função para alugar um carro
    public static void rentCar(Scanner scanner, ArrayList<Car> cars) {
        Set<String> availableBrands = new HashSet<>();
        for (Car car : cars) {
            availableBrands.add(car.getBrand());
        }
        
        System.out.println("Marcas disponíveis para aluguel:");
        for (String brand : availableBrands) {
            System.out.println("- " + brand);
        }
        
        System.out.print("Digite a marca do carro que deseja alugar: ");
        String rentBrand = scanner.next();
        
        boolean brandFound = false;
        ArrayList<Car> brandCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.getBrand().equalsIgnoreCase(rentBrand)) {
                brandCars.add(car);
                brandFound = true;
            }
        }

        if (brandFound) {
            System.out.println("Qual veículo da marca " + rentBrand + " você gostaria de alugar?");
            for (int i = 0; i < brandCars.size(); i++) {
                Car car = brandCars.get(i);
                System.out.println((i + 1) + ". " + car.getModel());
            }
            
            System.out.print("Digite o modelo que você deseja alugar: ");
            String chosenModel = scanner.next();
            boolean modelFound = false;
            
            for (Car car : brandCars) {
                if (car.getModel().equalsIgnoreCase(chosenModel)) {
                    modelFound = true;
                    if (car.isAvailable()) {
                        car.rent();
                    } else {
                        System.out.println("Este modelo já está alugado.");
                    }
                    break;
                }
            }
            
            if (!modelFound) {
                System.out.println("Modelo não encontrado.");
            }
        } else {
            System.out.println("Marca não encontrada.");
        }
    }
}
