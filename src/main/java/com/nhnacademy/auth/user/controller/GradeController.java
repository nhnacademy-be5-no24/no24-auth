package com.nhnacademy.auth.user.controller;

import com.nhnacademy.auth.user.dto.reponse.GradeDto;
import com.nhnacademy.auth.user.entity.Grade;
import com.nhnacademy.auth.user.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class GradeController {
    private final GradeService gradeService;

    @GetMapping("/grade/{id}")
    public ResponseEntity<GradeDto> getGrade(@PathVariable Long id) {
        return ResponseEntity.ok().body(gradeService.getGrade(id));
    }

    @PostMapping("/grade/create")
    public ResponseEntity<GradeDto> createGrade(@RequestBody GradeDto gradeDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gradeService.createGrade(gradeDto));
    }

    @PutMapping("/grade/{id}")
    public ResponseEntity<GradeDto> updateGrade(@PathVariable Long id, @RequestBody GradeDto gradeDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gradeService.modifyGrade(id, gradeDto));
    }

    @DeleteMapping("/grade/{id}")
    public ResponseEntity<GradeDto> deleteGrade(@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
