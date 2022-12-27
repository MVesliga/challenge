package com.xpand.challenge.repository;

import com.xpand.challenge.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    List<Actor> findAllByMovieId(Long movieId);
}
