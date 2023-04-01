package com.example.testbaseofsocks.mapper;

import com.example.testbaseofsocks.dto.SocksDto;
import com.example.testbaseofsocks.entity.Socks;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SocksMapper {
    SocksMapper INSTANCE = Mappers.getMapper(SocksMapper.class);

    @Mapping(source = "color", target = "color")
    @Mapping(source = "cottonPart", target = "cottonPart")
    @Mapping(source = "quantity", target = "quantity")
    SocksDto socksToSocksDto(Socks socks);
    @Mapping(source = "color", target = "color")
    @Mapping(source = "cottonPart", target = "cottonPart")
    @Mapping(source = "quantity", target = "quantity")
    Socks socksDtoToSoks(SocksDto socksDto);
}
