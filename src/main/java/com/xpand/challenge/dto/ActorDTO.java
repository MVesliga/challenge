package com.xpand.challenge.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActorDTO {
    @NotNull
    private String name;
    @NotNull
    private LocalDate birthDate;
    @NotNull
    private String gender;
}
