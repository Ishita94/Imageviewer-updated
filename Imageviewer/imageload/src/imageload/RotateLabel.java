/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageload;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Moumi
 */
public  class RotateLabel extends JLabel {

         

        public RotateLabel(ImageIcon i) {

            super(i);

        }
        @Override

        public void paintComponent(Graphics g) {

            Graphics2D gx = (Graphics2D) g;

            gx.rotate(3.14/2, getX() + getWidth() / 2, getY() + getHeight() / 2); //Rotate 0.2 radians around the center of the label

            super.paintComponent(g);
            gx.dispose();

        }

    }

