package br.com.transaction.pix.api.infraestructure.controller;

import static br.com.transaction.pix.api.application.functions.AccPaySerialize.accountPaymentSerialize;
import static br.com.transaction.pix.api.application.functions.AccPaySerialize.accountPaymentSerializeList;

import java.util.Optional;

import br.com.transaction.pix.api.application.dto.PixDto;
import br.com.transaction.pix.api.infraestructure.entity.Pix;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.transaction.pix.api.application.dto.Response;
import br.com.transaction.pix.api.application.service.PixService;
import br.com.transaction.pix.api.infraestructure.controller.commons.CommonsController;
import br.com.transaction.pix.api.infraestructure.controller.filter.AccountPaymentFilterBuilder;
import br.com.account.payment.api.infraestructure.repository.filter.data.PixFilter;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping(path = "/v1/account-payment")
public class AccountPaymentController extends CommonsController{

	private final PixService pixService;

	public AccountPaymentController(PixService pixService) {
		this.pixService = pixService;
	}

	@PostMapping
	public ResponseEntity<Response> create(@Validated @RequestBody PixDto request,
										   @RequestHeader("Authorization") String authorization) {
		validateAuthor(authorization);
		return buildResponse(HttpStatus.OK, Optional.of(accountPaymentSerialize.apply(pixService.save(request))));
	}

	@PutMapping
	public ResponseEntity<Response> update(@Validated @RequestBody PixDto request,
										   @RequestHeader("Authorization") String authorization) {
		validateAuthor(authorization);
		return buildResponse(HttpStatus.OK, Optional.of(accountPaymentSerialize.apply(pixService.update(request))));
	}

	@PatchMapping
	public ResponseEntity<Response> status(@RequestBody PixDto request,
										   @RequestHeader("Authorization") String authorization) {
		validateAuthor(authorization);
		return buildResponse(HttpStatus.OK, Optional.of(accountPaymentSerialize.apply(pixService.update(request))));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response> delete(@PathVariable Long id,
								    @RequestHeader("Authorization") String authorization) {
		validateAuthor(authorization);
		pixService.delete(id);
		return buildResponse(HttpStatus.OK, Optional.of(id));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> findBy(@RequestHeader("Authorization") String authorization,
										   @PathVariable("id") Long id) {
		validateAuthor(authorization);
		Optional<Pix> accountPayment = pixService.findBy(id);
		if (accountPayment.isPresent()) {
			return buildResponse(HttpStatus.OK, Optional.of(accountPaymentSerialize.apply(accountPayment.get())));
		}
		return buildResponse(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/all")
	public ResponseEntity<Response> findAll(@RequestHeader("Authorization") String authorization, 
											HttpServletRequest request, Pageable pageable) {
		validateAuthor(authorization);
		PixFilter filters = AccountPaymentFilterBuilder.create(request);
		Page<Pix> accountPaymentList = pixService.findAllByFilter(filters, pageable);
		if (accountPaymentList.hasContent()) {
			return buildResponse(HttpStatus.OK, Optional.of(accountPaymentSerializeList.apply(accountPaymentList.getContent())),
																							  accountPaymentList.getTotalPages(),
																							  accountPaymentList.getTotalElements());
		}
		return buildResponse(HttpStatus.NOT_FOUND);
	}
}
