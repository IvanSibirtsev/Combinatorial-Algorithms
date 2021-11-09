def parse(filename)
  File.open(filename, 'r') do |text|
    line = text.gets
    n, k = line.split(' ').map(&:to_i)
    matrix = get_empty_matrix(n, Float::INFINITY)
    (0..k - 1).each do |_|
      line = text.gets
      f, t, s = line.split(' ').map(&:to_i)
      f, t = f - 1, t - 1
      matrix[f][t] = s
      matrix[t][f] = s
    end
    matrix
  end
end

def get_empty_matrix(n, default)
  Array.new(n) { Array.new(n) }.
    each_with_index { |line, i| line.each_index { |j| line[j] = i == j ? 0 : default } }
end

def floyd(distance)
  n = distance.length - 1
  (0..n).each do |k|
    (0..n).each do |i|
      (0..n).each do |j|
        if distance[i][j] > distance[i][k] + distance[k][j]
          distance[i][j] = distance[i][k] + distance[k][j]
        end
      end
    end
  end
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
  (0..n).each do |i|
    maximum = distance[i].max
    if maximum < minimum
      minimum = maximum
      index = i
    end
  end
  result = "#{minimum} #{index + 1}"
  write_in_file('out.txt', result)
end

main
