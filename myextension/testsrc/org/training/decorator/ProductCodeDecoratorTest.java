package org.training.decorator;

import de.hybris.bootstrap.annotations.UnitTest;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;

@UnitTest
public class ProductCodeDecoratorTest {

    private static final String TEST_STRING = "My Test String";

    private static final String LOWERCASE_WITHOUT_SPACES_TEST_STRING = "myteststring";

    private static final String LOWERCASE_WITHOUT_SPACES_STRING = "string";

    private static final String UPPERCASE_WITHOUT_SPACES_STRING = "STRING";

    private static final String LOWERCASE_WITH_SPACES_STRING = "s t r i n g";

    private static final String SPACES = "   ";

    private static final String EMPTY_STRING = "";

    private static final Integer COLUMN_POSITION = 1;

    private ProductCodeDecorator productCodeDecorator;

    private Map<Integer, String> srcLine;

    @Before
    public void init() {
        productCodeDecorator = new ProductCodeDecorator();
        srcLine = new HashMap<>();
    }

    @Test
    public void shouldReturnEmptyStringForEmptyString() {
        srcLine.put(COLUMN_POSITION, EMPTY_STRING);

        String resultString = productCodeDecorator.decorate(COLUMN_POSITION, srcLine);

        assertEquals(EMPTY_STRING, resultString);
    }

    @Test
    public void shouldReturnEmptyStringForSpaces() {
        srcLine.put(COLUMN_POSITION, SPACES);

        String resultString = productCodeDecorator.decorate(COLUMN_POSITION, srcLine);

        assertEquals(EMPTY_STRING, resultString);
    }

    @Test
    public void shouldReturnUnchangedStringForLowercaseWithoutSpacesString() {
        srcLine.put(COLUMN_POSITION, LOWERCASE_WITHOUT_SPACES_TEST_STRING);

        String resultString = productCodeDecorator.decorate(COLUMN_POSITION, srcLine);

        assertEquals(LOWERCASE_WITHOUT_SPACES_TEST_STRING, resultString);
    }

    @Test
    public void shouldReturnWithoutSpacesStringForLowercaseWithSpacesString() {
        srcLine.put(COLUMN_POSITION, LOWERCASE_WITH_SPACES_STRING);

        String resultString = productCodeDecorator.decorate(COLUMN_POSITION, srcLine);

        assertEquals(LOWERCASE_WITHOUT_SPACES_STRING, resultString);
    }

    @Test
    public void shouldReturnLowercaseStringForUppercaseWithoutSpacesString() {
        srcLine.put(COLUMN_POSITION, UPPERCASE_WITHOUT_SPACES_STRING);

        String resultString = productCodeDecorator.decorate(COLUMN_POSITION, srcLine);

        assertEquals(LOWERCASE_WITHOUT_SPACES_STRING, resultString);
    }

    @Test
    public void shouldReturnLowercaseWithoutSpacesString() {
        srcLine.put(COLUMN_POSITION, TEST_STRING);

        String resultString = productCodeDecorator.decorate(COLUMN_POSITION, srcLine);

        assertEquals(LOWERCASE_WITHOUT_SPACES_TEST_STRING, resultString);
    }
}