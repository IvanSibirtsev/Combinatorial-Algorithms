import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class FordBellmanTest {

    @Test
    public void testSimple() {
        var matrix = Main.getDefaultMatrix(2);
        matrix[0][1] = 2;
        matrix[1][0] = 2;
        var fordBellman = new FordBellman();
        var ans = fordBellman.algorithm(0, 1, matrix);
        assertEquals(ans, "Y\n1 2\n2");
    }

    @Test
    public void testNoConnectivity() {
        var matrix = Main.getDefaultMatrix(3);
        matrix[0][1] = 2;
        var fordBellman = new FordBellman();
        var ans = fordBellman.algorithm(0, 2, matrix);
        assertEquals(ans, "N");
    }

    @Test
    public void testFromExample() {
        var matrix = Main.getDefaultMatrix(4);
        matrix[0][1] = -25;
        matrix[0][2] = 4;
        matrix[2][1] = 0;
        matrix[2][3] = 7;
        var fordBellman = new FordBellman();
        var ans = fordBellman.algorithm(0, 3, matrix);
        assertEquals(ans, "Y\n1 3 4\n11");
    }

    @Test
    public void testNormalGraph() {
        var matrix = Main.getDefaultMatrix(6);
        matrix[0][1] = 2;
        matrix[1][2] = 3;
        matrix[2][1] = 5;
        matrix[2][4] = 1;
        matrix[4][5] = 1;
        matrix[1][5] = 6;
        var fordBellman = new FordBellman();
        var ans = fordBellman.algorithm(0, 5, matrix);
        assertEquals(ans, "Y\n1 2 3 5 6\n7");
    }
}
