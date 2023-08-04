package davis.c196.DAO;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import davis.c196.Entities.Course;

@Dao
public interface CourseDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert (Course course);

    @Update
    void update (Course course);

    @Delete
    void delete (Course course);

    @Query("SELECT * FROM Courses ORDER BY CourseID ASC")
    List<Course> getAllCourses();

}
