package com.micro.framework.security.common.jwt;

import com.micro.framework.security.common.constants.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;

import java.security.Key;

/**
 * @author tb
 * @date 2019/1/2 10:27
 */
public final class JwtHelper {


    public static String generateToken(IJwt jwt, byte[] privateKey, int expire) throws Exception {
        return Jwts.builder()
                .setSubject(jwt.getUniqueName())
                .claim(SecurityConstants.JWT_USER_ID, jwt.getId())
                .claim(SecurityConstants.JWT_NAME, jwt.getName())
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                .signWith(SignatureAlgorithm.RS256, RsaHelper.getPrivateKey(privateKey))
                .compact();
    }

    public static Jws<Claims> parseToken(String token, String publicKeyPath) throws Exception {
        return getJws(RsaHelper.getPublicKey(publicKeyPath),token);
    }

    private static Jws<Claims> getJws(Key key,String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token);
    }

    public static IJwt getJwtFromToken(String token, String publicKeyPath) throws Exception {
        return getJwtFromJws(parseToken(token, publicKeyPath));
    }

    public static IJwt getInfoFromToken(String token, byte[] pubKey) throws Exception {
        return getJwtFromJws(parseToken(token, pubKey));
    }

    private static IJwt getJwtFromJws(Jws<Claims> jws) {
        Claims body = jws.getBody();
        return new JwtData(body.getSubject(), String.valueOf(body.get(SecurityConstants.JWT_USER_ID)), String.valueOf(body.get(SecurityConstants.JWT_NAME)));
    }

    public static Jws<Claims> parseToken(String token, byte[] publickKey) throws Exception {
        return getJws(RsaHelper.getPublicKey(publickKey),token);
    }
}
