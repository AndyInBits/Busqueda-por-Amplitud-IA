package sistema_de_busqueda;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author Andresz
 */
public class Sistema_de_Busqueda {

    public static void main(String[] args) {
       try

{
    JFrame.setDefaultLookAndFeelDecorated(true);
    JDialog.setDefaultLookAndFeelDecorated(true);
     UIManager.setLookAndFeel("net.sourceforge.napkinlaf.NapkinLookAndFeel");
}
       catch (Exception e)
{

    e.printStackTrace();
}

        
        Control con = new Control();
        con.setVisible(true);
 
    }
}
