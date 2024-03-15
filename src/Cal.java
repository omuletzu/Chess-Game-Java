public class Cal extends Piesa{
    @Override
    public boolean atac_valid(int i1, int j1, int i2, int j2, Piesa[][] tabla) {

        if(tabla[i2][j2] != null && tabla[i1][j1].black_white == tabla[i2][j2].black_white)
            return false;

        return (Math.abs(i2 - i1) == 2 && Math.abs(j2 - j1) == 1) || (Math.abs(i2 - i1) == 1 && Math.abs(j2 - j1) == 2);
    }
}
