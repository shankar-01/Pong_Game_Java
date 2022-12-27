import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Main {
    static JLabel label = null;
    static JLabel label1 = null;
    static JLabel ball = null;
    static JLabel score = null;
    static JLabel score1 = null;
    public static void main(String[] args) {
	    JFrame frame = new JFrame("Pong Game");
        frame.setSize(1270, 750);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        ImageIcon ii = new ImageIcon("stick.jpg");
        Image image = ii.getImage();
        Image newing = image.getScaledInstance(20, 100, Image.SCALE_SMOOTH);
        ii = new ImageIcon(newing);

        label = new JLabel(ii);
        label1 = new JLabel(ii);
        ball = new JLabel(ii);
        score = new JLabel("0");
        score1 = new JLabel("0");
        label.setBounds(1200, 320, 20, 100);
        label1.setBounds(20, 320, 20, 100);
        ball.setBounds(750, 320, 20, 20);
        score1.setBounds(700, 10, 30, 20);
        score.setBounds(750, 10, 30, 20);
        new BallMove().start();
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                    Th.c = e.getKeyCode();
                    new Th().start();

            }
        });
        frame.add(label);
        frame.add(label1);
        frame.add(ball);
        frame.add(score1);
        frame.add(score);

        frame.setVisible(true);




    }
}
class Th extends Thread {
    static int c = 0;
    public void run(){
        if(c==KeyEvent.VK_DOWN && Main.label.getY() <= 580)
            Main.label.setBounds(Main.label.getX(), Main.label.getY() + 20, 20, 100);
        else if(c==KeyEvent.VK_UP && Main.label.getY() >=10)
            Main.label.setBounds(Main.label.getX(), Main.label.getY()-20, 20, 100);
        else if(c==KeyEvent.VK_S && Main.label1.getY() <= 580)
            Main.label1.setBounds(Main.label1.getX(), Main.label1.getY() + 20, 20, 100);
        else if(c==KeyEvent.VK_W && Main.label1.getY() >=10)
            Main.label1.setBounds(Main.label1.getX(), Main.label1.getY()-20, 20, 100);
    }
}
class BallMove extends Thread {

    int Vx=new Random().nextInt(10) + 5;
    int Vy=new Random().nextInt(5) + 3;
    public void run(){
        while(true) {
            if(touchs()){
                Vx = -1*Vx;
            }
            else if (Main.ball.getX() <= 0 || Main.ball.getX() >= 1350) {
                if(Vx<0){
                    Main.score.setText(Integer.parseInt(Main.score.getText())+1 + "");
                }
                else if(Vx>0){
                    Main.score1.setText(Integer.parseInt(Main.score1.getText())+1 + "");
                }
                Vx = -1*Vx;
            }
            if (Main.ball.getY() <= 0 || Main.ball.getY() >= 680) {
                Vy = -1 * Vy;
            }

            Main.ball.setBounds(Main.ball.getX() + Vx, Main.ball.getY() + Vy, Main.ball.getWidth(), Main.ball.getHeight());
            try {
                sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    boolean touchs(){
        return (Main.ball.getX()+20>=Main.label.getX() && Main.ball.getX()+20<=Main.label.getX()+20 && Main.ball.getY() >= Main.label.getY() && Main.ball.getY()<=Main.label.getY()+100) || (Main.ball.getX()>=Main.label1.getX() && Main.ball.getX()<=Main.label1.getX()+20 && Main.ball.getY() >= Main.label1.getY() && Main.ball.getY()<=Main.label1.getY()+100);
    }
}
