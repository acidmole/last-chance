/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lastchance.ui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author hede
 */
public class Gun extends Rectangle {
    
    public Gun() {
        super(395, 520, 10, 160);
        this.setStroke(Color.BLACK);
    }
    
   
    public void rotateRight() {
        if (this.getRotate() <= 75){
            this.setRotate(this.getRotate() + 5);
        }
    }
    
    public void rotateLeft() {
        if (this.getRotate() >= -75){
            this.setRotate(this.getRotate() - 5);
        }
    }
}
