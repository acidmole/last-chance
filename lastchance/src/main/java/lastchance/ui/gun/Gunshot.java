/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lastchance.ui.gun;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.geometry.Point2D;

/**
 *
 * @author hede
 */
public class Gunshot extends Circle {
    
    private Point2D movement;
    private boolean destroyed;
    
    
    public Gunshot(double startX, double startY, double angleX, double angleY, double gunLength) {
        super();
        this.setRadius(6);
        this.setStroke(Color.BLACK);
        this.setCenterX(startX + 0.5 * angleX * gunLength);
        this.setCenterY(startY - 0.5 * angleY * gunLength);
        this.movement = new Point2D(angleX * 0.5, angleY * -0.5);
        this.destroyed = false;
        
    }
    
    public void move() {
        this.setCenterX(this.getCenterX() + this.movement.getX());
        this.setCenterY(this.getCenterY() + this.movement.getY());
    }
    
    public void destroy() {
        this.destroyed = true;
    }
    
    public boolean isDestroyed() {
        return this.destroyed;
    }
    
    
    
}
