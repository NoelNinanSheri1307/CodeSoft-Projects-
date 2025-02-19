package com.example.tasklistapp;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import android.app.AlertDialog;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private final ArrayList<Task> taskList;

    public TaskAdapter(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task currentTask = taskList.get(position);

        // Ensure task title is not null
        String taskTitle = currentTask.getTitle();
        if (taskTitle == null) {
            taskTitle = "Untitled Task";  // Set default value if title is null
        }

        holder.tvTitle.setText(taskTitle);  // Set task title

        // Prevent unwanted checked state changes to avoid conflicts
        holder.cbCompleted.setOnCheckedChangeListener(null);

        // Set checkbox state based on task completion
        holder.cbCompleted.setChecked(currentTask.isCompleted());

        // Handle delete button click
        holder.deleteTask.setOnClickListener(v -> {
            taskList.remove(position);  // Remove the task from the list
            notifyItemRemoved(position);  // Notify RecyclerView about item removal
            notifyItemRangeChanged(position, taskList.size()); // Optional: update remaining items if needed
        });

        String priority = currentTask.getPriority();
        if (priority == null) {
            priority = "Low";  // Default to "Low" if priority is null
        }

        switch (priority) {
            case "High":
                holder.priorityIndicator.setBackgroundColor(Color.RED);  // High priority -> Red
                break;
            case "Medium":
                holder.priorityIndicator.setBackgroundColor(Color.YELLOW);
                break;
            case "Low":
                holder.priorityIndicator.setBackgroundColor(Color.GREEN);
                break;
            default:
                holder.priorityIndicator.setBackgroundColor(Color.GRAY);
                break;
        }

        // Handle modify button click (opens dialog)
        holder.modifyTask.setOnClickListener(v -> openEditTaskDialog(holder.itemView.getContext(), currentTask, position));

        // Set the listener for checkbox changes
        holder.cbCompleted.setOnCheckedChangeListener((buttonView, isChecked) -> {
            currentTask.setCompleted(isChecked); // Update task's completion state
        });
    }


    private void openEditTaskDialog(Context context, Task task, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_add_task, null);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();
        dialog.show();

        EditText etTaskTitle = dialogView.findViewById(R.id.etTaskTitle);
        EditText etTaskDescription = dialogView.findViewById(R.id.etTaskDescription);
        Button btnAddTask = dialogView.findViewById(R.id.btnAddTask);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        Spinner prioritySpinner = dialogView.findViewById(R.id.spinner_priority);


        // Populate fields with current task data
        etTaskTitle.setText(task.getTitle());
        etTaskDescription.setText(task.getDescription());

        // Change button text to 'Update' for modification
        btnAddTask.setText("Update");

        btnAddTask.setOnClickListener(v -> {
            String title = etTaskTitle.getText().toString().trim();
            String description = etTaskDescription.getText().toString().trim();

            if (!title.isEmpty()) {
                task.setTitle(title); // Update task title
                task.setDescription(description); // Update task description
                notifyItemChanged(position); // Notify that item has been modified
                dialog.dismiss(); // Close the dialog
            } else {
                etTaskTitle.setError("Title is required!"); // Show error if title is empty
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.priority_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prioritySpinner.setAdapter(adapter);

        // Populate fields with current task data
        etTaskTitle.setText(task.getTitle());
        etTaskDescription.setText(task.getDescription());

        // Set the spinner selection based on the task's current priority
        String currentPriority = task.getPriority();
        if (currentPriority != null) {
            switch (currentPriority) {
                case "Low":
                    prioritySpinner.setSelection(0);  // Low
                    break;
                case "Medium":
                    prioritySpinner.setSelection(1);  // Medium
                    break;
                case "High":
                    prioritySpinner.setSelection(2);  // High
                    break;
                default:
                    prioritySpinner.setSelection(0);  // Default to Low
                    break;
            }
        }

        // Change button text to 'Update' for modification
        btnAddTask.setText("Update");

        btnAddTask.setOnClickListener(v -> {
            String title = etTaskTitle.getText().toString().trim();
            String description = etTaskDescription.getText().toString().trim();
            String priority = prioritySpinner.getSelectedItem().toString();  // Get selected priority

            if (!title.isEmpty()) {
                task.setTitle(title); // Update task title
                task.setDescription(description); // Update task description
                task.setPriority(priority); // Update task priority
                notifyItemChanged(position); // Notify that item has been modified
                dialog.dismiss(); // Close the dialog
            } else {
                etTaskTitle.setError("Title is required!"); // Show error if title is empty
            }
        });

        btnCancel.setOnClickListener(v -> dialog.dismiss()); // Close the dialog without making changes
    }

    @Override
    public int getItemCount() {
        return taskList.size();  // Return the number of tasks in the list
    }

    // ViewHolder class to hold references to views in each item
    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        CheckBox cbCompleted;
        ImageButton deleteTask, modifyTask;
        View priorityIndicator;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.task_text);
            cbCompleted = itemView.findViewById(R.id.task_checkbox);
            deleteTask = itemView.findViewById(R.id.delete_task);
            modifyTask = itemView.findViewById(R.id.modify_task);
            priorityIndicator = itemView.findViewById(R.id.priorityIndicator);
        }
    }
}
