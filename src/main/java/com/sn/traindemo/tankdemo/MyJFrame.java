package com.sn.traindemo.tankdemo;

import com.sn.traindemo.pojo.KeyEventTank;

import javax.swing.*;
import java.io.*;

public class MyJFrame extends JFrame {
    private MyPanel mp = null;

    public MyJFrame() {
        mp = new MyPanel();
        new Thread(mp).start();
        this.add(mp);
        this.setSize(KeyEventTank.WIDTH, KeyEventTank.HEIGHT);//设置大小
        this.addKeyListener(mp);//监听这个窗口是否有按键 需要实现 KeyListener
        this.setVisible(true);//可以显示
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//点击X退出程序

    }

    public static void main(String[] args) {
        //new MyJFrame();
        String file = "src\\main\\resources\\application.properties";
        FileReader fr = null;
        BufferedReader br = null;
        try {
           fr  = new FileReader(file);
           br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) !=null){
                String[] split = line.split("=");
                if ("id".equals(split[0])){

                    System.out.println(split[0]+"的值是 " + split[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
