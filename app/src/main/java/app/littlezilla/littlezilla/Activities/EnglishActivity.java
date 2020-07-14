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
import app.littlezilla.littlezilla.Listener.FirebaseLoadDone;
import app.littlezilla.littlezilla.Models.EnglishModel;
import app.littlezilla.littlezilla.R;
import app.littlezilla.littlezilla.Transformer.DepthPageTransformer;

public class EnglishActivity extends AppCompatActivity implements FirebaseLoadDone {

    private ViewPager viewPager;
    private EnglishAdapter adapter;
    private DatabaseReference databaseReference;
    private FirebaseLoadDone firebaseLoadDone;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english);
        viewPager = findViewById(R.id.viewPagerEnglish);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("English Alphabets");
        viewPager.setPageTransformer(true,new DepthPageTransformer());
        databaseReference = FirebaseDatabase.getInstance().getReference().child("english");

        firebaseLoadDone = this;


        getData();
    }

    private void getData() {

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            List<EnglishModel> englishModelList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot myDataSnapshot : snapshot.getChildren()) {
                    englishModelList.add(myDataSnapshot.getValue(EnglishModel.class));
                }
                firebaseLoadDone.onFirebaseLoadSuccess(englishModelList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                    firebaseLoadDone.onFirebaseLoadFailed(error.getMessage());
            }
        });
    }

    @Override
    public void onFirebaseLoadSuccess(List<EnglishModel> modelList) {
            adapter = new EnglishAdapter(this,modelList);
            viewPager.setAdapter(adapter);
    }

    @Override
    public void onFirebaseLoadFailed(String message) {
        Toast.makeText(this, "Error Loading Data", Toast.LENGTH_SHORT).show();
    }
}