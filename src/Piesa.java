public abstract class Piesa {

    public boolean mod_atac;
    public boolean black_white;                                 // false - white ; true - black

    public Piesa(){
        this.mod_atac = false;
    }

    public abstract boolean atac_valid(int i1, int j1, int i2, int j2, Piesa[][] tabla);

    protected boolean orizontal_valid(int j1, int j2, Piesa[][] tabla, int linie){

        if(j1 > j2){
            int aux = j1;
            j1 = j2;
            j2 = aux;
        }

        for(int i = j1 + 1; i < j2; i++){
            if(tabla[linie][i] != null)
                return false;
        }

        return true;
    }

    protected boolean vertical_valid(int i1, int i2, Piesa[][] tabla, int coloana){

        if(i1 > i2){
            int aux = i1;
            i1 = i2;
            i2 = aux;
        }

        for(int i = i1 + 1; i < i2; i++){
            if(tabla[i][coloana] != null)
                return false;
        }

        return true;
    }

    protected boolean diagonal_valid(int i1, int j1, int i2, int j2, Piesa[][] tabla){

        if(i1 < i2 && j1 < j2){
            i1++;
            j1++;
            while(i1 < i2)
                if(tabla[i1++][j1++] != null)
                    return false;
        }

        if(i1 < i2 && j1 > j2){
            i1++;
            j1--;
            while(i1 < i2)
                if(tabla[i1++][j1--] != null)
                    return false;
        }

        if(i1 > i2 && j1 < j2){
            i1--;
            j1++;
            while(i1 > i2)
                if(tabla[i1--][j1++] != null)
                    return false;
        }

        if(i1 > i2 && j1 > j2){
            i1--;
            j1--;
            while(i1 > i2)
                if(tabla[i1--][j1--] != null)
                    return false;
        }

        return true;
    }

    public static boolean verificare_sah(Piesa[][] tabla, boolean black_white){

        boolean flag = false;

        int cord_i = 0, cord_j = 0;

        for(int i = 0; i < 8 && !flag; i++) {
            for (int j = 0; j < 8; j++)
                if (tabla[i][j] instanceof Rege && tabla[i][j].black_white == black_white) {
                    cord_i = i;
                    cord_j = j;
                    flag = true;
                    break;
                }
        }

        for(int i = cord_i + 1; i < 8; i++){
            if(tabla[i][cord_j] != null && tabla[i][cord_j].black_white == !black_white && (tabla[i][cord_j] instanceof Regina || tabla[i][cord_j] instanceof Tura))
                return true;

            if(tabla[i][cord_j] != null && tabla[i][cord_j].black_white == black_white)
                break;
        }

        for(int i = cord_j + 1; i < 8; i++){
            if(tabla[cord_i][i] != null && tabla[cord_i][i].black_white == !black_white && (tabla[cord_i][i] instanceof Regina || tabla[cord_i][i] instanceof Tura))
                return true;

            if(tabla[cord_i][i] != null && tabla[cord_i][i].black_white == black_white)
                break;
        }

        for(int i = cord_i - 1; i >= 0; i--){
            if(tabla[i][cord_j] != null && tabla[i][cord_j].black_white == !black_white && (tabla[i][cord_j] instanceof Regina || tabla[i][cord_j] instanceof Tura))
                return true;

            if(tabla[i][cord_j] != null && tabla[i][cord_j].black_white == black_white)
                break;
        }

        for(int i = cord_j - 1; i >= 0; i--){
            if(tabla[cord_i][i] != null && tabla[cord_i][i].black_white == !black_white && (tabla[cord_i][i] instanceof Regina || tabla[cord_i][i] instanceof Tura))
                return true;

            if(tabla[cord_i][i] != null && tabla[cord_i][i].black_white == black_white)
                break;
        }

        for(int i = cord_i + 1, j = cord_j + 1; i < 8 && j < 8; i++, j++){
            if(tabla[i][j] != null && tabla[i][j].black_white == !black_white && (tabla[i][j] instanceof Regina || tabla[i][j] instanceof Nebun))
                return true;

            if(tabla[i][j] != null && tabla[i][j].black_white == black_white)
                break;
        }

        for(int i = cord_i - 1, j = cord_j + 1; i >= 0 && j < 8; i--, j++){
            if(tabla[i][j] != null && tabla[i][j].black_white == !black_white && (tabla[i][j] instanceof Regina || tabla[i][j] instanceof Nebun))
                return true;

            if(tabla[i][j] != null && tabla[i][j].black_white == black_white)
                break;
        }

        for(int i = cord_i + 1, j = cord_j - 1; i < 8 && j >= 0; i++, j--){
            if(tabla[i][j] != null && tabla[i][j].black_white == !black_white && (tabla[i][j] instanceof Regina || tabla[i][j] instanceof Nebun))
                return true;

            if(tabla[i][j] != null && tabla[i][j].black_white == black_white)
                break;
        }

        for(int i = cord_i - 1, j = cord_j - 1; i >= 0 && j >= 0; i--, j--){
            if(tabla[i][j] != null && tabla[i][j].black_white == !black_white && (tabla[i][j] instanceof Regina || tabla[i][j] instanceof Nebun))
                return true;

            if(tabla[i][j] != null && tabla[i][j].black_white == black_white)
                break;
        }

        int[] v1 = {-2 , -1 , 1 , 2 ,  2 ,  1 , -1 , -2}, v2 = {1 ,  2 , 2 , 1 , -1 , -2 , -2 , -1};

        for(int k = 0; k < 8; k++){
            int ii = cord_i + v1[k], jj = cord_j + v2[k];

            if(ii >= 0 && ii < 8 && jj >= 0 && jj < 8 && tabla[ii][jj] != null && tabla[ii][jj].black_white == !black_white && tabla[ii][jj] instanceof Cal)
                return true;
        }

        int[] c1 = {-1, -1, 1, 1}, c2 = {-1, 1, 1, -1};

        for(int k = 0; k < 4; k++){
            int ii = cord_i + c1[k], jj = cord_j + c2[k];

            if(ii >= 0 && ii < 8 && jj >= 0 && jj < 8 && tabla[ii][jj] != null && tabla[ii][jj].black_white == !black_white && tabla[ii][jj] instanceof Pion){
                if(!black_white && ii < cord_i)
                    return true;

                if(black_white && ii > cord_i)
                    return true;
            }
        }

        return false;
    }

    public static boolean verificare_sah_mat(Piesa[][] tabla, boolean black_white){

        boolean flag = false;

        int cord_i = 0, cord_j = 0;

        for(int i = 0; i < 8 && !flag; i++) {
            for (int j = 0; j < 8; j++)
                if (tabla[i][j] instanceof Rege && tabla[i][j].black_white == black_white) {
                    cord_i = i;
                    cord_j = j;
                    flag = true;
                    break;
                }
        }

        int[] v1 = {-1, -1, -1, 0, 1, 1, 1, 0}, v2 = {-1, 0, 1, 1, 1, 0, -1, -1};

        for(int k = 0; k < 8; k++){

            int ii = cord_i + v1[k], jj = cord_j + v2[k];

            if(ii >= 0 && ii < 8 && jj >= 0 && jj < 8 && (tabla[ii][jj] == null || tabla[ii][jj].black_white != black_white)){

                Piesa copie = tabla[ii][jj];

                tabla[ii][jj] = tabla[cord_i][cord_j];
                tabla[cord_i][cord_j] = null;

                if(!Piesa.verificare_sah(tabla, !black_white)) {
                    tabla[cord_i][cord_j] = tabla[ii][jj];
                    tabla[ii][jj] = copie;

                    return false;
                }
            }
        }

        return true;
    }
}
