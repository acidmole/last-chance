package lastchance.ui.robot;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;

public class Robot {
    
    private ImageView image;
    private boolean isLanded;
    private final int screenHeight;
    private final int screenWidth;
    private double walkingSpeed;
    private boolean destroyed;
    
    public Robot (Image i, double height, double width, int screenWidth, int screenHeight) {
        this.image = new ImageView(i);
        this.isLanded = false;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.walkingSpeed = 0.0;
        this.destroyed = false;
        
        Random rn = new Random();
        this.image.setX(rn.nextDouble()*screenWidth);
        this.image.setY(50);
        image.setFitHeight(height);
        image.setFitWidth(width);
    }
    
    public ImageView getImage() {
        return this.image;
    }
    
    
    
    public void move() {
        if(this.isLanded) {
            this.walk();
        } else {
            this.fall();
        }
    }

    public void fall() {
        this.image.setY(this.image.getY() + 0.1);
        if(this.image.getY() >= (this.screenHeight)-60) {
            this.land();
        }
    }
    
    public void walk() {
        this.image.setX(this.image.getX() + this.walkingSpeed);
    }
    
    public void land() {
        this.isLanded = true;
        this.image.setY(this.screenHeight-60);
        if (this.image.getX() < this.screenWidth/2) {
            this.walkingSpeed = 0.1;
        } else {
            this.walkingSpeed = -0.1;
        }
    }
    
    public void destroy() {
        this.destroyed = true;
    }

    public boolean isDestroyed() {
        return this.destroyed;
    }
    
    
    
}
