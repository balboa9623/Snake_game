package View;

import Controller.KeyEventListener;
import Controller.Main;

import javax.swing.*;
import java.awt.*;

public class MyWindow extends JFrame {
    public static MyCanvas canvas;
    public static JButton start_quit_button;
    public static JButton restartButton;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    public MyWindow(){

        setSize(WIDTH, HEIGHT);
        setLocation(150,20);
        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        canvas = new MyCanvas();
        KeyEventListener keyEventListener = new KeyEventListener();
        canvas.addKeyListener(keyEventListener );
        canvas.setFocusable(true);
        var cp = getContentPane();
        cp.add(BorderLayout.CENTER, canvas);
        start_quit_button = new JButton("Start");
        start_quit_button.setFocusable(false);
        restartButton = new JButton("Restart");
        restartButton.setFocusable(false);
        restartButton.setEnabled(false);
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(start_quit_button);
        bottomPanel.add(restartButton);
        cp.add(BorderLayout.SOUTH, bottomPanel);


        Main.running = false;

        start_quit_button.addActionListener(e ->{
           if(!Main.running){
               restartButton.setEnabled(true);
               Main.gameData.clear();
               start_quit_button.setText("Quit");
               Main.running = true;

            }
           else{
               Main.gameOver();
           }
        });

        restartButton.addActionListener(ae -> {
            Main.initGame();
        });
    }
}
