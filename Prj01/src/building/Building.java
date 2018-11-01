package building;

import exception.IlluminanceTooMuchException;
import exception.IlluminanceTooLowException;
import exception.SpaceUsageTooMuchException;
import java.util.ArrayList;

public class Building {
    private String nameOfBuilding;
    private ArrayList<Room> rooms = new ArrayList<>();
    private Room requestedRoom;

    public Building(String nameOfBuilding) {
        this.nameOfBuilding = nameOfBuilding;
    }

    public String getNameOfBuilding() { return nameOfBuilding; }

    public void addRoom(String nameOfRoom, double squareOfRoom, int windowsInRoom) {
        for(Room room : rooms) {
            if(nameOfRoom.equals(room.getNameOfRoom())) {
                System.out.println(nameOfRoom + " is already exists in " + nameOfBuilding + "!" +
                        " You can't add the room named " + nameOfRoom + " to " + nameOfBuilding + "!");
                System.out.println("The validation of " + nameOfBuilding + " failed!");
                break;
            }
        }
        Room room = new Room(nameOfRoom, squareOfRoom, windowsInRoom);
        rooms.add(room);
    }

    public Room getRoom(String nameOfRoom) {
        for (Room room : rooms) {
            if (room.getNameOfRoom().equals(nameOfRoom)) {
                requestedRoom = room;
            }
        }

        if(!requestedRoom.getNameOfRoom().equals(nameOfRoom)) {
            System.out.println("There is no " + nameOfRoom + " in " + nameOfBuilding + "!");
        }

        return requestedRoom;
    }

    public void validate() throws IlluminanceTooMuchException, IlluminanceTooLowException, SpaceUsageTooMuchException {
        for (Room room : rooms) {
            if ((room.getTotalSquareOfThingsInRoom() <= room.getSeventyPercentOfSquareOfRoom()) &&
                    (room.getTotalIlluminanceInRoom() >= room.getMinIlluminanceInRoom()) &&
                    (room.getTotalIlluminanceInRoom() <= room.getMaxIlluminanceInRoom())) {
                System.out.println("The validation of " + room.getNameOfRoom() + " succeeded!");
            } else if (room.getTotalSquareOfThingsInRoom() > room.getSeventyPercentOfSquareOfRoom()) {
                throw new SpaceUsageTooMuchException("The total square of things in " + room.getNameOfRoom() +
                        " is over 70 percent of the square of " + room.getNameOfRoom() + "!");
            } else if (room.getTotalIlluminanceInRoom() < room.getMinIlluminanceInRoom()) {
                throw new IlluminanceTooLowException("The total illuminance in " + room.getNameOfRoom() +
                        " is less than 300 lx!");
            } else if (room.getTotalIlluminanceInRoom() > room.getMaxIlluminanceInRoom()) {
                throw new IlluminanceTooMuchException("The total illuminance in " + room.getNameOfRoom() +
                        " is over 4000 lx!");
            }
        }
        System.out.println("The validation of " + nameOfBuilding + " succeeded!");
    }
}
