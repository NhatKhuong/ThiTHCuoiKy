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

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private static final String TAG = "EmailPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        EditText email = findViewById(R.id.txtEmailInput_re);
        EditText password = findViewById(R.id.txtPasswordInput_re);
        EditText rePassword = findViewById(R.id.txtRePasswordInput_re);
        EditText txtName = findViewById(R.id.txtNameInput_re);

        Button btnRegister = findViewById(R.id.btnRegister_re);
        TextView txtLogin_re = findViewById(R.id.txtLogin_re);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtName.getText().toString().equals("") || email.getText().toString().equals("") || password.getText().toString().equals("") || rePassword.getText().toString().equals("")){
                    Toast.makeText(Register.this,"Vui lòng nhập đủ thông tin",Toast.LENGTH_SHORT).show();

                }
                else if(password.getText().toString().equals(rePassword.getText().toString())){
                    createAccount(email.getText().toString(),password.getText().toString());
                } else{
                    Toast.makeText(Register.this,"Xem lai mat khau",Toast.LENGTH_SHORT).show();

                }

            }
        });

        txtLogin_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });

    }

    public void createAccount(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.

                            updateUI(null);
                        }
                    }
                });
    }

    public void updateUI(FirebaseUser user){
        if(user != null){
            Toast.makeText(Register.this, "createUserWithEmail:success",
                    Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(Register.this, "Authentication failed.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}