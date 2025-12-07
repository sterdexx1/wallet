package com.project.wallet.controller;

import com.project.wallet.dto.WalletDTO;
import com.project.wallet.entity.Wallet;
import com.project.wallet.exception.NotExistWalletException;
import com.project.wallet.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class ControllerApp {

    @Autowired
    private WalletService walletService;

    @PostMapping("/wallets")
    public Wallet createOperation(@Valid @RequestBody WalletDTO walletDTO){
        return walletService.createOperation(walletDTO);
    }

    @GetMapping("/wallets/{id}")
    public Wallet showInfo(@PathVariable UUID id){
        if (walletService.getWalletByID(id) == null){
            throw new NotExistWalletException("Wallet with UUID: " + id + " not found.");
        }
        return walletService.getWalletByID(id);
    }
}
