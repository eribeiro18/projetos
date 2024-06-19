package br.com.account.payment.api.infraestructure.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.account.payment.api.application.dto.AccountPaymentDto;
import br.com.account.payment.api.application.dto.Response;
import br.com.account.payment.api.application.service.AccountPaymentService;
import br.com.account.payment.api.builder.AccountPaymentBuilder;
import br.com.account.payment.api.infraestructure.entity.AccountPayment;

public class AccountPaymentControllerTest {
	
    private MockMvc mockMvc;

    @Mock
    private AccountPaymentService accountPaymentService;

    private static final String URL_BASE = "/v1/account-payment";

    private static final String TOKEN = "teste";
    
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @BeforeEach
    public void setup() {
    	OBJECT_MAPPER.registerModule(new JavaTimeModule());
        MockitoAnnotations.openMocks(this);
        AccountPaymentController supplierController = new AccountPaymentController(accountPaymentService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(supplierController).build();
    }

    @Test
    public void createTest() throws Exception {
        when(accountPaymentService.save(any())).thenReturn(AccountPaymentBuilder.build());

        String request = OBJECT_MAPPER.writeValueAsString(AccountPaymentBuilder.buildDto());
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post(URL_BASE)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .header("Authorization", "Bearer " + TOKEN)
                                    .content(request))
                                    .andExpect(MockMvcResultMatchers.status().isOk())
                                .andReturn();
        assertWasCreated(response);
    }

    @Test
    public void updateTest() throws Exception {
        when(accountPaymentService.update(any())).thenReturn(AccountPaymentBuilder.build());

        AccountPaymentDto accountPaymentDto = AccountPaymentBuilder.buildDto();
        accountPaymentDto.setId(2L);
        String request = OBJECT_MAPPER.writeValueAsString(accountPaymentDto);
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.put(URL_BASE)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .header("Authorization", "Bearer " + TOKEN)
                                    .content(request))
                                    .andExpect(MockMvcResultMatchers.status().isOk())
                                .andReturn();
        assertWasCreated(response);
    }
    
    @Test
    public void patchTest() throws Exception {
        when(accountPaymentService.update(any())).thenReturn(AccountPaymentBuilder.build());

        AccountPaymentDto accountPaymentDto = AccountPaymentBuilder.buildDto();
        accountPaymentDto.setSituation(AccountPayment.Status.PAYMENT_MADE);;
        String request = OBJECT_MAPPER.writeValueAsString(accountPaymentDto);
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.patch(URL_BASE)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .header("Authorization", "Bearer " + TOKEN)
                                    .content(request))
                                    .andExpect(MockMvcResultMatchers.status().isOk())
                                .andReturn();
        assertWasCreated(response);
    }

    @Test
    public void findByTest() throws Exception {
        when(accountPaymentService.findBy(any())).thenReturn(Optional.of(AccountPaymentBuilder.build()));

        mockMvc.perform(MockMvcRequestBuilders.get(URL_BASE+"/1")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .header("Authorization", "Bearer {accessToken}"))
                                    .andExpect(MockMvcResultMatchers.status().isOk())
                                .andReturn();
        verify(accountPaymentService, times(1)).findBy(any());
    }

    @Test
    public void findByNotFoundTest() throws Exception {
        when(accountPaymentService.findBy(any())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get(URL_BASE+"/1")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .header("Authorization", "Bearer {accessToken}"))
                                    .andExpect(MockMvcResultMatchers.status().isNotFound())
                                .andReturn();
        verify(accountPaymentService, times(1)).findBy(any());
    }

    private void assertWasCreated(MvcResult mvcResult) throws Exception {
        Response response = OBJECT_MAPPER.readValue(mvcResult.getResponse().getContentAsString(), Response.class);
        assertNotNull(response.getResponse());
    }

}
