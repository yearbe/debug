package app.f_adapter.player.impl;

import app.f_adapter.player.AdvanceMediaPlayer;

public class VlcPlayer implements AdvanceMediaPlayer {

    @Override
    public void playVlc(String filename) {
        System.out.println("Playing vlc file. Name: " + filename);
    }

    @Override
    public void playMp4(String filename) {

    }

}