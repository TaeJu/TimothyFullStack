package com.tjcode.rideproducer.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "tj_timothy_secret_key";

    public String dummy() {
        return "hello world";
    }

    public Authentication getAuthentication(String token) {
        String roles = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().get("roles").toString();
        roles = roles.substring(1, roles.length() - 1);
        roles = roles.replace(" ", "");
        System.out.println(roles);
        final Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(roles.split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        System.out.println(authorities);

        return new UsernamePasswordAuthenticationToken(null, null, authorities);
    }

    public boolean validateToken(String token) {
        System.out.println("inside validate token");
        Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
        System.out.println("inside validate token 2");

        return !claims.getBody().getExpiration().before(new Date());
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimResolver.apply(claims);
    }

    public String getUserNameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private boolean isTokenExpired(String token) {
        final Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
}
