public class Regina extends Piesa{
    @Override
    public boolean atac_valid(int i1, int j1, int i2, int j2, Piesa[][] tabla) {

        if(tabla[i2][j2] != null && tabla[i1][j1].black_white == tabla[i2][j2].black_white)
            return false;

        if(i1 == i2 && j1 != j2)
            return orizontal_valid(j1, j2 ,tabla, i1);

        if(i1 != i2 && j1 == j2)
            return vertical_valid(i1, i2, tabla, j1);

        return diagonal_valid(i1, j1, i2, j2, tabla);
    }
}
