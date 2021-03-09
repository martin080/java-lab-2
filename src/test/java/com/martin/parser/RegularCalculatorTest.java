package com.martin.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegularCalculatorTest {

    private Calculator calculator = new RegularCalculator();

    @Test
    public void plusTest() throws Exception{
        assertEquals(10d, calculator.calculate("1 + 9"));
        assertEquals(10d, calculator.calculate("1 + 8 + 1"));
        assertEquals(10d, calculator.calculate("1+ 7 + 1 +1"));
        assertEquals(9d, calculator.calculate("1 +8"));
        assertEquals(9d, calculator.calculate("8+ 1"));
    }

    @Test
    public void minusTest() throws Exception {
        assertEquals(7d, calculator.calculate("9 - 2"));
        assertEquals(7d, calculator.calculate("10- 3"));
        assertEquals(7d, calculator.calculate("10-          3"));
        assertEquals(7d, calculator.calculate("    10   - 3"));
        assertEquals(7d, calculator.calculate(" 10-    3        "));
        assertEquals(7d, calculator.calculate("12 - 3 - 2"));
    }

    @Test
    public void multiplicationTest() throws Exception{
        assertEquals(0.5d, calculator.calculate("1 * 0.5"));
        assertEquals(10d, calculator.calculate("   2 *     5"));
        assertEquals(17d, calculator.calculate("17 * 1"));
        assertEquals(17d, calculator.calculate("1 * 17"));
        assertEquals(12d, calculator.calculate(" 6 * 2"));
        assertEquals(17d, calculator.calculate("34 * 0.5"));
    }

    @Test
    public void divisionTest() throws Exception {
        assertEquals(2d, calculator.calculate("4 /   2"));
        assertEquals(2d, calculator.calculate("4 /   (1 + 1)"));
        assertEquals(3d, calculator.calculate("12 / (3 + 1)   "));
        assertEquals(3.5d, calculator.calculate("7 /  2"));
    }

    @Test
    public void parenthesisTest() throws Exception {
        assertEquals(3d, calculator.calculate("(1 + 5) / 2"));
        assertEquals(3d, calculator.calculate("(1 + (4 + 1)) / 2"));
        assertEquals(3d, calculator.calculate("((2 - 1) + 5) / (1 + 1)"));
        assertEquals(3d, calculator.calculate("(1 + (5 - 2 + (1 + 1))) / 2"));
        assertEquals(3d, calculator.calculate("(1 + 5) / (12.2 - 10.2)"));
    }

    @Test
    public void badInputTest(){
        assertThrows(Exception.class, () -> calculator.calculate("1 -"));
        assertThrows(Exception.class, () -> calculator.calculate("1 - a + 1"));
        assertThrows(Exception.class, () -> calculator.calculate("1  a + 1"));
        assertThrows(Exception.class, () -> calculator.calculate("1  10.12 + 1"));
        assertThrows(Exception.class, () -> calculator.calculate("1- +-10.12 + 1"));
        assertThrows(Exception.class, () -> calculator.calculate("."));
    }



}
