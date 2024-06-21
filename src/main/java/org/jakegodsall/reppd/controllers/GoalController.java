package org.jakegodsall.reppd.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jakegodsall.reppd.dtos.CompetencyDTO;
import org.jakegodsall.reppd.exceptions.NotFoundException;
import org.jakegodsall.reppd.services.CompetencyService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class GoalController {

    public static final String API_V1_GOAL = "/api/v1/goal";
    public static final String API_V1_GOAL_DETAIL = API_V1_GOAL + "/{goalId}";

    private final CompetencyService competencyService;

    @GetMapping(API_V1_GOAL)
    public ResponseEntity<List<CompetencyDTO>> getAllGoals() {
        List<CompetencyDTO> goals = competencyService.getAllGoals();
        return ResponseEntity.ok(goals);
    }

    @PostMapping(API_V1_GOAL)
    public ResponseEntity<CompetencyDTO> createGoal(
            @Valid @RequestBody CompetencyDTO competencyDTO
    ) {
        CompetencyDTO createdCompetencyDTO = competencyService.createGoal(competencyDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", API_V1_GOAL + createdCompetencyDTO.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping(API_V1_GOAL_DETAIL)
    public ResponseEntity<CompetencyDTO> getGoalById(@PathVariable("goalId") UUID goalId) {
        CompetencyDTO competencyDTO = competencyService.getGoalById(goalId).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(competencyDTO);
    }

    @PutMapping(API_V1_GOAL_DETAIL)
    public ResponseEntity<CompetencyDTO> updateGoalById(
            @PathVariable("goalId") UUID goalId,
            @Valid @RequestBody CompetencyDTO competencyDTO
    ) {
        CompetencyDTO updatedCompetencyDTO = competencyService.updateGoalById(goalId, competencyDTO)
                .orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(updatedCompetencyDTO);
    }

    @PatchMapping(API_V1_GOAL_DETAIL)
    public ResponseEntity<CompetencyDTO> updateGoalPatchById(
            @PathVariable("goalId") UUID goalId,
            @RequestBody CompetencyDTO competencyDTO
    ) {
        CompetencyDTO updatedCompetencyDTO = competencyService.updateGoalPatchById(goalId, competencyDTO)
                .orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(updatedCompetencyDTO);
    }

    @DeleteMapping(API_V1_GOAL_DETAIL)
    public ResponseEntity<Void> deleteGoalById(@PathVariable("goalId") UUID goalId) {
        if (!competencyService.deleteGoalById(goalId)) {
            throw new NotFoundException();
        }
        return ResponseEntity.noContent().build();
    }
}
