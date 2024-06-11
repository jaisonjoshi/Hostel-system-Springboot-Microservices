package com.hostel.authservice.util;
import java.util.Date;

import javax.security.sasl.AuthenticationException;

import org.springframework.stereotype.Component;

import com.hostel.authservice.exception.InvalidTokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Component
public class JwtUtil {

	public static final String SECRET = "036c2f11e1507f30b7d8e64ac3de500e002df9327001caa6ccf05c82da5c74cbcc7b533ef26cbaf48df4995eed102bb26359a804d872b8d55f2f7ffed6757784338719ef651ca33111bb0fcec52d7df54b604cdb239a9b6ba87e7d032c7408e59be9a1b31066811564558a4d1c63a9b8d0b3a6923862efdeeade07eff75c8fa17902002e5aa09e2c96bb33f1b24a68afd6b702ce5ae8f681e392d7a84019186719d19dc521b2b850290022389844e318078cedbb7ba98d19364c5cbff093a555729bd3de04eb431cc87e9e42325c0f0d70f54901816218705890a707192c2ae4964de3be05ab82dce8a4b74e75b16c8f87bdff6ac086ac5a9c3fbe447f6867ae9c5ead3c802969b01f39b6aba3bb07084f880ca6fa08f562ac2fdbb33df00dbeceb0c3ebca2dc98ac6c79b82647691574160e7ca1cdc3db9a0ee96c31a919ad9a8243f11f5ce0293daeca1f0875643b368e31185f66c56d24722d24b09f334a06d1ba45948b4909738afaddece5d57e7ad696ab95e04109db0b51fbf235725576ee36526cbb6415b48fef50d98f70762aae4cd7731e90c72e5dfb73dc65d0b53e16725379527452865dd7268021905bd535fcb43f97938ec640e0973ac6e21a0e9c26c0210862b30a1f0098645727f00e672c3bf490a79bebccccff7ba1b19e1948568ea4dac86737be856f7bbd98e6f848b094d7e72be3213dbdb7f217d48b6";
	
	
	
	
	//Function to generate JWT Token
	
	@SuppressWarnings("deprecation")
	public String generateToken(String email, String role) {
		try {
			Date now = new Date();
			Date expiryDate = new Date(now.getTime()+ 1000*60*30);
			
			return Jwts.builder()
					.setSubject(email)
					.claim("role", role)
					.setIssuedAt(now)
					.setExpiration(expiryDate)
					.signWith(SignatureAlgorithm.HS512, SECRET)
					.compact();
		}
		catch(JwtException e) {
			throw e;
		}
		
	}
	
	
	
	
	
	
	//Function to validate the JWT token
	
	@SuppressWarnings("deprecation")
	public boolean validateToken(String token)   {
		try {
			Jwts.parserBuilder().setSigningKey(SECRET).build().parseClaimsJws(token);
			return true;
		}
		catch(Exception e) {
			return false;
		}
		
		
	}
	
	
	
	
	//Function to decode the role of the user from JWT token body
	
	@SuppressWarnings("deprecation")

	public String decodeRoleFromJWTtoken(String token) {
			Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
			return (String) claimsJws.getBody().get("role");
		
		
	}
	
	
	public String decodeEmailFromJWTtoken(String token) {
		Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
		return (String) claimsJws.getBody().getSubject();
	
	
}
}
