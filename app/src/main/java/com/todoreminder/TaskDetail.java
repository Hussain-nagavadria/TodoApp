package com.todoreminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class TaskDetail extends AppCompatActivity {
String title,description;
TextView Text_Title,Text_Description;
Toolbar toolbar;
EditText et_test;
String test_data;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        Intent intent=getIntent();
        title=intent.getStringExtra("Title");
        description=intent.getStringExtra("Description");
        Text_Title=findViewById(R.id.tv_title);
        Text_Description=findViewById(R.id.tv_desciption);
        Text_Title.setText(title);
        Text_Description.setText(description);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        et_test=findViewById(R.id.tv_test);

         System.out.println("The value of the edit text is "+test_data);


    }

    @Override
    protected void onPause() {
        super.onPause();

       }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
