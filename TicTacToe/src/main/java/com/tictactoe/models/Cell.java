package com.tictactoe.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cell {
	
	private int row;
	private int column;
	
	private CellState cellState;
	private Player player;
	public Cell(int row, int column) {
		super();
		this.row = row;
		this.column = column;
		this.cellState=CellState.EMPTY;
	}
	public char printCell(Cell cell) {
		if(cell.getPlayer()==null) {
			return '-';
		}
		return cell.getPlayer().getSymbol().getAChar();
	}
}
