import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {



    @Test
    void constructorShouldThrowIllegalArgumentExceptionWhenArgumentIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }


    @Test
    void constructorShouldThrowIllegalArgumentExceptionWhenHorsesListIsEmpty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(Collections.emptyList()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }


    @BeforeEach
    void setUpTest() {
    }

    @AfterEach
    void tearDownTest() {
    }

    @Test
    void getHorsesTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse" + i, i, i));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> resultList = hippodrome.getHorses();

        assertEquals(horses, resultList, "The list returned by getHorses should contain the same objects in the same sequence");

    }

    @Test
    void move() {

        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        for (Horse horse : horses) {
            verify(horse).move();
        }

    }

    @Test
    void getWinnerTest() {
        Horse horseOne = new Horse("horse 1", 2, 10);
        Horse horseBigDistance = new Horse("horse 1", 15, 100);
        Horse horseTwo = new Horse("horse 1", 3, 5);
        List<Horse> horses = List.of(horseOne,horseTwo,horseBigDistance);
        Hippodrome hippodrome = new Hippodrome(horses);

        Horse winner = hippodrome.getWinner();

        assertEquals(horseBigDistance, winner);

    }
}
