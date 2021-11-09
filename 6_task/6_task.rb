def parse(filename)
  text = File.open(filename, "r")
  line = text.gets
  n, k = line.split(' ').map(&:to_i)
  matrix = get_empty_matrix(n, Float::INFINITY)
  (0..k - 1).each { |i|
    line = text.gets
    f, t, s = line.split(' ').map(&:to_i)
    f, t = f - 1, t - 1
    matrix[f][t] = s
    matrix[t][f] = s
  }
  text.close
  matrix
end

def get_empty_matrix(n, default)
  matrix = Array.new(n) { Array.new(n) }
  (0..n - 1).each { |i|
    (0..n - 1).each { |j|
      matrix[i][j] = j == i ? 0 : default
    }
  }
  matrix
end

def floyd(distance)
  n = distance.length - 1
  (0..n).each { |k|
    (0..n).each { |i|
      distance_ki = k < i ? distance[k][i] : distance[i][k]
      next if k != i && distance_ki != Float::INFINITY
      (i + 1..n).each { |j|
        distance_kj = k < j ? distance[k][j] : distance[j][k]
        next if k != j && i != j && distance_kj != Float::INFINITY
        if distance[i][j] > distance_kj + distance_ki
          distance[i][j] = distance_kj + distance_ki
        end
      }
    }
  }
  distance
end

def write_in_file(filename, information)
  File.write(filename, information)
end

def main
  matrix = parse('in.txt')
  n = matrix.length - 1
  distance = floyd(matrix)

  minimum = Float::INFINITY
  index = 0
  (0..n).each { |i|
    maximum = distance[i].max
    if maximum < minimum
      minimum = maximum
      index = i
    end
  }
  result = "#{minimum} #{index + 1}"
  write_in_file('out.txt', result)
end

main
