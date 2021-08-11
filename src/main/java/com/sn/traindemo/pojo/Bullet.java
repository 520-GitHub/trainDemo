package com.sn.traindemo.pojo;

public class Bullet implements Runnable {
    private int x;
    private int y;
    private int direction;
    private int speed;
    private Boolean flag = true;

    public Bullet(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        speed = 2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        while (flag) {
            switch (direction) {
                case KeyEventTank.TankUp:
                    y -= speed;
                    break;
                case KeyEventTank.TankDown:
                    y += speed;
                    break;
                case KeyEventTank.TankLeft:
                    x -= speed;
                    break;
                case KeyEventTank.TankRight:
                    x += speed;
                    break;
                default:

            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (x <= 0 || x >= KeyEventTank.WIDTH || y <= 0 || y >= KeyEventTank.HEIGHT || !flag) {
                flag = false;
            }
        }
    }
}
