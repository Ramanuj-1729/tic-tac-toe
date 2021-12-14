package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class gameDisplay extends AppCompatActivity {

    boolean gameActive = true;

    // Player representation
    // 0 - X
    // 1 - O

    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2 };

    // state meanings
    // 0 - X
    // 1 - O
    // 2 - Null

    int[][] winpositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                            {0, 4, 8}, {2, 4, 6}};

    public void playerTap (View view) {
        ImageView img = (ImageView) view;
        int tapedImage = Integer.parseInt(img.getTag().toString());

        if(gameState[tapedImage] == 2){
            gameState[tapedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0){
                img.setImageResource(R.drawable.cross);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn");
            }
            else {
                img.setImageResource(R.drawable.zero);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        // Check if any player won

        for (int[] winPosition: winpositions){
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]]!=2){
                //Somebody has won! - Find out who!

                String winnnerStr;
                if (gameState[winPosition[0]] == 0){
                    winnnerStr = "X won !!";
                }
                else {
                    winnnerStr = "O won !!";
                }
                // Update the status bar for winner announcement
                TextView status = findViewById(R.id.status);
                status.setText(winnnerStr);
            }
        }
        boolean emptySquare = false;

        for (int squareState: gameState)
        {
            if (squareState == 2){
                emptySquare = true;
                break;
            }
        }
        if(!emptySquare && gameActive)
        {
            gameActive = false;
            String winnerStr;
            winnerStr = "Tie !!";
            TextView status = findViewById(R.id.status);
            status.setText(winnerStr);
        }
    }

    public  void playAgain(View view){
        gameActive = true;
        activePlayer = 0;

        for (int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn");
    }

    public void playAgainButtonClick (View view) {
        //do fancy stuff
    }

    public void homeButtonClick (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_display);
    }


}