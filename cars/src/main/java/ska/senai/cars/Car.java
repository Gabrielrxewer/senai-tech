package poo;

import java.util.Date;

public class Car {
    private String brand;
    private String model;
    private boolean available;
    private Date rentalDate;
    private final double dailyRate = 50.0;

    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
        this.available = true;  
    }

    // Método para alugar o carro
    public void rent() {
        if (available) {
            available = false;
            rentalDate = new Date();
            System.out.println("O carro " + model + " foi alugado.");
        } else {
            System.out.println("O carro " + model + " já está alugado.");
        }
    }

    // Método para devolver o carro e calcular o preço do aluguel
    public void returnCar() {
        if (!available) {
            available = true;
            double total = calculatePrice();
            System.out.println("O carro " + model + " foi devolvido.");
            System.out.println("Preço total do aluguel: R$ " + total);
        } else {
            System.out.println("O carro " + model + " não foi alugado.");
        }
    }

    // Método para calcular o preço do aluguel
    private double calculatePrice() {
        long rentalDays = (new Date().getTime() - rentalDate.getTime()) / (1000 * 60 * 60 * 24); 
        if (rentalDays < 1) rentalDays = 1; 
        return rentalDays * dailyRate;
    }

    // Métodos getters
    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public boolean isAvailable() {
        return available;
    }
}
