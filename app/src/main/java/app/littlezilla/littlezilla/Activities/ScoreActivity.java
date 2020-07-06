package app.littlezilla.littlezilla.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import app.littlezilla.littlezilla.R;

public class ScoreActivity extends AppCompatActivity {
Toolbar toolbar;
TextView total,totalno,corect,corectno,wrong,wrongno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Score Activity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        total=findViewById(R.id.total);
        totalno=findViewById(R.id.totalno);
        corect=findViewById(R.id.corect);
        corectno=findViewById(R.id.corectno);
        wrong=findViewById(R.id.wrong);
        wrongno=findViewById(R.id.wrongno);
        Intent i=getIntent();
        String question=i.getStringExtra("total");
        String correct=i.getStringExtra("correct");
        String wrong=i.getStringExtra("incorrect");
        totalno.setText(question);
        corectno.setText(correct);
        wrongno.setText(wrong);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
           ScoreActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}