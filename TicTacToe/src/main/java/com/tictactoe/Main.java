package com.tictactoe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import com.tictactoe.controller.GameController;
import com.tictactoe.models.Bot;
import com.tictactoe.models.BotDifficultyLevel;
import com.tictactoe.models.Game;
import com.tictactoe.models.GameState;
import com.tictactoe.models.Player;
import com.tictactoe.models.PlayerType;
import com.tictactoe.models.Symbol;
import com.tictactoe.models.strategies.winningstrategies.ColumnWinningStrategy;
import com.tictactoe.models.strategies.winningstrategies.DiagonalWinningStrategy;
import com.tictactoe.models.strategies.winningstrategies.RowWinningStrategy;
import com.tictactoe.models.strategies.winningstrategies.WinningStrategy;

public class Main {

	public static void main(String[] args) {
		GameController gameController = new GameController();

		try {
			List<WinningStrategy> winningStrategies = new ArrayList<>();
			winningStrategies.add(new RowWinningStrategy());
			winningStrategies.add(new ColumnWinningStrategy());
			winningStrategies.add(new DiagonalWinningStrategy());
			ArrayList<Player> players = new ArrayList<Player>();
			players.add(new Player(new Symbol('X'), "P1", 0, PlayerType.HUMAN));
			players.add(new Bot(new Symbol('O'), "P2", 1, BotDifficultyLevel.HARD));
			Game game = gameController.startGame(3, players, winningStrategies);
			while (gameController.getGameState(game).equals(GameState.IN_PROGRESS)) {
				System.out.println("###############");
				System.out.println("Board State Now");
				gameController.printBoard(game);
				if(!gameController.makeMove(game)) {
					continue;
				}
				System.out.println("Board after move");
				gameController.printBoard(game);
				
				if(gameController.getGameState(game).equals(GameState.IN_PROGRESS)) {
					gameController.undo(game);
				}

				System.out.println("###############");
			}
			System.out.println("Game Status " + game.getGameState());
			System.out.println("Winner is " + game.getWinner().getName());
			gameController.printBoard(game);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Something is wrong");
		}

	}

}
