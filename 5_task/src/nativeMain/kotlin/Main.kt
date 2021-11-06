
fun main() {
    val (matrix, Y) = parse("4\na b c d 0\na b c 0\na b 0\na 0")
    val X = matrix.keys.toList()
    val currentMatching = getMatching(Y)
    val alg = KuhnAlgorithm(X, matrix)
    val (hasMatching, matching) = alg.run(currentMatching)
    if (hasMatching) {
        println("Y\n${matching.keys.joinToString(separator = " ")}")
    }
    else {
         println("N")
    }
}

fun getMatching(edges: List<String>): MutableMap<String, String> {
    return (edges).associateWith { "" }.toMutableMap()
}


fun parse(input: String): Pair<Map<String, List<String>>, List<String>> {
    val text = input.split('\n').iterator()
    val lines = text.next().toInt()
    val matrix = mutableMapOf<String, MutableList<String>>()
    val Y = mutableSetOf<String>()
    (1 until lines + 1).forEach { x ->
        val elements = text.next().split(' ').dropLast(1)
        matrix[x.toString()] = elements.toMutableList()
        Y.addAll(elements)
    }
    return Pair(matrix, Y.toList())
}

