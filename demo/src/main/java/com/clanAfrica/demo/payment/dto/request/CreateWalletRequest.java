package com.clanAfrica.demo.payment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateWalletRequest {
    private Long flightId;
    private String walletName;
    private String walletBank;
    private String walletbvn;
    private String walletPin;
}
