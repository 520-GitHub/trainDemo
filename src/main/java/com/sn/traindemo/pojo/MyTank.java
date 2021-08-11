package com.sn.traindemo.pojo;

import java.util.Vector;

/**
 * 我的坦克
 */
public class MyTank extends Tank {
    private int direction = 0;
    private Vector<Bullet> bullets = new Vector<>();

    public MyTank(int x, int y) {
        super(x, y);
    }

    @Override
    public int getDirection() {
        return direction;
    }

    @Override
    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Vector<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(Vector<Bullet> bullets) {
        this.bullets = bullets;
    }

    public void runBullet() {
        System.out.println(getB() == null);
        if (bullets.size() >= 5) {
            return;
        }
      /*  switch (getDirection()) {
            case KeyEventTank.TankUp:
                setB(new Bullet(getX() + 20, getY(), getDirection()));
                break;
            case KeyEventTank.TankDown:
                setB(new Bullet(getX() + 20, getY() + 60, getDirection()));
                break;
            case KeyEventTank.TankRight:
                setB(new Bullet(getX() + 50, getY() + 30, getDirection()));
                break;
            case KeyEventTank.TankLeft:
                setB(new Bullet(getX() - 10, getY() + 30, getDirection()));
                break;
            default:
        }*/
        setB(getBullet());
        bullets.add(getB());
        new Thread(getB()).start();

    }

}
