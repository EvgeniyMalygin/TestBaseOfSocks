package com.example.testbaseofsocks.service;

import com.example.testbaseofsocks.dto.SocksDto;
import com.example.testbaseofsocks.entity.Socks;
import com.example.testbaseofsocks.mapper.SocksMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class ApiSocksService {

    private static final Logger log = LoggerFactory.getLogger(ApiSocksService.class);
    
    public SocksDto addSocksToDb(SocksDto properties) {
        log.debug("method addSocksToDb started");
        Socks socks = SocksMapper.INSTANCE.socksDtoToSoks(properties);

        return SocksMapper.INSTANCE.socksToSocksDto(socks);
    }
}
