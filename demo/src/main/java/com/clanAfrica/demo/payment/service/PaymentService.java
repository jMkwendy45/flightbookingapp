package com.clanAfrica.demo.payment.service;

import com.clanAfrica.demo.flight.data.model.Flight;
import com.clanAfrica.demo.payment.dto.response.PaymentLinkResponse;
import com.clanAfrica.demo.payment.model.Transaction;
import com.clanAfrica.demo.payment.model.Wallet;

import java.math.BigDecimal;

public interface PaymentService {
    Wallet createWallet(Long flightId, String walletName, String walletBank, String walletbvn, String walletPin);
    String generatePaymentLink(Flight flight);
    String getPaymentLink(Long flightId);
    Wallet addFundToWallet(Long flightId, BigDecimal amount);

}
