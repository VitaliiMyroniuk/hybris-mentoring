package org.training.facades.populators;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class DaysBeforeOfflineProductPopulatorTest {

    private static final Integer DAYS_BEFORE_OFFLINE = 5;

    private static final Integer ZERO = 0;

    private static final Integer NEGATIVE_INTEGER_NUMBER = -1;

    private DaysBeforeOfflineProductPopulator daysBeforeOfflineProductPopulator;

    private ProductData productData;

    @Mock
    private ProductModel productModel;

    @Before
    public void init() {
        daysBeforeOfflineProductPopulator = new DaysBeforeOfflineProductPopulator();
        productData = new ProductData();
    }

    @Test
    public void shouldPopulateExpectedCountOfDaysBeforeOffline() {
        Date productOfflineDate = getDateWhenProductBecomeOffline(DAYS_BEFORE_OFFLINE);
        when(productModel.getOfflineDate()).thenReturn(productOfflineDate);

        daysBeforeOfflineProductPopulator.populate(productModel, productData);

        assertEquals(DAYS_BEFORE_OFFLINE, productData.getDaysBeforeOffline());
    }

    @Test
    public void shouldPopulateNothingWhenCountOfDaysBeforeOfflineIsNull() {
        when(productModel.getOfflineDate()).thenReturn(null);

        daysBeforeOfflineProductPopulator.populate(productModel, productData);

        assertNull(productData.getDaysBeforeOffline());
    }

    @Test
    public void shouldPopulateNothingWhenCountOfDaysBeforeOfflineIsZero() {
        Date productOfflineDate = getDateWhenProductBecomeOffline(ZERO);
        when(productModel.getOfflineDate()).thenReturn(productOfflineDate);

        daysBeforeOfflineProductPopulator.populate(productModel, productData);

        assertNull(productData.getDaysBeforeOffline());
    }

    @Test
    public void shouldPopulateNothingWhenCountOfDaysBeforeOfflineIsNegative() {
        Date productOfflineDate = getDateWhenProductBecomeOffline(NEGATIVE_INTEGER_NUMBER);
        when(productModel.getOfflineDate()).thenReturn(productOfflineDate);

        daysBeforeOfflineProductPopulator.populate(productModel, productData);

        assertNull(productData.getDaysBeforeOffline());
    }

    private Date getDateWhenProductBecomeOffline(Integer days) {
        LocalDate productOfflineDate = LocalDate.now().plusDays(days);
        return Date.from(productOfflineDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}