package com.javacode.testtask.mapper;

import com.javacode.testtask.model.dto.response.WalletResponseDto;
import com.javacode.testtask.model.entity.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WalletMapper {

    WalletMapper INSTANCE = Mappers.getMapper(WalletMapper.class);

    @Mapping(target = "balance", source = "balance")
    WalletResponseDto walletToWalletResponseDto(Wallet wallet);
}
