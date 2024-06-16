package org.jakegodsall.reppd.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jakegodsall.reppd.dtos.GoalDto;
import org.jakegodsall.reppd.exceptions.NotFoundException;
import org.jakegodsall.reppd.services.GoalService;
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

    private final GoalService goalService;

    @GetMapping(API_V1_GOAL)
    public ResponseEntity<List<GoalDto>> getAllGoals() {
        List<GoalDto> goals = goalService.getAllGoals();
        return ResponseEntity.ok(goals);
    }

    @PostMapping(API_V1_GOAL)
    public ResponseEntity<GoalDto> createGoal(
            @Valid @RequestBody GoalDto goalDto
    ) {
        GoalDto createdGoalDto = goalService.createGoal(goalDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", API_V1_GOAL + createdGoalDto.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping(API_V1_GOAL_DETAIL)
    public ResponseEntity<GoalDto> getGoalById(@PathVariable("goalId") UUID goalId) {
        GoalDto goalDto = goalService.getGoalById(goalId).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(goalDto);
    }

    @PutMapping(API_V1_GOAL_DETAIL)
    public ResponseEntity<GoalDto> updateGoalById(
            @PathVariable("goalId") UUID goalId,
            @Valid @RequestBody GoalDto goalDto
    ) {
        GoalDto updatedGoalDto = goalService.updateGoalById(goalId, goalDto)
                .orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(updatedGoalDto);
    }

    @PatchMapping(API_V1_GOAL_DETAIL)
    public ResponseEntity<GoalDto> updateGoalPatchById(
            @PathVariable("goalId") UUID goalId,
            @RequestBody GoalDto goalDto
    ) {
        GoalDto updatedGoalDto = goalService.updateGoalPatchById(goalId, goalDto)
                .orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(updatedGoalDto);
    }

    @DeleteMapping(API_V1_GOAL_DETAIL)
    public ResponseEntity<Void> deleteGoalById(@PathVariable("goalId") UUID goalId) {
        if (!goalService.deleteGoalById(goalId)) {
            throw new NotFoundException();
        }
        return ResponseEntity.noContent().build();
    }
}
