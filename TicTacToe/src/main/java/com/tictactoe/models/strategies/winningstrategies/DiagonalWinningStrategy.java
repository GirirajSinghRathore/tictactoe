package com.tictactoe.models.strategies.winningstrategies;

import java.util.HashMap;

import com.tictactoe.models.Board;
import com.tictactoe.models.Move;
import com.tictactoe.models.Symbol;

public class DiagonalWinningStrategy implements WinningStrategy {
	HashMap<Symbol, Integer> leftDiaCounts = new HashMap<>();
	HashMap<Symbol, Integer> rightDiaCounts = new HashMap<>();

	@Override
	public boolean checkWinner(Board board, Move move) {
		Symbol symbol = move.getCell().getPlayer().getSymbol();
		leftDiaCounts.putIfAbsent(symbol, 0);
		rightDiaCounts.putIfAbsent(symbol, 0);
		Integer row = move.getCell().getRow();
		Integer col = move.getCell().getColumn();
		if(row==col) {
			leftDiaCounts.put(symbol, leftDiaCounts.get(symbol)+1);
		}
		if(row+col==board.getSize()-1) {
			rightDiaCounts.put(symbol, rightDiaCounts.get(symbol)+1);
		}
		if(leftDiaCounts.get(symbol).equals(board.getSize())) {
			System.out.println("Won by leftDiaWinningStrategy");
			return true;
		}
		if(rightDiaCounts.get(symbol).equals(board.getSize())) {
			System.out.println("Won by rightWinningStrategy");
			return true;
		}
		return false;
	}

	@Override
	public void undo(Board board,Move move) {
		Symbol symbol = move.getPlayer().getSymbol();
		Integer row = move.getCell().getRow();
		Integer col = move.getCell().getColumn();
		if(row==col) {
			leftDiaCounts.put(symbol, leftDiaCounts.get(symbol)-1);
		}
		if(row+col==board.getSize()-1) {
			rightDiaCounts.put(symbol, rightDiaCounts.get(symbol)-1);
		}
	}

}
