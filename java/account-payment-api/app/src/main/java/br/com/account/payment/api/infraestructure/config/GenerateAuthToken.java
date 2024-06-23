package br.com.account.payment.api.infraestructure.config;

import java.io.IOException;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.account.payment.api.application.dto.UserContextDto;
import br.com.account.payment.api.application.service.UserService;
import br.com.account.payment.api.infraestructure.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class GenerateAuthToken {

    static final long EXPIRATION_TIME = 86400000;
    static final String SECRET = "ThisIsASecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";
    private final UserService userService;
    private final Gson gson;

    public GenerateAuthToken(UserService userService) {
        this.userService = userService;
        this.gson = new Gson();
    }

    public void addAuthentication(HttpServletResponse res, String username) throws IOException {
        String JWT = Jwts.builder()
                .setSubject(retrieveUserContext(username))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
        res.getWriter().write("{\"token\":\"" + JWT + "\"}");
    }

    private String retrieveUserContext(String username) {
        User user = userService.findByUsername(username);
        UserContextDto userContextDto = buildUserContext(user.getUsername());
        return gson.toJson(userContextDto);
    }

    private UserContextDto buildUserContext(String username) {
        return UserContextDto.builder()
                            .username(username).build();
    }
}
