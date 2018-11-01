package thing;

public class Table {
    private String nameOfTable;
    private double squareOfTable;

    public Table(String nameOfTable, double squareOfTable) {
        this.nameOfTable = nameOfTable;
        this.squareOfTable = squareOfTable;
    }

    public String getNameOfTable() {
        return nameOfTable;
    }

    public double getSquareOfTable() {
        return squareOfTable;
    }
}
