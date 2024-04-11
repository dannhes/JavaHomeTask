package expression.generic;
import java.util.Map;

public class GenericTabulator implements Tabulator{
    private static final Map<String,Types<?>> map = Map.of(
            "i", new TypeInt(),
            "d",new TypeDouble(),
            "bi",new TypeBigInt(),
            "u",new TypeIntWithExc(),
            "b",new TypeByte(),
            "bool",new TypeBool()

    );
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        return tab(map.get(mode),expression,x1, x2, y1, y2,z1,z2);
    }


    private <T> Object[][][] tab(Types<T> mod,String expression,int x1, int x2, int y1, int y2, int z1, int z2){
        GenericParser<T> parser = new GenericParser<>(mod);
        GenericExpr<T> parsed = parser.parse(expression);
        Object[][][] res = new Object[x2-x1+1][y2-y1+1][z2-z1+1];
        for (int i = 0; i < x2 - x1 + 1; i++) {
            for (int j = 0; j < y2 - y1 + 1; j++) {
                for (int k = 0; k < z2 - z1 + 1; k++) {
                    res[i][j][k] = parsed.evaluate(mod.cast((x1+i)), mod.cast(y1 + j), mod.cast(z1+k));
                }
            }
        }
        return res;
    }
}
