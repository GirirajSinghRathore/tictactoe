package com.tictactoe.models.strategies.botplayerstrategies;

import com.tictactoe.models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
	public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel botDifficultyLevel) {
		return new EasyBotPlayingStrategy();
	}
}
