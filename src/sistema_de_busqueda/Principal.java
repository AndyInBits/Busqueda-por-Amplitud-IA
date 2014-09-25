
package sistema_de_busqueda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public final class Principal extends JFrame implements ActionListener {

    int filas, columnas, pfila, pcolumna, sfila, scolumna, ostaculos;
    private static final long serialVersionUID = 1L;
    private int mapa[][] = new int[100][100];
    private static Principal pri;
    private JButton buttonNextStep;
    private JButton buttonLog;
    private int count = 0;
    private Laberinto b;
    private int vecx[] = new int[100], vecy[] = new int[100];

    Principal(int x, int y, int z, int v, int n, int m, int p) throws InterruptedException {

        this.filas = x;
        this.columnas = y;
        this.pfila = z - 1;
        this.pcolumna = v - 1;
        this.sfila = n - 1;
        this.scolumna = m - 1;
        this.ostaculos = p;

        setTitle("Busquedad por Amplitud"); // Titulo
        setSize((columnas) * 30, (filas + 1) * 30); // Tamaño
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Salir cuando apretamos el boton 'x'

        setLocationRelativeTo(null); // Centrar
        setVisible(true); // Visible
        
        setResizable(false); //

        JFrame windowOption = new JFrame();
        windowOption.setTitle("Sistema");
        windowOption.setVisible(true);
        windowOption.setBounds(50, 300, 170, 200);
       
        windowOption.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowOption.setResizable(false);
        windowOption.setLayout(null);
        buttonNextStep = new JButton("Manual");
        buttonLog = new JButton("Automatico");
        buttonNextStep.setBounds(15, 30, 130, 30);
        buttonLog.setBounds(15, 90, 130, 30);
        windowOption.add(buttonNextStep);
        windowOption.add(buttonLog);
        buttonNextStep.addActionListener(this);
        buttonLog.addActionListener(this);



        //lleno la matriz      
        for (int w = 0; w < p; w++) {
            int i = (int) Math.floor(Math.random() * filas);
            int r = (int) Math.floor(Math.random() * columnas);



            this.mapa[i][r] = 3;

        }
        this.mapa[pfila][pcolumna] = 1;
        this.mapa[sfila][scolumna] = 2;
        this.mostrar();

        b = new Laberinto(mapa, filas, columnas);
        add(b);

        int min = Laberinto.BFS(pfila, pcolumna, filas, columnas);


        if (min == -1) {
            JOptionPane.showMessageDialog(null, "No se pudo llegar al destino");
        } else {
            System.out.println("Menor numero de pasos: " + min);
        }
        int counto = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (Laberinto.ady[i][j] == 1) {
                    vecx[counto] = i;
                    vecy[counto] = j;
                    counto++;
                }
            }
        }

    }

    public void mostrar() {
        System.out.printf("\nSu matriz es: \n\n");
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                System.out.printf("[%d]", mapa[f][c]);//imprime la matriz
            }
            System.out.printf("\n");
        }




    }
    Timer timer = new Timer(500, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!(vecx[count] == sfila && vecy[count] == scolumna)) {
                if (count > 0) {
                    Laberinto.ady2[vecx[count]][vecy[count]] = 1;
                    Laberinto.ady2[vecx[count - 1]][vecy[count - 1]] = 4;
                } else {
                    Laberinto.ady2[vecx[count]][vecy[count]] = 1;
                }
                count++;
                b.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Se encontró la salida");
                timer.stop();
            }
        }
    });

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttonNextStep) {
            if (!(vecx[count] == sfila && vecy[count] == scolumna)) {
                if (count > 0) {
                    Laberinto.ady2[vecx[count]][vecy[count]] = 1;
                    Laberinto.ady2[vecx[count - 1]][vecy[count - 1]] = 4;
                } else {
                    Laberinto.ady2[vecx[count]][vecy[count]] = 1;
                }
                count++;
                b.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Se encontró la salida");
            }

        } else {
            if (e.getSource() == buttonLog) {
                timer.start();
            }


        }
    }
}
