package org.training.facades.impl;

import de.hybris.platform.commercefacades.product.data.ReviewData;
import de.hybris.platform.commercefacades.product.impl.DefaultProductVariantFacade;
import org.springframework.beans.factory.annotation.Required;

public class CustomProductFacade extends DefaultProductVariantFacade {

    private Double minRating;

    @Override
    public ReviewData postReview(final String productCode, final ReviewData reviewData) {
        if (reviewData.getRating().compareTo(minRating) < 0) {
            return reviewData;
        }
        return super.postReview(productCode, reviewData);
    }

    public Double getMinRating() {
        return minRating;
    }

    @Required
    public void setMinRating(Double minRating) {
        this.minRating = minRating;
    }
}