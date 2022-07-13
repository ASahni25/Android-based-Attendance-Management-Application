package miniproject.codes.abhishek;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    CardView esd, sepm, ssd, cg, cn, fe;
    Button dbButton;
    String sec = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        sec = getIntent().getStringExtra("section");

        esd = findViewById(R.id.esd);
        sepm = findViewById(R.id.sepm);
        ssd = findViewById(R.id.ssd);
        cg = findViewById(R.id.cg);
        cn = findViewById(R.id.cn);
        fe = findViewById(R.id.fe);
        dbButton = findViewById(R.id.dbbutton);
        esd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), commonActivity.class);
                intent.putExtra("subject", "esd");
                intent.putExtra("section", sec);
                startActivity(intent);
            }
        });
        sepm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), commonActivity.class);
                intent.putExtra("subject", "sepm");
                intent.putExtra("section", sec);
                startActivity(intent);
            }
        });
        ssd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Processing", Toast.LENGTH_SHORT).show();
            }
        });
        cg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Processing", Toast.LENGTH_SHORT).show();
            }
        });
        cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Processing", Toast.LENGTH_SHORT).show();
            }
        });
        fe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Processing", Toast.LENGTH_SHORT).show();
            }
        });
        dbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sec.equals("A")) {
                    Intent intent = new Intent(getApplicationContext(), webViewActivity.class);
                    intent.putExtra("URL","https://abhishek-473fd-default-rtdb.firebaseio.com/sectionA");
                    startActivity(intent);
                } else if (sec.equals("B")) {
                    Intent intent = new Intent(getApplicationContext(), webViewActivity.class);
                    intent.putExtra("URL","https://abhishek-473fd-default-rtdb.firebaseio.com/sectionB");
                    startActivity(intent);
                }
            }
        });

    }
}