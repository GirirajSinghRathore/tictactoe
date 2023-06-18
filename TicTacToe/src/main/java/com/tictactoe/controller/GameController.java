package com.tictactoe.controller;

import java.util.List;

import com.tictactoe.models.Game;
import com.tictactoe.models.GameState;
import com.tictactoe.models.Player;
import com.tictactoe.models.strategies.winningstrategies.WinningStrategy;

public class GameController {
	public Game startGame(int dimentionsOfBoard,List<Player> players,List<WinningStrategy> winningStrategies ) throws Exception {
		 return Game.getBuilder().setDimentions(dimentionsOfBoard).setPlayers(players).setWinningStrategies(winningStrategies).build();
		
	}
	public boolean makeMove(Game game) {
		return game.makeMove();
	}
	
	void checkStatus(Game game) {
		
	}
	public GameState getGameState(Game game) {
		return game.getGameState();
	}
	public void printBoard(Game game) {
		game.printBoard();
		
	}
	public void undo(Game game) {
		game.undo();
	}
}
