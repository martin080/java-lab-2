package com.martin.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

public class VariableCalculatorTest {

    VariableCalculator calculator = new VariableCalculator();

    @Test
    public void multiplicationTest() throws Exception{
        Map<String, Double> vars = Map.of("a", 10d, "b", 20d);
        calculator.setVariables(vars);

        assertEquals(200d, calculator.calculate("a * b"));
        assertEquals(200d, calculator.calculate("b *   a"));
        assertEquals(100d, calculator.calculate("a * b * 0.5"));
        assertEquals(100d, calculator.calculate("a * 0.5 * b"));
        assertEquals(100d, calculator.calculate("0.5 * b * a"));
        assertEquals(100d, calculator.calculate("a * 0.5 * b"));
    }

    @Test
    public void divisionTest() throws Exception{
        Map<String, Double> vars = Map.of("a", 10d, "b", 20d);
        calculator.setVariables(vars);

        assertEquals(2d, calculator.calculate("b / a"));
        assertEquals(1, calculator.calculate("b / (a + 10)"));
        assertEquals(20d / (10d + 1), calculator.calculate("b / (a + 1)"));
        assertEquals((10d + 15) / 20d, calculator.calculate("(a + 15) / b"));
    }

    @Test
    public void plusTest() throws Exception{
        Map<String, Double> vars = Map.of("a", 10d, "b", 20d);
        calculator.setVariables(vars);

        assertEquals(30d, calculator.calculate("b + a"));
        assertEquals(30d, calculator.calculate("a + b"));
        assertEquals(40d, calculator.calculate("a + b + 10.0"));
        assertEquals(40d, calculator.calculate("10 + a + b"));
        assertEquals(60d, calculator.calculate("a + b + b + a"));
        assertEquals(20d, calculator.calculate("a + (-a) + b"));
    }

    @Test
    public void longNameTest() throws Exception{
        Map<String, Double> vars = Map.of("ab", 10d, "ba", 20d);
        calculator.setVariables(vars);

        assertEquals(200d, calculator.calculate("ab * ba"));
        assertEquals(200d, calculator.calculate("ba * ab"));
        assertEquals(2000d, calculator.calculate("ab * ab * ba"));
        assertEquals(10d, calculator.calculate("ab * ba / ba"));
        assertEquals(1d, calculator.calculate("ab * ba / (ba * ab)"));
    }

    @Test
    public void variablesNotFoundTest() {
        assertThrows(Exception.class, () -> calculator.calculate("a + 10"));
        assertThrows(Exception.class, () -> calculator.calculate("a / 10"));
        assertThrows(Exception.class, () -> calculator.calculate("a * 10"));
        assertThrows(Exception.class, () -> calculator.calculate("10 / a"));
        assertThrows(Exception.class, () -> calculator.calculate("a +10 - a + b"));
    }


}
