package lastchance.ui.robot;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;

/**
 * Class responsible for drawing and managing movement for robots
 */
public class Robot {
    
    private ImageView image;
    private boolean isLanded;
    private final double screenHeight;
    private final double screenWidth;
    private double walkingSpeed;
    private boolean destroyed;
    
    /**
     *
     * @param i Image on screen
     * @param height Robot's image height drawn on screen
     * @param width Robot's image width drawn on screen
     * @param screenWidth Screen width where a robot can appear
     * @param screenHeight Screen height where a robot will land
     */
    
    public Robot (Image i, double height, double width, double screenWidth, double screenHeight) {
        this.image = new ImageView(i);
        this.isLanded = false;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.walkingSpeed = 0.0;
        this.destroyed = false;
        
        Random rn = new Random();
        this.image.setX(rn.nextDouble()*(screenWidth - width));
        this.image.setY(50);
        image.setFitHeight(height);
        image.setFitWidth(width);
    }
    
    /**
     * @return image for collision with base or gunshot
     */
    public ImageView getImage() {
        return this.image;
    }
    
    /**
     * A method for changing robot's movement from landing to walking
     */
    public void move() {
        if(this.isLanded) {
            this.walk();
        } else {
            this.fall();
        }
    }

    /**
     * This method is responsible for falling speed and hitting the ground
     */
    public void fall() {
        this.image.setY(this.image.getY() + 0.1);
        if(this.image.getY() >= (this.screenHeight)-60) {
            this.land();
        }
    }
    
    /**
     * This method is responsible for walking towards base
     */
    public void walk() {
        this.image.setRotate(this.image.getRotate() + 1.0);
        this.image.setX(this.image.getX() + this.walkingSpeed);
    }
    
    /**
     * This method is responsible for telling a robot which way and how to walk
     */
    public void land() {
        this.isLanded = true;
        this.image.setY(this.screenHeight-60);
        if (this.image.getX() < this.screenWidth/2) {
            this.walkingSpeed = 0.1;
        } else {
            this.walkingSpeed = -0.1;
        }
    }
    
    /**
     * Robot has been hit by a gunshot and is soon to be removed
     */
    public void destroy() {
        this.destroyed = true;
    }

    /**
     * @return if a robot is destroyed
     */
    public boolean isDestroyed() {
        return this.destroyed;
    }
}
