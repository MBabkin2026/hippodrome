import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {


    @Test
    void constructorShouldThrowIllegalArgumentExceptionWhenNameIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 10, 5);
        });
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    void constructorShouldThrowIllegalArgumentExceptionWhenNameIsBlank(String blankName) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(blankName, 10, 5);
        });
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void constructorShouldThrowIllegalArgumentExceptionWhenSpeedIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("ValidName", -1, 5);
        });
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void constructorShouldThrowIllegalArgumentExceptionWhenDistanceIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("ValidName", 10, -5);
        });
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }


    @BeforeEach
    void setUpTest() {
    }

    @AfterEach
    void tearDownTest() {
    }

    @Test
    void getNameTest() {
        String paramName = "name";
        double paramSpeed = 2d;
        double paramDistance = 3d;

        Horse horse = new Horse(paramName, paramSpeed, paramDistance);
        String expected = horse.getName();
        assertInstanceOf(String.class, expected);
        assertEquals(paramName, expected);
    }

    @Test
    void getSpeedTest() {
        String paramName = "name";
        double paramSpeed = 2d;
        double paramDistance = 3d;

        Horse horse = new Horse(paramName, paramSpeed, paramDistance);
        double expected = horse.getSpeed();

        assertEquals(paramSpeed, expected, 0.0001);
    }

    @Test
    void getDistanceTest() {
        String paramName = "name";
        double paramSpeed = 2d;
        double paramDistance = 3d;

        Horse horse = new Horse(paramName, paramSpeed, paramDistance);
        double expected = horse.getDistance();
        assertEquals(paramDistance, expected, 0.0001);
    }

    @Test
    void getZeroDistanceTwoParamsTest() {
        String paramName = "name";
        double paramSpeed = 2d;

        Horse horseTwoParams = new Horse(paramName, paramSpeed);
        double distanceZero = horseTwoParams.getDistance();

        assertEquals(0.0, distanceZero, 0.0001);
    }

    @Test
    void moveTest() {
        String param1 = "строка";
        double param2 = 0.2d;
        double param3 = 0.3d;


        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {

            Horse obj = new Horse(param1, param2, param3);
            obj.move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }

    }

    @ParameterizedTest
    @CsvSource({
            "0.2, 3.4",
            "0.4, 3.8",
            "0.9, 4.8",
    })
    void getRandomDoubleTest(double random, double expected) {
        String name = "строка";
        double speed = 2d;
        double distance = 3d;


        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);
            Horse obj = new Horse(name, speed, distance);
            double result = distance + speed * random;
            obj.move();

            assertEquals(expected, result);
        }

    }
}

