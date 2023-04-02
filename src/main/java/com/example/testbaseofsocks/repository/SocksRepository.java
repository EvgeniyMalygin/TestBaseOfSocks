package com.example.testbaseofsocks.repository;

import com.example.testbaseofsocks.entity.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM socks WHERE color = :color AND cotton = :cotton")
    Socks findSocksByColorAndCottonPart(@Param(value = "color") String color,
                                        @Param(value = "cotton") Double cotton);

    @Query(nativeQuery = true, value = "SELECT quantity FROM socks WHERE color = :color AND cotton > :cotton")
    List<Integer> findSocksByColorAndCottonMoreThen(@Param(value = "color") String color,
                                                    @Param(value = "cotton") Double cotton);

    @Query(nativeQuery = true, value = "SELECT quantity FROM socks WHERE color = :color AND cotton < :cotton")
    List <Integer> findSocksByColorAndCottonLessThen(@Param(value = "color") String color,
                                              @Param(value = "cotton") Double cotton);

}
