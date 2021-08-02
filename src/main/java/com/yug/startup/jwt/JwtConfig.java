package com.yug.startup.jwt;

import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Configuration
public class JwtConfig {

    private final String secretKey = "%j=f$v&u08(7+98yb45p48u3498yb45p48u3498yb45p48u3498yb45p48u3498yb45p48u3498yb45p48u34'?9j";
    private final String tokenPrefix = "Bearer ";
    private final Integer tokenExpiresAfterWeeks = 4;

    public java.sql.Date getExpiration() {
        return java.sql.Date.valueOf(LocalDate.now().plusWeeks(tokenExpiresAfterWeeks));
    }

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
