package davis.c196.UI;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import davis.c196.Database.Repository;
import davis.c196.Entities.Assessment;
import davis.c196.Entities.Course;
import davis.c196.R;



public class Assessmentaddedit extends AppCompatActivity {

    private EditText AssessmentTitleText;

    private RadioButton performanceButton;

    private RadioButton objectiveButton;

    private RadioGroup assessmentGroup;

    private Spinner CourseSpinner;

    private EditText ContentMultiLine;

    private Button saveButton;

    private EditText startDateText;
    private EditText endDateText;


    private int CourseID;

    private int AssessmentID;

    Repository repository;
    List<Course> courses;


    private void populateCourseSpinner() {
        Repository repository = new Repository(getApplication());
        courses = repository.getAllCourses();
        List<String> courseTitles = new ArrayList<>();
        for (Course course : courses) {
            courseTitles.add(course.getTitle());
        }
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courseTitles);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CourseSpinner.setAdapter(spinnerAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_addedit);
        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        repository = new Repository(getApplication());


        AssessmentTitleText = findViewById(R.id.AssessmentTitleText);
        performanceButton = findViewById(R.id.performanceButton);
        objectiveButton = findViewById(R.id.objectiveButton);
        assessmentGroup = findViewById(R.id.assessmentGroup);
        CourseSpinner = findViewById(R.id.CourseSpinner);
        saveButton = findViewById(R.id.saveButton3);
        ContentMultiLine = findViewById(R.id.ContentMultiLine);
        startDateText = findViewById(R.id.startDateEditText);
        endDateText = findViewById(R.id.endDateEditText);

        // Get the term information passed from the AssessmentAdapter
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            CourseID = extras.getInt("CourseID");
            AssessmentID = extras.getInt("AssessmentID");
            String Title = extras.getString("Title");
            String type = extras.getString("Type");
            String Start = extras.getString("Start");
            String End = extras.getString("End");
            String Content = extras.getString("Content");


        // Fill the EditText fields with the existing assessment information

        AssessmentTitleText.setText(Title);
        ContentMultiLine.setText(Content);

        startDateText.setText(Start);
        endDateText.setText(End);

        if (type != null) {

            switch (type) {
                case "Performance Assessment":
                    performanceButton.setChecked(true);
                    break;
                case "Objective Assessment":
                    objectiveButton.setChecked(true);
                    break;
            }

        }

        }

        populateCourseSpinner();


        CourseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Course selectedCourse = courses.get(position);
                CourseID = selectedCourse.getCourseID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAssessment();
            }
        });
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_addassessment, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        if (item.getItemId() == R.id.assessmentNotify) {
            String startDateFromScreen = startDateText.getText().toString();
            String myFormat = "MM/dd/yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            try {
                Date startDate = sdf.parse(startDateFromScreen);
                String assessmentName = AssessmentTitleText.getText().toString();


                long startTrigger = startDate.getTime();
                Intent startIntent = new Intent(Assessmentaddedit.this, MyReceiver.class);
                startIntent.putExtra("key", "Your Assessment:" + assessmentName + " Starts Today!");
                PendingIntent startPendingIntent = PendingIntent.getBroadcast(Assessmentaddedit.this, MainActivity.numAlert++, startIntent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager startAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                startAlarmManager.set(AlarmManager.RTC_WAKEUP, startTrigger, startPendingIntent);

                return true;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (item.getItemId() == R.id.assessmentendnotify) {
            String endDateFromScreen = endDateText.getText().toString();
            String myFormat = "MM/dd/yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            try {
                Date endDate = sdf.parse(endDateFromScreen);
                String assessmentName = AssessmentTitleText.getText().toString();


                long endTrigger = endDate.getTime();
                Intent endIntent = new Intent(Assessmentaddedit.this, MyReceiver.class);
                endIntent.putExtra("key", "Your Assessment:" + assessmentName + " Ends Today!");
                PendingIntent endPendingIntent = PendingIntent.getBroadcast(Assessmentaddedit.this, MainActivity.numAlert++, endIntent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager endAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                endAlarmManager.set(AlarmManager.RTC_WAKEUP, endTrigger, endPendingIntent);

                return true;
            } catch (ParseException e) {
                e.printStackTrace();
            }



        }

        if (item.getItemId() == R.id.assessmentDelete) {

            if(AssessmentID != 0){
                Repository repository = new Repository(getApplication());
                Assessment currentAssessment = new Assessment(CourseID,AssessmentID,"","","","","");
                repository.delete(currentAssessment);
                Toast.makeText(this,"The following assessment has been deleted return to the Assessment Home and refresh!", Toast.LENGTH_LONG).show();
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveAssessment() {
        String Title = AssessmentTitleText.getText().toString().trim();
        String Content = ContentMultiLine.getText().toString().trim();
        int selectedRadioButtonID= assessmentGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedRadioButtonID);
        String selectedType = selectedRadioButton.getText().toString().trim();
        String Type = selectedType;
        String Start = startDateText.getText().toString().trim();
        String End = endDateText.getText().toString().trim();


        // Perform validation on the input fields
        if (TextUtils.isEmpty(Title)) {
            AssessmentTitleText.setError("Please enter an assessment name");
            AssessmentTitleText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Content)) {
            ContentMultiLine.setError("Please enter a assessment description");
            ContentMultiLine.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Start)) {
            startDateText.setError("Please enter a start date");
            startDateText.requestFocus();
        }

        if (TextUtils.isEmpty(End)) {
            endDateText.setError("Please enter a end date");
            endDateText.requestFocus();
        }


        // Save the course to the database
        Repository repository = new Repository(getApplication());
        if (AssessmentID != 0) {
            // Existing course being edited
            Assessment assessment = new Assessment(CourseID, AssessmentID, Title, Type, Start, End, Content);
            repository.update(assessment);
        } else {
            // New course being added
            Assessment assessment = new Assessment(CourseID, AssessmentID, Title, Type, Start, End, Content);
            repository.insert(assessment);
        }

        finish();
    }




}


