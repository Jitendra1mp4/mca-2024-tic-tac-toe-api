package in.ac.prsu.mca.mca2025_tictactoeapi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
	private String errorCode ;
	private String errorMessage ;
	private String errorDescription ;
}
