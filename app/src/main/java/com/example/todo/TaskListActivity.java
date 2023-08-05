package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class TaskListActivity extends AppCompatActivity {

    private EditText etTask;
    private Button btnAddTask;
    private ListView listViewTasks;

    private List<String> taskList = new ArrayList<>();
    private ArrayAdapter<String> taskListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        etTask = findViewById(R.id.etTask);
        btnAddTask = findViewById(R.id.btnAddTask);
        listViewTasks = findViewById(R.id.listViewTasks);

        taskListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        listViewTasks.setAdapter(taskListAdapter);

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etTask.getText().toString();
                taskList.add(task);
                taskListAdapter.notifyDataSetChanged();
                etTask.setText("");
            }
        });
    }
}
