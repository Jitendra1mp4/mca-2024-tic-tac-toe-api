package in.ac.prsu.mca.mca2025_tictactoeapi.pojo;

import lombok.Data;

@Data
public class NewGameRequest {
  private boolean startByHuman ;
  private int position ;
}
