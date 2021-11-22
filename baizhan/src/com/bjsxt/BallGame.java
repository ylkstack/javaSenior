package com.bjsxt;
import java.awt.*;
import javax.swing.*;
/**
 * 桌球小游戏测试
 * @Author ylkstack
 * @Date 2021/11/4 0:34
 */
public class BallGame extends JFrame{

    //加载图片
    Image ball = Toolkit.getDefaultToolkit().getImage("images/ball.png");
    Image desk = Toolkit.getDefaultToolkit().getImage("images/desk.png");

    double x = 200;
    double y = 200;

    double degree = 3.14 / 3;//弧度。 3.14 = 180°
    /**
     * 绘制窗口，加载背景图片，等
     * @Date 2021/11/4 0:52
     * @param g
     * @return void
     */
    public void paint(Graphics g) {
        System.out.println("窗口被画了一次！");
        g.drawImage(desk,0,0,null);
        g.drawImage(ball, (int) x,(int) y, null);

        x = x + 10 * Math.cos(degree);
        y = x + 10 * Math.sin(degree);

        //碰到下上边界
        if (y > 501 - 40 - 30 || y < 40 + 40) {
            degree = -degree;
        }
        //碰到左右边界
        if (x > 856 - 40 - 30 || x < 40) {
            degree = 3.14 - degree;
        }
    }
    /**
     * 创建窗口，并设置窗口大小及出现位置
     * 设置功能也在此方法内
     * @Date 2021/11/4 0:49
     */
    void launchFrame() {
        setSize(856, 501);
        setLocation(100, 100);
        setVisible(true);//视图的真假
        //实现动画，每秒绘制窗口25次(使用循环来处理)
        while (true) {
            repaint();
            try {
                Thread.sleep(40);//1s = 1000ms;大约1秒绘制1000/40=25次
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public static void main(String[] args) {
        System.out.println("这是我的第一个java项目，桌球小游戏");
        BallGame game = new BallGame();
        game.launchFrame();
    }}
