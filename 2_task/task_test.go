package main

import "testing"

func TestPositions(t *testing.T) {
	pawn := ChessFigure{6, 'e'}
	knight := ChessFigure{2, 'b'}
	dfs := DFS{}
	testChessBoard := makeChessBoard()
	dfs.run(testChessBoard, knight, pawn)
	expected := "b2 a4 b6 a8 c7 e8 g7 h5 g3 h1 f2 e4 d6 c8 e7 g8 h6 g4 f6 h7 g5 f7 h8 g6 f8 e6"
	assertEquals(dfs.result, expected, t)
}

func TestFromArtem(t *testing.T) {
	pawn := ChessFigure{5, 'b'}
	knight := ChessFigure{7, 'e'}
	testChessBoard := makeChessBoard()
	dfs := DFS{}
	dfs.run(testChessBoard, knight, pawn)
	expected := "e7 g8 h6 g4 f6 e8 g7 h5 g3 f5 e7 g6 f8 h7 g5 f7 e5 d7 " +
		"c5 b7 d8 e6 f4 h3 g1 f3 h4 g2 e1 d3 f2 e4 d6 c8 b6 a8 c7 d5 " +
		"e3 f1 d2 b1 a3 b5"
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
