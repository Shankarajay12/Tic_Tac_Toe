package com.example.tictactoe;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundPlayer {

    private static SoundPool soundPool;
    public static int winsound;
    public static int drawsound;


    public SoundPlayer(Context context)
    {
        soundPool=new SoundPool(2, AudioManager.STREAM_MUSIC,0);

        winsound=soundPool.load(context,R.raw.game_won,1);
        drawsound=soundPool.load(context,R.raw.game_draw,1);
    }

    public void WinSound()
    {
        soundPool.play(winsound,1.0f,1.0f,1,0,1.0f);
    }
    public void DrawSound()
    {
        soundPool.play(drawsound,1.0f,1.0f,1,0,1.0f);
    }
}
