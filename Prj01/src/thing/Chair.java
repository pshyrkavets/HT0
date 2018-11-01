package thing;

public class Chair {
    private String nameOfChair;
    private double squareOfChair;

    public Chair(String nameOfChair, double squareOfChair) {
        this.nameOfChair = nameOfChair;
        this.squareOfChair = squareOfChair;
    }

    public String getNameOfChair() {
        return nameOfChair;
    }

    public double getSquareOfChair() {
        return squareOfChair;
    }
}
