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
 * A class for drawing (sic!) the gun and it's functions
 * 
 */
public class Gun extends Rectangle {
    
    private List<Gunshot> gunshots;
    
    /**
     * Constructor draws the gun and sets its color
     */
    public Gun() {
        super(395, 520, 12, 160);
        this.setStroke(Color.BLACK);
        this.gunshots = new ArrayList<>();
    }

    /**
     * This method rotates the gun according to mouse pointer
     * 
     * @param x mouse x coordinate
     * @param y mouse y coordinate
     */
    public void rotateWithMouse(double x, double y) {
            this.setRotate(-1 * Math.toDegrees(Math.atan2(395-x, 600-y)));
            if(this.getRotate() > 75) {
                this.setRotate(75);
            } else if(this.getRotate() < -75) {
                this.setRotate(-75);
            }
    }
    
    /**
     * This method rotates gun after keyboard right 
     */
    public void rotateRight() {
        if (this.getRotate() <= 75){
            this.setRotate(this.getRotate() + 7.5);
        }
    }
    
    /**
     * This method rotates gun after keyboard left
     */
    public void rotateLeft() {
        if (this.getRotate() >= -75){
            this.setRotate(this.getRotate() - 7.5);
        }
    }
    
    /**
     * Gun fire method. Calculates the gunshot angle and adds it to the list.
     *  
     * @return gunshot Circle object
     */
    public Circle fire() {
        double shotY = Math.cos(Math.toRadians(this.getRotate()));
        double shotX = Math.sin(Math.toRadians(this.getRotate()));
        Gunshot newGunshot = new Gunshot(shotX, shotY);
        this.gunshots.add(newGunshot);
        return newGunshot;
    }
    
    /**
     *
     * @return list of gunshots
     */
    public List<Gunshot> getGunshots() {
        return this.gunshots;
    }
}
