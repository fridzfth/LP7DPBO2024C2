import java.awt.*;

public class Pipe {
    private int postX;
    private int postY;
    private int width;
    private int height;
    private int velocityX;
    private Image image;
    private  boolean passed = false;

    public Pipe(int postX, int postY, int width, int height, Image image) {
        this.postX = postX;
        this.postY = postY;
        this.width = width;
        this.height = height;
        this.velocityX = -3;
        this.image = image;
        this.passed =false;
    }

    public int getPostX() {
        return postX;
    }

    public void setPostX(int postX) {
        this.postX = postX;
    }

    public int getPostY() {
        return postY;
    }

    public void setPostY(int postY) {
        this.postY = postY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
