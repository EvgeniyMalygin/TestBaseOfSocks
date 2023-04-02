package com.example.testbaseofsocks.service;

import com.example.testbaseofsocks.dto.SocksDto;
import com.example.testbaseofsocks.entity.Socks;
import com.example.testbaseofsocks.mapper.SocksMapper;
import com.example.testbaseofsocks.repository.SocksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class ApiSocksService {

    private static final Logger log = LoggerFactory.getLogger(ApiSocksService.class);

    private final SocksRepository socksRepository;


    public ApiSocksService(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    public SocksDto addSocksToDb(SocksDto properties) {
        log.debug("method addSocksToDb started");

        Socks temp = SocksMapper.INSTANCE.socksDtoToSoks(properties);
        Socks socks = socksRepository.findSocksByColorAndCottonPart(temp.getColor(), temp.getCottonPart());
        if (!(socks == null)) {
            socks.setQuantity(socks.getQuantity() + temp.getQuantity());
            return SocksMapper.INSTANCE.socksToSocksDto(socksRepository.save(socks));
        } else {
            Socks socks1 = new Socks();
            socks1.setQuantity(temp.getQuantity());
            socks1.setColor(temp.getColor());
            socks1.setCottonPart(temp.getCottonPart());
            return SocksMapper.INSTANCE.socksToSocksDto(socksRepository.save(socks1));
        }

    }

    public SocksDto decreaseSocks(SocksDto properties) {
        log.debug("method decreaseSocks started");
        Socks temp = SocksMapper.INSTANCE.socksDtoToSoks(properties);
        Socks socks = socksRepository.findSocksByColorAndCottonPart(temp.getColor(), temp.getCottonPart());
        if (!(socks == null)) {
            socks.setQuantity(socks.getQuantity() - temp.getQuantity());
            return SocksMapper.INSTANCE.socksToSocksDto(socksRepository.save(socks));
        }
        return null;
    }

    public String quantitySocksFromWarehouse(String color, String operation, Double cottonPart) {
        log.debug("quantitySocksFromWarehouse");
        String str = "0";
        switch (operation) {
            case "moreThan":
                str = "" + socksRepository.findSocksByColorAndCottonMoreThen(color, cottonPart).stream()
                        .mapToInt(i -> i).sum();
                return str;
            case "lessThan":
                str = "" + socksRepository.findSocksByColorAndCottonLessThen(color, cottonPart).stream()
                        .mapToInt(i -> i).sum();
                return str;
            case "equal":
                if(!(socksRepository.findSocksByColorAndCottonPart(color, cottonPart) == null)) {
                    str = "" + socksRepository.findSocksByColorAndCottonPart(color, cottonPart).getQuantity();
                }
                return str;
        }
        return str;
    }
}
