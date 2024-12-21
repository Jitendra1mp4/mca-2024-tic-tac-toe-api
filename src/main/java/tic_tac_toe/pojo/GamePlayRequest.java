package tic_tac_toe.pojo;

import lombok.Data;

@Data
public class GamePlayRequest {
	  private boolean startedByHuman ;
	  private int position ;
	  private int[][] gameState;
	
}
