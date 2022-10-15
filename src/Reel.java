import javax.swing.*;

public class Reel implements Runnable {
    private final String[] tiles={"cherries", "club", "diamond","heart","joker","seven","spade"};
    private final JLabel tileIcon;

    public Reel(JLabel tileIcon){
        this.tileIcon=tileIcon;
    }

    @Override
    public void run() {
        int i=0;
        while(!Thread.interrupted()){
            tileIcon.setIcon(new ImageIcon("Tiles//" + tiles[i]+ ".png"));
            if(++i>=6){
                i=0;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
