package sistema_de_busqueda;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Laberinto extends JPanel implements ActionListener {

    static int filas, columnas, fil, colum;
    static final int MAX = 100;						//m�ximo n�mero de filas y columnas del laberinto
    static int ady[][] = new int[MAX][MAX];	//laberinto
    static int ady2[][] = new int[MAX][MAX];
    private static final long serialVersionUID = 1L;
    private Image map;
    private Image ostaculo;
    private Image agente;
    private Image salida;
    private Image poo;
    private Timer timer;

    public Laberinto(int[][] mapa, int filas, int columnas) {

        Laberinto.fil = filas;
        Laberinto.colum = columnas;
        for (int i = 0; i < fil; i++) {
            for (int j = 0; j < colum; j++) {
                Laberinto.ady2[i][j] = mapa[i][j];
                Laberinto.ady[i][j] = mapa[i][j];
            }
        }
        ImageIcon ii = new ImageIcon(this.getClass().getResource("0.png"));
        ImageIcon i2 = new ImageIcon(this.getClass().getResource("1.png"));
        ImageIcon i3 = new ImageIcon(this.getClass().getResource("2.png"));
        ImageIcon i4 = new ImageIcon(this.getClass().getResource("3.png"));
        ImageIcon i5 = new ImageIcon(this.getClass().getResource("4.png"));

        map = ii.getImage();
        ostaculo = i4.getImage();
        agente = i2.getImage();
        salida = i3.getImage();
        poo = i5.getImage();
        timer = new Timer(3000, this);



    }

    @Override
    public void actionPerformed(ActionEvent e) {

        repaint();
    }

    static class Estado {

        int x, y, d;								// Fila, columna y distancie del estado

        public Estado(int x1, int y1, int d1) {
            this.x = x1;
            this.y = y1;
            this.d = d1;
        }
    };

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        int tx = 0, ty = 0;

        for (int i = 0; i < fil; i++) {
            for (int j = 0; j < colum; j++) {
                switch (Laberinto.ady2[i][j]) {
                    case 0:
                        g.drawImage(this.map, tx, ty, null);

                        break;
                    case 1:

                        g.drawImage(this.map, tx, ty, null);
                        g2d.drawImage(this.agente, tx, ty, null);

                        break;
                    case 2:
                        g.drawImage(this.map, tx, ty, null);
                        g2d.drawImage(this.salida, tx, ty, null);

                        break;
                    case 3:
                        g2d.drawImage(this.ostaculo, tx, ty, null);

                        break;
                    case 4:
                        g.drawImage(this.map, tx, ty, null);
                        g2d.drawImage(this.poo, tx, ty, null);

                        break;
                }
                tx += 30;
            }
            tx = 0;
            ty += 30;
        }
        //g2d.drawString("prueba", 500, 50);   

    }

    public static int BFS(int x, int y, int h, int w) throws InterruptedException { //coordenadas de inicial "I" y dimensiones de laberinto
        filas = h;
        columnas = w;


        Estado prev[][] = new Estado[MAX][MAX];

        boolean visitado[][] = new boolean[MAX][MAX];	//arreglo de estados visitados
        Queue<Estado> Q = new LinkedList<Estado>();			//Cola de todos los posibles Estados por los que se pase para llegar al destino

        Q.add(new Estado(x, y, 0));
        //Insertamos el estado inicial en la Cola con distnacia 0.
        int dx[] = {0, 0, 1, -1};		//incremento en coordenada x
        int dy[] = {1, -1, 0, 0};		//incremento en coordenada y
        int nx, ny;

        while (!Q.isEmpty()) {							//Mientras cola no este vacia

            Estado actual = Q.remove();					//Obtengo de la cola el estado actual, en un comienzo ser� el inicial
            prev[x][ y] = new Estado(-1, -1, -1);

            if (ady[ actual.x][ actual.y] == 2) {
                for (int i = actual.x, j = actual.y; prev[ i][ j].d != -1; i = prev[ actual.x][ actual.y].x, j = prev[ actual.x][ actual.y].y) {

                    ady[i][j] = 1;

                    actual.x = i;
                    actual.y = j;
                }
                System.out.printf("Camino optimo :\n");
                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        System.out.printf("[%d]", ady[i][j]);
                    }
                    System.out.printf("\n");
                }
                return actual.d;						//Retornamos distancia recorrida hasta ese momento
            }

            visitado[ actual.x][ actual.y] = true;	//Marco como visitado dicho estado para no volver a recorrerlo

            for (int i = 0; i < 4; ++i) {				//Recorremos hasta 4 porque tenemos 4 posibles adyacentes
                nx = dx[ i] + actual.x;				//nx y ny tendran la coordenada adyacente
                ny = dy[ i] + actual.y;				//ejemplo en i=0 y actual (3,4) -> 3+dx[0]=3+0=3, 4+dy[0]=4+1=5, nueva coordenada (3,5)
                //aqui comprobamos que la coordenada adyacente no sobrepase las dimensiones del laberinto
                //ademas comprobamos que no sea pared "#" y no este visitado
                if (nx >= 0 && nx < h && ny >= 0 && ny < w && !visitado[ nx][ ny] && ady[ nx][ ny] != 3) {
                    Q.add(new Estado(nx, ny, actual.d + 1));
                    prev[ nx][ ny] = actual;
                }
            }
        }
        return -1;
    }
}