
package spacegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JPanel;
import javax.swing.Timer;


// FIRE CLASS 
class Fire {
    private int x,y;

    public Fire(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}




public class MyGame extends JPanel implements KeyListener, ActionListener{
    
    Timer timer = new Timer(5,this);

    private int passingTime = 0;
    private int countFire = 0;
    private BufferedImage myimage;
    
    private ArrayList<Fire> fires = new ArrayList<>();
    
    private int fireY = 1;
    private int ballX = 0;
    private int ballDirectionX = 2;
    
    //SPACE SHIP
    private int spaceX = 0;
    private int directionSpaceX = 20;
    
    
    
    // CONSTRUCTOR
    public MyGame(){
        
        try {
            myimage = ImageIO.read(new FileImageInputStream(new File("spaceship-png-icon-17260.png")));
        } catch (IOException ex) {
            Logger.getLogger(MyGame.class.getName()).log(Level.SEVERE, null, ex);
        } 

        setBackground(Color.BLACK);
        
        timer.start();  // 5 ms'de bir,actionPerformed() bu metod kendiliğinden çalışıcak.
        
        
    }

   // FIRE BALL
    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        
        g.setColor(Color.red);
        g.fillOval(ballX, 0, 30, 30);
        g.drawImage(myimage, spaceX, 550, myimage.getWidth()/4, myimage.getHeight()/4, this);
    }

    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    // KEYBOARD OPERATIONS
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ballX += ballDirectionX;
        
        if(ballX >= 865){
            ballDirectionX = -ballDirectionX;
        }
        
        if(ballX <= 0){
            ballDirectionX = -ballDirectionX;
        }
        
        repaint();
        
    }
    
}
