package com.xpand.challenge.service;

import com.xpand.challenge.dto.ActorDTO;
import com.xpand.challenge.dto.IdentifiableActorDTO;

import java.util.List;

public interface ActorService {
    IdentifiableActorDTO getActorById(Long id);
    List<IdentifiableActorDTO> getActors();
    List<IdentifiableActorDTO> getActorsByMovieId(Long movieId);
    ActorDTO createActor(ActorDTO actorDTO);
    void updateActor(Long id, ActorDTO actorDTO);
    void deleteActor(Long id);
}
