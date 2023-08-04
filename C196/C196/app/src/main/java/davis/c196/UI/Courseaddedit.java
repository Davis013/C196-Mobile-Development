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
import davis.c196.Entities.Term;
import davis.c196.R;

public class Courseaddedit extends AppCompatActivity {
    private EditText coursenameeditText;
    private EditText editStartCDate;
    private EditText editEndCDate;
    private Button saveButton;

    private EditText MentorEmail;
    private EditText MentorName;
    private EditText MentorPhone;
    private EditText NotesText;

    private RadioButton InProgressButton;
    private RadioButton DroppedButton;
    private RadioButton PlanToTakeButton;
    private RadioButton CompletedButton;
    private RadioGroup statusRadioGroup;

    private Spinner spinner;

    Repository repository;

    private int CourseID;
    private int TermID;
    private List<Term> terms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_addedit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        repository = new Repository(getApplication());

        coursenameeditText = findViewById(R.id.coursenameeditText);
        editStartCDate = findViewById(R.id.editStartCDate);
        editEndCDate = findViewById(R.id.editEndCDate);
        saveButton = findViewById(R.id.saveButton2);
        MentorName = findViewById(R.id.MentorName);
        MentorEmail = findViewById(R.id.MentorEmail);
        MentorPhone = findViewById(R.id.MentorPhone);
        NotesText = findViewById(R.id.NotesText);
        InProgressButton = findViewById(R.id.InProgressButton);
        DroppedButton = findViewById(R.id.DroppedButton);
        CompletedButton = findViewById(R.id.CompletedButton);
        PlanToTakeButton = findViewById(R.id.PlanToTakeButton);
        statusRadioGroup = findViewById(R.id.statusRadioGroup);
        spinner = findViewById(R.id.spinner);

        // Get the term information passed from the CourseAdapter
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            CourseID = extras.getInt("CourseID");
            TermID = extras.getInt("TermID");
            String Title = extras.getString("Title");
            String Start = extras.getString("Start");
            String End = extras.getString("End");
            String status = extras.getString("Status");
            String Mentorname = extras.getString("MentorName");
            String Mentoremail = extras.getString("MentorEmail");
            String Mentorphone = extras.getString("MentorPhone");
            String Note = extras.getString("Note");

            // Fill the EditText fields with the existing course information
            coursenameeditText.setText(Title);
            editStartCDate.setText(Start);
            editEndCDate.setText(End);
            MentorName.setText(Mentorname);
            MentorEmail.setText(Mentoremail);
            MentorPhone.setText(Mentorphone);
            NotesText.setText(Note);

            if (status != null) {
                switch (status) {
                    case "In Progress":
                        InProgressButton.setChecked(true);
                        break;
                    case "Dropped":
                        DroppedButton.setChecked(true);
                        break;
                    case "Plan to Take":
                        PlanToTakeButton.setChecked(true);
                        break;
                    case "Completed":
                        CompletedButton.setChecked(true);
                        break;
                }
            }
        }

        populateSpinner();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Term selectedTerm = terms.get(position);
                TermID = selectedTerm.getTermID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });




        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCourse();
            }
        });
    }

    private void populateSpinner() {
        Repository repository = new Repository(getApplication());
        terms = repository.getAllTerms();

        List<String> termTitles = new ArrayList<>();
        for (Term term : terms) {
            termTitles.add(term.getTitle());
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, termTitles);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_addeditcourse, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        if (item.getItemId() == R.id.action_notify) {
            String startDateFromScreen = editStartCDate.getText().toString();
            String myFormat = "MM/dd/yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            try {
                Date startDate = sdf.parse(startDateFromScreen);
                String courseName = coursenameeditText.getText().toString();


                long startTrigger = startDate.getTime();
                Intent startIntent = new Intent(Courseaddedit.this, MyReceiver.class);
                startIntent.putExtra("key", "Your Course: " + courseName + " Starts Today!");
                PendingIntent startPendingIntent = PendingIntent.getBroadcast(Courseaddedit.this, MainActivity.numAlert++, startIntent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager startAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                startAlarmManager.set(AlarmManager.RTC_WAKEUP, startTrigger, startPendingIntent);

                return true;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (item.getItemId() == R.id.action_notifyend) {
            String endDateFromScreen = editEndCDate.getText().toString();
            String myFormat = "MM/dd/yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            try {
                Date endDate = sdf.parse(endDateFromScreen);
                String courseName = coursenameeditText.getText().toString();

                long endTrigger = endDate.getTime();
                Intent endIntent = new Intent(Courseaddedit.this, MyReceiver.class);
                endIntent.putExtra("key", "Your Course: " + courseName + " Ends Today!");
                PendingIntent endPendingIntent = PendingIntent.getBroadcast(Courseaddedit.this, MainActivity.numAlert++, endIntent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager endAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                endAlarmManager.set(AlarmManager.RTC_WAKEUP, endTrigger, endPendingIntent);

                return true;
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        if (item.getItemId() == R.id.action_share) {
            try {
                EditText notesText = findViewById(R.id.NotesText);
                String notes = notesText.getText().toString();

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, notes);
                shareIntent.putExtra(Intent.EXTRA_TITLE, "Notes From Course");
                startActivity(Intent.createChooser(shareIntent, "Share via"));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        if (item.getItemId() == R.id.coursedelete) {
            if(CourseID != 0){
                Repository repository = new Repository(getApplication());
                Course currentCourse = new Course(TermID, CourseID,"","","","","","","","");
                repository.delete(currentCourse);
                Toast.makeText(this,"The following course has been deleted return to the Course Home and refresh!", Toast.LENGTH_LONG).show();
            }

            return true;
        }

        return super.onOptionsItemSelected(item);


    }







    private void saveCourse() {
        String Title = coursenameeditText.getText().toString().trim();
        String Start = editStartCDate.getText().toString().trim();
        String End = editEndCDate.getText().toString().trim();
        String Mentorname = MentorName.getText().toString().trim();
        String Mentoremail = MentorEmail.getText().toString().trim();
        String Mentorphone = MentorPhone.getText().toString().trim();
        String Note = NotesText.getText().toString().trim();
        int selectedRadioButtonID= statusRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedRadioButtonID);
        String selectedStatus = selectedRadioButton.getText().toString().trim();
        String Status = selectedStatus;


        // Perform validation on the input fields
        if (TextUtils.isEmpty(Title)) {
            coursenameeditText.setError("Please enter a course name");
            coursenameeditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Start)) {
            editStartCDate.setError("Please enter a start date");
            editStartCDate.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(End)) {
            editEndCDate.setError("Please enter an end date");
            editEndCDate.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Mentorname)) {
            MentorName.setError("Please enter Course Instructor's Name");
            MentorName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Mentoremail)) {
            MentorEmail.setError("Please enter valid Course Instructor's Email");
            MentorEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Mentorphone)) {
            MentorPhone.setError("Please enter valid Instructor Phone Number");
            MentorPhone.requestFocus();
            return;
        }

        // Save the course to the database
        Repository repository = new Repository(getApplication());
        if (CourseID != 0) {
            // Existing course being edited
            Course course = new Course(TermID, CourseID, Title, Start, End, Status, Mentorname, Mentorphone, Mentoremail, Note);
            repository.update(course);
        } else {
            // New course being added
            Course course = new Course(TermID, CourseID, Title, Start, End, Status, Mentorname, Mentorphone, Mentoremail, Note);
            repository.insert(course);
        }

        finish();
    }
}
