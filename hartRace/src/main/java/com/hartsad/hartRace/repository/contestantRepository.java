package com.hartsad.hartRace.repository;

import com.hartsad.hartRace.entity.Contestant;
import com.hartsad.hartRace.entity.Eventitems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface contestantRepository extends JpaRepository<Contestant, Long> {
        List<Contestant> findByItem(String item);
        List<Contestant> findByEvent(String event);
        List<Contestant> findByGender(String gender);

        List<Contestant> findByEventAndItem(String event, String item);
        List<Contestant> findByEventAndItemAndGender(String event, String item, String gender);

        @Query("SELECT e FROM Contestant e ORDER BY (e.gold + e.silver + e.bronze) DESC, " +
                "e.gold DESC, e.silver DESC, e.bronze DESC")
        List<Contestant> findTopMedalist();

        @Query("SELECT e FROM Contestant e WHERE e.gender = :gender " +
                "ORDER BY (e.gold * 3 + e.silver * 2 + e.bronze) DESC, "+
                "e.gold DESC, e.silver DESC, e.bronze DESC")
        List<Contestant> findTopContestantDetailsByGender(@Param("gender") String gender);

        @Query("SELECT new com.hartsad.hartRace.entity.Eventitems(e.event, e.item) FROM Contestant e GROUP BY e.event,e.item")
        List<Eventitems> findAllEventAndItems();


}


