package test1;

public class Tripcalculator {
    public static void main(String[] args) {
        double distance = 160;
        double mpg = 15;
        double pricePerGallon = 4;
        double totalCost = distance / mpg * pricePerGallon;
        System.out.println("The total cost of the trip is $" + totalCost);
    }
}
