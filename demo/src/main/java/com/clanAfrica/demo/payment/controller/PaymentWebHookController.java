package com.clanAfrica.demo.payment.controller;

import com.clanAfrica.demo.payment.dto.response.WalletBaseResponse;
import com.clanAfrica.demo.payment.dto.response.WebHookResponse;
import com.clanAfrica.demo.payment.service.PaymentWebHookService;
import com.clanAfrica.demo.payment.service.WebHookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/wallet")
@RequiredArgsConstructor
public class PaymentWebHookController {
    private PaymentWebHookService webHookService;
    @PostMapping("/webhook")
    public ResponseEntity<?> webhook(@RequestBody Map<String, Object> webhookNotification){
        Map<String, Object> response =webHookService.findWebhookDetails(webhookNotification);
        return new ResponseEntity<>(
                new WebHookResponse(true, HttpStatus.CREATED.value(), "Payment successful",
                        LocalDateTime.now(), response)
                ,HttpStatus.CREATED);
    }
}
