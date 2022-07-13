package miniproject.codes.abhishek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class addUser extends AppCompatActivity {
    EditText rollno, name, section;
    Button save;

    String decSec = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_user);
        rollno = findViewById(R.id._rollNo);
        name = findViewById(R.id._name);
        section = findViewById(R.id._sec);

        save = findViewById(R.id.add_btn);

        decSec = section.getText().toString().toUpperCase().trim();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CheckInternet checkInternet = new CheckInternet();
                if (!checkInternet.isConnected(getApplicationContext())) {
                    Toast.makeText(addUser.this, "Man connect to network first to save users", Toast.LENGTH_LONG).show();
                    ;
                    return;
                }

                String n = name.getText().toString().trim();
                String n1 = rollno.getText().toString().trim();

                if (n.length() > 0 && n1.length() > 0) {
                    insertData();
                } else {
                    Toast.makeText(getApplicationContext(), "pls fill all the field", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void insertData() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();

        String n;

        if (name.getText().toString().trim().length() > 1)
            n = name.getText().toString().trim().substring(0, 1).toUpperCase() + name.getText().toString().trim().substring(1);
        else
            n = name.getText().toString().trim();
        map.put("name", n);
        map.put("rollNo", rollno.getText().toString().trim().toUpperCase());

        map1.put("total", 0);
        map.put("cg", map1);

        map1.clear();
        map1.put("total", 0);
        map.put("cn", map1);

        map1.clear();
        map1.put("total", 0);
        map.put("ssd", map1);

        map1.clear();
        map1.put("total", 0);
        map.put("fe", map1);

        map1.clear();
        map1.put("total", 0);
        map.put("sepm", map1);

        map1.clear();
        map1.put("total", 0);
        map.put("esd", map1);

        String key = rollno.getText().toString().trim().toUpperCase();

        if (section.getText().toString().toUpperCase().equals("A")) {
            FirebaseDatabase.getInstance().getReference().child("sectionA").child(key).setValue(map)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();
                            clearAll();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Error While Insertion", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            FirebaseDatabase.getInstance().getReference().child("sectionB").child(key).setValue(map)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();
                            clearAll();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Error While Insertion", Toast.LENGTH_SHORT).show();
                        }
                    });
        }


    }

    private void clearAll() {
        name.setText("");
        rollno.setText("");
    }
}