package toys_Java;

public class Toys {
    private int id;
    private String name;
    private int weight;
    private int quantity;
    
    public int getId() {
        return id;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void substractQuantity() {
        this.quantity -= 1;
    }
    
    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    public Toys(int id, String name, int weight, int quantity) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "[id=" + id +
                ", название=" + name +
                ", вероятность=" + weight +
                ", остаток=" + quantity + "]";
    }
}