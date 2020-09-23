package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


public class Second extends AppCompatActivity implements View.OnClickListener {
    //add for showing
    private AdView mAdView;
    private AdView mAdView1;
    private TextView playerOneScore,playerTwoScore,playerStatus;
    private Button[] buttons =new Button[9];
    private Button resetGame;
    private SoundPlayer sound;

    private int playerOneScoreCount,playerTwoScoreCount,rountCount;
    boolean activePlayer;

    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningPosition={{0,1,2},{3,4,5},{6,7,8,},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //For showing Add
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        mAdView1 = findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView1.loadAd(adRequest);

        //showing add

        sound=new SoundPlayer( this);

        playerOneScore =(TextView)findViewById(R.id.playerOneScore);
        playerTwoScore=(TextView)findViewById(R.id.playerTwoScore);
        playerStatus=(TextView)findViewById(R.id.playerStatus);

        resetGame=(Button)findViewById(R.id.resetGame);

        for(int i=0;i<buttons.length;i++)
        {
            String buttonID="btn_"+i;
            int resourceID=getResources().getIdentifier(buttonID,"id",getPackageName());
            buttons[i]=(Button)findViewById(resourceID);
            buttons[i].setOnClickListener(this);
        }
        rountCount=0;
        playerOneScoreCount=0;
        playerTwoScoreCount=0;
        activePlayer=true;
    }

    @Override
    public void onClick(View v) {
        if(!((Button)v).getText().toString().equals(""))
        {
            return;
        }
        String buttonID=v.getResources().getResourceEntryName(v.getId());
        int gameStatePointer=Integer.parseInt(buttonID.substring(buttonID.length()-1,buttonID.length()));

        if(activePlayer)
        {
            ((Button)v).setText("X");
            ((Button)v).setTextColor(Color.parseColor("#FFC34A"));

            gameState[gameStatePointer]=0;
        }
        else
        {
            ((Button)v).setText("O");
            ((Button)v).setTextColor(Color.parseColor("#70FFEA"));

            gameState[gameStatePointer]=1;
        }
        rountCount++;
        if(checkWinner())
        { if(activePlayer)
        {
            playerOneScoreCount++;
            updatePlayerScore();
            sound.WinSound();
            final Toast toast = Toast.makeText(getApplicationContext(), "Player one Won!", Toast.LENGTH_SHORT);
            toast.show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, 500);
            playagain();
        }
        else
        {

            playerTwoScoreCount++;
            updatePlayerScore();
            sound.WinSound();
            final Toast toast = Toast.makeText(getApplicationContext(), "Player Two Won!", Toast.LENGTH_SHORT);
            toast.show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, 500);
            playagain();
        }

        }
        else if(rountCount==9)
        {   sound.DrawSound();
            playagain();
            final Toast toast = Toast.makeText(getApplicationContext(), "Draw!", Toast.LENGTH_SHORT);
            toast.show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, 500);
        }
        else
        {
            activePlayer=!activePlayer;
        }

        if(playerOneScoreCount>playerTwoScoreCount)
        {
            playerStatus.setText("Player one is winning!");
        }
        else if(playerTwoScoreCount>playerOneScoreCount)
        {
            playerStatus.setText("Player Two is winning!");
        }
        else
        {
            playerStatus.setText("");
        }

        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playagain();
                playerOneScoreCount=0;
                playerTwoScoreCount=0;
                playerStatus.setText("");
                updatePlayerScore();
            }
        });

    }
    public  boolean checkWinner(){
        boolean winnerResult=false;

        for(int [] winningPosition:winningPosition)
        {
            if(gameState[winningPosition[0]]==gameState[winningPosition[1]] && gameState[winningPosition[1]]
                    ==gameState[winningPosition[2]] && gameState[winningPosition[0]]!=2){
                winnerResult=true;
            }
        }
        return  winnerResult;
    }
    public void updatePlayerScore(){
        playerOneScore.setText(Integer.toString(playerOneScoreCount));
        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));
    }
    public  void playagain(){
        rountCount=0;
        activePlayer=true;
        for(int i=0;i<buttons.length;i++)
        {
            gameState[i]=2;
            buttons[i].setText("");
        }
    }
}
