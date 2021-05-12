import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class FordBellmanTest {

    @Test
    public void Test() {
        var matrix = FileHandler.GetDefaultMatrix(2);
        matrix[0][1] = 2;
        matrix[1][0] = 2;
        var fordBellman = new FordBellman();
        var ans = fordBellman.Algorithm(0, 1, matrix);
        assertEquals(ans, "0 1\n2");
    }

    @Test
    public void TestNoConnectivity() {
        var matrix = FileHandler.GetDefaultMatrix(3);
        matrix[0][1] = 2;
        var fordBellman = new FordBellman();
        var ans = fordBellman.Algorithm(0, 2, matrix);
        assertEquals(ans, "NO");
    }

    @Test
    public void TestFromExample() {
        var matrix = FileHandler.GetDefaultMatrix(4);
        matrix[0][1] = -25;
        matrix[0][2] = 4;
        matrix[2][1] = 0;
        matrix[2][3] = 7;
        var fordBellman = new FordBellman();
        var ans = fordBellman.Algorithm(0, 3, matrix);
        assertEquals(ans, "0 2 3\n11");
    }

    @Test
    public void TestSimple() {
        var matrix = FileHandler.GetDefaultMatrix(6);
        matrix[0][1] = 2;
        matrix[1][2] = 3;
        matrix[2][1] = 5;
        matrix[2][4] = 1;
        matrix[4][5] = 1;
        matrix[1][5] = 6;
        var fordBellman = new FordBellman();
        var ans = fordBellman.Algorithm(0, 5, matrix);
        assertEquals(ans, "0 1 2 4 5\n7");
    }
}
