import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;


public class Gui extends JFrame {

    private JLabel Balance,currentBet;

    private SlotPanel tilePanel1,tilePanel2,tilePanel3;
    private final JLabel tileIcon1,tileIcon2,tileIcon3;
    private ImageIcon imgThisImg;

    private final User player;
    private final Spin spinClass;
    private final MoneyController controller;

    private Boolean isPressed=false;
    private Thread t1,t2,t3;
    private final Music music;


    public Gui(){
        player=new User();
        spinClass=new Spin();
        controller=new MoneyController(player);
        tileIcon1=new JLabel();
        tileIcon2=new JLabel();
        tileIcon3=new JLabel();
        music=new Music();
        music.playLoop("SoundFX//MusicLoop.wav");


        CreateMainFrame();
        setVisible(true);
    }


    private void CreateMainFrame() {
        setTitle("Slot Machine");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(646,440);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(180,1,1));
        setLayout(null);

        tilePanel1=new SlotPanel(tileIcon1,8,55,200,200);
        add(tilePanel1);

        tilePanel2=new SlotPanel(tileIcon2,216,55,200,200);
        add(tilePanel2);

        tilePanel3=new SlotPanel(tileIcon3,424,55,200,200);
        add(tilePanel3);


        JButton deposit=new JButton("Deposit");
        deposit.setFocusable(false);
        deposit.setBounds(524,10,100,35);
        deposit.addActionListener(e -> {
            try {
                String deposition = JOptionPane.showInputDialog("Insert Money : ");
                if (deposition==null)
                    deposition="0";

                if (Integer.parseInt(deposition)>=0){
                    player.setBalance(player.getBalance() + Integer.parseInt(deposition));
                    Balance.setText("Current Balance : " + player.getBalance());
                }else
                    throw new Exception();
            }catch (Exception exp){
                JOptionPane.showInternalMessageDialog(null,"Wrong Input. \nPlease try again...");
            }

        });

        add(deposit);

        currentBet=new JLabel();
        currentBet.setText("Bet per spin : " + player.getCurrentBet());
        currentBet.setBackground(Color.black);
        currentBet.setBounds(8,0,currentBet.toString().length(),30);
        currentBet.setFont(new Font("Serif", Font.BOLD, 25));
        add(currentBet);

        JButton ChangeBet=new JButton("Change");
        ChangeBet.setBounds(8,30,80,20);
        ChangeBet.setFocusable(false);
        ChangeBet.addActionListener(e -> {
            try {
                String currentBet = JOptionPane.showInputDialog("Insert Money : ");
                if (currentBet==null)
                    currentBet = player.getCurrentBet() +"";


                if (Integer.parseInt(currentBet)>0) {
                    player.setCurrentBet(Integer.parseInt(currentBet));
                    this.currentBet.setText("Bet per spin : " + player.getCurrentBet());
                }else
                    throw new NumberFormatException();
            }catch (NumberFormatException exp){
                JOptionPane.showInternalMessageDialog(null,"Wrong Input. \nPlease try again...");
            }

        });
        add(ChangeBet);

        CreatBottomPanel();
    }

    public void CreatBottomPanel(){
        JPanel BottomPanel =new JPanel();

        BottomPanel.setBounds(8,265,616,125);
        BottomPanel.setBackground(new Color(127,83,81));
        BottomPanel.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        BottomPanel.setLayout(null);

        CircleButton spin=new CircleButton("SPIN");
        spin.setFocusable(true);
        spin.setBounds(480,20,90,90);
        spin.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }
            @Override
            public void keyPressed(KeyEvent e) { }

            @Override
            public void keyReleased(KeyEvent e) {

                if(e.getKeyCode()==32 && !isPressed){
                    SpinPress();
                }
            }
        });
        spin.addActionListener(e -> {
            if(!isPressed){
                SpinPress();
            }
        });


        BottomPanel.add(spin);

        Balance=new JLabel();
        Balance.setFont(new Font("Serif", Font.BOLD, 25));
        Balance.setBackground(Color.black);
        Balance.setBounds(5,94,Balance.toString().length(),30);
        Balance.setText("Current Balance : " + player.getBalance());
        BottomPanel.add(Balance);

        JLabel DecorImage1=new JLabel();
        imgThisImg = new ImageIcon("cherries.png");
        DecorImage1.setIcon(imgThisImg);
        DecorImage1.setBounds(5,10,imgThisImg.getIconWidth(),imgThisImg.getIconWidth());
        BottomPanel.add(DecorImage1);

        JLabel DecorImage2=new JLabel();
        imgThisImg = new ImageIcon("diamond.png");
        DecorImage2.setIcon(imgThisImg);
        DecorImage2.setBounds(450,-25,imgThisImg.getIconWidth(),imgThisImg.getIconWidth());
        BottomPanel.add(DecorImage2);

        JLabel CasinoLogo =new JLabel();
        imgThisImg = new ImageIcon("CasinoLogo.png");
        CasinoLogo.setIcon(imgThisImg);
        CasinoLogo.setBounds(180,-90,imgThisImg.getIconWidth(),imgThisImg.getIconWidth());
        BottomPanel.add(CasinoLogo);

        this.add(BottomPanel);

    }

    public void WinAlert(){
        music.playMusic("SoundFX//WinSound.wav");
        tilePanel1.WinEffect();
        tilePanel2.WinEffect();
        tilePanel3.WinEffect();

    }
    public void SpinPress(){

        if(player.getCurrentBet()<=player.getBalance() && player.getCurrentBet()>0 ) {
            isPressed=true;
            music.playMusic("SoundFX//HitSpinSound.wav");

            music.playMusic("SoundFX//ReelSound.wav");
            player.setBalance(player.getBalance()-player.getCurrentBet());
            Balance.setText("Current Balance : " + player.getBalance());
            spinClass.LuckDesider();

            t1=new Thread(new Reel(tileIcon1));
            t2=new Thread(new Reel(tileIcon2));
            t3=new Thread(new Reel(tileIcon3));


            t1.start();
            tilePanel1.Focus();

            new Timer().schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            t1.interrupt();
                            tileIcon1.setIcon(new ImageIcon("Tiles//" + spinClass.getSymbol1().toString() + ".png"));
                            tilePanel1.StopFocus();
                            t2.start();
                            tilePanel2.Focus();
                        }
                    },1000);

            new Timer().schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            t2.interrupt();
                            tileIcon2.setIcon(new ImageIcon("Tiles//" + spinClass.getSymbol2().toString() + ".png"));
                            tilePanel2.StopFocus();
                            t3.start();

                            tilePanel3.Focus();
                        }
                    },2000);

            new Timer().schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            t3.interrupt();
                            tileIcon3.setIcon(new ImageIcon("Tiles//" + spinClass.getSymbol3().toString() + ".png"));
                            tilePanel3.StopFocus();
                            music.StopMusic();


                            if (controller.isAWin(spinClass)){
                                WinAlert();
                            }
                            Balance.setText("Current Balance : " + player.getBalance());
                            isPressed=false;


                        }
                    }, 3000);


        }else
            music.playMusic("SoundFX//WrongSound.wav");
    }

}

