package com.clanAfrica.demo.payment.controller;

import com.clanAfrica.demo.Exception.BadRequestException;
import com.clanAfrica.demo.Exception.NotFoundException;
import com.clanAfrica.demo.auth.dto.response.AuthResponse;
import com.clanAfrica.demo.auth.dto.response.AuthenticationResponse;
import com.clanAfrica.demo.flight.dto.response.BaseResponse;
import com.clanAfrica.demo.payment.dto.request.CreateWalletRequest;
import com.clanAfrica.demo.payment.dto.response.PaymentLinkResponse;
import com.clanAfrica.demo.payment.dto.response.WalletBaseResponse;
import com.clanAfrica.demo.payment.model.Wallet;
import com.clanAfrica.demo.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/wallet")
@RequiredArgsConstructor
public class PaymentController {
    private PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<?> createWallet(CreateWalletRequest walletRequest) {

        try {
            Wallet wallet = paymentService.createWallet(walletRequest.getFlightId(), walletRequest.getWalletName(), walletRequest.getWalletBank(), walletRequest.getWalletbvn(), walletRequest.getWalletPin());
            return new ResponseEntity<>(
                    new WalletBaseResponse(true, HttpStatus.CREATED.value(), "successful",
                            LocalDateTime.now(), wallet)
                    ,HttpStatus.CREATED);
        }
        catch (BadRequestException|NotFoundException e){
            return new ResponseEntity<>(
                    new BaseResponse(false, HttpStatus.BAD_REQUEST.value(), e.getMessage(),
                            LocalDateTime.now())
                    ,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/link/{flightId}")
    public ResponseEntity<?> getPaymentLink(@PathVariable Long flightId){
        try {
            String paymentLink = paymentService.getPaymentLink(flightId);
            return new ResponseEntity<>(
                    new PaymentLinkResponse(true, HttpStatus.OK.value(), "successful",
                            LocalDateTime.now(), paymentLink)
                    ,HttpStatus.OK);
        }
        catch (NotFoundException e){
            return new ResponseEntity<>(
                    new BaseResponse(false, HttpStatus.BAD_REQUEST.value(), e.getMessage(),
                            LocalDateTime.now())
                    ,HttpStatus.BAD_REQUEST);

        }
    }
}
