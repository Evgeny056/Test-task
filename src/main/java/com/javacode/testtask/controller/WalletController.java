package com.javacode.testtask.controller;

import com.javacode.testtask.model.dto.request.RequestChangeWalletDto;
import com.javacode.testtask.model.dto.response.SimpleMessage;
import com.javacode.testtask.model.dto.response.WalletResponseDto;
import com.javacode.testtask.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @GetMapping("wallets/{WALLET_UUID}")
    public ResponseEntity<WalletResponseDto> getWallet(@PathVariable("WALLET_UUID") UUID walletId) {
        return ResponseEntity.ok(walletService.getBalance(walletId));
    }

    @PostMapping("wallet")
    public ResponseEntity<?> changeBalance(@Valid @RequestBody RequestChangeWalletDto requestChangeWalletDto) {
        walletService.changeBalance(requestChangeWalletDto);
        return ResponseEntity.ok(new SimpleMessage("Operation completed successfully"));
    }

}
