package com.example.tic_tac_toe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Gameactivity extends AppCompatActivity implements View.OnClickListener {

    // 0 1 2
    // 3 4 5
    // 6 7 8

    public Button resetButton;
    public ImageView[] buttons = new ImageView[9];
    int gameStatus[] = {2,2,2,2,2,2,2,2,2};
    int winningCombinations[][] = {{0,1,2}, {3,4,5}, {6,7,8}, // rows
                                   {0,3,6}, {1,4,7}, {2,5,8}, // columns
                                   {0,4,8}, {2,4,6}}; // diagonals
    int playerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameactivity);

        resetButton = findViewById(R.id.ResetButton);

        buttons[0] = findViewById(R.id.id_1);
        buttons[1] = findViewById(R.id.id_2);
        buttons[2] = findViewById(R.id.id_3);
        buttons[3] = findViewById(R.id.id_4);
        buttons[4] = findViewById(R.id.id_5);
        buttons[5] = findViewById(R.id.id_6);
        buttons[6] = findViewById(R.id.id_7);
        buttons[7] = findViewById(R.id.id_8);
        buttons[8] = findViewById(R.id.id_9);

        buttons[0].setOnClickListener(this);
        buttons[1].setOnClickListener(this);
        buttons[2].setOnClickListener(this);
        buttons[3].setOnClickListener(this);
        buttons[4].setOnClickListener(this);
        buttons[5].setOnClickListener(this);
        buttons[6].setOnClickListener(this);
        buttons[7].setOnClickListener(this);
        buttons[8].setOnClickListener(this);

        Intent intent = getIntent();
        playerId = intent.getIntExtra("key",1);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

    public void reset(){
        for(ImageView view: buttons) // clearing all image views
        {
            view.setImageResource(R.drawable.bg);
        }

        for(int i = 0; i < 9; i++) // clearing game status
        {
            gameStatus[i] = 2;
        }

        if(playerId%2 != 0) // setting player id to original
        {
            playerId = 1;
        }
        else {
            playerId = 2;
        }
    }

    public boolean checkForWin(){

        boolean bool = false;
        for(int arr[]: winningCombinations)
        {
            if(gameStatus[arr[0]] == gameStatus[arr[1]] && //checking 0 with 1
                    gameStatus[arr[1]] == gameStatus[arr[2]] && //checking 1 with 2
                    gameStatus[arr[0]] != 2) // cheking 0 is not 2
            {
                bool = true;
            }
        }

        return bool;
    }

    public void playerWin(int n){
        AlertDialog alertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(Gameactivity.this);

        View view = getLayoutInflater().inflate(R.layout.dialog, null);

        TextView textView = view.findViewById(R.id.dialogTextView);
        Button button = view.findViewById(R.id.dialogButton);

        if(n == 1){
            textView.setText("Player 1 Wins!");
        }else
        {
            textView.setText("Player 2 Wins!");
        }

        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                reset();
            }
        });
    }

    public void player2Win(){

    }

    @Override
    public void onClick(View v) {
        String stringId = v.getResources().getResourceEntryName(v.getId());

        int id = 0;

        switch(stringId){
            case "id_1":
                id = 0;
                break;
            case "id_2":
                id = 1;
                break;
            case "id_3":
                id = 2;
                break;
            case "id_4":
                id = 3;
                break;
            case "id_5":
                id = 4;
                break;
            case "id_6":
                id = 5;
                break;
            case "id_7":
                id = 6;
                break;
            case "id_8":
                id = 7;
                break;
            case "id_9":
                id = 8;
                break;
        }

        if(gameStatus[id] == 0 || gameStatus[id] == 1){
            Toast.makeText(Gameactivity.this, "Occupied", Toast.LENGTH_LONG).show();
        }else
        {

            if(playerId%2 != 0) {
                ((ImageView)v).setImageResource(R.drawable.x);
                gameStatus[id] = 0;

                if(checkForWin())
                {
                    playerWin(1);
                }

                playerId++;
            }
            else {
                ((ImageView)v).setImageResource(R.drawable.o);
                gameStatus[id] = 1;

                if(checkForWin())
                {
                    playerWin(2);
                }

                playerId++;
            }
        }
    }
}
