package com.project.wallet.service;

import com.project.wallet.dto.WalletDTO;
import com.project.wallet.entity.Wallet;

import java.util.UUID;

public interface WalletService {
    public Wallet createOperation(WalletDTO walletDTO);
    public Wallet getWalletByID(UUID id);
}