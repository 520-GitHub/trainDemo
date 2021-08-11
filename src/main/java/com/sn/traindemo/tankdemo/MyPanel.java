package com.sn.traindemo.tankdemo;

import com.sn.traindemo.pojo.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener, Runnable {

    private MyTank t = null;
    private Vector<OtherTank> v = new Vector<>();
    private Vector<Boom> booms = new Vector<>();
    private int otherTankSize = 3;
    Image image = null;
    Image image2 = null;

    MyPanel() {
        t = new MyTank(100, 200);
        Random r = new Random();
        for (int i = 0; i < otherTankSize; i++) {
            int x = r.nextInt(500);
            int y = 20;
            OtherTank otherTank = new OtherTank(x, y);
            Bullet bullet = new Bullet(x + 20, y + 60, otherTank.getDirection());
            otherTank.setBullet(bullet);
            v.add(otherTank);
            //启动子弹
            new Thread(bullet).start();
            //启动坦克线程
            new Thread(otherTank).start();
        }
        System.out.println(Panel.class.getResource("/img1.png"));
        //爆炸图片 target->classes目录下的
        image = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img1.png"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/boom.png"));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //画一个圆形
        //g.drawOval(10, 10, 100, 100);
        //引用图片
        //Image image = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/demo.png"));
        //g.drawImage(image,10,10,335,165,this);
        //写字
        //g.setColor(Color.blue);
        //g.setFont(new Font("隶书",Font.BOLD,20));
        //g.drawString("北京欢迎你",100,100);
        //画出坦克 draw 画   fill 填充
/*        g.drawRect(15,10,5,35);
        g.drawRect(30,10,5,35);
        g.drawRect(20,15,10,20);
        g.drawLine(25,10,25,25);
        g.drawOval(20, 20, 10, 10);*/


        g.fillRect(0, 0, KeyEventTank.WIDTH, KeyEventTank.HEIGHT);
        drawTank(t.getX(), t.getY(), g, t.getDirection(), 0);
        if (v.size() > 0) {
            for (OtherTank o : v) {
                if (o.isLive()) {
                    drawTank(o.getX(), o.getY(), g, o.getDirection(), 1);
                    //子弹
                    if (o.getBullets().size() > 0) {
                        for (int i = o.getBullets().size() - 1; i >= 0; i--) {
                            if (o.getBullets().get(i) != null && o.getBullets().get(i).getFlag()) {
                                g.fill3DRect(o.getBullets().get(i).getX(), o.getBullets().get(i).getY(), 2, 2, false);
                            } else {
                                o.getBullets().get(i).setFlag(false);
                                o.getBullets().remove(i);
                            }
                        }
                    }
                }
            }
        }

        for (int i = t.getBullets().size() - 1; i >= 0; i--) {
            if (t.getB().getFlag()) {
                g.fill3DRect(t.getBullets().get(i).getX(), t.getBullets().get(i).getY(), 2, 2, false);
            } else {
                t.getBullets().remove(i);
            }
        }
        /**
         * 爆炸效果
         */
        for (int i = booms.size() - 1; i >= 0; i--) {
            Boom boom = booms.get(i);
            if (boom.getBoomSize() > 6) {
                g.drawImage(image, boom.getX(), boom.getY(), 60, 60, this);
            } else if (boom.getBoomSize() > 3) {
                g.drawImage(image, boom.getX() + 15, boom.getY() + 15, 30, 30, this);
            } else {
                g.drawImage(image, boom.getX() + 25, boom.getY() + 25, 10, 10, this);
            }
            boom.boomSizeDown();
            if (!boom.isLive()) {
                booms.remove(i);
            }
        }
        hitMyTank(g);
    }

    /**
     * 击杀敌方
     */
    public boolean hitTank(Bullet bullet, Tank tank) {

        switch (tank.getDirection()) {
            case 0:
            case 1:
                if (bullet.getX() > tank.getX() && bullet.getX() < tank.getX() + 40
                        && bullet.getY() > tank.getY() && bullet.getY() < tank.getY() + 60
                ) {
                    bullet.setFlag(false);
                    tank.setLive(false);
                    return true;
                }
                break;
            case 2:
            case 3:
                if (bullet.getX() > tank.getX() && bullet.getX() < tank.getX() + 60
                        && bullet.getY() > tank.getY() && bullet.getY() < tank.getY() + 40
                ) {
                    bullet.setFlag(false);
                    tank.setLive(false);
                    return true;
                }
                break;
            default:
        }
        return false;
    }

    /**
     * TODO 用一句话描述该方法的作用
     *
     * @Params: x/y 坦克左上角坐标
     * @param: g 画笔
     * @param: direction 坦克方向（上下左右）
     * @param: type 坦克类型
     * @Author: ccy
     * @Date: 2021/8/3
     * @Return: void
     **/
    private void drawTank(int x, int y, Graphics g, int direction, int type) {

        switch (type) {
            case 0://己方坦克
                g.setColor(Color.yellow);
                break;
            case 1://敌方坦克
                g.setColor(Color.cyan);
                break;
            default:
        }
        //绘制坦克
        switch (direction) {
            case KeyEventTank.TankUp:
                tankUp(x, y, g);
                break;
            case KeyEventTank.TankDown:
                tankDown(x, y, g);
                break;
            case KeyEventTank.TankRight:
                tankLeft(x, y, g);
                break;
            case KeyEventTank.TankLeft:
                tankRight(x, y, g);
                break;
            default:
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyChar());
        System.out.println("监听到字符");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("按下触发" + e.getKeyChar());

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            t.setDirection(KeyEventTank.TankDown);
            t.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            t.setDirection(KeyEventTank.TankUp);
            t.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            t.setDirection(KeyEventTank.TankLeft);
            t.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            t.setDirection(KeyEventTank.TankRight);
            t.moveRight();
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            t.runBullet();
        }
        this.repaint();//重绘
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("松开触发" + (char) e.getKeyCode());
    }

    private void tankUp(int x, int y, Graphics g) {
        g.fill3DRect(x, y, 10, 60, false);//坦克左边模块
        g.fill3DRect(x + 30, y, 10, 60, false);//坦克右边模块
        g.fill3DRect(x + 10, y + 10, 20, 40, false);//坦克中心矩形
        g.fillOval(x + 10, y + 20, 20, 20);//坦克中心圆形
        g.drawLine(x + 20, y, x + 20, y + 30); //炮筒
    }

    private void tankDown(int x, int y, Graphics g) {
        g.fill3DRect(x, y, 10, 60, false);//坦克左边模块
        g.fill3DRect(x + 30, y, 10, 60, false);//坦克右边模块
        g.fill3DRect(x + 10, y + 10, 20, 40, false);//坦克中心矩形
        g.fillOval(x + 10, y + 20, 20, 20);//坦克中心圆形
        g.drawLine(x + 20, y + 60, x + 20, y + 30); //炮筒
    }

    private void tankLeft(int x, int y, Graphics g) {
        g.fill3DRect(x - 10, y + 10, 60, 10, false);//坦克左边模块
        g.fill3DRect(x - 10, y + 40, 60, 10, false);//坦克右边模块
        g.fill3DRect(x, y + 20, 40, 20, false);//坦克中心矩形
        g.fillOval(x + 10, y + 20, 20, 20);//坦克中心圆形
        g.drawLine(x + 20, y + 30, x + 50, y + 30); //炮筒
    }

    private void tankRight(int x, int y, Graphics g) {
        g.fill3DRect(x - 10, y + 10, 60, 10, false);//坦克左边模块
        g.fill3DRect(x - 10, y + 40, 60, 10, false);//坦克右边模块
        g.fill3DRect(x, y + 20, 40, 20, false);//坦克中心矩形
        g.fillOval(x + 10, y + 20, 20, 20);//坦克中心圆形
        g.drawLine(x + 20, y + 30, x - 10, y + 30); //炮筒
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //重绘的时候判断子弹是否碰到坦克
            for (int i1 = t.getBullets().size() - 1; i1 >= 0; i1--) {
                Bullet bullet = t.getBullets().get(i1);
                if (bullet != null && bullet.getFlag()) {//子弹存在
                    for (int i = v.size() - 1; i >= 0; i--) {
                        if (v.get(i).isLive()) {
                            boolean b = hitTank(bullet, v.get(i));
                            if (b) {
                                t.getBullets().remove(i1);
                                booms.add(new Boom(v.get(i).getX(), v.get(i).getY()));
                            }
                        } else {
                            v.remove(i);
                        }
                    }
                } else {
                    t.getBullets().remove(i1);
                }
            }
            this.repaint();
        }
    }

    public void hitMyTank(Graphics g) {
        for (int i = 0; i < v.size(); i++) {
            if (v.get(i).isLive()) {
                Vector<Bullet> bullets = v.get(i).getBullets();
                for (int i1 = 0; i1 < bullets.size(); i1++) {
                    if (bullets.get(i1).getFlag()) {
                        boolean b = hitTank(bullets.get(i1), t);
                        if (b) {
                            Boom boom = new Boom(t.getX(), t.getY());
                            if (boom.getBoomSize() > 6) {
                                g.drawImage(image, boom.getX(), boom.getY(), 60, 60, this);
                            } else if (boom.getBoomSize() > 3) {
                                g.drawImage(image, boom.getX() + 15, boom.getY() + 15, 30, 30, this);
                            } else {
                                g.drawImage(image, boom.getX() + 25, boom.getY() + 25, 10, 10, this);
                            }
                            boom.boomSizeDown();
                            t =  new MyTank(100, 200);
                        }
                    }
                }
            }
        }
    }
}
