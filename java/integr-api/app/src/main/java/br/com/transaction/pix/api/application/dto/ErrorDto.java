package br.com.transaction.pix.api.application.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String error;
}
