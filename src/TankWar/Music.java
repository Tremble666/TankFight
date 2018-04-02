package TankWar;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.*;

/**
 *
 * @author Administrator
 */
public  class Music {
    File file;
    AudioInputStream stream;
    AudioFormat format;
    DataLine.Info info;
    Clip clip;
    public Music(String fileName){
        file = new File(fileName);
        try{
            stream = AudioSystem.getAudioInputStream(file);
        }catch(UnsupportedAudioFileException ex){
        }catch(IOException ex){
        }
        format = stream.getFormat();
    }
    public void playSound(){
        info = new DataLine.Info(Clip.class, format);
        try{
            clip = (Clip)AudioSystem.getLine(info);
        }catch(LineUnavailableException ex){}
        try{
            clip.open(stream);
        }catch(LineUnavailableException ex){ }
        catch(IOException ex){ }       
        clip.start();
    }
    public void stopSound(){
        clip.stop();
    }
}