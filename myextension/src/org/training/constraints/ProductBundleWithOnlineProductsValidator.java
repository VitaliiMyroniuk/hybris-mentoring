package org.training.constraints;

import de.hybris.platform.core.model.product.ProductModel;
import org.training.model.ProductBundleModel;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class ProductBundleWithOnlineProductsValidator implements ConstraintValidator<ProductBundleWithOnlineProducts, ProductBundleModel> {
    @Override
    public void initialize(ProductBundleWithOnlineProducts productBundleWithOnlineProducts) {
    }

    @Override
    public boolean isValid(ProductBundleModel productBundleModel, ConstraintValidatorContext constraintValidatorContext) {
        for (ProductModel productModel : productBundleModel.getProducts()) {
            if (isProductOffline(productModel)) {
                return false;
            }
        }
        return true;
    }

    private boolean isProductOffline(ProductModel productModel) {
        Date currentDate = new Date();
        Date onlineDate = productModel.getOnlineDate();
        Date offlineDate = productModel.getOfflineDate();
        return (onlineDate != null && currentDate.before(onlineDate)) || (offlineDate != null && currentDate.after(offlineDate));
    }
}