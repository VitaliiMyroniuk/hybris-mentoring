package org.training.decorator;

import de.hybris.platform.util.CSVCellDecorator;
import java.util.Map;

public class ProductCodeDecorator implements CSVCellDecorator {

    private static final String SPACE = " ";

    private static final String EMPTY_STRING = "";

    @Override
    public String decorate(int position, Map<Integer, String> srcLine) {
        String parsedValue = srcLine.get(position);
        return parsedValue.toLowerCase().replace(SPACE, EMPTY_STRING);
    }
}