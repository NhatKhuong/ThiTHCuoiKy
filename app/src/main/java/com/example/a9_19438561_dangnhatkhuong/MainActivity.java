package com.example.a9_19438561_dangnhatkhuong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView lvCongViec;
    List<CongViec> list;
    CongViecAdapter adt;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView txtCongViec;
    TextView txtGhiChu;
    public static MainActivity getInstance;

    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getInstance = this;

        lvCongViec = findViewById(R.id.lvCongViec);
        list = new ArrayList<>();
        getListCongViec();

        txtCongViec = findViewById(R.id.txtCongViecInput);
        txtGhiChu = findViewById(R.id.txtGhiChuInput);
        btnAdd = findViewById(R.id.btnAdd);

        adt = new CongViecAdapter(this,R.layout.item_lv_work,list);
        lvCongViec.setAdapter(adt);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CongViec congViec = new CongViec(txtCongViec.getText().toString(),txtGhiChu.getText().toString());
                addCongViec(congViec);
                updateUI();
            }
        });

    }

    public void getListCongViec() {


        db.collection("congviec")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CongViec congViec = new CongViec(document.getString("congViec"), document.getString("ghiChu"));
                                congViec.setId(document.getId());
                                list.add(congViec);
                                System.out.println(list);

                            }
                            adt.notifyDataSetChanged();
                        }
                    }
                });
        System.out.println("list---->" + list);
    }

    public void addCongViec(CongViec congViec1){
        // Create a new user with a first and last name
        Map<String, Object> congViec = new HashMap<>();
        congViec.put("congViec",congViec1.getTenCongViec());
        congViec.put("ghiChu", congViec1.getGhiChu());


// Add a new document with a generated ID
        db.collection("congviec")
                .add(congViec)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(MainActivity.this,"Add success",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,"Add fail",Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void updateUI(){
        getListCongViec();
        txtCongViec.setText("");
        txtGhiChu.setText("");

    }

    public void delete(String id){
        db.collection("congviec").document(id)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this,"Delete success",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,"Delete fail",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void update(CongViec congViec1){

        Map<String, Object> congViec = new HashMap<>();
        congViec.put("congViec",congViec1.getTenCongViec());
        congViec.put("ghiChu", congViec1.getGhiChu());


        db.collection("congviec").document(congViec1.getId())
                .set(congViec)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this,"Update success",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,"Update fail",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public static MainActivity getMainActive(){
        return getInstance;
    }

}