package com.sn.traindemo.pojo;

import java.util.Vector;

/**
 * 敌人的坦克
 */
public class OtherTank extends Tank implements Runnable {
    private Vector<Bullet> bullets = new Vector<>();
    private Boom boom;
    private int direction = 1;

    public OtherTank(int x, int y) {
        super(x, y);
    }

    public Boom getBoom() {
        return boom;
    }

    public void setBoom(Boom boom) {
        this.boom = boom;
    }

    public Vector<Bullet> getBullets() {
        return bullets;
    }

    public void setBullet(Bullet bullet) {
        this.bullets.add(bullet);
    }

    @Override
    public void run() {
        while (isLive()) {
            if (bullets.size() == 0 || !bullets.get(0).getFlag()) {
                Bullet bullet = getBullet();
                bullets.add(bullet);
                new Thread(bullet).start();
            }
            switch (getDirection()) {
                case KeyEventTank.TankUp:
                    for (int i = 0; i < 30; i++) {
                        moveUp();
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case KeyEventTank.TankDown:
                    for (int i = 0; i < 30; i++) {
                        moveDown();
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case KeyEventTank.TankLeft:
                    for (int i = 0; i < 30; i++) {
                        moveLeft();
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case KeyEventTank.TankRight:
                    for (int i = 0; i < 30; i++) {
                        moveRight();
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                default:
            }
            setDirection((int) (Math.random() * 4));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
