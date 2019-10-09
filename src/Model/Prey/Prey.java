package Model.Prey;

import Model.GameFigure;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.ImageObserver;

public class Prey extends GameFigure implements ImageObserver {
    public static final int STATE_DONE = 0;
    public static final int STATE_RANDOM = 1;
    public static int state;
    public  final int SIZE = 18;

    ImageIcon i_con = new ImageIcon("src/prey.png");
    Image prey = i_con.getImage();
    public static Point2D.Float preyLocation;

    public static boolean flag = false;

    public PreyAnimateStratergy preyAnimateStratergy;

    public static Graphics2D g;


    public Prey(int x, int y){
        super(x,y);
        preyLocation = new Point2D.Float(x, y);
        //System.out.printf("x1 = %d x2 = %d", x1, x2);
        state = STATE_RANDOM;
    }

    @Override
    public void render(Graphics2D g2) {
        //g2.setColor(Color.RED);
        //g2.fillOval((int)preyLocation.x, (int)preyLocation.y, 20, 20);
        addImage(g2);
    }

    private void addImage(Graphics2D g2) {
        // https://stackoverflow.com/questions/6638803/how-to-use-imageobserver-in-graphics-method-drawimage
        g2.drawImage(prey, (int)preyLocation.x, (int)preyLocation.y, SIZE, SIZE, null);

    }

    @Override
    public void update() {
        preyAnimateStratergy = new PreyAnimateLocation(this);
    }

    @Override
    public int getCollitionRadius() {
        return SIZE/2 + 5;
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }


}
