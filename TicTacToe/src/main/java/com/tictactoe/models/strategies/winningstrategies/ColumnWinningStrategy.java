package com.tictactoe.models.strategies.winningstrategies;

import java.util.HashMap;

import com.tictactoe.models.Board;
import com.tictactoe.models.Move;
import com.tictactoe.models.Symbol;

public class ColumnWinningStrategy implements WinningStrategy {
	HashMap<Integer,HashMap<Symbol,Integer>> hm = new HashMap<Integer, HashMap<Symbol,Integer>>();
	public boolean checkWinner(Board board, Move move) {
		int col = move.getCell().getColumn();
		hm.putIfAbsent(col,new HashMap<Symbol, Integer>());
		HashMap<Symbol, Integer> colMap=hm.get(col);
		Symbol symbol= move.getCell().getPlayer().getSymbol();
		colMap.computeIfAbsent(symbol,k->0);
		
		colMap.put(symbol	, colMap.get(symbol)+1);
		if(colMap.get(symbol)==board.getSize()) {
			System.out.println("Won By ColumnWinningStrategy");
			return true;
		}
		return false;
		
	}
	@Override
	public void undo(Board board,Move move) {
		int col = move.getCell().getColumn();
		HashMap<Symbol, Integer> colMap=hm.get(col);
		Symbol symbol= move.getPlayer().getSymbol();
		colMap.put(symbol	, colMap.get(symbol)-1);
	}
	

}
