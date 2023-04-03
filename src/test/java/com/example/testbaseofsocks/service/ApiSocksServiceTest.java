package com.example.testbaseofsocks.service;

import com.example.testbaseofsocks.dto.SocksDto;
import com.example.testbaseofsocks.entity.Socks;
import com.example.testbaseofsocks.repository.SocksRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(MockitoExtension.class)
class ApiSocksServiceTest {

    @Mock
    private SocksRepository socksRepository;

    @InjectMocks
    private ApiSocksService apiSocksService;

    @Test
    void addSocksToDbNotNullTest() {
        SocksDto socksDto = new SocksDto("red", 10.0, 11);

        Socks socks = new Socks();
        socks.setColor(socksDto.getColor());
        socks.setQuantity(socksDto.getQuantity());
        socks.setCottonPart(socksDto.getCottonPart());

        Mockito.when(socksRepository.findSocksByColorAndCottonPart(socks.getColor(), socks.getCottonPart()))
                .thenReturn(null);

        Mockito.when(socksRepository.save(socks)).thenReturn(socks);

        assertThat(apiSocksService.addSocksToDb(socksDto)).isEqualTo(socksDto);
    }

    @Test
    void addSocksToDbNullTest() {
        SocksDto socksDto = new SocksDto("red", 10.0, 11);

        Socks socks = new Socks();
        socks.setColor(socksDto.getColor());
        socks.setQuantity(socksDto.getQuantity());
        socks.setCottonPart(socksDto.getCottonPart());

        Mockito.when(socksRepository.findSocksByColorAndCottonPart(socks.getColor(), socks.getCottonPart()))
                .thenReturn(socks);

        SocksDto socksDto1 = new SocksDto("red", 10.0, socksDto.getQuantity() + socks.getQuantity());

        Mockito.when(socksRepository.save(socks)).thenReturn(socks);

        assertThat(apiSocksService.addSocksToDb(socksDto)).isEqualTo(socksDto1);
    }

    @Test
    void decreaseSocksNotNullTest() {
        SocksDto socksDto = new SocksDto("red", 10.0, 11);

        Socks socks = new Socks();
        socks.setColor(socksDto.getColor());
        socks.setQuantity(socksDto.getQuantity() + 1);
        socks.setCottonPart(socksDto.getCottonPart());

        Mockito.when(socksRepository.findSocksByColorAndCottonPart(socks.getColor(), socks.getCottonPart()))
                .thenReturn(socks);

        SocksDto socksDto1 = new SocksDto("red", 10.0, socks.getQuantity() - socksDto.getQuantity());

        Mockito.when(socksRepository.save(socks)).thenReturn(socks);

        assertThat(apiSocksService.decreaseSocks(socksDto)).isEqualTo(socksDto1);
    }

    @Test
    void decreaseSocksNullTest() {
        SocksDto socksDto = new SocksDto("red", 10.0, 11);

        Socks socks = new Socks();
        socks.setColor(socksDto.getColor());
        socks.setQuantity(socksDto.getQuantity() + 1);
        socks.setCottonPart(socksDto.getCottonPart());

        Mockito.when(socksRepository.findSocksByColorAndCottonPart(socks.getColor(), socks.getCottonPart()))
                .thenReturn(null);

        assertThat(apiSocksService.decreaseSocks(socksDto)).isEqualTo(null);
    }

    @Test
    void quantitySocksFromWarehouseMoreThanTest() {
        String color = "red";
        String operation = "moreThan";
        Double cottonPart = 10.0;

        Socks socks = new Socks();
        socks.setQuantity(12);
        socks.setColor("red");
        socks.setCottonPart(15.0);

        List<Integer> socksList = new ArrayList<>();
        socksList.add(socks.getQuantity());

        Mockito.when(socksRepository.findSocksByColorAndCottonMoreThen(color, cottonPart))
                .thenReturn(Collections.singletonList(socksList.get(0)));

        assertThat(apiSocksService.quantitySocksFromWarehouse(color, operation, cottonPart))
                .isEqualTo("" + socksList.get(0));

    }
    @Test
    void quantitySocksFromWarehouseLessThanTest() {
        String color = "red";
        String operation = "lessThan";
        Double cottonPart = 10.0;

        Socks socks1 = new Socks();
        socks1.setQuantity(12);
        socks1.setColor("red");
        socks1.setCottonPart(9.0);

        List<Integer> socksList = new ArrayList<>();
        socksList.add(socks1.getQuantity());

        Mockito.when(socksRepository.findSocksByColorAndCottonLessThen(color, cottonPart))
                .thenReturn(Collections.singletonList(socksList.get(0)));

        assertThat(apiSocksService.quantitySocksFromWarehouse(color, operation, cottonPart))
                .isEqualTo("" + socksList.get(0));

    }

    @Test
    void quantitySocksFromWarehouseEqualTest() {
        String color = "red";
        String operation = "equal";
        Double cottonPart = 10.0;

        Socks socks2 = new Socks();
        socks2.setQuantity(12);
        socks2.setColor("red");
        socks2.setCottonPart(10.0);

        Mockito.when(socksRepository.findSocksByColorAndCottonPart(color, cottonPart))
                .thenReturn(socks2);

        assertThat(apiSocksService.quantitySocksFromWarehouse(color, operation, cottonPart))
                .isEqualTo("" + socks2.getQuantity());

    }
}