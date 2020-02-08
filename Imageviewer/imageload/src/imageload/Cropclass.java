/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageload;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Moumi
 */
public class Cropclass implements MouseListener,MouseMotionListener{
    public int ix=0,iy=0,fx=0,fy=0;
     BufferedImage buffImg,buffImg2,buffImg3;
     ImageIcon m;
    public Cropclass(BufferedImage o,ImageIcon i) throws IOException
    {
        buffImg = new BufferedImage(i.getIconWidth(),i.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        buffImg=o;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
       //statusbar.setText(String.format("you pressed the mouse at %d %d",e.getX(),e.getY()));
      ix=e.getX();
      iy=e.getY();
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
          //statusbar.setText(String.format("you released the mouse at %d %d",e.getX(),e.getY()));
          //System.out.println(e.getX()+e.getY());
    fx=e.getX();
    fy=e.getY();
       cropwork(ix,iy,fx-ix,fy-iy); 
    
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void cropwork(int inx,int iny,int width,int height){
 
     buffImg2 = buffImg.getSubimage(inx, iny-40, width,height);//fill in the corners of the desired crop location here
     buffImg3 = new BufferedImage(buffImg2.getWidth(), buffImg2.getHeight(), BufferedImage.TYPE_INT_RGB);
     Graphics g = buffImg3.createGraphics();
     g.drawImage(buffImg2, 0, 0, null);
     g.dispose();
     Image pimage=Toolkit.getDefaultToolkit().createImage(buffImg3.getSource());
     m=new ImageIcon(pimage);
     
    }
    public ImageIcon getimage()
    {
        return m;
    }
    public BufferedImage getbuffer(){
        return buffImg3;
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
          //System.out.println("dragging");
    
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

