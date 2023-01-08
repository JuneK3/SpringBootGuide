package com.rootlab.ch13.config.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

	private final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);
	private final UserDetailsService userDetailsService; // Spring Security 에서 제공하는 서비스 레이어

	private final Key key;
	private final long tokenValidMillisecond = 1000L * 60 * 60; // 1시간 토큰 유효

	// SecretKey 에 대해 인코딩 수행
	public JwtTokenProvider(@Value("${jwt.secret}") String secretKey, UserDetailsService userDetailsService) {
		log.info("[init] JwtTokenProvider에서 secretKey 초기화 시작");
		byte[] keyBytes = Base64.getEncoder().encode(secretKey.getBytes(StandardCharsets.UTF_8));
		this.key = Keys.hmacShaKeyFor(keyBytes);
		log.info("[init] JwtTokenProvider에서 secretKey 초기화 완료");
		this.userDetailsService = userDetailsService;
	}

	// JWT 토큰 생성
	public String createToken(String uid, List<String> roles) {
		log.info("[createToken] 토큰 생성 시작");
		Claims claims = Jwts.claims().setSubject(uid);
		claims.put("roles", roles);

		Date now = new Date();
		String token = Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + tokenValidMillisecond))
				.signWith(key) // 암호화 알고리즘, secret 값 세팅
				.compact();

		log.info("[createToken] 토큰 생성 완료");
		return token;
	}

	// JWT 토큰으로 인증 정보 조회
	public Authentication getAuthentication(String token) {
		log.info("[getAuthentication] 토큰 인증 정보 조회 시작");
		UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserId(token));
		log.info("[getAuthentication] 토큰 인증 정보 조회 완료, UserDetails UserName : {}",
				userDetails.getUsername());
		return new UsernamePasswordAuthenticationToken(userDetails, "",
				userDetails.getAuthorities());
	}

	// JWT 토큰에서 회원 구별 정보 추출
	public String getUserId(String token) {
		log.info("[getUsername] 토큰 기반 회원 구별 정보 추출");
		JwtParser parser = Jwts.parserBuilder()
				.setSigningKey(key)
				.build();
		String uid = parser.parseClaimsJws(token).getBody().getSubject();
		log.info("[getUsername] 토큰 기반 회원 구별 정보 추출 완료, uid : {}", uid);
		return uid;
	}

	/**
	 * HTTP Request Header 에 설정된 토큰 값을 가져옴
	 *
	 * @param request Http Request Header
	 * @return String type Token 값
	 */
	public String resolveToken(HttpServletRequest request) {
		log.info("[resolveToken] HTTP 헤더에서 Token 값 추출");
		String header = request.getHeader("Authorization");
		String token = header.split(" ")[1].trim();
		return token;
	}

	// JWT 토큰의 유효성 + 만료일 체크
	public boolean validateToken(String token) {
		log.info("[validateToken] 토큰 유효 체크 시작");
		JwtParser parser = Jwts.parserBuilder()
				.setSigningKey(key)
				.build();
		Jws<Claims> claims = parser.parseClaimsJws(token);
		log.info("[validateToken] 토큰 유효 체크 완료");
		if (!claims.getBody().getExpiration().before(new Date())) {
			return true;
		}
		return false;
	}
}
