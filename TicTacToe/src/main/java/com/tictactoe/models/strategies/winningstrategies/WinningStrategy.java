package com.tictactoe.models.strategies.winningstrategies;

import com.tictactoe.models.Board;
import com.tictactoe.models.Move;

public interface WinningStrategy {
	boolean checkWinner(Board board,Move move);

	void undo(Board board, Move lastMove);
}
