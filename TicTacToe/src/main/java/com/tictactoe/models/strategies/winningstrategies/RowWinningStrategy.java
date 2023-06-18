package com.tictactoe.models.strategies.winningstrategies;

import java.util.HashMap;

import com.tictactoe.models.Board;
import com.tictactoe.models.Move;
import com.tictactoe.models.Symbol;

public class RowWinningStrategy implements WinningStrategy {

	HashMap<Integer, HashMap<Symbol, Integer>> hm = new HashMap<Integer, HashMap<Symbol, Integer>>();

	public boolean checkWinner(Board board, Move move) {
		int row = move.getCell().getRow();
		hm.putIfAbsent(row, new HashMap<Symbol, Integer>());
		HashMap<Symbol, Integer> rowMap = hm.get(row);
		Symbol symbol = move.getCell().getPlayer().getSymbol();
		rowMap.computeIfAbsent(symbol, k -> 0);

		rowMap.put(symbol, rowMap.get(symbol) + 1);
		if (rowMap.get(symbol) == board.getSize()) {
			System.out.println("Won by RowWinningStrategy");
			return true;
		}
		return false;

	}

	@Override
	public void undo(Board board,Move move) {
		int row = move.getCell().getRow();
		HashMap<Symbol, Integer> rowMap = hm.get(row);
		Symbol symbol = move.getPlayer().getSymbol();

		rowMap.put(symbol, rowMap.get(symbol) - 1);
	}

}
