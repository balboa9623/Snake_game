package View;

import Controller.Main;

import javax.swing.*;
import java.awt.*;


public class MyCanvas extends JPanel {
    public int width;
    public int height;
    public Graphics2D g2OffScreen;
    ImageIcon i_con = new ImageIcon("src/background.png");
    Image background = i_con.getImage();

    public void render() {

        width = getSize().width;
        height = getSize().height;

        // off-screen double buffer image
        Image doubleBufferImage = createImage(width, height);
        if (doubleBufferImage == null) {
            System.out.println("Fatal Error: doubleBufferImage is null");
            System.exit(1);
        }

        //off-screen rendering
        /*Graphics2D*/
        g2OffScreen = (Graphics2D) doubleBufferImage.getGraphics();
        if (g2OffScreen == null) {
            System.out.println("Fatal Error: g2OffScreen is null");
            System.exit(1);
        }


        //g2OffScreen.setBackground(Color.BLACK);
        g2OffScreen.clearRect(0, 0, width, height);
        g2OffScreen.drawImage(background,0, 0, width, height,null);

        // render all game data here...
        for (var fig : Main.gameData.enemyObject) {
            fig.render(g2OffScreen);
        }

        for (var fig : Main.gameData.friendObject) {
            fig.render(g2OffScreen);
        }

        for (var fig : Main.gameData.fixedObject) {
            fig.render(g2OffScreen);
        }

        // use active rendering to put the buffer image on screen
        Graphics gOnScreen = this.getGraphics();
        if (gOnScreen != null) {
            //copy off-screen image to onScreen
            gOnScreen.drawImage(doubleBufferImage, 0, 0, null);
        }

        Toolkit.getDefaultToolkit().sync(); // sync the display
        if (gOnScreen != null) {
            gOnScreen.dispose();
        }

    }
}
