package com.example.a9_19438561_dangnhatkhuong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText password;
    EditText email;
    Button btnLogin;
    TextView txtRegister;

    private static final String TAG = "EmailPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        btnLogin = findViewById(R.id.btnLogin_log);
        txtRegister = findViewById(R.id.txtRegister_log);

        password = findViewById(R.id.txtPasswordInputLogin);
        email = findViewById(R.id.txtEmailInputLogin);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(email.getText().toString());
                System.out.println(password.getText().toString());
                singin(email.getText().toString(),password.getText().toString());
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if(currentUser != null){
                currentUser.reload();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void singin(String email,String password){
        System.out.println(email);
        System.out.println(password);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Toast.makeText(Login.this, "Vui lòng kiểm tra lại Email, Password",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }
    public void updateUI(FirebaseUser user){
        if(user != null){
            Intent intent = new Intent(Login.this,MainActivity.class);
            startActivity(intent);
        }
    }
}