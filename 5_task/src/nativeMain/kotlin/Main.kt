
fun main(args: Array<String>) {
    val (matrix, Y) = parse(args[0])
    val X = matrix.keys.toMutableList()
    val xDouble = mutableMapOf<String, String>()
    val yDouble = mutableMapOf<String, String>()
    for (x in X)
        xDouble[x] = ""
    for (y in Y)
        yDouble[y] = ""
    val alg = KuhnAlgorithm(X, matrix)
    val (hasMatching, matching) = alg.run(xDouble, yDouble)
    if (hasMatching) {
        println("Y\n${matching.values.joinToString(separator = " ")}")
    }
    else {
         println("N")
    }
}


fun parse(input: String): Pair<Map<String, List<String>>, MutableList<String>> {
    val text = input.split('\n').iterator()
    val lines = text.next().toInt()
    val matrix = mutableMapOf<String, MutableList<String>>()
    val Y = mutableSetOf<String>()
    (1 until lines + 1).forEach { x ->
        val elements = text.next().split(' ').dropLast(1);
        matrix[x.toString()] = elements.toMutableList()
        Y.addAll(elements)
    }
    return Pair(matrix, Y.toMutableList())
}

