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
import davis.c196.Entities.Term;
import davis.c196.R;
public class TermHome extends AppCompatActivity implements TermAdapter.OnItemClickListener {


    private TermAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Repository repository = new Repository(getApplication());
        List<Term> terms = repository.getAllTerms();
        adapter = new TermAdapter(this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setTerms(terms);

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermHome.this, Termaddedit.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_termhome, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }

        if (item.getItemId() == R.id.termRefresh) {
            Repository repository = new Repository(getApplication());
            List<Term> terms = repository.getAllTerms();
            RecyclerView recyclerview = findViewById(R.id.recyclerView);
            TermAdapter termAdapter = new TermAdapter(this, this);
            recyclerview.setAdapter(termAdapter);
            recyclerview.setLayoutManager(new LinearLayoutManager(this));
            termAdapter.setTerms(terms);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(Term term) {
        Intent intent = new Intent(TermHome.this, Termaddedit.class);
        intent.putExtra("TermID", term.getTermID());
        intent.putExtra("title", term.getTitle());
        intent.putExtra("start", term.getStart());
        intent.putExtra("end", term.getEnd());
        startActivity(intent);
    }


    public void goToCourseHome(View view) {
        Intent intent = new Intent(TermHome.this, CourseHome.class);
        startActivity(intent);
    }
}





