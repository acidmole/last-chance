/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lastchance.ui.gun;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.shape.Circle;

/**
 *
 * @author hede
 */
public class Gun extends Rectangle {
    
    private List<Gunshot> gunshots;
    
    public Gun() {
        super(395, 520, 12, 160);
        this.setStroke(Color.BLACK);
        this.gunshots = new ArrayList<>();
    }
    
   
    public void rotateRight() {
        if (this.getRotate() <= 75){
            this.setRotate(this.getRotate() + 7.5);
        }
    }
    
    public void rotateLeft() {
        if (this.getRotate() >= -75){
            this.setRotate(this.getRotate() - 7.5);
        }
    }
    
    public Circle fire() {
        double shotY = Math.cos(Math.toRadians(this.getRotate()));
        double shotX = Math.sin(Math.toRadians(this.getRotate()));
        Gunshot newGunshot = new Gunshot(shotX, shotY);
        this.gunshots.add(newGunshot);
        return newGunshot;
    }
    
    public List<Gunshot> getGunshots() {
        return this.gunshots;
    }
}
