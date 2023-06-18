package com.tictactoe.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.tictactoe.exception.MoreThanOneBotExeption;
import com.tictactoe.exception.PlayersCountDimentionsMismatchExcetion;
import com.tictactoe.exception.SymbolMismatchException;
import com.tictactoe.models.strategies.winningstrategies.WinningStrategy;

import lombok.Getter;
import lombok.Setter;
@Getter
public class Game {
	private List<Player> players;
	private List<Move> moves;
	private Player winner;
	private GameState gameState;
	private int nextMovePlayerIndex;
	private List<WinningStrategy> winningStrategies;
	private int dimentions;
	private Board board;
	private Scanner sc = new Scanner(System.in);
	public Game(Builder builder) {
		this.players = builder.players;
		this.winningStrategies = builder.winningStrategies;
		this.nextMovePlayerIndex = 0;
		this.gameState = GameState.IN_PROGRESS;
		this.moves = new ArrayList<Move>();
		this.board = new Board(builder.dimentions);
	}

	public static Builder getBuilder()

	{
		return new Builder();
	}
	public void printBoard() {
		board.printBoard();
	}
	 public static class Builder {
		private List<Player> players;
		private List<WinningStrategy> winningStrategies;
		private int dimentions;

		private Builder() {
			this.players = new ArrayList<Player>();
			this.winningStrategies = new ArrayList<WinningStrategy>();
		}

		public Builder setDimentions(int dimentions) {
			this.dimentions = dimentions;
			return this;
		}

		public Builder setPlayers(List<Player> players) {
			this.players = players;
			return this;
		}

		public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
			this.winningStrategies = winningStrategies;
			return this;
		}

		public Builder addPlayer(Player player) {
			this.players.add(player);
			return this;
		}

		public Builder addWinningStrategies(WinningStrategy winningStrategy) {
			this.winningStrategies.add(winningStrategy);
			return this;
		}

		public Game build() throws Exception {
			validate();
			return new Game(this);
		}

		private boolean validate() throws Exception {
			int botCount = 0;
			for (Player player : players) {
				if (player.getPlayerType().equals(PlayerType.BOT)) {
					botCount++;
				}
			}
			if (botCount > 1) {
				throw new MoreThanOneBotExeption();
			}
			if (players.size() != dimentions - 1) {
				throw new PlayersCountDimentionsMismatchExcetion();
			}
			Set<Character> symbolsSet = new HashSet<Character>();
			for (Player player : players) {
				symbolsSet.add(player.getSymbol().getAChar());
			}
			if (symbolsSet.size() != players.size()) {
				throw new SymbolMismatchException();
			}
			return true;
		}
	}
	public boolean makeMove() {
		Player currentPlayer = players.get(this.nextMovePlayerIndex);
		System.out.println("It's "+ currentPlayer.getName()+"'s turn");
		Move m = currentPlayer.makeMove(this.board);
		if(!validateMove(m)) {
			System.out.println("Invalid Move for"+m.getCell().getRow()+" "+m.getCell().getColumn());
			return false;
		}
		int row = m.getCell().getRow();
		int column = m.getCell().getColumn();
		Cell cellToChange = board.getBoard().get(row).get(column);
		cellToChange.setPlayer(currentPlayer);
		cellToChange.setCellState(CellState.FILLED);
		Move finalMoveObject = new Move(cellToChange, currentPlayer);
		moves.add(finalMoveObject);
		nextMovePlayerIndex++;
		nextMovePlayerIndex%=players.size();
		if(CheckWinner(finalMoveObject)) {
			gameState = GameState.WIN;
			winner=currentPlayer;
		}else if(moves.size()==this.board.getSize()*this.board.getSize()) {
			gameState=GameState.DRAW;
		}
		return true;
		
	}
	private boolean CheckWinner(Move move) {
		for(WinningStrategy winningStrategy : winningStrategies) {
			if(winningStrategy.checkWinner(board, move)) {
				return true;
			}
		}
		return false;
	}
	private boolean validateMove(Move move) {
		int row = move.getCell().getRow();
		int column = move.getCell().getColumn();
		if(row>=board.getSize()||row<0||column>=board.getSize()||column<0) {
			return false;
		}
		if(board.getBoard().get(row).get(column).getCellState().equals(CellState.EMPTY)) {
			return true;
		}
		return false;
	}

	public void undo() {
		Move lastMove = moves.get(moves.size()-1);
		if(!lastMove.getPlayer().getPlayerType().equals(PlayerType.HUMAN)) {
			return;
		}
		System.out.println("Do you want to undo operation? ");
		
		
		String undoOrNot = sc.next();
		if(!undoOrNot.equals("y")) {return;}
		moves.remove(lastMove);
		int row = lastMove.getCell().getRow();
		int column = lastMove.getCell().getColumn();
		for(WinningStrategy winningStrategy : winningStrategies) {
			winningStrategy.undo(board,lastMove);
		}
		Cell cellToChange = board.getBoard().get(row).get(column);
		cellToChange.setPlayer(null);
		cellToChange.setCellState(CellState.EMPTY);
		nextMovePlayerIndex--;
		nextMovePlayerIndex=(nextMovePlayerIndex + players.size())%players.size();
		
	}

	

}
