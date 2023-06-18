package com.tictactoe.models;

import lombok.Getter;

@Getter
public class Symbol {
	public Symbol(char aChar) {
		super();
		this.aChar = aChar;
	}

	private char aChar;
}
