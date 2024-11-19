package com.dev.homework.university.apierreservice.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SecretKeyService {
    private final Map<String, byte[]> secretKeyStore = new ConcurrentHashMap<>();

    public void storeSecretKey(String userId, byte[] secretKey) {
        secretKeyStore.put(userId, secretKey);
    }

    public byte[] getSecretKey(String userId) {
        return secretKeyStore.get(userId);
    }

    public void removeSecretKey(String userId) {
        secretKeyStore.remove(userId);
    }
}
