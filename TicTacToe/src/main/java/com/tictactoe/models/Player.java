package com.tictactoe.models;

import java.util.Scanner;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
	private Symbol symbol;
	private String name;
	private int id;
	private PlayerType playerType;
	private Scanner sc = new Scanner(System.in); 
	public Player(Symbol symbol, String name, int id, PlayerType playerType) {
		super();
		this.symbol = symbol;
		this.name = name;
		this.id = id;
		this.playerType = playerType;
	}
	
	public Move makeMove(Board board) {
		
		System.out.println("insert row");
		int r = sc.nextInt();
		System.out.println("insert column");
		int c = sc.nextInt();
		return new Move(new Cell(r,c),this);
	}
}
