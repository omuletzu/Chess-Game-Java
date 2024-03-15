import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Piesa_listener implements MouseListener {

    private static boolean black_white_round;                   // false - white ; true - black

    private JLabel[][] piese_tabla;
    private Piesa[][] piese_logica;
    private JFrame tabla;
    private int cord_i;
    private int cord_j;
    private static int cord_i_sah_rege = -1;
    private static int cord_j_sah_rege = -1;

    public Piesa_listener(JFrame tabla, JLabel[][] piese_tabla, Piesa[][] piese_logica, int cord_i, int cord_j){
        this.tabla = tabla;
        this.piese_tabla = piese_tabla;
        this.piese_logica = piese_logica;
        this.cord_i = cord_i;
        this.cord_j = cord_j;
        black_white_round = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        boolean flag = false;

        for(int i = 0; i < 8 && !flag; i++)
            for(int j = 0; j < 8; j++)
                if(piese_logica[i][j] != null && piese_logica[i][j].mod_atac){
                    flag = true;
                    break;
                }

        if(flag || piese_logica[cord_i][cord_j] == null || black_white_round == piese_logica[cord_i][cord_j].black_white) {

            flag = false;
            int init_i = cord_i;
            int init_j = cord_j;

            for (int i = 0; i < 8 && !flag; i++) {
                for (int j = 0; j < 8; j++) {

                    if ((i != cord_i || j != cord_j) && piese_logica[i][j] != null && piese_logica[i][j].mod_atac) {
                        flag = true;
                        init_i = i;
                        init_j = j;

                        break;
                    }
                }
            }

            if (!piese_logica[init_i][init_j].atac_valid(init_i, init_j, cord_i, cord_j, piese_logica) && piese_logica[init_i][init_j].mod_atac) {

                piese_logica[init_i][init_j].mod_atac = false;

                if(init_i == cord_i_sah_rege && init_j == cord_j_sah_rege)
                    piese_tabla[init_i][init_j].setBorder(BorderFactory.createLineBorder(Color.BLUE));
                else
                    piese_tabla[init_i][init_j].setBorder(BorderFactory.createLineBorder(Color.GRAY));

                piese_tabla[init_i][init_j].updateUI();

                return;
            }

            if (init_i == cord_i && init_j == cord_j && piese_logica[cord_i][cord_j] != null && piese_logica[cord_i][cord_j].mod_atac) {
                piese_logica[cord_i][cord_j].mod_atac = false;

                if(cord_i == cord_i_sah_rege && cord_j == cord_j_sah_rege)
                    piese_tabla[cord_i][cord_j].setBorder(BorderFactory.createLineBorder(Color.BLUE));
                else
                    piese_tabla[cord_i][cord_j].setBorder(BorderFactory.createLineBorder(Color.GRAY));

                piese_tabla[cord_i][cord_j].updateUI();
            } else {

                if (flag) {         //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                    Piesa savedPiece = piese_logica[cord_i][cord_j];
                    Piesa movedPiece = piese_logica[init_i][init_j];

                    //////////////////////////////////////////////////////////////////

                    piese_logica[cord_i][cord_j] = piese_logica[init_i][init_j];
                    piese_logica[init_i][init_j] = null;

                    if(Piesa.verificare_sah(piese_logica, black_white_round)){

                        piese_logica[cord_i][cord_j] = savedPiece;
                        piese_logica[init_i][init_j] = movedPiece;
                    }
                    else {

                        piese_logica[cord_i][cord_j].mod_atac = false;

                        piese_tabla[cord_i][cord_j].setIcon(piese_tabla[init_i][init_j].getIcon());
                        piese_tabla[init_i][init_j].setIcon(null);
                        piese_tabla[init_i][init_j].setBorder(BorderFactory.createLineBorder(Color.GRAY));

                        piese_tabla[cord_i][cord_j].updateUI();
                        piese_tabla[init_i][init_j].updateUI();

                        if(cord_i_sah_rege > -1) {
                            piese_tabla[cord_i_sah_rege][cord_j_sah_rege].setBorder(BorderFactory.createLineBorder(Color.GRAY));
                            piese_tabla[cord_i_sah_rege][cord_j_sah_rege].updateUI();
                        }

                        cord_i_sah_rege = -1;

                        black_white_round = !black_white_round;

                        flag = false;

                        for (int i = 0; i < 8 && !flag; i++)
                            for (int j = 0; j < 8; j++)
                                if (piese_logica[i][j] instanceof Rege && piese_logica[i][j].black_white == black_white_round) {
                                    cord_i_sah_rege = i;
                                    cord_j_sah_rege = j;
                                    flag = true;
                                    break;
                                }

                        if (Piesa.verificare_sah(piese_logica, black_white_round)) {
                            piese_tabla[cord_i_sah_rege][cord_j_sah_rege].setBorder(BorderFactory.createLineBorder(Color.BLUE));
                            piese_tabla[cord_i][cord_j].updateUI();
                        } else {
                            piese_tabla[cord_i_sah_rege][cord_j_sah_rege].setBorder(BorderFactory.createLineBorder(Color.GRAY));
                            piese_tabla[cord_i_sah_rege][cord_j_sah_rege].updateUI();

                            cord_i_sah_rege = -1;
                        }
                    }

                } else {
                    piese_tabla[cord_i][cord_j].setBorder(BorderFactory.createLineBorder(Color.RED));
                    piese_tabla[cord_i][cord_j].updateUI();

                    piese_logica[cord_i][cord_j].mod_atac = true;
                }
            }

            if (black_white_round)
                tabla.setTitle("Chess - Black's turn");
            else
                tabla.setTitle("Chess - White' turn");

            if(Piesa.verificare_sah(piese_logica, black_white_round) && Piesa.verificare_sah_mat(piese_logica, black_white_round)){
                if (!black_white_round)
                    tabla.setTitle("Chess - Black WIN");
                else
                    tabla.setTitle("Chess - White WIN");
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
