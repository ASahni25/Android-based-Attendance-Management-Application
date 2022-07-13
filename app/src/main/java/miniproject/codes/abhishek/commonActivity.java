package miniproject.codes.abhishek;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class commonActivity extends AppCompatActivity {

    RecyclerView aRecyclerView;
    esdAdapterA aEsdAdapterA;
    esdAdapterB aEsdAdapterB;
    sepmAdapterA aSepmAdapterA;
    sepmAdapterB aSepmAdapterB;
    String name, sec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        name = getIntent().getStringExtra("subject");
        sec = getIntent().getStringExtra("section");
        getSupportActionBar().setTitle(name.toUpperCase() + " Attendance");

        setContentView(R.layout.activity_common);

        aRecyclerView = (RecyclerView) findViewById(R.id.rec_view);
        aRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<subjectModel> options;

        if (sec.equals("A"))
            options = new FirebaseRecyclerOptions.Builder<subjectModel>()
                    .setQuery(FirebaseDatabase.getInstance().getReference().child("sectionA"), subjectModel.class)
                    .build();
        else
            options = new FirebaseRecyclerOptions.Builder<subjectModel>()
                    .setQuery(FirebaseDatabase.getInstance().getReference().child("sectionB"), subjectModel.class)
                    .build();

        if (name.equals("esd") && sec.equals("A")) {
            aEsdAdapterA = new esdAdapterA(options);
            aRecyclerView.setAdapter(aEsdAdapterA);
        } else if (name.equals("esd") && sec.equals("B")) {
            aEsdAdapterB = new esdAdapterB(options);
            aRecyclerView.setAdapter(aEsdAdapterB);
        } else if (name.equals("sepm") && sec.equals("A")) {
            aSepmAdapterA = new sepmAdapterA(options);
            aRecyclerView.setAdapter(aSepmAdapterA);
        } else if (name.equals("sepm") && sec.equals("B")) {
            aSepmAdapterB = new sepmAdapterB(options);
            aRecyclerView.setAdapter(aSepmAdapterB);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (name.equals("esd") && sec.equals("A")) {
            aEsdAdapterA.startListening();
        } else if (name.equals("esd") && sec.equals("B")) {
            aEsdAdapterB.startListening();
        } else if (name.equals("sepm") && sec.equals("A")) {
            aSepmAdapterA.startListening();
        } else if (name.equals("sepm") && sec.equals("B")) {
            aSepmAdapterB.startListening();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (name.equals("esd") && sec.equals("A")) {
            aEsdAdapterA.stopListening();
        } else if (name.equals("esd") && sec.equals("B")) {
            aEsdAdapterB.stopListening();
        } else if (name.equals("sepm") && sec.equals("A")) {
            aSepmAdapterA.stopListening();
        } else if (name.equals("sepm") && sec.equals("B")) {
            aSepmAdapterB.stopListening();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query.toUpperCase());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query.toUpperCase());
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void txtSearch(String str) {
        FirebaseRecyclerOptions<subjectModel> options = new FirebaseRecyclerOptions.Builder<subjectModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("student").orderByChild("rollNo").startAt(str).endAt(str + "~"), subjectModel.class)
                .build();
        if (name.equals("esd") && sec.equals("A")) {
            aEsdAdapterA = new esdAdapterA(options);
            aEsdAdapterA.startListening();
            aRecyclerView.setAdapter(aEsdAdapterA);
        } else if (name.equals("esd") && sec.equals("B")) {
            aEsdAdapterB = new esdAdapterB(options);
            aEsdAdapterB.startListening();
            aRecyclerView.setAdapter(aEsdAdapterB);
        } else if (name.equals("sepm") && sec.equals("A")) {
            aSepmAdapterA = new sepmAdapterA(options);
            aSepmAdapterA.startListening();
            aRecyclerView.setAdapter(aSepmAdapterA);
        } else if (name.equals("sepm") && sec.equals("B")) {
            aSepmAdapterB = new sepmAdapterB(options);
            aSepmAdapterB.startListening();
            aRecyclerView.setAdapter(aSepmAdapterB);
        }

    }
}