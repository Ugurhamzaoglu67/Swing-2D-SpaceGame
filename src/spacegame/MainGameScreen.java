
package spacegame;

import java.awt.HeadlessException;
import javax.swing.JFrame;


public class MainGameScreen  extends JFrame{

    
    public MainGameScreen(String title) throws HeadlessException {
        super(title);
    }
    
    
    public static void main(String[] args) {
        MainGameScreen mainGameScreen = new MainGameScreen("***Space Game***");
       
        mainGameScreen.setResizable(false);
        mainGameScreen.setFocusable(false);
        mainGameScreen.setSize(900,700);
        mainGameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        MyGame game = new MyGame();
        
        game.requestFocus(); // Odağı bana ver. Klavyeden işlemler için.
        
        game.addKeyListener(game);  
        game.setFocusable(true);    // Odak JPanel'de.
        game.setFocusTraversalKeysEnabled(false); //Klave işlemleri gerçekleşmesi için
        
        //JPanel add -> JFrame
        mainGameScreen.add(game);
        mainGameScreen.setVisible(true);
        
    }
}
