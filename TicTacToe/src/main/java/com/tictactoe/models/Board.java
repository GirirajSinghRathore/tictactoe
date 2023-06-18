package com.tictactoe.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
	
	private int size;
	private List<List<Cell>> board;
	public Board(int dimentions) {
		this.size=dimentions;
		this.board=new ArrayList<List<Cell>>();
		for(int i=0;i<dimentions;i++) {
			board.add(new ArrayList<Cell>());
			for(int j=0;j<dimentions;j++) {
				board.get(i).add(new Cell(i,j));
			}
		}
	}
	public void printBoard() {
		for(List<Cell> row : board) {
			for(Cell cell : row) {
				System.out.print(" | "+cell.printCell(cell));
			}
			System.out.println(" | ");
		}
	}
}
