package org.jakegodsall.reppd.controllers;

import lombok.RequiredArgsConstructor;
import org.jakegodsall.reppd.dtos.GoalDto;
import org.jakegodsall.reppd.entities.enums.Status;
import org.jakegodsall.reppd.services.GoalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
        return null;
    }

    @PostMapping(API_V1_GOAL)
    public ResponseEntity<GoalDto> createGoal(@RequestBody GoalDto goalDto) {
        return null;
    }

    @GetMapping(API_V1_GOAL_DETAIL)
    public ResponseEntity<GoalDto> getGoalById(@PathVariable("goalId") UUID goalID) {
        return null;
    }

    @PutMapping(API_V1_GOAL_DETAIL)
    public ResponseEntity<GoalDto> updateGoalById(
            @PathVariable("goalId") UUID goalId,
            @RequestBody GoalDto goalDto
    ) {
        return null;
    }

    @PatchMapping(API_V1_GOAL_DETAIL)
    public ResponseEntity<GoalDto> updateGoalPatchById(
            @PathVariable("goalId") UUID goalId,
            @RequestBody GoalDto goalDto
    ) {
        return null;
    }

    @DeleteMapping(API_V1_GOAL_DETAIL)
    public ResponseEntity<Void> deleteGoalById(@PathVariable("goalId") UUID goalId) {
        return null;
    }
}
