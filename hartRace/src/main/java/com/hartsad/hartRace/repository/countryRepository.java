package com.hartsad.hartRace.repository;

import com.hartsad.hartRace.entity.Country;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface countryRepository extends JpaRepository<Country, Long> {
    Country findByCountryCode(String iso);

    Country findFirstByOrderByGoldDescSilverDescBronzeDesc();

    @Query("SELECT c FROM Country c ORDER BY c.gold DESC, c.silver DESC, c.bronze DESC, c.countryCode")
    List<Country> findTopCountries(Pageable pageable);

    @Query("SELECT c FROM Country c WHERE c.countryCode IN " +
            "(SELECT DISTINCT e.isocode FROM Contestant e WHERE e.event = :event) " +
            "ORDER BY c.gold DESC, c.silver DESC, c.bronze DESC")
    List<Country> findByEventNameOrderByMedals(@Param("event") String event,Pageable pageable);

    @Query("SELECT c FROM Country c WHERE c.gold = (SELECT MIN(c2.gold) FROM Country c2)")
    List<Country> findCountryWithMinimumGold();

    @Query("SELECT c FROM Country c WHERE c.silver = (SELECT MIN(c2.silver) FROM Country c2)")
    List<Country> findCountryWithMinimumSilver();

    @Query("SELECT c FROM Country c WHERE c.bronze = (SELECT MIN(c2.bronze) FROM Country c2)")
    List<Country> findCountryWithMinimumBronze();

    @Query("SELECT c FROM Country c WHERE c.points = (SELECT MIN(c2.points) FROM Country c2)")
    List<Country> findCountryWithMinimumPoints();

    @Query("SELECT c FROM Country c WHERE c.gold = (SELECT MAX(c2.gold) FROM Country c2)")
    List<Country> findCountryWithMaximumGold();

    @Query("SELECT c FROM Country c WHERE c.silver = (SELECT MAX(c2.silver) FROM Country c2)")
    List<Country> findCountryWithMaximumSilver();

    @Query("SELECT c FROM Country c WHERE c.bronze = (SELECT MAX(c2.bronze) FROM Country c2)")
    List<Country> findCountryWithMaximumBronze();

    @Query("SELECT c FROM Country c WHERE (c.points) = (SELECT MAX(c2.points) FROM Country c2)")
    List<Country> findCountryWithMaximumPoints();



}
