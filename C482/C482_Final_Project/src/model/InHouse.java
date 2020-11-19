package model;

/**
 * @author Greg Westmoreland
 * C482 Class Project
 */

/**
 * This class contains all the methods for the In-house parts. It extends the part class.
 */
public class InHouse extends Part {

    // Private Int variable called machineId
    private int machineId;

    /**
     * In House Constructor
     * @param id - Part Id
     * @param name - Part Name
     * @param price - Part Price
     * @param stock - Part Inventory
     * @param min - Part Min
     * @param max - Part Max
     * @param machineId - Part Machine ID
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * getMachineId starter method
     * @return machineId- This returns the machine ID.
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * setMachineId starter method
     * @param machineId - This returns the machine ID.
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}