package com.tictactoe.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Move {
	private Cell cell;
	private Player player;
	public Move(Cell cell, Player player) {
		super();
		this.cell = cell;
		this.player = player;
	}

}
