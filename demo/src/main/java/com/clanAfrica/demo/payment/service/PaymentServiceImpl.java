package com.clanAfrica.demo.payment.service;

import com.clanAfrica.demo.Exception.BadRequestException;
import com.clanAfrica.demo.Exception.NotFoundException;
import com.clanAfrica.demo.flight.data.model.Flight;
import com.clanAfrica.demo.flight.data.repositories.FlightRepository;
import com.clanAfrica.demo.payment.model.Wallet;
import com.clanAfrica.demo.payment.walletRepository.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {
    private final WalletRepository walletRepository;
    final String alphaNumeric = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private FlightRepository flightRepository;

    @Override
    public Wallet createWallet(Long flightId, String walletName, String walletBank, String walletbvn, String walletPin) {
        Optional<Flight>  flight  = flightRepository.findById(flightId);

        if (walletPin.length() <6 | walletName.length() > 6){
            throw new BadRequestException("invalid Pin");
        }
        else if (flight.isEmpty()){
            throw new NotFoundException("Flight not found");
        }
        Wallet wallet = new Wallet();
        wallet.setFlightId(flightId);
        wallet.setWalletName(walletName);
        wallet.setWalletBank(walletBank);
        wallet.setWalletPin(walletPin);
        wallet.setWalletBVN(walletbvn);
        wallet.setPaymentLink(generatePaymentLink(flight.get()));
        wallet.setDateCreated(LocalDate.now());
      return  walletRepository.save(wallet);

    }

    @Override
    public String generatePaymentLink(Flight flight) {
        Optional<Flight>  flight1  = flightRepository.findById(flight.getId());
        if (flight1.isEmpty()){
            throw new NotFoundException("flight not found");
        }
        else {
            SecureRandom random = new SecureRandom();
            int randomCodeLength = 6;
            StringBuilder code = new StringBuilder(randomCodeLength);
            for (int i =0; i <randomCodeLength; i++){
                code.append(alphaNumeric.charAt(random.nextInt(alphaNumeric.length())));
            }
            String referralCode = code.toString();
            return flight1.get().getAirLineType().toString().concat(referralCode).concat(flight1.get().getFlightNumber());
        }
    }

    @Override
    public String getPaymentLink(Long flightId) {
        Optional<Wallet> wallet = walletRepository.findByFlightId(flightId);
        if (wallet.isEmpty()){
            throw new NotFoundException("Link does not exist");
        }
        return wallet.get().getPaymentLink();

    }

    @Override
    public Wallet addFundToWallet(Long flightId, BigDecimal amount) {
        Optional<Wallet> wallet = walletRepository.findByFlightId(flightId);
        if (wallet.isPresent()){
           wallet.get().setWalletBalance(wallet.get().getWalletBalance().add(amount));
           walletRepository.save(wallet.get());
           return wallet.get();
        }
        else {
            throw new NotFoundException("wallet not found");
        }
    }
}
