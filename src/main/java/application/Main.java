package application;

import Unit.Enemy;
import Unit.Player;


public class Main {

    public static void main(String[] args) {


        Player pp = new Player("PP",80,100,3,0,99);
        GameManager.getInstance().setPlayer(pp);
        System.out.println(GameManager.getInstance().getPlayer());
        //printing out all cards
        for (int i = GameManager.getInstance().getPlayer().getDeck().size()-1; i >= 0 ; i--) {
            GameManager.getInstance().getPlayer().getDeck().get(i).execute();
            GameManager.getInstance().getPlayer().getDeck().remove(GameManager.getInstance().getPlayer().getDeck().get(i));
            System.out.println("now hand have " + GameManager.getInstance().getPlayer().getDeck().size() + " cards");
        }
        Enemy FD_battery = new Enemy("FD battery",23,23);
            System.out.println(FD_battery);

    }
}