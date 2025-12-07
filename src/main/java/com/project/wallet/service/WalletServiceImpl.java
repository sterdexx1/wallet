package com.project.wallet.service;

import com.project.wallet.dto.WalletDTO;
import com.project.wallet.entity.Wallet;
import com.project.wallet.exception.NotEnoughMoneyException;
import com.project.wallet.exception.NotExistWalletException;
import com.project.wallet.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WalletServiceImpl implements WalletService{

    @Autowired
    private WalletRepository walletRepository;

    @Override
    @Transactional
    public Wallet createOperation(WalletDTO walletDTO) {
        Wallet wallet = walletRepository.findWalletById(walletDTO.getId());
        if (wallet==null){
            throw new NotExistWalletException("Wallet with UUID: " + walletDTO.getId() + " not found.");
        }
        switch (walletDTO.getOperationType()){
            case DEPOSIT:
                wallet.setAmount(wallet.getAmount() + walletDTO.getAmount());
                break;
            case WITHDRAW:
                if (walletDTO.getAmount() <= wallet.getAmount()){
                    wallet.setAmount(wallet.getAmount() - walletDTO.getAmount());
                } else {
                    throw new NotEnoughMoneyException("Not enough money in the wallet");
                }
                break;
        }
        return wallet;
    }

    @Override
    @Transactional
    public Wallet getWalletByID(UUID id) {
        return walletRepository.findWalletById(id);
    }
}
