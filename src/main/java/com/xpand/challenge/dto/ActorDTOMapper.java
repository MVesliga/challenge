package com.xpand.challenge.dto;

import com.xpand.challenge.model.Actor;
import com.xpand.challenge.model.Movie;
import com.xpand.challenge.model.constants.Gender;

import java.util.Optional;

public class ActorDTOMapper {

    public static IdentifiableActorDTO toActorDTO(Actor actor) {
        return Optional.ofNullable(actor).map(a -> {
            IdentifiableActorDTO dto = new IdentifiableActorDTO();
            dto.setId(actor.getId());
            dto.setName(actor.getName());
            dto.setBirthDate(actor.getBirthDate());
            dto.setGender(Gender.valueOfName(actor.getGender()));
            return dto;
        }).orElse(null);
    }

    public static Actor fromActorDTO(ActorDTO dto) {
        return Optional.ofNullable(dto).map(d -> {
            Actor actor = new Actor();
            actor.setName(dto.getName());
            actor.setBirthDate(dto.getBirthDate());
            actor.setGender(Gender.valueOfLabel(dto.getGender()));
            return actor;
        }).orElse(null);
    }
}
