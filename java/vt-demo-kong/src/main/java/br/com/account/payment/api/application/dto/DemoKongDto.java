package br.com.account.payment.api.application.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class DemoKongDto implements Serializable {
	
    private static final long serialVersionUID = 1L;

    private String consumerId;
    private boolean validate;
    private String category;
    private String empresa;
    private String record;

}