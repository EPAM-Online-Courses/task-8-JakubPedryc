package efs.task.unittests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FitCalculatorTest {

    @Test
    void shouldReturnTrue_whenDietRecommended() {
        //given
        double weight = 89.2;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @Test
    void shouldReturnFalse_whenDietRecommended() {
        //given
        double weight = 69.5;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @Test
    void shouldReturnException_whenDietRecommended() {
        double weight = 89.2;
        double height = 0;

        //when and then
        assertThrows(IllegalArgumentException.class, () -> {
            FitCalculator.isBMICorrect(weight, height);
        });
    }
    @ParameterizedTest(name = "Test {index}: height={0}, weight={1}")
    @ValueSource(doubles = {80.0, 85.0, 90.0} )
    void shouldReturnTrueForAllWeights_whenDietRecommended(double weight) {

        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @ParameterizedTest(name = "Test {index}: height={0}, weight={1}")
    @CsvSource({
            "50.2, 1.72",
            "70.2, 1.92",
            "60.2, 1.8"
    })
    void shouldReturnFalseForAllPairs_whenDietRecommended(double weight, double height) {

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @ParameterizedTest(name = "Test {index}: height={0}, weight={1}")
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void shouldReturnFalseForCsv_whenDietRecommended(double height, double weight) {
        // when
        boolean result = FitCalculator.isBMICorrect(weight, height);

        // then
        assertFalse(result);
    }

    @Test
    void shouldReturnUserWithWorstBMI() {
        List<User> userList = TestConstants.TEST_USERS_LIST;

        // Znajdź użytkownika z najgorszym wynikiem BMI
        User userWithWorstBMI = FitCalculator.findUserWithTheWorstBMI(userList);

        // Sprawdź, czy znaleziony użytkownik ma oczekiwane wartości wagi i wzrostu
        assertNotNull(userWithWorstBMI);
        assertEquals(97.3, userWithWorstBMI.getWeight());
        assertEquals(1.79, userWithWorstBMI.getHeight());
    }

    @Test
    void shouldReturnNullForEmptyUserList() {
        List<User> emptyUserList = Collections.emptyList();

        // Znajdź użytkownika z najgorszym wynikiem BMI
        User userWithWorstBMI = FitCalculator.findUserWithTheWorstBMI(emptyUserList);

        // Sprawdź, czy metoda zwraca null dla pustej listy użytkowników
        assertNull(userWithWorstBMI);
    }

    @Test
    void shouldReturnExpectedBMIScoreForUserList() {
        List<User> userList = TestConstants.TEST_USERS_LIST;
        double[] expectedBMIScores = TestConstants.TEST_USERS_BMI_SCORE;

        // Oblicz wyniki BMI dla listy użytkowników
        double[] calculatedBMIScores = FitCalculator.calculateBMIScore(userList);

        // Sprawdź, czy obliczone wyniki BMI są zgodne z oczekiwanymi
        assertArrayEquals(expectedBMIScores, calculatedBMIScores, 0.01);
    }



}