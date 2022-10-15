import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class SlotPanel extends JPanel{

    private Thread t;

    public SlotPanel(JLabel tileIcon1,int x,int y,int width,int height){
        setBounds(x,y,width,height);
        setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        setBackground(new Color(219,192,191));
        add(tileIcon1);
    }

    public void Focus(){
        setBorder(new MatteBorder(6,6,6,6,Color.black));
    }

    public void StopFocus(){

        setBorder(new EtchedBorder(EtchedBorder.LOWERED));
    }

    public void WinEffect(){

        Color[] colors={Color.YELLOW,Color.RED,Color.green,Color.BLUE,Color.PINK};
            t=new Thread(new Runnable() {
            @Override
            public void run() {
                int i=1;
                int j=0;
                while(!Thread.interrupted()) {
                    try {
                        setBorder(new MatteBorder(i,i,i,i,colors[j]));
                        if(++j>4)
                            j=0;

                        Thread.sleep(250);

                        i=(i==1)?5:1;
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        });
        t.start();

        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                       t.interrupt();
                       StopFocus();
                    }
                },4000);
    }
}
