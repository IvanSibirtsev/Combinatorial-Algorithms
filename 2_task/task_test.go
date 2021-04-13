package main

import (
	"testing"
)

func TestPositions(t *testing.T) {
	pawn := ChessFigure{6, 'e'}
	knight := ChessFigure{2, 'b'}
	dfs := DFS{}
	testChessBoard := makeChessBoard()
	dfs.run(testChessBoard, knight, pawn)
	expected := "b2 a4 b6 a8 c7 e8 f6 g8 h6 f7 h8 g6 f8 e6"
	assertEquals(dfs.result, expected, t)
}

func TestBoundaryPositions(t *testing.T) {
	knights := []ChessFigure{{1, 'a'}, {1, 'h'}}
	pawns := []ChessFigure{{8, 'h'}, {8, 'a'}}
	expected := []string{
		"a1 b3 a5 b7 d8 e6 f8 g6 h8",
		"h1 g3 f5 e7 g8 h6 f7 h8 g6 " +
			"f8 e6 d8 c6 b8 a6 c7 e8 " +
			"f6 h7 g5 h3 f4 h5 g4 e5 d7 " +
			"c5 d3 b4 d5 b6 a8"}
	for i := 0; i < 2; i++ {
		dfs := DFS{}
		testChessBoard := makeChessBoard()
		dfs.run(testChessBoard, knights[i], pawns[i])
		assertEquals(dfs.result, expected[i], t)
	}
}

func TestFromArtem(t *testing.T) {
	pawn := ChessFigure{5, 'b'}
	knight := ChessFigure{7, 'e'}
	testChessBoard := makeChessBoard()
	dfs := DFS{}
	dfs.run(testChessBoard, knight, pawn)
	expected := "e7 g8 h6 f7 h8 g6 f8 e6 d8 c6 " +
		"b8 a6 c7 e8 f6 h7 g5 h3 f4 h5 g7 f5 d6" +
		" c8 b6 d7 e5 f3 h4 g2 e3 d5 c3 b5"
	assertEquals(dfs.result, expected, t)
}

func TestGetFigure(t *testing.T) {
	line := "a6"
	figure := getFigure(line)
	assertEquals(figure.toString(), "a6", t)
}

func TestWriteRead(t *testing.T) {
	information := "a6"
	fileToTest := "test.txt"
	writeInFile(fileToTest, information)
	read := readFile(fileToTest)
	assertEquals(read, information, t)
}

func assertEquals(actual string, expected string, test *testing.T) {
	if actual != expected {
		test.Errorf("Fail\nactaul: %s\nexpected: %s", actual, expected)
	}
}
