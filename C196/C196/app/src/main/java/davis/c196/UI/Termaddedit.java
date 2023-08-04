package davis.c196.UI;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import davis.c196.Database.Repository;
import davis.c196.Entities.Course;
import davis.c196.Entities.Term;
import davis.c196.R;

public class Termaddedit extends AppCompatActivity {

    private EditText termTitleEditText;
    private EditText startDateEditText;
    private EditText endDateEditText;
    private Button saveButton;

    Repository repository;

    Term currentTerm;

    int numCourses;

    private int TermID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_addedit);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        repository = new Repository(getApplication());

        termTitleEditText = findViewById(R.id.TermTitletext);
        startDateEditText = findViewById(R.id.editStartDateterm);
        endDateEditText = findViewById(R.id.editEndDateterm);
        saveButton = findViewById(R.id.saveButton);

        // Get the term information passed from the TermAdapter
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            TermID = extras.getInt("TermID");
            String Title = extras.getString("title");
            String Start = extras.getString("start");
            String End = extras.getString("end");

            // Fill the EditText fields with the existing term information
            termTitleEditText.setText(Title);
            startDateEditText.setText(Start);
            endDateEditText.setText(End);
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTerm();
            }
        });

        RecyclerView recyclerview = findViewById(R.id.coursesInTerm);
        CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerview.setAdapter(courseAdapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        List<Course> filteredCourses = new ArrayList<>();
        for(Course c:repository.getAllCourses()){
            if(c.getTermID()==TermID)filteredCourses.add(c);
        }
        courseAdapter.setCourses(filteredCourses);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_termadd, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        if(item.getItemId()== R.id.termdelete) {

            for(Term term: repository.getAllTerms()){
                if(term.getTermID()==TermID)currentTerm=term;
            }

            numCourses=0;
            for(Course course:repository.getAllCourses()){
                if(course.getTermID()==TermID)++numCourses;
            }

            if(numCourses==0){
                repository.delete(currentTerm);
                Toast.makeText(Termaddedit.this, "The term:" + currentTerm.getTitle()+" has been deleted return to Term Home and refresh",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(Termaddedit.this,"Cannot delete term with courses assigned to it",Toast.LENGTH_LONG).show();
            }
          return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveTerm() {
        String Title = termTitleEditText.getText().toString().trim();
        String Start = startDateEditText.getText().toString().trim();
        String End = endDateEditText.getText().toString().trim();

        // Perform validation on the input fields
        if (TextUtils.isEmpty(Title)) {
            termTitleEditText.setError("Please enter a term title");
            termTitleEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Start)) {
            startDateEditText.setError("Please enter a start date");
            startDateEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(End)) {
            endDateEditText.setError("Please enter an end date");
            endDateEditText.requestFocus();
            return;
        }

        // Save the term to the database
        Repository repository = new Repository(getApplication());
        if (TermID != 0) {
            // Existing term being edited
            Term term = new Term(TermID, Title, Start, End);
            repository.update(term);
        } else {
            // New term being added
            Term term = new Term(TermID, Title, Start, End);
            repository.insert(term);
        }

        finish();
    }


}
