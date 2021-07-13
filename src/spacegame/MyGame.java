
package spacegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
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
import javax.swing.JOptionPane;
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
    private int spaceX = -30;
    private int directionSpaceX = 20;
    
    
    // WAS THERE  CRASH 
    public boolean wasCrash(){
        for(Fire fire : fires){
            
            if( new Rectangle(fire.getX(), fire.getY(),7,15).intersects(new Rectangle(ballX,0,20,20))  ){
                return true;
            }     
            
        }
        
        return false;
    }
    
    
    
    
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
        passingTime += 5;
        
        g.setColor(Color.red);
        g.fillOval(ballX, 0, 30, 30);
        g.drawImage(myimage, spaceX, 550, myimage.getWidth()/4, myimage.getHeight()/4, this);
        
        //Fire
        for(Fire fire : fires){
             
            if(fire.getY() < 0){
                fires.remove(fire);
            }
        }
        
        g.setColor(Color.ORANGE);
        
        for(Fire fire : fires){
            g.fillRect(fire.getX(), fire.getY(), 7, 15);
        }
        
        // CRASH
        if(wasCrash()){
            timer.stop();
            String message = "You WON...\n"+"Consume Fire: "+countFire+
                    "\nPassing Time : "+ (passingTime / 1000.0)+"s";
            
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
        }
        
        
    }

    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    @Override
    public void keyTyped(KeyEvent e) {
      
        
    }
   
    // KEYBOARD OPERATIONS
    @Override
    public void keyPressed(KeyEvent e) {
        int myKey = e.getKeyCode();
        
         //LEFT KEY
        if(myKey == KeyEvent.VK_LEFT){          
            
                if(spaceX <=-30){
                    spaceX =-30;
                }else {
                    spaceX -= directionSpaceX;
                }
        }
        
        //RIGHT KEY
        else if ( myKey == KeyEvent.VK_RIGHT){  
            
            if(spaceX >= 770){
                spaceX = 770;
            }else {
                spaceX += directionSpaceX;
            }
        }
        
        // FIRE SPACE KEY
        else if(myKey == KeyEvent.VK_SPACE || myKey == KeyEvent.VK_CONTROL){
            fires.add(new Fire(spaceX+70, 560));
            countFire++;
            
        }
    }

    
    
    @Override
    public void keyReleased(KeyEvent e) {

    }

    
    // BALL COORDINATE
    @Override
    public void actionPerformed(ActionEvent e) {

        //when you fire  -> fire Y Coordinate decrease
        for(Fire fire : fires){
            fire.setY(fire.getY() - fireY);
        }
        
        
        
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
