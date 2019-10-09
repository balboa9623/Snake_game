package Model;

import Controller.Main;

import java.awt.*;

public class Text extends GameFigure{
    private String text1;
    private static  String text2;
    private Color color;
    private Font font;
    private int x, y;
    public static boolean flag = false;

    public Text(String t1, String t2, int x, int y, Color c, Font f){
        text1 = t1;
        text2 = t2;
        this.x = x;
        this.y = y;
        this.color = c;
        this.font = f;
    }



    @Override
    public void render(Graphics2D g2) {
        String t = text1 + text2;
        g2.setFont(font);
        g2.setColor(color);
        g2.drawString(t, x, y);
        /*if(flag == true){
            String tx = " : " + Prey.preyHitCount;
            Main.gameData.fixedObject.add(new Text("Score",tx , 590, 20, Color.BLUE, Main.font2));
        }*/
    }

    @Override
    public void update() {

    }

    @Override
    public int getCollitionRadius() {
        return 0;
    }
}
