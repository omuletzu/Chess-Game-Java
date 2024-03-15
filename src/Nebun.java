public class Nebun extends Piesa{
    @Override
    public boolean atac_valid(int i1, int j1, int i2, int j2, Piesa[][] tabla) {

        if(tabla[i2][j2] != null && tabla[i1][j1].black_white == tabla[i2][j2].black_white)
            return false;

        if(Math.abs(i2 - i1) != Math.abs(j2 - j1))
            return false;

        return diagonal_valid(i1, j1, i2, j2, tabla);
    }
}
