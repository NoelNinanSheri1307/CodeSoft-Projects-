package com.example.quoteofthedayapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Set;

public class SavedQuotesActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "SavedQuotesPrefs";
    private static final String SAVED_QUOTES_KEY = "saved_quotes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_quotes);

        ListView savedQuotesListView = findViewById(R.id.savedQuotesListView);
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        Set<String> savedQuotesSet = sharedPreferences.getStringSet(SAVED_QUOTES_KEY, null);

        if (savedQuotesSet != null && !savedQuotesSet.isEmpty()) {
            ArrayList<String> savedQuotesList = new ArrayList<>(savedQuotesSet);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, savedQuotesList);
            savedQuotesListView.setAdapter(adapter);
        } else {
            Toast.makeText(this, "No saved quotes found.", Toast.LENGTH_SHORT).show();
        }
    }
}