package com.example.wordly_by_yuvalmiz;


import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class GameActivity extends AppCompatActivity {
    private BoardGame boardGame;

    LinearLayout linearLayout,
            linearLayout1,
            linearLayout2,//for all the cells of the guesses
            linearLayout3;
    KeyboardView m;


    private String targetWord;
    private int attempts;
    String result = null;


    String random5LetterWord = "https://random-word-api.herokuapp.com/word?length=5";
    String userGuess;

    public void transferUserGuess(String str) {
        userGuess = str;
        startGame();
    }




    public class DownloadJson extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            URL url;
            HttpURLConnection httpURLConnection;
            InputStream inputStream;
            InputStreamReader inputStreamReader;

            try {
                url = new URL(strings[0]);

                httpURLConnection = (HttpURLConnection) url.openConnection();
                inputStream = httpURLConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                int data = inputStreamReader.read();
                while (data != -1)
                {
                    result += (char)data;
                    data = inputStreamReader.read();
                }

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return result;
        }
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);



        linearLayout = findViewById(R.id.activitygame);
        linearLayout1 = findViewById(R.id.firstLayout);
        linearLayout2 = findViewById(R.id.secondLayout);
        linearLayout3 = findViewById(R.id.thirdLayout);


        boardGame = new BoardGame(this);
        linearLayout2.addView(boardGame);
        m=new KeyboardView(this);
        linearLayout3.addView(m);








        Intent i = getIntent();
        String color = i.getStringExtra("color");
        setBackgroundColor(color);
        DownloadJson downloanJson = new DownloadJson();


        String url = random5LetterWord;

        try {
            result = downloanJson.execute(url).get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        attempts =0;
        targetWord = result;
        Toast.makeText(this, ""+targetWord, Toast.LENGTH_SHORT).show();
    }



    private void startGame() {
        if (userGuess.length() != 5) {
            Toast.makeText(this, "Your word is not 5 letters", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isWordValid(userGuess)) {
            Toast.makeText(this, "Your word does not exist in English", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isWordValid(userGuess) && userGuess.length() == 5) {
            boolean[] targetMatched = new boolean[5]; // Tracks matched letters in the target word
            boolean[] guessMatched = new boolean[5]; // Tracks matched letters in the user guess

            // First pass: Check for correct positions (green)
            for (int i = 0; i < 5; i++) {
                if (userGuess.charAt(i) == targetWord.charAt(i)) {
                    boardGame.setCellBackgroundColor(attempts, i, Color.GREEN);
                    targetMatched[i] = true;
                    guessMatched[i] = true;
                }
            }

            // Second pass: Check for correct letters in wrong positions (yellow)
            for (int i = 0; i < 5; i++) {
                if (!guessMatched[i]) {
                    for (int j = 0; j < 5; j++) {
                        if (!targetMatched[j] && userGuess.charAt(i) == targetWord.charAt(j)) {
                            boardGame.setCellBackgroundColor(attempts, i, Color.YELLOW);
                            targetMatched[j] = true;
                            break;
                        }
                    }
                }
            }

            // Third pass: Mark unmatched letters as gray
            for (int i = 0; i < 5; i++) {
                if (!guessMatched[i] && boardGame.getCellBackgroundColor(attempts, i) == Color.WHITE) {
                    boardGame.setCellBackgroundColor(attempts, i, Color.GRAY);
                }
            }

            attempts++;

            // Check if the game is won
            if (userGuess.equals(targetWord)) {
                Toast.makeText(this, "Congratulations! You guessed the word!", Toast.LENGTH_SHORT).show();
            } else if (attempts == 6) {
                Toast.makeText(this, "Game over! The word was: " + targetWord, Toast.LENGTH_SHORT).show();
            }
        }

        //boardGame.gridCells[0][0].setBackgroundColor(Color.RED);
        boardGame.setCellBackgroundColor(0,0,Color.RED);
    }


    private boolean isWordValid(String userGuess) {
        /// TODO: 13/01/2025 להשלים את זה עם api
        /// TODO: 13/01/2025 https://api.datamuse.com/words?sp=?????&max=1000

        return(true);
    }



    private void setBackgroundColor(String backgroundColor) {
        switch (backgroundColor)
        {
            case"Blue":
            {
                linearLayout.setBackgroundColor(Color.BLUE);
                break;
            }
            case"Purple":
            {
                linearLayout.setBackgroundColor(Color.argb(255,167,29,216));
                break;

            }
            case"Orange":
            {
                linearLayout.setBackgroundColor(Color.argb(255,217,105,28));
                break;

            }


            case "Pink":
            {
                linearLayout.setBackgroundColor(Color.argb(255,255,192,203));
                break;
            }
            case "Cyan":
            {
                linearLayout.setBackgroundColor(Color.argb(255, 0,255,255));
            }
            default:
                break;


        }
    }
}