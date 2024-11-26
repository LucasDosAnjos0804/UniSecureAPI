package com.dev.homework.university.apierreservice.controller;

import com.dev.homework.university.apierreservice.dto.KeyExchangeRequest;
import com.dev.homework.university.apierreservice.dto.KeyExchangeResponse;
import com.dev.homework.university.apierreservice.service.SecretKeyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/key-exchange")
public class KeyExchangeController {

    private final SecretKeyService secretKeyService;
    private final BigInteger p;
    private final BigInteger g;
    private final BigInteger privateKey;

    public KeyExchangeController(SecretKeyService secretKeyService) {
        this.secretKeyService = secretKeyService;

        this.p = new BigInteger("23");
        this.g = new BigInteger("5");
        this.privateKey = generatePrivateKey();
    }

    private BigInteger generatePrivateKey() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(p.bitLength() - 1, random);
    }

    @PostMapping
    public ResponseEntity<KeyExchangeResponse> exchangeKeys(@RequestBody KeyExchangeRequest request) {
        if (request.getClientPublicKey() == null || request.getClientPublicKey().isEmpty()) {
            return ResponseEntity.badRequest().body(new KeyExchangeResponse("Invalid client public key", null));
        }

        try {
            BigInteger clientPublicKey = new BigInteger(request.getClientPublicKey());

            // Calcula a chave pública do servidor
            BigInteger serverPublicKey = g.modPow(privateKey, p);

            // Calcula o segredo compartilhado
            BigInteger sharedSecret = clientPublicKey.modPow(privateKey, p);

            // Gera um ID único para esta sessão
            String userId = UUID.randomUUID().toString();

            // Armazena o segredo compartilhado de forma segura
            secretKeyService.storeSecretKey(userId, sharedSecret.toByteArray());

            log.info("Key exchange completed for user: {}", userId);

            // Retorna a chave pública do servidor
            return ResponseEntity.ok(new KeyExchangeResponse(serverPublicKey.toString(), userId));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(new KeyExchangeResponse("Invalid client public key format", null));
        }
    }

}