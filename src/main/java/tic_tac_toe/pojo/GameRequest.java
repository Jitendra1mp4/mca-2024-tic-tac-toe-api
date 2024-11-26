package tic_tac_toe.pojo;

import lombok.Data;

@Data
public class GameRequest {
  private boolean startByHuman ;
  private int position ;
}
