package id.ac.polinema.idealbodyweight.util;

public class BodyMassIndex {
    private float weight;
    private float height;
    private float index;

    public BodyMassIndex(float weight, float height) {
        this.weight = weight;
        this.height = height;
        this.index = calculate();
    }

    public float getIndex() {
        return index;
    }

    private float calculate() {
        return weight / ((height/100)*(height/100));
    }
}
