package android.reserver.c196_greg_westmoreland.All.Database;

import android.content.Context;
import android.reserver.c196_greg_westmoreland.All.DAO.ThingDao;
import android.reserver.c196_greg_westmoreland.All.Entities.Thing;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Thing.class}, version = 1, exportSchema = false)
public abstract class ThingDatabaseBuilder extends RoomDatabase {
    public abstract ThingDao thingDao();

    private static volatile ThingDatabaseBuilder INSTANCE;

    static ThingDatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ThingDatabaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ThingDatabaseBuilder.class,
                            "MyThingDatabase.db").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
