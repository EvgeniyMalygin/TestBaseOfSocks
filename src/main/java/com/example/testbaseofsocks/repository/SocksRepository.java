package com.example.testbaseofsocks.repository;

import com.example.testbaseofsocks.entity.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM socks WHERE color = :color AND cottonPart = :cottonPart")
    Socks findSocksByColorAndCottonPart(@Param(value = "color") String color,
                                        @Param(value = "cottonPart") Double cottonPart);
}
