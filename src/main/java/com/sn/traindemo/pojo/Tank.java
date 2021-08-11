package com.sn.traindemo.pojo;

public class Tank {
    private int x;
    private int y;
    private int speed = 2;
    private Bullet b = null;
    private int direction = 1;
    private boolean isLive = true;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Bullet getB() {
        return b;
    }

    public void setB(Bullet b) {
        this.b = b;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public void moveUp() {
        if (y < 0) {
            return;
        }
        y -= speed;

    }

    public void moveDown() {
        if (y > (KeyEventTank.HEIGHT - 60)) {//750-60
            return;
        }
        y += speed;

    }

    public void moveLeft() {
        if (x < 0) {
            return;
        }
        x -= speed;

    }

    public void moveRight() {
        if (x > (KeyEventTank.WIDTH - 60)) {//1000-60
            return;
        }
        x += speed;
    }

    public Bullet getBullet() {
        switch (getDirection()) {
            case KeyEventTank.TankUp:
                return (new Bullet(getX() + 20, getY(), getDirection()));
            case KeyEventTank.TankDown:
                return (new Bullet(getX() + 20, getY() + 60, getDirection()));
            case KeyEventTank.TankRight:
                return (new Bullet(getX() + 50, getY() + 30, getDirection()));
            case KeyEventTank.TankLeft:
                return (new Bullet(getX() - 10, getY() + 30, getDirection()));
            default:
        }
        return null;
    }
}
