package com.javacode.testtask.service;

import com.javacode.testtask.model.dto.request.RequestChangeWalletDto;
import com.javacode.testtask.model.dto.response.WalletResponseDto;

import java.util.UUID;

public interface WalletService {
    WalletResponseDto getBalance(UUID walletId);
    void changeBalance(RequestChangeWalletDto requestChangeWalletDto);

}
