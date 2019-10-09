package Controller;

import Model.*;
import Model.Prey.Prey;
import Model.Snake.Snake;
import View.MyWindow;

import java.awt.*;
import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;

import java.util.Random;

public class Main {
    public static MyWindow win;
    public static boolean running = false;
    public static final int FPS = 30;
    public static GameData gameData;
    public static int index_snake = 0;
    public static final int MAX_FRAME_SIZE = win.WIDTH - 80;
    public static final int MIN_FRAME_SIZE = 30;
    public static PlayerInputEventQueue playerInputEventQueue;
    public static Font font = new Font("Courier New", Font.BOLD, 33);
    public static Font font2 = new Font("Courier New", Font.ITALIC, 20);
    public static Snake s;
    public static Prey p;
    public static int p_x;
    public static int p_y;


    public static void main(String[] args) {

        win = new MyWindow();
        win.setVisible(true);
        gameData = new GameData();
        playerInputEventQueue = new PlayerInputEventQueue();
        startGame();
        initGame();
        gameLoop();
    }

    public static void initGame() {
        gameData.clear();
        p_x = generate_X_Y();
        p_y = generate_X_Y();
        p = new Prey(p_x, p_y);
        gameData.enemyObject.add(p);
        int x = 500;
        int y = 400;
        //s = new Snake(x, y);
        for (int i = 0; i < 15; i++) {
            gameData.friendObject.add(new Snake(x, y));
            x += Snake.SIZE;
        }


    }


    public static int generate_X_Y() {
        Random r = new Random();
        int seed = r.nextInt();
        Random r2 = new Random(seed);
        return r2.nextInt((MAX_FRAME_SIZE - MIN_FRAME_SIZE) + 1 + MIN_FRAME_SIZE);
    }

    private static void startGame() {
        gameData.fixedObject.add(new Text("Press Start To Begin", "", win.getX() / 2 + 140, 300, Color.BLACK, font));
        while (!running) {
            Main.win.canvas.render();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void gameLoop() {
        running = true;
        playerInputEventQueue.movingLeft = true;
        while (running) {
            String t = " : " + s.preyHitCount;
            gameData.fixedObject.add(new Text("Score: ", t, 560, 20, Color.BLACK, Main.font2));

            long startTime = System.currentTimeMillis();
            playerInputEventQueue.processInputEvents();
            processCollision();
            gameData.update();
            update();
            win.canvas.render();
            long endTime = System.currentTimeMillis();
            long timeSpend = endTime - startTime;
            long sleepTime = (long) (1000.0 / FPS - timeSpend);
            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void update() {
        if (running) {
            for (int i = gameData.friendObject.size() - 1; i > 0; i--) {
                var current = (Snake) gameData.friendObject.get(i);
                var previous = (Snake) gameData.friendObject.get(i - 1);
                current.snake_head.x = previous.snake_head.x;
                current.snake_head.y = previous.snake_head.y;
                current.location.x = current.snake_head.x;
                current.location.y = current.snake_head.y;
            }
        }
    }

    private static void processCollision() {

        // https://stackoverflow.com/questions/47235090/java-snake-collision
        // moving the whole body move along:- https://www.google.com/search?q=moving+a+snake+in+java&rlz=1C1CHZL_enUS713US713&oq=moving+a+snake+in+java&aqs=chrome..69i57.12723j0j7&sourceid=chrome&ie=UTF-8#kpvalbx=1
        // moving the whole body move along:- https://github.com/codepath/java_snake_demo/blob/master/src/SnakeGame.java


        if (gameData.friendObject.size() != 0) {
            s = (Snake) gameData.friendObject.get(0);
        }

        if(gameData.friendObject.size() > 5) {
                for (int i = 5; i < gameData.friendObject.size(); i++) {
                    var s2 = (Snake) gameData.friendObject.get(i);
                    if (s.collideWith(s2)){
                        int option = JOptionPane.showConfirmDialog(null, "The snake ate itself! Would you like to " +
                                "restart the game?", "Woops!!",JOptionPane.YES_NO_OPTION);

                        if(option == JOptionPane.YES_OPTION){
                            initGame();
                        }else if(option == JOptionPane.NO_OPTION){
                            //gameData.clear();
                            gameOver();
                        }
                    }
                }
        }

        if (s.collideWith(p)) {
            playSound();
            //s.unit_move++;
            s.preyHitCount++;
            addPreyWithListener();
            addSnakeWithListener();
        }

        if (s.location.x >= MAX_FRAME_SIZE + 70 || s.location.x <= 5) {
            //System.out.println("snake hit the wall at x ");
            s.state = s.STATE_DONE;
        } else if (s.location.y >= MAX_FRAME_SIZE + 20 || s.location.y <= 5) {
            //System.out.println("Snake hit the wall at y");
            s.state = s.STATE_DONE;
        }
    }

    public static void playSound() {
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("effect1.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addSnakeWithListener() {

        int size = gameData.friendObject.size() - 1;
        var sn = gameData.friendObject.get(size);
        int x = (int) sn.location.x;
        int y = (int) sn.location.y;
        Snake new_snake = new Snake(x + Snake.SIZE + 7, y);
        gameData.friendObject.add(new_snake);
    }

    private static void addPreyWithListener() {
        Prey.flag = true;

        //gameData.update();
        p_x = generate_X_Y();
        p_y = generate_X_Y();
        var prey = (Prey) gameData.enemyObject.get(0);
        //System.out.println("Prey's location before changing location: " + prey.location.x + "  " + prey.location.y);
        prey.location.x = p_x;
        prey.location.y = p_y;

        prey.preyLocation.x = prey.location.x;
        prey.preyLocation.y = prey.location.y;

        //System.out.println("Prey's location after changing location: " + prey.location.x + "  " + prey.location.y);
    }


    public static void gameOver() {
        gameData.clear();
        Text t = new Text("GAME OVER", "", 280, 300, Color.MAGENTA, Main.font);
        Text t2 = new Text("Total Score: ", "" + s.preyHitCount, 285, 320, Color.ORANGE, Main.font2);
        Main.gameData.fixedObject.add(t);
        Main.gameData.fixedObject.add(t2);

        //Main.running = false;
        try {
            Main.running = false;
            for (int i = 0; i < gameData.friendObject.size(); ++i) {
                var all_snake = (Snake) gameData.friendObject.get(i);
                all_snake.state = all_snake.STATE_DONE;
            }
            /*for (var all_snake : Main.gameData.friendObject) {
                all_snake.done = true;
            }*/
            for (var all_prey : Main.gameData.enemyObject) {
                all_prey.done = true;
            }

            if (!Main.running) {
                win.canvas.render();
            }

            gameData.clear();
            Thread.sleep(1000);
            System.exit(0);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

    }


}
