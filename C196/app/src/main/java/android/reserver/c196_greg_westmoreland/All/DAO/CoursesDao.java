package android.reserver.c196_greg_westmoreland.All.DAO;

import android.reserver.c196_greg_westmoreland.All.Entities.CoursesEntity;

public interface CoursesDao {
    void delete(CoursesEntity courseEntity);

    void update(CoursesEntity coursesEntity);

    void insert(CoursesEntity coursesEntity);
}
