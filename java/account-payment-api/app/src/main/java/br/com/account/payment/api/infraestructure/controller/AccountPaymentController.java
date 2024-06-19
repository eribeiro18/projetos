package br.com.account.payment.api.infraestructure.controller;

import static br.com.account.payment.api.application.functions.AccPaySerialize.accountPaymentSerialize;
import static br.com.account.payment.api.application.functions.AccPaySerialize.accountPaymentSerializeList;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import br.com.account.payment.api.application.dto.AccountPaymentDto;
import br.com.account.payment.api.application.dto.Response;
import br.com.account.payment.api.application.service.AccountPaymentService;
import br.com.account.payment.api.infraestructure.controller.commons.CommonsController;
import br.com.account.payment.api.infraestructure.controller.filter.AccountPaymentFilterBuilder;
import br.com.account.payment.api.infraestructure.entity.AccountPayment;
import br.com.account.payment.api.infraestructure.repository.filter.data.AccountPaymentFilter;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping(path = "/v1/account-payment")
public class AccountPaymentController extends CommonsController{

	private final AccountPaymentService accountPaymentService;

	public AccountPaymentController(AccountPaymentService accountPaymentService) {
		this.accountPaymentService = accountPaymentService;
	}

	@PostMapping
	public ResponseEntity<Response> create(@Validated @RequestBody AccountPaymentDto request,
										   @RequestHeader("Authorization") String authorization) {
//		addAuthor(ControllerType.POST, request, authorization);
		return buildResponse(HttpStatus.OK, Optional.of(accountPaymentSerialize.apply(accountPaymentService.save(request))));
	}
	
	@PostMapping(path = "/import-csv", produces = { "application/json" }, consumes = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<Response> importCsv(@RequestHeader("Authorization") String authorization,
 										      @RequestBody String request) {
//		addAuthor(ControllerType.POST, request, authorization);
		Optional<String> opt = this.accountPaymentService.importCsv(request);
		if(opt.isPresent()) {
			return buildResponse(HttpStatus.OK, opt);
		}
		return buildResponse(HttpStatus.BAD_REQUEST, Optional.of("Arquivo em branco, invalido ou fora do formato esperado!"));
	}

	@PutMapping
	public ResponseEntity<Response> update(@Validated @RequestBody AccountPaymentDto request,
										   @RequestHeader("Authorization") String authorization) {
//		addAuthor(ControllerType.PUT, request, authorization);
		return buildResponse(HttpStatus.OK, Optional.of(accountPaymentSerialize.apply(accountPaymentService.update(request))));
	}

	@PatchMapping
	public ResponseEntity<Response> status(@RequestBody AccountPaymentDto request,
										   @RequestHeader("Authorization") String authorization) {
//		addAuthor(ControllerType.PUT, request, authorization);
		return buildResponse(HttpStatus.OK, Optional.of(accountPaymentSerialize.apply(accountPaymentService.update(request))));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response> delete(@PathVariable Long id,
								    @RequestHeader("Authorization") String authorization) {
		accountPaymentService.delete(id);
		return buildResponse(HttpStatus.OK, Optional.of(id));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> findBy(@PathVariable("id") Long id) {
		Optional<AccountPayment> accountPayment = accountPaymentService.findBy(id);
		if (accountPayment.isPresent()) {
			return buildResponse(HttpStatus.OK, Optional.of(accountPaymentSerialize.apply(accountPayment.get())));
		}
		return buildResponse(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/all")
	public ResponseEntity<Response> findAll(HttpServletRequest request, Pageable pageable) {
		AccountPaymentFilter filters = AccountPaymentFilterBuilder.create(request);
		Page<AccountPayment> accountPaymentList = accountPaymentService.findAllByFilter(filters, pageable);
		if (accountPaymentList.hasContent()) {
			return buildResponse(HttpStatus.OK, Optional.of(accountPaymentSerializeList.apply(accountPaymentList.getContent())),
																							  accountPaymentList.getTotalPages(),
																							  accountPaymentList.getTotalElements());
		}
		return buildResponse(HttpStatus.NOT_FOUND);
	}
}
