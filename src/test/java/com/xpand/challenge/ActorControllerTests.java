package com.xpand.challenge;

import com.xpand.challenge.dto.ActorDTO;
import com.xpand.challenge.dto.IdentifiableActorDTO;
import com.xpand.challenge.dto.IdentifiableMovieDTO;
import com.xpand.challenge.dto.MovieDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActorControllerTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void doTestGetActors() {
        assertEquals(HttpStatus.OK.value(), restTemplate.getForEntity("http://localhost:"+port+"/actors", List.class).getStatusCodeValue());
    }

    @Test
    void doTestGetActor() {
        ResponseEntity<IdentifiableActorDTO> response = restTemplate.getForEntity("http://localhost:"+port+"/actors/1", IdentifiableActorDTO.class);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(1, response.getBody().getId());
    }

    @Test
    void doTestGetActorNotFound() {
        ResponseEntity<IdentifiableActorDTO> response = restTemplate.getForEntity("http://localhost:"+port+"/actor/666", IdentifiableActorDTO.class);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode().value());
    }

    @Test
    void doTestGetActorsByMovieId() {
        ResponseEntity<List<IdentifiableActorDTO>> response = restTemplate.exchange("http://localhost:"+port+"/actors/movie/2", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<IdentifiableActorDTO>>() {});
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertTrue(response.getBody().size() > 0);
    }

    @Test
    void doTestGetActorsByMovieIdEmpty() {
        ResponseEntity<List<IdentifiableActorDTO>> response = restTemplate.exchange("http://localhost:"+port+"/actors/movie/999", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<IdentifiableActorDTO>>() {});
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertTrue(response.getBody().size() == 0);
    }

    @Test
    void doTestUpdateActor() {
        ActorDTO dto = new ActorDTO();
        dto.setName("Monkey D. Luffy");
        dto.setBirthDate(LocalDate.now());
        dto.setGender("Male");
        HttpEntity<ActorDTO> putRequest = new HttpEntity<>(dto);
        ResponseEntity<?> putResponse = restTemplate.exchange("http://localhost:"+port+"/actors/1", HttpMethod.PUT, putRequest, Void.class);
        assertEquals(HttpStatus.NO_CONTENT.value(), putResponse.getStatusCode().value());
        ResponseEntity<ActorDTO> getResponse = restTemplate.getForEntity("http://localhost:"+port+"/actors/1", ActorDTO.class);
        assertEquals(HttpStatus.OK.value(), getResponse.getStatusCode().value());
        assertEquals("Monkey D. Luffy", getResponse.getBody().getName());
    }

    @Test
    void doTestDeleteActor() {
        ResponseEntity<?> response = restTemplate.exchange("http://localhost:"+port+"/actors/1", HttpMethod.DELETE, null, Void.class);
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatusCode().value());
        assertEquals(HttpStatus.NOT_FOUND.value(), restTemplate.getForEntity("http://localhost:"+port+"/actors/1", ActorDTO.class).getStatusCode().value());
    }
}
