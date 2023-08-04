package davis.c196.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import davis.c196.Database.Repository;
import davis.c196.Entities.Course;
import davis.c196.Entities.Term;
import davis.c196.R;

public class CourseHome extends AppCompatActivity implements CourseAdapter.OnItemClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        Repository repository = new Repository(getApplication());
        List<Course> courses = repository.getAllCourses();
        final CourseAdapter adapter = new CourseAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setCourses(courses);

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton2);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseHome.this, Courseaddedit.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_coursehome, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }

        if (item.getItemId() == R.id.courseRefresh) {
            Repository repository = new Repository(getApplication());
            List<Course> courses = repository.getAllCourses();
            RecyclerView recyclerview = findViewById(R.id.recyclerView2);
            CourseAdapter courseAdapter = new CourseAdapter(this);
            recyclerview.setAdapter(courseAdapter);
            recyclerview.setLayoutManager(new LinearLayoutManager(this));
            courseAdapter.setCourses(courses);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(Course course) {
        Intent intent = new Intent(CourseHome.this, Courseaddedit.class);
        intent.putExtra("CourseID", course.getCourseID());
        intent.putExtra("Title", course.getTitle());
        intent.putExtra("Start", course.getStart());
        intent.putExtra("End", course.getEnd());
        intent.putExtra("Status", course.getStatus());
        intent.putExtra("MentorName", course.getMentorName());
        intent.putExtra("MentorEmail", course.getMentorEmail());
        intent.putExtra("MentorPhone", course.getMentorPhone());
        intent.putExtra("Note", course.getNote());
        startActivity(intent);
    }



    public void goToAssessmentHome(View view) {
        Intent intent=new Intent(CourseHome.this,AssessmentHome.class);
        startActivity(intent);

    }
}
