package com.kamged.kmovies.repositories;

import com.kamged.kmovies.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IMovieRepo extends JpaRepository<Movie, Integer> {

    @Transactional
    @Modifying
    @Query(value = "update movies set title = :title, year = :year, rating = :rating where id = :id", nativeQuery = true)
    void _update(@Param("id") Integer id, @Param("title") String title, @Param("year") String year, @Param("rating") Integer rating);
}
