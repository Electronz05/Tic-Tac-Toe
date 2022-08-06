package com.example.tic_tac_toe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean executed;
    String winnerStr;
    boolean gameActive = true;
    int i = 0, j = 0;
    public int activePlayer = 1;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winPositions = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
    };

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if (gameState[tappedImage] == 2 && gameActive) {
            gameState[tappedImage] = activePlayer;

            if (activePlayer == 1) {
                img.setImageResource(R.drawable.newx);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                String StatusTxt = "0's turn - Tap to play";
                status.setText(StatusTxt);
            } else {
                img.setImageResource(R.drawable.whiteo);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                String StatusTxt = "X's turn - Tap to play";
                status.setText(StatusTxt);
            }
        }

        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]]
                    && gameState[winPosition[1]] == gameState[winPosition[2]]
                    && gameState[winPosition[0]] != 2) {

                gameActive = false;
                if (gameState[winPosition[0]] == 1) {
                    if (!executed) {
                        winX();
                        executed = true;
                    }
                }

                if (gameState[winPosition[0]] == 0) {
                    if (!executed) {
                        winO();
                        executed = true;
                    }
                }
            }
        }

        boolean emptySqr = false;
        for (int counterState : gameState) {
            if (counterState == 2) {
                emptySqr = true;
                break;
            }
        }

        if (!emptySqr && gameActive) {
            TextView status = findViewById(R.id.status);
            String statusTxt = "It's a draw";
            status.setText(statusTxt);
        }
    }

    public void winX() {
        i++;
        winnerStr = "X has won!";
        TextView status = findViewById(R.id.status);
        status.setText(winnerStr);
        String counter = " X = " + i;
        TextView xCounter = findViewById(R.id.xCounter);
        xCounter.setText(counter);
    }

    public void winO() {
        j++;
        winnerStr = "0 has won!";
        TextView status = findViewById(R.id.status);
        status.setText(winnerStr);
        String counter = " 0 = " + j;
        TextView oCounter = findViewById(R.id.oCounter);
        oCounter.setText(counter);

    }


    public void resetGame(View view) {
        gameActive = true;
        activePlayer = 1;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        ImageView imageView0 = findViewById(R.id.imageView0);
        imageView0.setImageResource(0);
        ImageView imageView1 = findViewById(R.id.imageView1);
        imageView1.setImageResource(0);
        ImageView imageView2 = findViewById(R.id.imageView2);
        imageView2.setImageResource(0);
        ImageView imageView3 = findViewById(R.id.imageView3);
        imageView3.setImageResource(0);
        ImageView imageView4 = findViewById(R.id.imageView4);
        imageView4.setImageResource(0);
        ImageView imageView5 = findViewById(R.id.imageView5);
        imageView5.setImageResource(0);
        ImageView imageView6 = findViewById(R.id.imageView6);
        imageView6.setImageResource(0);
        ImageView imageView7 = findViewById(R.id.imageView7);
        imageView7.setImageResource(0);
        ImageView imageView8 = findViewById(R.id.imageView8);
        imageView8.setImageResource(0);
        TextView status = findViewById(R.id.status);
        String statusTxt = "X's turn - Tap to play";
        status.setText(statusTxt);
        executed = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView xCounter = findViewById(R.id.xCounter);
        xCounter.setText("X = 0");
        TextView oCounter = findViewById(R.id.oCounter);
        oCounter.setText("0 = 0");

    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setMessage("Are you sure you want to exit ?");
        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
