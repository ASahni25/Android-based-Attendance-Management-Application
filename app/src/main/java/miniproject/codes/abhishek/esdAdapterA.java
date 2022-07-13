package miniproject.codes.abhishek;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class esdAdapterA extends FirebaseRecyclerAdapter<subjectModel, esdAdapterA.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public esdAdapterA(@NonNull FirebaseRecyclerOptions<subjectModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull subjectModel model) {
        holder.rollno.setText(model.getRollNo());
        holder.studnetName.setText(model.getName());
        final int[] currentTotal = new int[1];

        FirebaseDatabase.getInstance().getReference().child("sectionA").child(getRef(holder.getAdapterPosition()).getKey()).child("esd").child("total").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                currentTotal[0] = snapshot.getValue(Integer.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        holder.present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pattern = "dd-MM-yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String date = simpleDateFormat.format(new Date());
                Map<String, Object> map = new HashMap<>();
                map.put("total", currentTotal[0] + 1);

                FirebaseDatabase.getInstance().getReference().child("sectionA").child(getRef(holder.getLayoutPosition()).getKey()).child("esd").child(date).setValue("1");
                FirebaseDatabase.getInstance().getReference().child("sectionA").child(getRef(holder.getLayoutPosition()).getKey()).child("esd").updateChildren(map);
            }
        });

        holder.absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pattern = "dd-mm-yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String date = simpleDateFormat.format(new Date());
                FirebaseDatabase.getInstance().getReference().child("sectionA").child(getRef(holder.getLayoutPosition()).getKey()).child("esd").child(date).setValue("0");
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_item, parent, false));
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        TextView rollno, present, absent, studnetName;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            rollno = (TextView) itemView.findViewById(R.id.rollno);
            present = (TextView) itemView.findViewById(R.id.present);
            absent = (TextView) itemView.findViewById(R.id.absent);
            studnetName = (TextView) itemView.findViewById(R.id.nameStudent);
        }
    }
}