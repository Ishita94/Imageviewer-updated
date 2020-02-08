/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageload;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;

/**
 *
 * @author Moumi
 */

public class MyFrame extends JFrame{

    
public  MyFrame(ImageIcon i,int n)
{
        

        setLayout(new GridLayout(0, 1));
        JLabel label2 = new RotateLabel(i,n);
        add(label2);
        setSize(1000, 500);
        this.setLocation(180, 0);

       
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        

    }

  private class RotateLabel extends JLabel {

        private int jkl;
        public RotateLabel(ImageIcon i,int n) {

            super(i);
            jkl=n;

        }

         

        @Override

        public void paintComponent(Graphics g) {

            Graphics2D gx = (Graphics2D) g;

            gx.rotate((3.14/2)*jkl, getX() + getWidth() / 2, getY() + getHeight() / 2); //Rotate 0.2 radians around the center of the label

            super.paintComponent(g);
            gx.dispose();

        }

    }

}

