package tic_tac_toe.pojo;

import lombok.Data;

@Data
public class NewGameRequest {
  private boolean startByHuman ;
  private int position ;
}
