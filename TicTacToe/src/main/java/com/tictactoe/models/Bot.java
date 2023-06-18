package com.tictactoe.models;

import com.tictactoe.models.strategies.botplayerstrategies.BotPlayingStrategy;
import com.tictactoe.models.strategies.botplayerstrategies.BotPlayingStrategyFactory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bot extends Player{
	

	private BotDifficultyLevel botDifficultyLevel;
	private BotPlayingStrategy botPlayingStrategy;
	public Bot(Symbol symbol, String name, int id,  BotDifficultyLevel botDifficultyLevel) {
		super(symbol, name, id, PlayerType.BOT);
		this.botDifficultyLevel = botDifficultyLevel;
		botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(botDifficultyLevel);
	}
	@Override
	public Move makeMove(Board board) {
		Move move =  botPlayingStrategy.makeMove(board);
		move.setPlayer(this);
		return move;
	}
	
}
