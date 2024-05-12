package com.javacode.testtask.service;

import com.javacode.testtask.exception.ErrorChangeBalance;
import com.javacode.testtask.exception.InsufficientFundsException;
import com.javacode.testtask.exception.WalletNotFoundException;
import com.javacode.testtask.mapper.WalletMapper;
import com.javacode.testtask.model.dto.request.RequestChangeWalletDto;
import com.javacode.testtask.model.dto.response.WalletResponseDto;
import com.javacode.testtask.model.entity.Wallet;
import com.javacode.testtask.repository.WalletRepository;
import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    private static final String WALLET_NOT_FOUND = "Wallet not found";
    private static final String INSUFFICIENT_FUNDS = "Insufficient Funds";
    private static final String ERROR_CHANGE_BALANCE = "Error Change Balance";

    @Override
    @Transactional
    public WalletResponseDto getBalance(UUID walletId) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException(WALLET_NOT_FOUND, walletId));
        return WalletMapper.INSTANCE.walletToWalletResponseDto(wallet);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void changeBalance(RequestChangeWalletDto requestChangeWalletDto) {

        Wallet wallet = walletRepository.findById(requestChangeWalletDto.getWalletId())
                .orElseThrow(() -> new WalletNotFoundException(WALLET_NOT_FOUND, requestChangeWalletDto.getWalletId()));

        if (requestChangeWalletDto.getOperationType().equals("DEPOSIT")) {
            try {
                wallet.setBalance(wallet.getBalance().add(requestChangeWalletDto.getAmount()));
                Thread.sleep(0);
            } catch (InterruptedException e) {
                throw new ErrorChangeBalance(ERROR_CHANGE_BALANCE);
            }
        }

        if (requestChangeWalletDto.getOperationType().equals("WITHDRAW")) {
            if (wallet.getBalance().compareTo(requestChangeWalletDto.getAmount()) < 0) {
                throw new InsufficientFundsException(INSUFFICIENT_FUNDS);
            }
            try {
                wallet.setBalance(wallet.getBalance().subtract(requestChangeWalletDto.getAmount()));
                Thread.sleep(0);
            } catch (InterruptedException e) {
                throw new ErrorChangeBalance(ERROR_CHANGE_BALANCE);
            }
        }
    }

}

