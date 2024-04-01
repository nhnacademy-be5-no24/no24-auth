package com.nhnacademy.auth.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeCreateDto {
    private String gradeName;
    private Long accumulateRate;
}