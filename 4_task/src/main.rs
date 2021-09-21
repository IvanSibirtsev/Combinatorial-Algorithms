use std::fs;
use std::path::Path;


fn file_read(filename: String) -> String {
    return fs::read_to_string(filename)
        .expect("Something went wrong reading the file");
}

fn write_to_file(filename: &Path, answer: String) {
    fs::write(filename, answer)
        .expect("Something went wrong writing the file");
}

fn main() {
    let task_information = file_read(("in.txt").to_string());
    let mut adjacency_list: Vec<&str> = task_information.split("\r\n").collect();
    let n = adjacency_list.remove(0).parse::<usize>().unwrap();
    let mut edges = parse_to_edges(adjacency_list, n);
    let mut name: Vec<usize> = (0..n).collect();
    let mut size: Vec<i32> = (0..n).map(|_x| 1).collect();
    let mut next: Vec<usize> = (0..n).collect();
    let mut mst = Vec::<(usize, usize, usize)>::new();
    while mst.len() != n - 1 {
        let vw = edges.pop().unwrap();
        let (p, q) = (name[vw.0], name[vw.1]);
        if p != q {
            if size[p] > size[q] {
                merge(vw.0, vw.1, p, q, &mut name, &mut next, &mut size)
            }
            else {
                merge(vw.1, vw.0, q, p, &mut name, &mut next, &mut size)
            }
            mst.push(vw);
        }
    }

    write_to_file(Path::new("out.txt"), prepare_to_write(mst));
}

fn parse_to_edges(adjacency_list: Vec<&str>, n: usize) -> Vec<(usize, usize, usize)> {
    let mut edges = Vec::<(usize,usize, usize)>::new();
    for i in 0..n {
        let line = adjacency_list[i];
        let elements: Vec<usize> = line
            .split(' ')
            .map(|x| x.parse().unwrap())
            .collect();
        let current_edges: Vec<(usize, usize, usize)>  = elements
            .chunks_exact(2)
            .filter(|x| x[0] > i)
            .map(|x| (i, x[0] - 1, x[1]))
            .collect();
        edges.extend(current_edges);
    }
    edges.sort_by_key(|x| !x.2);
    return edges;
}

fn merge(v: usize, w: usize, p: usize, q: usize, name: &mut Vec<usize>, next: &mut Vec<usize>, size: &mut Vec<i32>) {
    fn recursion(u: usize, p: usize, name: &mut Vec<usize>, next: &Vec<usize>) {
        if name[u] == p {
            return;
        }
        name[u] = p;
        recursion(next[u], p, name, next)
    }
    name[w] = p;
    recursion(next[w], p, name, next);
    size[p] += size[q];
    let (x, y) = (next[v], next[w]);
    next[v] = y;
    next[w] = x;
}

fn prepare_to_write(mst: Vec<(usize, usize, usize)>) -> String {
    let mut mst_cost = 0;
    let mut final_string =  String::new();
    for i in 0..mst.len() {
        let mut line: Vec<(usize, usize, usize)> = mst.to_vec()
            .into_iter()
            .filter(|x| x.0 == i)
            .collect();
        line.sort_by_key(|x| x.1);
        for (_v1, v2, cost) in line {
            final_string.push_str(&format!("{} ", v2 + 1));
            mst_cost += cost;
        }
        final_string.push_str("0\n");
    }
    final_string.push_str(&format!("{}", mst_cost));
    return final_string;
}
