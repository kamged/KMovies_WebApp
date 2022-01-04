package com.kamged.kmovies.repositories;

import com.kamged.kmovies.models.TVSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITVSeriesRepo extends JpaRepository<TVSeries, Integer> {
}
