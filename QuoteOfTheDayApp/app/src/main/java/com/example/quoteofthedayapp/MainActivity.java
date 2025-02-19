package com.example.quoteofthedayapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private TextView quoteTextView;
    private ArrayList<String> quotes;
    private Random random;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "SavedQuotesPrefs";
    private static final String SAVED_QUOTES_KEY = "saved_quotes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteTextView = findViewById(R.id.quoteTextView);
        Button newQuoteButton = findViewById(R.id.newQuoteButton);
        Button shareQuoteButton = findViewById(R.id.shareQuoteButton);
        Button saveQuoteButton = findViewById(R.id.saveQuoteButton);
        Button viewSavedQuotesButton = findViewById(R.id.viewSavedQuotesButton);

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        random = new Random();
        quotes = new ArrayList<>(Arrays.asList(
                "Believe in yourself.",
                "Stay positive and happy.",
                "Your limitation—it’s only your imagination.",
                "Push yourself, because no one else is going to do it for you.",
                "Great things never come from comfort zones.",
                "Dream it. Wish it. Do it.",
                "Success doesn’t just find you. You have to go out and get it.",
                "The harder you work for something, the greater you’ll feel when you achieve it.",
                "Don’t stop when you’re tired. Stop when you’re done.",
                "Wake up with determination. Go to bed with satisfaction.",
                "Do something today that your future self will thank you for.",
                "Little things make big days.",
                "It’s going to be hard, but hard does not mean impossible.",
                "Don’t wait for opportunity. Create it.",
                "Sometimes later becomes never. Do it now.",
                "Success is not for the lazy.",
                "Be stronger than your excuses.",
                "The key to success is to focus on goals, not obstacles.",
                "Dream bigger. Do bigger.",
                "Don’t quit. Suffer now and live the rest of your life as a champion."
        ));

        newQuoteButton.setOnClickListener(v -> displayNewQuote());

        shareQuoteButton.setOnClickListener(v -> {
            String quote = quoteTextView.getText().toString();
            if (!quote.isEmpty()) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, quote);
                startActivity(Intent.createChooser(shareIntent, "Share Quote via"));
            } else {
                Toast.makeText(this, "No quote to share", Toast.LENGTH_SHORT).show();
            }
        });

        saveQuoteButton.setOnClickListener(v -> saveQuote());

        viewSavedQuotesButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SavedQuotesActivity.class);
            startActivity(intent);
        });
    }

    private void displayNewQuote() {
        String newQuote = quotes.get(random.nextInt(quotes.size()));
        quoteTextView.setText(newQuote);
    }

    private void saveQuote() {
        String currentQuote = quoteTextView.getText().toString();
        if (!currentQuote.isEmpty()) {
            Set<String> savedQuotes = new HashSet<>(sharedPreferences.getStringSet(SAVED_QUOTES_KEY, new HashSet<>()));
            if (savedQuotes.add(currentQuote)) {
                sharedPreferences.edit().putStringSet(SAVED_QUOTES_KEY, savedQuotes).apply();
                Toast.makeText(this, "Quote saved!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Quote already saved", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No quote to save", Toast.LENGTH_SHORT).show();
        }
    }
}