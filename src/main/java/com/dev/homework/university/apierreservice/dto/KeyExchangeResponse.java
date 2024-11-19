package com.dev.homework.university.apierreservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KeyExchangeResponse {
    private String serverPublicKey;
    private String userId;
}
