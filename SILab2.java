import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Arrays;

public class SILab2Test {

    @Test
    void testStatementCoverage() {
        // Тест 1: Листата е null
        RuntimeException ex1 = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, "1234567890123456"));
        assertEquals("allItems list can't be null!", ex1.getMessage());

        // Тест 2: Предмет со невалидно име
        List<Item> items2 = Arrays.asList(new Item(null, 1, 100, 0));
        RuntimeException ex2 = assertThrows(RuntimeException.class, () -> SILab2.checkCart(items2, "1234567890123456"));
        assertEquals("Invalid item!", ex2.getMessage());

        // Тест 3: Валиден предмет без попуст/казна, валидна картичка
        List<Item> items3 = Arrays.asList(new Item("Book", 1, 100, 0));
        assertEquals(100.0, SILab2.checkCart(items3, "1234567890123456"));

        // Тест 4: Валиден предмет со попуст и казна, валидна картичка
        List<Item> items4 = Arrays.asList(new Item("Pen", 11, 400, 0.1));
        assertEquals(3920.0, SILab2.checkCart(items4, "1234567890123456"));
    }

    // Multiple Condition Coverage тестови за if (price > 300 || discount > 0 || quantity > 10)
    @Test
    void testMultipleConditionCoverage() {
        String validCard = "1234567890123456";
        // 1. false, false, false
        assertEquals(100.0, SILab2.checkCart(Arrays.asList(new Item("A", 10, 300, 0)), validCard));
        // 2. false, false, true
        assertEquals(70.0, SILab2.checkCart(Arrays.asList(new Item("A", 11, 300, 0)), validCard));
        // 3. false, true, false
        assertEquals(270.0, SILab2.checkCart(Arrays.asList(new Item("A", 10, 300, 0.1)), validCard));
        // 4. false, true, true
        assertEquals(267.0, SILab2.checkCart(Arrays.asList(new Item("A", 11, 300, 0.1)), validCard));
        // 5. true, false, false
        assertEquals(70.0, SILab2.checkCart(Arrays.asList(new Item("A", 10, 301, 0)), validCard));
        // 6. true, false, true
        assertEquals(70.0, SILab2.checkCart(Arrays.asList(new Item("A", 11, 301, 0)), validCard));
        // 7. true, true, false
        assertEquals(270.9, SILab2.checkCart(Arrays.asList(new Item("A", 10, 301, 0.1)), validCard));
        // 8. true, true, true
        assertEquals(267.9, SILab2.checkCart(Arrays.asList(new Item("A", 11, 301, 0.1)), validCard));
    }
}
