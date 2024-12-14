package br.com.transaction.pix.api.application.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Response implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<?> response;
	private Integer totalPages;
	private Long totalRecords;
	private List<ErrorDto> errors;
	
}
