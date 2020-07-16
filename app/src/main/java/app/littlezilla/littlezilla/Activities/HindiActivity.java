package app.littlezilla.littlezilla.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import app.littlezilla.littlezilla.Adapters.EnglishAdapter;
import app.littlezilla.littlezilla.Adapters.HindiAdapter;
import app.littlezilla.littlezilla.Listener.FirebaseLoadDone;
import app.littlezilla.littlezilla.Listener.HindiFirebaseLoadDone;
import app.littlezilla.littlezilla.Models.EnglishModel;
import app.littlezilla.littlezilla.Models.HindiModel;
import app.littlezilla.littlezilla.R;
import app.littlezilla.littlezilla.Transformer.DepthPageTransformer;

public class HindiActivity extends AppCompatActivity implements HindiFirebaseLoadDone {

    private ViewPager viewPager;
    private HindiAdapter adapter;
    private DatabaseReference databaseReference;
    private HindiFirebaseLoadDone firebaseLoadDone;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hindi);

        viewPager = findViewById(R.id.viewPagerHindi);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Hindi Alphabets");
        viewPager.setPageTransformer(true,new DepthPageTransformer());


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Hindi");


        firebaseLoadDone = this;


        getData();
    }

    private void getData() {

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            List<HindiModel> hindiModelList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot myDataSnapshot : snapshot.getChildren()) {
                    hindiModelList.add(myDataSnapshot.getValue(HindiModel.class));
                }
                firebaseLoadDone.onFirebaseLoadSuccess(hindiModelList);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                firebaseLoadDone.onFirebaseLoadFailed(error.getMessage());
            }
        });
    }

    @Override
    public void onFirebaseLoadSuccess(List<HindiModel> modelList) {
        adapter = new HindiAdapter(this,modelList);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onFirebaseLoadFailed(String message) {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }
}