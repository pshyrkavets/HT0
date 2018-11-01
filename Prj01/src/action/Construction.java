package action;

import building.Building;
import exception.IlluminanceTooMuchException;
import exception.IlluminanceTooLowException;
import exception.SpaceUsageTooMuchException;
import thing.Bulb;
import thing.Chair;
import thing.Table;

public class Construction {
    public static void main(String[] args) throws IlluminanceTooMuchException, IlluminanceTooLowException,
            SpaceUsageTooMuchException {
        Building building1 = new Building("Building1");
        building1.addRoom("Room1", 100, 3);
        building1.addRoom("Room2", 5, 2);
        building1.getRoom("Room1").add(new Bulb(150));
        building1.getRoom("Room1").add(new Bulb(250));
        building1.getRoom("Room1").add(new Table("Writing table", 3));
        building1.getRoom("Room1").add(new Chair("Cozy chair", 2));
        building1.validate();
    }
}
