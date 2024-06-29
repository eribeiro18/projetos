package br.com.account.payment.api.infraestructure.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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
import com.google.gson.Gson;

import br.com.account.payment.api.application.dto.AccountPaymentDto;
import br.com.account.payment.api.application.dto.Response;
import br.com.account.payment.api.application.dto.UserJwtDto;
import br.com.account.payment.api.application.service.AccountPaymentService;
import br.com.account.payment.api.application.service.UserService;
import br.com.account.payment.api.builder.AccountPaymentBuilder;
import br.com.account.payment.api.builder.UserBuilder;
import br.com.account.payment.api.infraestructure.entity.AccountPayment;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AccountPaymentControllerTest {
	
    private MockMvc mockMvc;

    @Mock
    private AccountPaymentService accountPaymentService;
    
    @Mock
    private UserService userService;

    private static final String URL_BASE = "/v1/account-payment";

    private String token;
    
    private static final long EXPIRATION_TIME = TimeUnit.MINUTES.toMillis(15);
    private static final String SECRET = "ThisIsASecret";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Gson GSON = new Gson();;

    @BeforeEach
    public void setup() {
    	token = this.buildToken("validacao");
    	OBJECT_MAPPER.registerModule(new JavaTimeModule());
        MockitoAnnotations.openMocks(this);
        AccountPaymentController supplierController = new AccountPaymentController(accountPaymentService, userService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(supplierController).build();
    }
    
    private String buildToken(String username) {
        String JWT = Jwts.builder()
                .setSubject(buildUserJwt(username))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return JWT;
    }

    private String buildUserJwt(String username) {
         return GSON.toJson(UserJwtDto.builder()
                    .username(username)
                    .build());
    }

    @Test
    public void createTest() throws Exception {
        when(accountPaymentService.save(any())).thenReturn(AccountPaymentBuilder.build());
        when(userService.findByUsername(any())).thenReturn(UserBuilder.build());
        
        String request = OBJECT_MAPPER.writeValueAsString(AccountPaymentBuilder.buildDto());
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post(URL_BASE)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .header("Authorization", "Bearer " + token)
                                    .content(request))
                                    .andExpect(MockMvcResultMatchers.status().isOk())
                                .andReturn();
        assertWasCreated(response);
    }

    @Test
    public void updateTest() throws Exception {
        when(accountPaymentService.update(any())).thenReturn(AccountPaymentBuilder.build());
        when(userService.findByUsername(any())).thenReturn(UserBuilder.build());
        
        AccountPaymentDto accountPaymentDto = AccountPaymentBuilder.buildDto();
        accountPaymentDto.setId(2L);
        String request = OBJECT_MAPPER.writeValueAsString(accountPaymentDto);
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.put(URL_BASE)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .header("Authorization", "Bearer " + token)
                                    .content(request))
                                    .andExpect(MockMvcResultMatchers.status().isOk())
                                .andReturn();
        assertWasCreated(response);
    }
    
    @Test
    public void patchTest() throws Exception {
        when(accountPaymentService.update(any())).thenReturn(AccountPaymentBuilder.build());
        when(userService.findByUsername(any())).thenReturn(UserBuilder.build());
        
        AccountPaymentDto accountPaymentDto = AccountPaymentBuilder.buildDto();
        accountPaymentDto.setSituation(AccountPayment.Status.PAYMENT_MADE);;
        String request = OBJECT_MAPPER.writeValueAsString(accountPaymentDto);
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.patch(URL_BASE)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .header("Authorization", "Bearer " + token)
                                    .content(request))
                                    .andExpect(MockMvcResultMatchers.status().isOk())
                                .andReturn();
        assertWasCreated(response);
    }

    @Test
    public void findByTest() throws Exception {
        when(accountPaymentService.findBy(any())).thenReturn(Optional.of(AccountPaymentBuilder.build()));
        when(userService.findByUsername(any())).thenReturn(UserBuilder.build());
        
        mockMvc.perform(MockMvcRequestBuilders.get(URL_BASE+"/1")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .header("Authorization", "Bearer " + token))
                                    .andExpect(MockMvcResultMatchers.status().isOk())
                                .andReturn();
        verify(accountPaymentService, times(1)).findBy(any());
    }

    @Test
    public void findByNotFoundTest() throws Exception {
        when(accountPaymentService.findBy(any())).thenReturn(Optional.empty());
        when(userService.findByUsername(any())).thenReturn(UserBuilder.build());
        
        mockMvc.perform(MockMvcRequestBuilders.get(URL_BASE+"/1")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .header("Authorization", "Bearer " + token))
                                    .andExpect(MockMvcResultMatchers.status().isNotFound())
                                .andReturn();
        verify(accountPaymentService, times(1)).findBy(any());
    }

    private void assertWasCreated(MvcResult mvcResult) throws Exception {
        Response response = OBJECT_MAPPER.readValue(mvcResult.getResponse().getContentAsString(), Response.class);
        assertNotNull(response.getResponse());
    }
}
