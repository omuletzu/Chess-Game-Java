public class Pion extends Piesa{
    @Override
    public boolean atac_valid(int i1, int j1, int i2, int j2, Piesa[][] tabla) {

        if(tabla[i1][j1].black_white && i1 > i2)
            return false;

        if(!tabla[i1][j1].black_white && i1 < i2)
            return false;

        if((Math.abs(i2 - i1) == 1 && Math.abs(j2 - j1) == 0 && tabla[i2][j2] == null))
            return true;

        if(tabla[i2][j2] != null && tabla[i1][j1].black_white == tabla[i2][j2].black_white)
            return false;

        return Math.abs(i2 - i1) == 1 && Math.abs(j2 - j1) == 1 && tabla[i2][j2] != null;
    }
}
