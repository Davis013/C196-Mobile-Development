package davis.c196.Database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import davis.c196.DAO.TermDAO;
import davis.c196.DAO.CourseDAO;
import davis.c196.DAO.AssessmentDAO;
import davis.c196.Entities.Assessment;
import davis.c196.Entities.Course;
import davis.c196.Entities.Term;

@Database(entities = {Term.class, Course.class, Assessment.class}, version = 6, exportSchema = false)
public abstract class C196DatabaseBuilder extends RoomDatabase {

    public abstract TermDAO termDAO();
    public abstract CourseDAO courseDAO();
    public abstract AssessmentDAO assessmentDAO();

    private static volatile C196DatabaseBuilder INSTANCE;

    static C196DatabaseBuilder getDatabase(final Context context){
        if (INSTANCE==null){
            synchronized (C196DatabaseBuilder.class){
                if (INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), C196DatabaseBuilder.class, "TermTrackerDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
