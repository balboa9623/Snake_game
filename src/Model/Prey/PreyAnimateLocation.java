package Model.Prey;

import Controller.Main;

public class PreyAnimateLocation implements PreyAnimateStratergy {
    Prey context;


    public PreyAnimateLocation(Prey context){
        this.context = context;

    }
    @Override
    public void animate() {
        var p = (Prey)Main.gameData.enemyObject.get(0);
        context.location.x = p.location.x;
        context.location.y = p.location.y;
    }

}
