package davis.c196.UI;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import davis.c196.Database.Repository;
import davis.c196.Entities.Assessment;
import davis.c196.Entities.Term;
import davis.c196.R;

public class AssessmentHome extends AppCompatActivity implements AssessmentAdapter.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView = findViewById(R.id.recyclerView4);
        Repository repository = new Repository(getApplication());
        List<Assessment> assessments = repository.getAllAssessments();
        final AssessmentAdapter adapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setAssessments(assessments);

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AssessmentHome.this, Assessmentaddedit.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_assessmenthome, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }

        if (item.getItemId() == R.id.assessmentRefresh) {
            Repository repository = new Repository(getApplication());
            List<Assessment> assessments = repository.getAllAssessments();
            RecyclerView recyclerview = findViewById(R.id.recyclerView4);
            AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
            recyclerview.setAdapter(assessmentAdapter);
            recyclerview.setLayoutManager(new LinearLayoutManager(this));
            assessmentAdapter.setAssessments(assessments);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(Assessment assessment) {
        Intent intent = new Intent(AssessmentHome.this, Assessmentaddedit.class);
        intent.putExtra("AssessmentID", assessment.getAssessmentID());
        intent.putExtra("Title", assessment.getTitle());
        intent.putExtra("Type", assessment.getType());
        intent.putExtra("Content", assessment.getContent());
        intent.putExtra("Start", assessment.getStart());
        intent.putExtra("End", assessment.getEnd());
        startActivity(intent);
    }

    public void goToMainScreen (View view){
        Intent intent = new Intent(AssessmentHome.this, MainActivity.class);
        startActivity(intent);

    }
}




