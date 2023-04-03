package com.example.testbaseofsocks.mapper;

import com.example.testbaseofsocks.dto.SocksDto;
import com.example.testbaseofsocks.entity.Socks;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SocksMapper {
    SocksMapper INSTANCE = Mappers.getMapper(SocksMapper.class);

    SocksDto socksToSocksDto (Socks socks);
    Socks socksDtoToSoks(SocksDto socksDto);
}
