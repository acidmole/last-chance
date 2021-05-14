/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lastchance.audio;

import java.io.InputStream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

/**
 *
 * @author hede
 */
public class ImprovedAdvancedPlayer extends AdvancedPlayer {
    private int pausedOnFrame;
    private PlaybackListener listener;
    
    /**
     *
     * @param stream InputStream object
     * 
     * @see lastchance.audio.Jukebox
     * 
     * @throws JavaLayerException
     */
    public ImprovedAdvancedPlayer(InputStream stream) throws JavaLayerException {
        super(stream);
        pausedOnFrame = 0;
        
        this.setPlayBackListener(new PlaybackListener() {
            @Override
            public void playbackStarted(PlaybackEvent e) {
                System.out.println("Song started");
            }
            
            @Override
            public void playbackFinished(PlaybackEvent e) {
                pausedOnFrame = e.getFrame();
            }
        });
    }
    
    /**
     * Pauses player and halts the music.
     * 
     * @return frame to carry on from after pause
     */
    public int pause() {
        this.stop();
        System.out.println(pausedOnFrame);
        this.close();
        return pausedOnFrame;
    }
    
}
