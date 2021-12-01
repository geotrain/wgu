package android.reserver.c196_greg_westmoreland.All.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "thing_table")
public class Thing {
    @PrimaryKey(autoGenerate = true)
    private int thingID;

    @Override
    public String toString() {
        return "Thing{" +
                "thingName='" + thingName + '\'' +
                '}';
    }

    public int getThingID() {
        return thingID;
    }

    public void setThingID(int thingID) {
        this.thingID = thingID;
    }

    public String getThingName() {
        return thingName;
    }

    public void setThingName(String thingName) {
        this.thingName = thingName;
    }

    public Thing(int thingID, String thingName) {
        this.thingID = thingID;
        this.thingName = thingName;
    }

    private String thingName;

}
