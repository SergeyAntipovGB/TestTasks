package toys_Java;

public class Toys {
    private int id;
    private int weight;
    private String name;
    private int quantity;
    
    public Toys(int id, int weight, String name, int quantity) {
        this.id = id;
        this.weight = weight;
        this.name = name;
        this.quantity = quantity;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}