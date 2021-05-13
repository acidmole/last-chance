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
    private final double cornerX;
    private final double cornerY;
    private final double diameter;
    
    /**
     * Constructor draws the gun and sets its color
     * @param cornerX gun's starting point on x axis
     * @param cornerY gun's starting point on y axis
     * @param diameter gun's diameter
     * @param length gun's length
     */
    public Gun(double cornerX, double cornerY, double diameter, double length) {
        super(cornerX, cornerY, diameter, length);
        this.cornerX = cornerX;
        this.cornerY = cornerY;
        this.diameter = diameter;
        
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
        Gunshot newGunshot = new Gunshot((this.cornerX + this.diameter/2), this.cornerY + 80, shotX, shotY, 160.0);
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
