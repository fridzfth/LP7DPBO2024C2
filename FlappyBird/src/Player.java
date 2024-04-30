import java.awt.*;

public class Player {
    private int postX;
    private int postY;
    private int width;
    private int height;
    private Image image;
    private int velocityY;
    public Player(int postX, int postY, int width, int height, Image image) {
        this.postX = postX;
        this.postY = postY;
        this.width = width;
        this.height = height;
        this.image = image;

        this.velocityY = 0;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
