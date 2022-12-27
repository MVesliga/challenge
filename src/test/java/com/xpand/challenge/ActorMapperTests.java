package com.xpand.challenge;

import com.xpand.challenge.dto.*;
import com.xpand.challenge.model.Actor;
import com.xpand.challenge.model.Movie;
import com.xpand.challenge.model.constants.Gender;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ActorMapperTests {


    @Test
    void doTestfromActorDTO() {
        ActorDTO dto = new ActorDTO();
        dto.setName("Name");
        dto.setBirthDate(LocalDate.now());
        dto.setGender("Male");
        Actor actor = ActorDTOMapper.fromActorDTO(dto);
        assertNotNull(actor);
        assertEquals(dto.getName(), actor.getName());
        assertEquals(dto.getBirthDate(), actor.getBirthDate());
        assertEquals(dto.getGender(), Gender.valueOfName(actor.getGender()));
    }

    @Test
    void doTestToActorDTO() {
        Actor actor = new Actor();
        actor.setId(1l);
        actor.setName("Name");
        actor.setBirthDate(LocalDate.now());
        actor.setGender(Gender.M);
        IdentifiableActorDTO dto = ActorDTOMapper.toActorDTO(actor);
        assertNotNull(dto);
        assertEquals(actor.getId(), dto.getId());
        assertEquals(actor.getName(), dto.getName());
        assertEquals(actor.getBirthDate(), dto.getBirthDate());
        assertEquals(actor.getGender(), Gender.valueOfLabel(dto.getGender()));
    }

    @Test
    void doTestNull() {
        assertNull(ActorDTOMapper.fromActorDTO(null));
        assertNull(ActorDTOMapper.toActorDTO(null));
    }

}
