package davis.c196.Database;

import android.app.Application;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import davis.c196.DAO.AssessmentDAO;
import davis.c196.DAO.CourseDAO;
import davis.c196.DAO.TermDAO;
import davis.c196.Entities.Assessment;
import davis.c196.Entities.Course;
import davis.c196.Entities.Term;

public class Repository {



    private TermDAO mTermDAO;
    private CourseDAO mCourseDAO;
    private AssessmentDAO mAssessmentDAO;
    private List<Term> mAllTerms;
    private List<Course> mAllCourses;
    private List<Assessment> mAllAssessments;


    private static int NUMBER_OF_THREADS = 5;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public Repository(Application application){

        C196DatabaseBuilder db = C196DatabaseBuilder.getDatabase(application);
        mTermDAO = db.termDAO();
        mCourseDAO = db.courseDAO();
        mAssessmentDAO = db.assessmentDAO();
    }

    public List<Term>getAllTerms(){
        databaseExecutor.execute(()->{
            mAllTerms=mTermDAO.getAllTerms();

        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e ){
            e.printStackTrace();
        }
        return mAllTerms;
    }

    public void insert(Term term){
        databaseExecutor.execute(() ->{
            mTermDAO.insert(term);

        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void update(Term term) {
        databaseExecutor.execute(() ->{
            mTermDAO.update(term);
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }


    public void delete(Term term){
        databaseExecutor.execute(() ->{
            mTermDAO.delete(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public List<Course>getAllCourses(){
        databaseExecutor.execute(()->{
            mAllCourses=mCourseDAO.getAllCourses();

        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e ){
            e.printStackTrace();
        }
        return mAllCourses;
    }


    public void insert(Course course){
        databaseExecutor.execute(() ->{
            mCourseDAO.insert(course);

        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void update(Course course) {
        databaseExecutor.execute(() ->{
            mCourseDAO.update(course);
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }


    public void delete(Course course){
        databaseExecutor.execute(() ->{
            mCourseDAO.delete(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public List<Assessment>getAllAssessments(){
        databaseExecutor.execute(()->{
            mAllAssessments= mAssessmentDAO.getAllAssessments();

        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e ){
            e.printStackTrace();
        }
        return mAllAssessments;
    }

    public void insert(Assessment assessment){
        databaseExecutor.execute(() ->{
            mAssessmentDAO.insert(assessment);

        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void update(Assessment assessment) {
        databaseExecutor.execute(() ->{
            mAssessmentDAO.update(assessment);
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }


    public void delete(Assessment assessment){
        databaseExecutor.execute(() ->{
            mAssessmentDAO.delete(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
