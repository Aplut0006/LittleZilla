package app.littlezilla.littlezilla.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
    Button prev,next;
    private DatabaseReference databaseReference;
    private HindiFirebaseLoadDone firebaseLoadDone;
    private Toolbar toolbar;
    int page=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hindi);
prev=findViewById(R.id.prevButton);
next=findViewById(R.id.nextButton);
        viewPager = findViewById(R.id.viewPagerHindi);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Hindi Alphabets");
        viewPager.setPageTransformer(true, new DepthPageTransformer());


        databaseReference = FirebaseDatabase.getInstance().getReference().child("hindi");

        firebaseLoadDone = this;
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() -1,true);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() +1,true);
            }
        });
             getData();
        pagechange();
        prev.setVisibility(View.GONE);
    }

    private void pagechange() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                page=position;
                switch (position){
                    case 0:
                        prev.setVisibility(View.GONE);
                        next.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        prev.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        prev.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        prev.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        prev.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);
                        break;
                    case 5:
                        prev.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);
                        break;
                    case 6:
                        prev.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);
                        break;
                    case 7:
                        prev.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);
                        break;
                    case 8:
                        prev.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);
                        break;
                    case 9:
                        prev.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);
                        break;
                    case 10:
                        prev.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);
                        break;
                    case 11:
                        prev.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);
                        break;
                    case 12:
                        prev.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);
                        break;
                    case 13:
                        prev.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);
                        break;
                    case 14:
                        prev.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);
                        break;
                    case 15:
                        prev.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);
                        break;
                    case 16:
                        prev.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);
                        break;
                    case 17:
                        prev.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);
                        break;
                    case 18:
                        prev.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);
                        break;
                    case 19:
                        prev.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);
                        break;
                    case 20:
                        prev.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);
                        break;
                    case 21:
                        prev.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);
                        break;
                    case 22:
                        prev.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);
                        break;
                    case 23:
                        prev.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);
                        break;
                    case 24:
                        prev.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);
                        break;

                    case 25:
                        prev.setVisibility(View.VISIBLE);
                        next.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                startActivity(new Intent(this,MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
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