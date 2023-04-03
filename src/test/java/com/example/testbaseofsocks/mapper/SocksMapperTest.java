package com.example.testbaseofsocks.mapper;

import com.example.testbaseofsocks.dto.SocksDto;
import com.example.testbaseofsocks.entity.Socks;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
class SocksMapperTest {

    @Test
    void socksToSocksDtoTest() {
        SocksDto socksDto = new SocksDto("red", 10.0, 11);

        Socks socks = new Socks();
        socks.setColor(socksDto.getColor());
        socks.setQuantity(socksDto.getQuantity());
        socks.setCottonPart(socksDto.getCottonPart());

        assertThat(SocksMapper.INSTANCE.socksToSocksDto(socks)).isEqualTo(socksDto);
    }

    @Test
    void socksDtoToSoksTest() {
        SocksDto socksDto = new SocksDto("red", 10.0, 11);

        Socks socks = new Socks();
        socks.setColor(socksDto.getColor());
        socks.setQuantity(socksDto.getQuantity());
        socks.setCottonPart(socksDto.getCottonPart());

        assertThat(SocksMapper.INSTANCE.socksDtoToSoks(socksDto)).isEqualTo(socks);
    }
}