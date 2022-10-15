import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Music {

    private Clip clip;


    public Music(){
        //
    }

    public void playMusic(String path){

        try{
            File musicPath =new File(path);

            if (musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip=AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }else
                System.out.println("music path dont exist");

        }catch (Throwable e){
            e.printStackTrace();
        }
    }

    public void StopMusic(){
        clip.stop();
        clip.close();
    }

    public void playLoop(String path){

        try{
            File musicPath =new File(path);

            if (musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip=AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(clip.LOOP_CONTINUOUSLY);
            }else
                System.out.println("music path dont exist");

        }catch (Throwable e){
            e.printStackTrace();
        }
    }

}
