package app.littlezilla.littlezilla.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import app.littlezilla.littlezilla.R;

public class RegisterActivity extends AppCompatActivity {
    EditText mname, Gemail, mpassword, mphone;
    Button Register;
    TextView Logintext;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mname = findViewById(R.id.userName);
        mphone = findViewById(R.id.phone);
        Gemail = findViewById(R.id.email);
        mpassword = findViewById(R.id.password);

        Register = findViewById(R.id.register);
        Logintext = findViewById(R.id.textLogin);
        progressBar = findViewById(R.id.progresbar);
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Gemail.getText().toString().trim();
                String password = mpassword.getText().toString().trim();
                String name = mname.getText().toString().trim();
                String phone = mphone.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Gemail.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mpassword.setError("Password must be required");
                    return;
                }
                if (password.length() < 6) {
                    mpassword.setError("Password length must be >= 6");
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    mname.setError("Name is required");
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    mname.setError("Phone no is required");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                //register the user firrebase
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "User created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(RegisterActivity.this, "Error!.." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });
        Logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }
/*
    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(this, MainActivity.class));

        }
    } */
}