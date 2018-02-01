package org.training.facades.populators;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.ChronoUnit.DAYS;

public class DaysBeforeOfflineProductPopulator implements Populator<ProductModel, ProductData> {

    @Override
    public void populate(ProductModel productModel, ProductData productData) throws ConversionException {
        if(productModel.getOfflineDate() == null) {
            return;
        }

        LocalDate offlineDate = productModel.getOfflineDate()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        int days = (int) DAYS.between(LocalDate.now(), offlineDate);
        if (days <= 0) {
            return;
        }

        productData.setDaysBeforeOffline(days);
    }
}