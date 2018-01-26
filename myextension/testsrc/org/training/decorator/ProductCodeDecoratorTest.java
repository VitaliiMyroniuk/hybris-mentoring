package org.training.decorator;

import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;

public class ProductCodeDecoratorTest {

    private static final String TEST_STRING = "Test String";

    private static final String LOWERCASE_AND_WITHOUT_SPACES_TEST_STRING = "teststring";

    private static final Integer COLUMN_POSITION = 1;

    @Test
    public void shouldReturnLowercaseAndWithoutSpacesString() {
        ProductCodeDecorator productCodeDecorator = new ProductCodeDecorator();
        Map<Integer, String> srcLine = new HashMap<>();
        srcLine.put(COLUMN_POSITION, TEST_STRING);

        String resultString = productCodeDecorator.decorate(COLUMN_POSITION, srcLine);

        assertEquals(LOWERCASE_AND_WITHOUT_SPACES_TEST_STRING, resultString);
    }
}