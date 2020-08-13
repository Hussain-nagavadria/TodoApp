package com.todoreminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentProviderClient;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.PreferenceChangeEvent;

public class MainActivity extends AppCompatActivity implements AddTaskDialog.DialogListner, CustomAdapter.OnNoteListener {
    private static final String LIST_KEY = "List_key";
    private RecyclerView recyclerView;
    private List<Todo> todolist=new ArrayList<>();;
    private CustomAdapter customAdapter;
    CheckBox checkBox;
    EditText et_title, et_desc;
    PrefConfig prefConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todolist=prefConfig.LoadData(this);

        recyclerView = findViewById(R.id.recyclerView);

        checkBox = findViewById(R.id.checkbox);
        customAdapter = new CustomAdapter(todolist, this);


        et_title = findViewById(R.id.et_title);
        et_desc = findViewById(R.id.et_description);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDelete(customAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(customAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
/*
        if(gradientmountains==null)//if null create a new list
        {
            System.out.println("gradientmountains is null creating new gradientmountains.........");
            gradientmountains = new ArrayList<Todo>();
        }
        else{
            System.out.println("gradientmountains is not  null");
    }
*/
        FloatingActionButton fab = findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Floating Action Button", Toast.LENGTH_SHORT).show();
                openDialog();
            }
        });

    }

    private void openDialog() {
        AddTaskDialog addDialog = new AddTaskDialog();
        addDialog.show(getSupportFragmentManager(), "example dialog");
    }

    private void addNewData(String Title, String Description) {
        System.out.println("========addnewdata called======");

        try {
            Todo todo = new Todo(Title, Description);
            todolist.add(todo);
            customAdapter.submitList(todolist);
            customAdapter.notifyDataSetChanged();
            //prefConfig.SaveData(getApplicationContext(),gradientmountains);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void addListItem(String data_title, String data_Description) {
        System.out.println("======addlistitem called=======");
        addNewData(data_title, data_Description);
        customAdapter.submitList(todolist);
    }


    @Override
    public void onNoteClicked(int position) {
        System.out.println("=========onnoteclicked called=======");
        Todo itemposition = todolist.get(position);
        String mytitle = itemposition.getTitle();
        String mydesc = itemposition.getDescription();
        Intent intent = new Intent(MainActivity.this, TaskDetail.class);
        intent.putExtra("Title", mytitle);
        intent.putExtra("Description", mydesc);
        startActivity(intent);
        Toast.makeText(MainActivity.this, "item" + mytitle + "clicked", Toast.LENGTH_SHORT).show();

    }



    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("=======================Activity paused===============");
        prefConfig.SaveData(getApplicationContext(),todolist);
    }



}
