/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema_de_busqueda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import sun.awt.RepaintArea;

/**
 *
 * @author Andresvz
 */
public class Dibujar implements ActionListener {

    private static Laberinto lab;
    int x, y;

    Dibujar(int filas, int columnas) {
        this.x = filas;
        this.y = columnas;
        JFrame windowAnimation = new JFrame();
        
        windowAnimation.setTitle("Proyecto de IA");
        windowAnimation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowAnimation.setSize(x * 100, y * 100);
        windowAnimation.setVisible(true);
        windowAnimation.setLocationRelativeTo(null);
        windowAnimation.setResizable(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        getlab().repaint();
    }

    public static Laberinto getlab() {
       
        return lab;
    }

    public static void setlab(Laberinto lab) {
        Dibujar.lab = lab;
    }
}
