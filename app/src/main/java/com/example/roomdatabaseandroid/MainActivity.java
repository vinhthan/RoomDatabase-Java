package com.example.roomdatabaseandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomdatabaseandroid.database.User;
import com.example.roomdatabaseandroid.database.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edtName;
    Button btnAdd;
    RecyclerView rcy;
    UserAdapter userAdapter;
    List<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.edt_title);
        btnAdd = findViewById(R.id.btn_add);
        rcy = findViewById(R.id.rcy);

        userAdapter = new UserAdapter();
        list = new ArrayList<>();
        userAdapter.setData(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcy.setLayoutManager(linearLayoutManager);
        rcy.setAdapter(userAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });
        list = UserDatabase.getInstance(this).userDAO().getListUser();
        userAdapter.setData(list);


    }

    private void addUser() {
        String name = edtName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            return;
        }
        User user = new User(name);

        UserDatabase.getInstance(this).userDAO().insertUser(user);
        Toast.makeText(this, "add success", Toast.LENGTH_SHORT).show();
        edtName.setText("");

        list = UserDatabase.getInstance(this).userDAO().getListUser();
        userAdapter.setData(list);
    }
}