package com.whereismyhome.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

	private static final String secret = "n0syh2pcj1W9hGmeXsEFrfSiSqh3BE8ZOV4HOkAM2yFpFSQ9E3pWV4TQCgwvAmYaTveCepecwcZpBbFF";

	public static final long ACCESS_TOKEN_EXPIRE_MIN = 10; // 10min
	public static final long REFRESH_TOKEN_EXPIRE_MIN = 60; // 60min

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getId);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public String generateAccessToken(String id) {
		return generateAccessToken(id, new HashMap<>());
	}

	public String generateAccessToken(String id, Map<String, Object> claims) {
		return doGenerateToken(id, claims, ACCESS_TOKEN_EXPIRE_MIN);
	}

	public String generateRefreshToken(String id) {
		return generateRefreshToken(id, new HashMap<>());
	}

	public String generateRefreshToken(String id, Map<String, Object> claims) {
		return doGenerateToken(id, claims, REFRESH_TOKEN_EXPIRE_MIN);
	}

	private String doGenerateToken(String id, Map<String, Object> claims, long expireTime) {
		return Jwts.builder()
				.setClaims(claims)
				.setId(id)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expireTime * 1000 * 60))
				.signWith(Keys.hmacShaKeyFor(secret.getBytes()),SignatureAlgorithm.HS512) // 키와 알고리즘 설정
				.compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}

}
