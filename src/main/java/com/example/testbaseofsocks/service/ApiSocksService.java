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
        }else {
            socks.setQuantity(temp.getQuantity());
            socks.setColor(temp.getColor());
            socks.setCottonPart(temp.getCottonPart());
        }
        socks = socksRepository.save(socks);
        return SocksMapper.INSTANCE.socksToSocksDto(socks);
}
}
