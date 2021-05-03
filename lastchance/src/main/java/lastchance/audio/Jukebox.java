/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lastchance.audio;

import java.io.FileInputStream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author hede
 */
public class Jukebox extends Player {
    
    private boolean initialStart;
    
    public Jukebox(String fileURI) throws Exception{
        super(new FileInputStream(fileURI));
        this.initialStart = true;
    }
    
    
    @Override
    public boolean isComplete() {
            return super.isComplete();
    }
    
    @Override
    public void play(){
        try {
            super.play();
        } catch (Exception e) {
        }
    }
}
