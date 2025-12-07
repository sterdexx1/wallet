package com.project.wallet.wallet;

import com.project.wallet.dto.WalletDTO;
import com.project.wallet.enums.OperationType;
import com.project.wallet.exception.NotEnoughMoneyException;
import com.project.wallet.exception.NotExistWalletException;
import com.project.wallet.repository.WalletRepository;
import com.project.wallet.service.WalletService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class WalletApplicationTests {

    @Autowired
    private WalletService walletService;

    @Autowired
    private WalletRepository walletRepository;

    @Test
    @Transactional
    void testDeposit() {
        Long amount = 10L;
        WalletDTO walletDTO = new WalletDTO(UUID.fromString("111a1111-a11a-11a1-a111-111111111111"), OperationType.DEPOSIT, amount);
        Long walletAmount = walletRepository.findWalletById(walletDTO.getId()).getAmount();
        walletService.createOperation(walletDTO);

        assertEquals(walletRepository.findWalletById(walletDTO.getId()).getAmount(), walletAmount + amount);
    }

    @Test
    @Transactional
    void testWithdraw() {
        Long amount = 10L;
        WalletDTO walletDTO = new WalletDTO(UUID.fromString("111a1111-a11a-11a1-a111-111111111111"), OperationType.WITHDRAW, amount);
        Long walletAmount = walletRepository.findWalletById(walletDTO.getId()).getAmount();
        walletService.createOperation(walletDTO);

        assertEquals(walletRepository.findWalletById(walletDTO.getId()).getAmount(), walletAmount - amount);
    }

    @Test
    @Transactional
    void testWithdrawWhenMoreThanPossible() {
        Long amount = 10000000000L;
        WalletDTO walletDTO = new WalletDTO(UUID.fromString("111a1111-a11a-11a1-a111-111111111111"), OperationType.WITHDRAW, amount);
        assertThrows(NotEnoughMoneyException.class,() -> walletService.createOperation(walletDTO));
    }

    @Test
    @Transactional
    void testWhenUUIDUnknown() {
        WalletDTO walletDTO = new WalletDTO(UUID.fromString("111a1111-a11a-11a1-a111-111111111110"), OperationType.DEPOSIT, 10L);
        assertThrows(NotExistWalletException.class,() -> walletService.createOperation(walletDTO));
    }
}
