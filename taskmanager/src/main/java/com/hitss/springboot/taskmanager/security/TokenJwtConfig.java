package com.hitss.springboot.taskmanager.security;

import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class TokenJwtConfig {
   
    public static final String SECRET_KEY_STRING = "AlgunaClaveSecretaMuyLargaYSeguraParaElProyectoTaskmanagerDeHitss2025"; 
    
    public static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes(StandardCharsets.UTF_8));
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "application/json";

    
}