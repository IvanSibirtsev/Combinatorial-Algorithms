import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class FordBellmanTest {

    @Test
    public void Test() {
        var matrix = new int[2][2];
        matrix[0][1] = 2;
        matrix[1][0] = 2;
        var edges = new int[]{0, 1};
        var fordBellman = new FordBellman();
        var ans = fordBellman.Algorithm(0, 1, PrepareAdjectiveMatrix(matrix));
        assertEquals(ans, "0 1\n2");
    }

    @Test
    public void TestNoConnectivity() {
        var matrix = new int[3][3];
        matrix[0][1] = 2;
        var fordBellman = new FordBellman();
        var ans = fordBellman.Algorithm(0, 2, PrepareAdjectiveMatrix(matrix));
        assertEquals(ans, "NO");
    }

    @Test
    public void TestFromExample() {
        var matrix = new int[4][4];
        matrix[0][1] = -25;
        matrix[0][2] = 4;
        matrix[2][1] = 1;
        matrix[2][3] = 7;
        var fordBellman = new FordBellman();
        var ans = fordBellman.Algorithm(0, 3, PrepareAdjectiveMatrix(matrix));
        assertEquals(ans, "0 2 3\n11");
    }

    private int[][] PrepareAdjectiveMatrix(int[][] A) {
        for (var i = 0; i < A.length; i++) {
            for (var j = 0; j < A.length; j++) {
                if (A[i][j] == 0) {
                    A[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        return A;
    }

    @Test
    public void TestSimple() {
        var matrix = new int[6][6];
        matrix[0][1] = 2;
        matrix[1][2] = 3;
        matrix[2][1] = 5;
        matrix[2][4] = 1;
        matrix[4][5] = 1;
        matrix[1][5] = 6;
        var fordBellman = new FordBellman();
        var ans = fordBellman.Algorithm(0, 5, PrepareAdjectiveMatrix(matrix));
        assertEquals(ans, "0 1 2 4 5\n7");
    }
}
