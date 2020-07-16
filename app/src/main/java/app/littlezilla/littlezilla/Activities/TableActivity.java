package app.littlezilla.littlezilla.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import app.littlezilla.littlezilla.Adapters.FragmentTableAdapter;
import app.littlezilla.littlezilla.R;

public class TableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        ViewPager viewPager = findViewById(R.id.viewPager);
        FragmentTableAdapter fragmentAdapter = new FragmentTableAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdapter);
    }
}