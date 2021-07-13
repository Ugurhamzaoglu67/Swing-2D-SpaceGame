
package spacegame;

import java.awt.HeadlessException;
import javax.swing.JFrame;


public class MainGameScreen  extends JFrame{

    
    public MainGameScreen(String title) throws HeadlessException {
        super(title);
    }
    
    
    public static void main(String[] args) {
        MainGameScreen mainGameScreen = new MainGameScreen("***Space Game***");
        
    }
}
