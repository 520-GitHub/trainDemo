package com.sn.traindemo.pojo;

public class Boom {
    private int x;
    private int y;
    private boolean isLive;
    private  int boomSize = 9;

    public Boom(int x, int y) {
        this.x = x;
        this.y = y;
        isLive = true;
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

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int getBoomSize() {
        return boomSize;
    }

    public void setBoomSize(int boomSize) {
        this.boomSize = boomSize;
    }

    public void boomSizeDown(){
        if (boomSize>0) {
            boomSize--;
        }else {
            isLive = false;
        }
    }
}
