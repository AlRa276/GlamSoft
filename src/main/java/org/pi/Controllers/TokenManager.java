package org.pi.Controllers;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class TokenManager {
    private final SecretKey key;
    private static final long EXPIRATION_TIME = 36000000;

    public TokenManager() {
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String issueToken(String email){
        return Jwts.builder().setSubject(email).setIssuedAt(new Date()).
                setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME)).
                signWith(key).compact();
    }

    public boolean validarToken(String token, String email){
        try{
            Claims claims = Jwts.parserBuilder().setSigningKey(this.key).build().
                    parseClaimsJws(token).getBody();
            return claims.getSubject().equals(email);
        }catch (JwtException | IllegalArgumentException e){
            return false;
        }
  }
}
