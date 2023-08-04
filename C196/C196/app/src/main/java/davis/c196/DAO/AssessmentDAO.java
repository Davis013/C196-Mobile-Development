package davis.c196.DAO;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import davis.c196.Entities.Assessment;

@Dao
public interface AssessmentDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert (Assessment assessment);

    @Update
    void update (Assessment assessment);

    @Delete
    void delete (Assessment assessment);

    @Query("SELECT * FROM Assessment ORDER BY AssessmentID")
    List<Assessment> getAllAssessments();

}
