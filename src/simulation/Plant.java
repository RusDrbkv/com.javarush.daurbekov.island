package simulation;

public class Plant extends Creature {
    private int quantity;
    private double nutritionValue;

    public Plant() {
        super(0.0); // Растения не имеют веса как единицы
        this.quantity = 0;
        this.nutritionValue = Config.PLANT_NUTRITION;
    }

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
    
    @Override
    public void eat(Creature food) {
        // Растения не едят других существ
    }
}