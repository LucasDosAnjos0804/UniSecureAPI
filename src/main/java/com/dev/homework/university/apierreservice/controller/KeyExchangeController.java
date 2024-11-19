package com.dev.homework.university.apierreservice.controller;

import com.dev.homework.university.apierreservice.dto.KeyExchangeRequest;
import com.dev.homework.university.apierreservice.dto.KeyExchangeResponse;
import com.dev.homework.university.apierreservice.service.SecretKeyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.KeyAgreement;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/key-exchange")
public class KeyExchangeController {
    private final KeyPair serverKeyPair;

    private final SecretKeyService secretKeyService;

    public KeyExchangeController(SecretKeyService secretKeyService) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DH");
        keyPairGenerator.initialize(2048);
        this.serverKeyPair = keyPairGenerator.generateKeyPair();
        this.secretKeyService = secretKeyService;
    }

    @PostMapping
    public ResponseEntity<KeyExchangeResponse> exchangeKeys(@RequestBody KeyExchangeRequest request) throws Exception {
        // Decode client's public key
        byte[] clientPublicKeyBytes = Base64.getDecoder().decode(request.getClientPublicKey());
        KeyFactory keyFactory = KeyFactory.getInstance("DH");
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(clientPublicKeyBytes);
        PublicKey clientPublicKey = keyFactory.generatePublic(x509KeySpec);

        // Generate shared secret
        KeyAgreement keyAgreement = KeyAgreement.getInstance("DH");
        keyAgreement.init(serverKeyPair.getPrivate());
        keyAgreement.doPhase(clientPublicKey, true);
        byte[] sharedSecret = keyAgreement.generateSecret();

        // Store the shared secret securely
        String userId = UUID.randomUUID().toString(); // Generate a unique ID for this session
        secretKeyService.storeSecretKey(userId, sharedSecret);

        // Log the key exchange (avoid logging the actual secret in production)
        log.info("Key exchange completed for user: {}", userId);

        // Return server's public key
        String serverPublicKeyString = Base64.getEncoder().encodeToString(serverKeyPair.getPublic().getEncoded());
        return ResponseEntity.ok(new KeyExchangeResponse(serverPublicKeyString, userId));
    }

}