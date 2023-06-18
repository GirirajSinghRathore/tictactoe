package com.tictactoe.models.strategies.botplayerstrategies;

import com.tictactoe.models.Board;
import com.tictactoe.models.Move;

public interface BotPlayingStrategy {

	Move makeMove(Board board);

}
