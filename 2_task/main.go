package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

type Delta struct {
	number int
	letter int32
}

var deltas = []Delta{
	{2, -1}, {2, 1}, {1, 2}, {-1, 2},
	{-2, 1}, {-2, -1}, {-1, -2}, {1, -2}}

type ChessFigure struct {
	number int
	letter int32
}

func (chessFigure ChessFigure) inBoard() bool {
	return chessFigure.number <= 8 && chessFigure.letter <= 104 && chessFigure.number >= 1 && chessFigure.letter >= 97
}

func (chessFigure ChessFigure) toString() string {
	return fmt.Sprintf("%s%d", string(chessFigure.letter), chessFigure.number)
}

func (chessFigure ChessFigure) isInDanger(pawn ChessFigure) bool {
	pawnAttackLeft := ChessFigure{pawn.number - 1, pawn.letter - 1}
	pawnAttackRight := ChessFigure{pawn.number - 1, pawn.letter + 1}
	return isInOneCell(chessFigure, pawnAttackLeft) || isInOneCell(chessFigure, pawnAttackRight)
}

type DFS struct {
	result string
}

func (dfs *DFS) run(visited []map[int32]bool, knight ChessFigure, pawn ChessFigure) bool {
	if isInOneCell(knight, pawn) {
		dfs.result = pawn.toString()
		return true
	}
	for _, delta := range deltas {
		knightAfterMove := ChessFigure{knight.number + delta.number, knight.letter + delta.letter}
		if !isValidPosition(knightAfterMove, pawn) || visited[knightAfterMove.number][knightAfterMove.letter] {
			continue
		}
		visited[knightAfterMove.number][knightAfterMove.letter] = true
		if dfs.run(visited, knightAfterMove, pawn) {
			dfs.result = knight.toString() + " " + dfs.result
			return true
		}
	}

	return false
}

func isValidPosition(knight ChessFigure, pawn ChessFigure) bool {
	return knight.inBoard() && !knight.isInDanger(pawn)
}

func isInOneCell(knight ChessFigure, pawn ChessFigure) bool {
	return knight.number == pawn.number && knight.letter == pawn.letter
}

func readFile(filename string) string {
	data, err := ioutil.ReadFile(filename)
	checkError(err)
	return string(data)
}

func writeInFile(filename string, result string) {
	count := strings.Count(result, " ")
	lines := strings.Replace(result, " ", "\n", count)
	err := ioutil.WriteFile(filename, []byte(lines), 0644)
	checkError(err)
}

func getFigure(line string) ChessFigure {
	letter := int32(line[0])
	number, _ := strconv.Atoi(line[1:])
	figure := ChessFigure{number, letter}
	return figure
}

func makeChessBoard() []map[int32]bool {
	matrix := make([]map[int32]bool, 9)
	for i := 1; i <= 8; i++ {
		matrix[i] = make(map[int32]bool, 8)
		for _, char := range "abcdefgh" {
			matrix[i][char] = false
		}
	}
	return matrix
}

func main() {
	input := readFile("in.txt")
	lines := strings.Split(input, "\r\n")
	knight := getFigure(lines[0])
	pawn := getFigure(lines[1])
	chessBoard := makeChessBoard()
	dfs := DFS{}
	dfs.run(chessBoard, knight, pawn)
	writeInFile("out.txt", dfs.result)
}

func checkError(err error) {
	if err != nil {
		fmt.Println(fmt.Errorf("error: %s", err))
		os.Exit(1)
	}
}
