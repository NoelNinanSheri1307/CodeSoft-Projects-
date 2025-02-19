package com.example.tasklistapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Task list UI components
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private ArrayList<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView productivityMessage = findViewById(R.id.productivity_message);
        productivityMessage.setText("Stay organized, complete tasks, and enhance your productivity!");
        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(taskList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(taskAdapter);
        // Floating Action Button to add tasks
        findViewById(R.id.fabAddTask).setOnClickListener(v -> openAddTaskDialog());
    }

    private void openAddTaskDialog() {
        // Create an AlertDialog to input a new task
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_task, null);
        builder.setView(dialogView);

        // Create the AlertDialog instance
        AlertDialog dialog = builder.create();
        dialog.show();

        // Input fields and buttons in the dialog
        EditText etTaskTitle = dialogView.findViewById(R.id.etTaskTitle);
        EditText etTaskDescription = dialogView.findViewById(R.id.etTaskDescription);
        Button btnAddTask = dialogView.findViewById(R.id.btnAddTask);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);

        // Listener for the Add Task button
        btnAddTask.setOnClickListener(v -> {
            String title = etTaskTitle.getText().toString().trim();
            String description = etTaskDescription.getText().toString().trim();

            if (!title.isEmpty()) {
                // Add a new task to the list
                taskList.add(new Task(title, description, false));
                taskAdapter.notifyDataSetChanged();
                dialog.dismiss(); // Close the dialog
            } else {
                etTaskTitle.setError("Title is required!");
            }
        });

        // Listener for the Cancel button
        btnCancel.setOnClickListener(v -> dialog.dismiss());
    }
}
