package toys_Java;

import java.util.Random;

public class Randomizer {
    private int[] variants;
    private Random nextRandom = new Random();

    public int getVariant(int index) {
        return variants[index];
    }

    public void setVariant(int index, int value) {
        this.variants[index] = value;
    }

    public Randomizer() {
        this.variants = new int[100];
    }

    public int nextId() {
        return getVariant(nextRandom.nextInt(100));
    }
}
