package com.clanAfrica.demo.payment.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface PaymentWebHookService {
     Map<String, Object> findWebhookDetails(Map<String, Object> webHookNotification);


}
