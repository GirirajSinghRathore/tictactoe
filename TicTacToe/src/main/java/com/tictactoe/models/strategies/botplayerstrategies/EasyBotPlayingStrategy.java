package com.tictactoe.models.strategies.botplayerstrategies;

import java.util.Iterator;
import java.util.List;

import com.tictactoe.models.Board;
import com.tictactoe.models.Cell;
import com.tictactoe.models.CellState;
import com.tictactoe.models.Move;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {

	public Move makeMove(Board board) {
		for(List<Cell> row : board.getBoard()) {
			for (Cell cell : row) {
				if(cell.getCellState().equals(CellState.EMPTY)) {
					return new Move(cell, null);
				}
			}
		}
		return null;
	}

}
