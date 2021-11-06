class KuhnAlgorithm(private val T: MutableList<String>, private val matrix: Map<String, List<String>>) {
    fun run(xDouble: MutableMap<String, String>, yDouble: MutableMap<String, String>): Pair<Boolean, Map<String, String>> {
        val visited = mutableMapOf<String, MutableList<String>>();
        for (edge in matrix.keys)
            visited[edge] = mutableListOf();
        var indication = 1;
        while (indication != 0 && T.isNotEmpty()) {
            var x = start();
            val stack = mutableListOf(x);
            indication = 0;
            while (stack.isNotEmpty() && indication == 0) {
                x = stack.last();
                val y = choice(x, visited);
                if (y != "") {
                    stack.add(y);
                    val z = yDouble[y]!!;
                    if (z != "") {
                        stack.add(z);
                    }
                    else {
                        indication = 1
                    }
                }
                else {
                    stack.remove(stack.last());
                    if (stack.isNotEmpty())
                        stack.remove(stack.last());
                }
            }
            if (indication == 1) {
                while (stack.isNotEmpty()) {
                    val y = stack.last();
                    stack.remove(y);
                    val x = stack.last();
                    stack.remove(x);
                    T.remove(x);
                    xDouble[x] = y;
                    yDouble[y] = x;
                }
            }
        }
        if (indication == 0 || T.isNotEmpty())
            return Pair(false, xDouble)
        return Pair(true, xDouble)
    }

    private fun start(): String {
        return T[0];
    }

    private fun choice(x: String, visited: Map<String, MutableList<String>>): String {
        for (edge in matrix[x]!!)
            if (!visited[x]!!.contains(edge)){
                visited[x]?.add(edge);
                return edge
            }
        return "";
    }
}