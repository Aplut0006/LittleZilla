package app.littlezilla.littlezilla.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import app.littlezilla.littlezilla.Adapters.HindiAdapter;
import app.littlezilla.littlezilla.Adapters.NumbersAdapter;
import app.littlezilla.littlezilla.Listener.HindiFirebaseLoadDone;
import app.littlezilla.littlezilla.Listener.NumbersFirebaseLoadDone;
import app.littlezilla.littlezilla.Models.HindiModel;
import app.littlezilla.littlezilla.Models.NumbersModel;
import app.littlezilla.littlezilla.R;
import app.littlezilla.littlezilla.Transformer.DepthPageTransformer;

public class ZerotoNineActivity extends AppCompatActivity  implements NumbersFirebaseLoadDone {

    private ViewPager viewPager;
    private NumbersAdapter adapter;
    private DatabaseReference databaseReference;
    private NumbersFirebaseLoadDone firebaseLoadDone;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zeroto_nine);
        viewPager = findViewById(R.id.viewPagerNumbers);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Numbers ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager.setPageTransformer(true,new DepthPageTransformer());


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Numbers");

        firebaseLoadDone = this;


        getData();
    }

    private void getData() {

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            List<NumbersModel> numbersModelList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot myDataSnapshot : snapshot.getChildren()) {
                    numbersModelList.add(myDataSnapshot.getValue(NumbersModel.class));
                }
                firebaseLoadDone.onFirebaseLoadSuccess(numbersModelList);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                firebaseLoadDone.onFirebaseLoadFailed(error.getMessage());
            }
        });
    }



    @Override
    public void onFirebaseLoadSuccess(List<NumbersModel> modelList) {
        adapter = new NumbersAdapter(this,modelList);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onFirebaseLoadFailed(String message) {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            ZerotoNineActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
    }
