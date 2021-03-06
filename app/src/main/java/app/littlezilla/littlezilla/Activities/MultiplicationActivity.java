package app.littlezilla.littlezilla.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import app.littlezilla.littlezilla.Models.AddModel;
import app.littlezilla.littlezilla.R;

public class MultiplicationActivity extends AppCompatActivity {
    TextView noofquestion, question;
    Button options1, options2, options3, options4;
    Toolbar toolbar;
    DatabaseReference reference;
    int total = 0;
    int correct = 0;
    int wrong = 0;
    long maxVaragen=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Multiplication");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        noofquestion = findViewById(R.id.no_of_question);
        question = findViewById(R.id.question);
        options1 = findViewById(R.id.optionon1);
        options2 = findViewById(R.id.optionon2);
        options3 = findViewById(R.id.optionon3);
        options4 = findViewById(R.id.optionon4);
        updatequestion();
    }
    private void updatequestion() {
        options1.setEnabled(true);
        options2.setEnabled(true);
        options3.setEnabled(true);
        options4.setEnabled(true);
        reference = FirebaseDatabase.getInstance().getReference().child("Mutiplication");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    maxVaragen = (snapshot.getChildrenCount());
                }
                total++;
                if (total > maxVaragen) {
                    total--;
                    Intent i = new Intent(MultiplicationActivity.this, ScoreActivity.class);
                    i.putExtra("total", String.valueOf(total));
                    i.putExtra("correct", String.valueOf(correct));
                    i.putExtra("incorrect", String.valueOf(wrong));
                    //  i.putExtra("points",String.valueOf(points));
                    startActivity(i);
                }
                else{
                    reference = FirebaseDatabase.getInstance().getReference().child("Mutiplication").child(String.valueOf(total));
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                final AddModel addModel=snapshot.getValue(AddModel.class);
                                noofquestion.setText(addModel.getQuestionno());
                                question.setText(addModel.getQuestion());
                                options1.setText(addModel.getOptionA());
                                options2.setText(addModel.getOptionB());
                                options3.setText(addModel.getOptionC());
                                options4.setText(addModel.getOptionD());

                                options1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        options1.setEnabled(false);
                                        options2.setEnabled(false);
                                        options3.setEnabled(false);
                                        options4.setEnabled(false);
                                        if(options1.getText().toString().equals(addModel.getCorectAns()))
                                        {
                                            Toast.makeText(MultiplicationActivity.this, "Right", Toast.LENGTH_SHORT).show();
                                            options1.setBackgroundColor(Color.GREEN);

                                            Handler handler=new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    correct++;
                                                    //   points=points+10;

                                                    options1.setBackgroundColor(Color.parseColor("#00ACC1"));
                                                    updatequestion();
                                                }
                                            },1000);

                                        }
                                        else
                                        {
                                            //wrong answer
                                            wrong++;
                                            //    points=points-5;
                                            Toast.makeText(MultiplicationActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                                            options1.setBackgroundColor(Color.RED);
                                            if(options2.getText().toString().equals(addModel.getCorectAns()))
                                            {
                                                options2.setBackgroundColor(Color.GREEN);
                                            }
                                            else if(options3.getText().toString().equals(addModel.getCorectAns()))
                                            {
                                                options3.setBackgroundColor(Color.GREEN);
                                            }
                                            else if(options4.getText().toString().equals(addModel.getCorectAns()))
                                            {
                                                options4.setBackgroundColor(Color.GREEN);
                                            }
                                            Handler handler=new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    // correct++;
                                                    options1.setBackgroundColor(Color.parseColor("#00ACC1"));
                                                    options2.setBackgroundColor(Color.parseColor("#00ACC1"));
                                                    options3.setBackgroundColor(Color.parseColor("#00ACC1"));
                                                    options4.setBackgroundColor(Color.parseColor("#00ACC1"));
                                                    updatequestion();
                                                }
                                            },1000);

                                        }
                                    }
                                });
                                options2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        options1.setEnabled(false);
                                        options2.setEnabled(false);
                                        options3.setEnabled(false);
                                        options4.setEnabled(false);
                                        if(options2.getText().toString().equals(addModel.getCorectAns()))
                                        {
                                            options2.setBackgroundColor(Color.GREEN);
                                            Toast.makeText(MultiplicationActivity.this, "Right", Toast.LENGTH_SHORT).show();
                                            Handler handler=new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    correct++;
                                                    //   points=points+10;

                                                    options2.setBackgroundColor(Color.parseColor("#00ACC1"));
                                                    updatequestion();
                                                }
                                            },1000);

                                        }
                                        else
                                        {
                                            //wrong answer
                                            wrong++;
                                            //  points=points-5;
                                            Toast.makeText(MultiplicationActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                                            options2.setBackgroundColor(Color.RED);
                                            if(options1.getText().toString().equals(addModel.getCorectAns()))
                                            {
                                                options1.setBackgroundColor(Color.GREEN);
                                            }
                                            else if(options3.getText().toString().equals(addModel.getCorectAns()))
                                            {
                                                options3.setBackgroundColor(Color.GREEN);
                                            }
                                            else if(options4.getText().toString().equals(addModel.getCorectAns()))
                                            {
                                                options4.setBackgroundColor(Color.GREEN);
                                            }
                                            Handler handler=new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    // correct++;
                                                    options1.setBackgroundColor(Color.parseColor("#00ACC1"));
                                                    options2.setBackgroundColor(Color.parseColor("#00ACC1"));
                                                    options3.setBackgroundColor(Color.parseColor("#00ACC1"));
                                                    options4.setBackgroundColor(Color.parseColor("#00ACC1"));
                                                    updatequestion();
                                                }
                                            },1000);

                                        }
                                    }
                                });
                                options3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        options1.setEnabled(false);
                                        options2.setEnabled(false);
                                        options3.setEnabled(false);
                                        options4.setEnabled(false);
                                        if(options3.getText().toString().equals(addModel.getCorectAns()))
                                        {
                                            options3.setBackgroundColor(Color.GREEN);
                                            Toast.makeText(MultiplicationActivity.this, "Right", Toast.LENGTH_SHORT).show();
                                            Handler handler=new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    correct++;
                                                    //  points=points+10;

                                                    options3.setBackgroundColor(Color.parseColor("#00ACC1"));
                                                    updatequestion();
                                                }
                                            },1000);

                                        }
                                        else
                                        {
                                            //wrong answer
                                            wrong++;
                                            // points=points-5;
                                            options3.setBackgroundColor(Color.RED);
                                            Toast.makeText(MultiplicationActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                                            if(options1.getText().toString().equals(addModel.getCorectAns()))
                                            {
                                                options1.setBackgroundColor(Color.GREEN);
                                            }
                                            else if(options2.getText().toString().equals(addModel.getCorectAns()))
                                            {
                                                options2.setBackgroundColor(Color.GREEN);
                                            }
                                            else if(options4.getText().toString().equals(addModel.getCorectAns()))
                                            {
                                                options4.setBackgroundColor(Color.GREEN);
                                            }
                                            Handler handler=new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    // correct++;
                                                    options1.setBackgroundColor(Color.parseColor("#00ACC1"));
                                                    options2.setBackgroundColor(Color.parseColor("#00ACC1"));
                                                    options3.setBackgroundColor(Color.parseColor("#00ACC1"));
                                                    options4.setBackgroundColor(Color.parseColor("#00ACC1"));
                                                    updatequestion();
                                                }
                                            },1000);

                                        }
                                    }
                                });
                                options4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        options1.setEnabled(false);
                                        options2.setEnabled(false);
                                        options3.setEnabled(false);
                                        options4.setEnabled(false);
                                        if(options4.getText().toString().equals(addModel.getCorectAns()))
                                        {
                                            options4.setBackgroundColor(Color.GREEN);
                                            Toast.makeText(MultiplicationActivity.this, "Right", Toast.LENGTH_SHORT).show();
                                            Handler handler=new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    correct++;
                                                    //   points=points+10;

                                                    options4.setBackgroundColor(Color.parseColor("#00ACC1"));
                                                    updatequestion();
                                                }
                                            },1000);

                                        }
                                        else
                                        {
                                            //wrong answer
                                            wrong++;
                                            // points=points-5;
                                            Toast.makeText(MultiplicationActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                                            options4.setBackgroundColor(Color.RED);
                                            if(options1.getText().toString().equals(addModel.getCorectAns()))
                                            {
                                                options1.setBackgroundColor(Color.GREEN);
                                            }
                                            else if(options2.getText().toString().equals(addModel.getCorectAns()))
                                            {
                                                options2.setBackgroundColor(Color.GREEN);
                                            }
                                            else if(options3.getText().toString().equals(addModel.getCorectAns()))
                                            {
                                                options3.setBackgroundColor(Color.GREEN);
                                            }
                                            Handler handler=new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    // correct++;
                                                    options1.setBackgroundColor(Color.parseColor("#00ACC1"));
                                                    options2.setBackgroundColor(Color.parseColor("#00ACC1"));
                                                    options3.setBackgroundColor(Color.parseColor("#00ACC1"));
                                                    options4.setBackgroundColor(Color.parseColor("#00ACC1"));
                                                    updatequestion();
                                                }
                                            },1000);

                                        }
                                    }
                                });

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            MultiplicationActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}