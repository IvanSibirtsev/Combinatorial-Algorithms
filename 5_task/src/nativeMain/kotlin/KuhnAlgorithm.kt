class KuhnAlgorithm(X: List<String>, private val matrix: Map<String, List<String>>) {
     private val T: MutableList<String> = X.toMutableList()

    fun run(yDouble: MutableMap<String, String>): Pair<Boolean, Map<String, String>> {
        val visited = getVisited()
        var indication = 1
        while (indication != 0 && T.isNotEmpty()) {
            val stack = mutableListOf(start())
            indication = 0
            while (stack.isNotEmpty() && indication == 0) {
                val x = stack.last()
                val y = choice(x, visited)
                if (y != "") {
                    stack.add(y)
                    val z = yDouble[y]!!
                    if (z != "") {
                        stack.add(z)
                    }
                    else {
                        indication = 1
                    }
                }
                else {
                    stack.removeLast()
                    if (stack.isNotEmpty())
                        stack.removeLast()
                }
            }
            if (indication == 1) {
                while (stack.isNotEmpty()) {
                    val y = stack.removeLast()
                    val x = stack.removeLast()
                    T.remove(x)
                    yDouble[y] = x
                }
            }
        }
        if (indication == 0 || T.isNotEmpty())
            return Pair(false, yDouble)
        return Pair(true, yDouble)
    }

    private fun getVisited(): MutableMap<String, MutableList<String>> {
        return (matrix.keys).associateWith { mutableListOf<String>() }.toMutableMap()
    }

    private fun start(): String {
        return T[0]
    }

    private fun choice(x: String, visited: Map<String, MutableList<String>>): String {
        for (edge in matrix[x]!!)
            if (!visited[x]!!.contains(edge)){
                visited[x]?.add(edge)
                return edge
            }
        return ""
    }
}