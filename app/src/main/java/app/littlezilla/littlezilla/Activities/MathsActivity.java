package app.littlezilla.littlezilla.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import app.littlezilla.littlezilla.Adapters.MathsCategoryAdapter;
import app.littlezilla.littlezilla.R;

public class MathsActivity extends AppCompatActivity {

private Toolbar toolbar;
    RecyclerView recyclerView;
    MathsCategoryAdapter adapter;
    List<String> catlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maths);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Maths Category");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
   recyclerView=findViewById(R.id.recyclerview);
   recyclerView.setHasFixedSize(true);

      catlist=new ArrayList<>();
        catlist.add("0-20");
        catlist.add("+");
        catlist.add("-");
        catlist.add("x");
        catlist.add("/");
        catlist.add("table");
        adapter=new MathsCategoryAdapter(catlist,this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            MathsActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}