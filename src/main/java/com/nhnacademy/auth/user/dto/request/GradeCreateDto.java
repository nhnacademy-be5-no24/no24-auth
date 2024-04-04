package com.nhnacademy.auth.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GradeCreateDto {
    private String gradeName;
    private Long accumulateRate;
}
