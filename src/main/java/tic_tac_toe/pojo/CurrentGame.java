package tic_tac_toe.pojo;

import lombok.Data;

@Data
public class CurrentGame {
	private int lastSetPosition;
	private int lastSetSymbol;
	private int newSetPosition;
	private int newSetSymbol;
	private boolean startByHuman;
	private boolean isWinningState;
	private String winner;
	private GameState previousState;
	private GameState newState;
}
