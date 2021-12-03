package android.reserver.c196_greg_westmoreland.All.Database;

import android.content.Context;
import android.reserver.c196_greg_westmoreland.All.DAO.TermsDao;
import android.reserver.c196_greg_westmoreland.All.Entities.TermsEntity;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;

@Database(entities = {TermsEntity.class}, version = 3, exportSchema = false)
//@TypeConverter(DateConverter.class);
public abstract class SchedulerDatabaseBuilder extends RoomDatabase {
    public abstract TermsDao termsDao();

    private static volatile SchedulerDatabaseBuilder INSTANCE;

    static SchedulerDatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SchedulerDatabaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), SchedulerDatabaseBuilder.class,
                            "Scheduler.db").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
