package in.ac.prsu.mca.mca2025_tictactoeapi.pojo;

import lombok.Data;

@Data
public class GamePlayRequest {
	  private boolean startedByHuman ;
	  private int position ;
	  private int[][] gameState;
	
}
