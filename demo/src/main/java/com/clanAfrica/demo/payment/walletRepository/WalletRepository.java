package com.clanAfrica.demo.payment.walletRepository;

import com.clanAfrica.demo.payment.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByPaymentLink(String paymentLink);
    Optional<Wallet> findByFlightId(Long flightId);
}
