import javax.swing.*;
import java.awt.*;

public class ChessTable {

    private JFrame tabla;
    private JLabel[][] piese_tabla;
    private Piesa[][] piese_logica;
    public ChessTable(){

        tabla = new JFrame("Chess - White's turn");
        tabla.setLayout(new GridLayout(8, 8));
        tabla.setSize(600, 600);
        tabla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        piese_tabla = new JLabel[8][8];
        piese_logica = new Piesa[8][8];

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){

                JLabel cell = new JLabel();
                cell.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                piese_tabla[i][j] = cell;
                piese_logica[i][j] = null;

                switch (i){

                    case 0 :{

                        switch (j){

                            case 0, 7:{
                                piese_logica[i][j] = new Tura();
                                piese_logica[i][j].black_white = true;
                                cell.setIcon(new ImageIcon("imagini_piese/tura_neagra.png"));
                                break;
                            }

                            case 1, 6:{
                                piese_logica[i][j] = new Cal();
                                piese_logica[i][j].black_white = true;
                                cell.setIcon(new ImageIcon("imagini_piese/cal_negru.png"));
                                break;
                            }

                            case 2, 5:{
                                piese_logica[i][j] = new Nebun();
                                piese_logica[i][j].black_white = true;
                                cell.setIcon(new ImageIcon("imagini_piese/nebun_negru.png"));
                                break;
                            }

                            case 3 :{
                                piese_logica[i][j] = new Regina();
                                piese_logica[i][j].black_white = true;
                                cell.setIcon(new ImageIcon("imagini_piese/regina_neagra.png"));
                                break;
                            }

                            case 4 :{
                                piese_logica[i][j] = new Rege();
                                piese_logica[i][j].black_white = true;
                                cell.setIcon(new ImageIcon("imagini_piese/rege_negru.png"));
                                break;
                            }
                        }

                        break;
                    }

                    case 1 :{
                        piese_logica[i][j] = new Pion();
                        piese_logica[i][j].black_white = true;
                        cell.setIcon(new ImageIcon("imagini_piese/pion_negru.png"));
                        break;
                    }

                    case 6 :{
                        piese_logica[i][j] = new Pion();
                        cell.setIcon(new ImageIcon("imagini_piese/pion_alb.png"));
                        break;
                    }

                    case 7 :{

                        switch (j){

                            case 0, 7:{
                                piese_logica[i][j] = new Tura();
                                cell.setIcon(new ImageIcon("imagini_piese/tura_alba.png"));
                                break;
                            }

                            case 1, 6:{
                                piese_logica[i][j] = new Cal();
                                cell.setIcon(new ImageIcon("imagini_piese/cal_alb.png"));
                                break;
                            }

                            case 2, 5:{
                                piese_logica[i][j] = new Nebun();
                                cell.setIcon(new ImageIcon("imagini_piese/nebun_alb.png"));
                                break;
                            }

                            case 3 :{
                                piese_logica[i][j] = new Regina();
                                cell.setIcon(new ImageIcon("imagini_piese/regina_alba.png"));
                                break;
                            }

                            case 4 :{
                                piese_logica[i][j] = new Rege();
                                cell.setIcon(new ImageIcon("imagini_piese/rege_alb.png"));
                                break;
                            }
                        }

                        break;
                    }
                }

                cell.setHorizontalAlignment(SwingConstants.CENTER);
                cell.setVerticalAlignment(SwingConstants.CENTER);
                tabla.add(cell);

                piese_tabla[i][j].addMouseListener(new Piesa_listener(tabla, piese_tabla, piese_logica, i, j));
            }
        }

        tabla.setVisible(true);
    }
}
