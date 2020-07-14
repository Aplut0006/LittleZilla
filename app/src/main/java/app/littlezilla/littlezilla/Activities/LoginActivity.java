package app.littlezilla.littlezilla.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import app.littlezilla.littlezilla.R;

public class LoginActivity extends AppCompatActivity {
    EditText Gemail, mpassword;
    Button Login,skip;
    TextView SiginupText, Forgettext;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Gemail=findViewById(R.id.email);
        mpassword=findViewById(R.id.password);
        Login=findViewById(R.id.login);
        skip=findViewById(R.id.skip);
        SiginupText=findViewById(R.id.siginup);
        Forgettext=findViewById(R.id.forget);
        progressBar = findViewById(R.id.progresbar);
        mAuth = FirebaseAuth.getInstance();

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser currentUser=mAuth.getCurrentUser();
                updateUI(currentUser);
            }
        });

        //login par click listener
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Gemail.getText().toString().trim();
                String password = mpassword.getText().toString().trim();

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

                progressBar.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Login sucessfully" +
                                    "", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Error!.." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }

        });
        SiginupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
        Forgettext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder PasswordResetDilog = new AlertDialog.Builder(v.getContext());
                PasswordResetDilog.setTitle("Reset Password");
                PasswordResetDilog.setMessage("Enter Your Email to recived Reset Link");
                PasswordResetDilog.setView(resetMail);
                PasswordResetDilog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //extract email
                        String mail = resetMail.getText().toString().trim();
                        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(LoginActivity.this, "Reset Link Sent to Email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this, "Error!.. Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
                PasswordResetDilog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                PasswordResetDilog.create().show();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(this, MainActivity.class));

        }
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser==null)
        {
            mAuth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        FirebaseUser currentUser=mAuth.getCurrentUser();
                        updateUI(currentUser);

                    }
                    else {
                        updateUI(null);
                    }
                }
            });
        }
    }

}
