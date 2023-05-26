package efs.task.unittests;

import efs.task.unittests.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlannerTest {

    private Planner planner;
    private User testUser;

    @BeforeEach
    void setup() {
        planner = new Planner();
        testUser = TestConstants.TEST_USER;
    }

    @ParameterizedTest
    @EnumSource(ActivityLevel.class)
    void calculateDailyCaloriesDemand_returnsCorrectCaloriesDemand(ActivityLevel activityLevel) {
        // Given
        int expectedCalories = TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(activityLevel);

        // When
        int calculatedCalories = planner.calculateDailyCaloriesDemand(testUser, activityLevel);

        // Then
        assertEquals(expectedCalories, calculatedCalories);
    }

    @Test
    void calculateDailyIntake_returnsCorrectDailyIntake() {
        // Given
        DailyIntake expectedDailyIntake = TestConstants.TEST_USER_DAILY_INTAKE;

        // When
        DailyIntake calculatedDailyIntake = planner.calculateDailyIntake(testUser);

        // Then
        assertEquals(expectedDailyIntake.getCalories(), calculatedDailyIntake.getCalories());
        assertEquals(expectedDailyIntake.getProtein(), calculatedDailyIntake.getProtein());
        assertEquals(expectedDailyIntake.getFat(), calculatedDailyIntake.getFat());
        assertEquals(expectedDailyIntake.getCarbohydrate(), calculatedDailyIntake.getCarbohydrate());
    }
}
