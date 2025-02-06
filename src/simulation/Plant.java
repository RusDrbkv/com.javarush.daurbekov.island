package simulation;

public class Plant {
    private int quantity;
    private double nutritionValue = 0.5;

    public void grow() {
        if (quantity < Config.MAX_PLANTS_IN_CELL) {
            quantity++;
        }
    }

    public void consume() {
        if (quantity > 0) quantity--;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getNutritionValue() {
        return nutritionValue;
    }
}