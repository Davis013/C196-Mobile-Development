package davis.c196.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import davis.c196.Database.Repository;
import davis.c196.Entities.Assessment;
import davis.c196.Entities.Course;
import davis.c196.Entities.Term;
import davis.c196.R;

public class MainActivity extends AppCompatActivity {
public static int numAlert;
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        repository = new Repository(getApplication());
    }

    public void Begin(View view) {
        try {
            Term term = new Term(1, "Term 1", "7/10/2023", "1/31/2024");
            repository.insert(term);

            Course course = new Course(1, 1, "C196 Mobile Development", "7/12/2023", "8/31/2023", "In Progress", "Homer Simpson", "5558795862", "Ieatdonuts@live.com", "This class is fun");
            repository.insert(course);

            Assessment assessment = new Assessment(1, 1, "Performance Assessment", "Performance Assessment","7/12/2023","8/1/2023","This assessment will have you develop a mobile application in Android Studio");
            repository.insert(assessment);

            Intent intent = new Intent(MainActivity.this, TermHome.class);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
