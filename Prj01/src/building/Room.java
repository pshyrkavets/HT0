package building;

import thing.Bulb;
import thing.Chair;
import thing.Table;
import java.util.ArrayList;

public class Room {
    private final int LX_OF_ONE_WINDOW = 700;
    private final int MIN_ILLUMINANCE_IN_ROOM = 300;
    private final int MAX_ILLUMINANCE_IN_ROOM = 4000;
    private String nameOfRoom;
    private double squareOfRoom;
    private int windowsInRoom;
    private int totalLxOfBulbsInRoom = 0;
    private double squareOfTablesInRoom = 0;
    private double squareOfChairsInRoom = 0;
    private ArrayList<Bulb> bulbs = new ArrayList<>();
    private ArrayList<Table> tables = new ArrayList<>();
    private ArrayList<Chair> chairs = new ArrayList<>();
    private double totalSquareOfThingsInRoom = 0;
    private double seventyPercentOfSquareOfRoom;
    private int totalIlluminanceInRoom;


    public Room(String nameOfRoom, double squareOfRoom, int windowsInRoom) {
        this.nameOfRoom = nameOfRoom;
        this.squareOfRoom = squareOfRoom;
        this.windowsInRoom = windowsInRoom;
        totalIlluminanceInRoom = this.windowsInRoom * LX_OF_ONE_WINDOW;
        seventyPercentOfSquareOfRoom = this.squareOfRoom * 0.7;
    }

    public void add(Bulb bulb) {
        bulbs.add(bulb);
        for(Bulb aBulb : bulbs) {
            totalLxOfBulbsInRoom += aBulb.getLxOfBulb();
        }
        totalIlluminanceInRoom += bulb.getLxOfBulb();
    }

    public void add(Table table) {
        tables.add(table);
        for(Table aTable : tables) {
            squareOfTablesInRoom += aTable.getSquareOfTable();
        }
        totalSquareOfThingsInRoom += table.getSquareOfTable();
    }

    public void add(Chair chair) {
        chairs.add(chair);
        for(Chair aChair : chairs) {
            squareOfChairsInRoom += aChair.getSquareOfChair();
        }
        totalSquareOfThingsInRoom += chair.getSquareOfChair();
    }

    public int getLxOfOneWindow() { return LX_OF_ONE_WINDOW; }
    public int getMinIlluminanceInRoom() { return MIN_ILLUMINANCE_IN_ROOM; }
    public int getMaxIlluminanceInRoom() { return MAX_ILLUMINANCE_IN_ROOM; }
    public String getNameOfRoom() {
        return nameOfRoom;
    }
    public double getSquareOfRoom() {
        return squareOfRoom;
    }
    public int getWindowsInRoom() {
        return windowsInRoom;
    }
    public int getTotalLxOfBulbsInRoom() {
        return totalLxOfBulbsInRoom;
    }
    public double getSquareOfTablesInRoom() {
        return squareOfTablesInRoom;
    }
    public double getSquareOfChairsInRoom() {
        return squareOfChairsInRoom;
    }
    public ArrayList<Bulb> getBulbs() { return bulbs; }
    public ArrayList<Table> getTables() { return tables; }
    public ArrayList<Chair> getChairs() { return chairs; }
    public double getTotalSquareOfThingsInRoom() {return totalSquareOfThingsInRoom; }
    public double getSeventyPercentOfSquareOfRoom() { return seventyPercentOfSquareOfRoom; }
    public int getTotalIlluminanceInRoom() { return totalIlluminanceInRoom; }
}
