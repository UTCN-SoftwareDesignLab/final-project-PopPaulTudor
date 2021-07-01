package com.lab4.demo.pair;

import com.lab4.demo.pair.model.Pair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PairRepository extends JpaRepository<Pair, Long> {

    List<Pair> getPairsByUserId(long userId);

    List<Pair> getPairsByUserIdIsNot(long userId);

    Pair getPairByUserIdAndAndGatherId(long userId, long gatherId);



}
