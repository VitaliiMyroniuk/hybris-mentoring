package org.training.job;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.util.CSVWriter;
import org.training.service.ProductModelService;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExportProductCodesJob extends AbstractJobPerformable<CronJobModel> {

    private ProductModelService productModelService;

    private Integer daysBeforeOffline;

    private String path;

    @Override
    public PerformResult perform(CronJobModel cronJobModel) {
        List<ProductModel> productModels = productModelService.findProductsThatBecomeOffline(daysBeforeOffline);

        try (FileWriter fileWriter = new FileWriter(path);
             CSVWriter csvWriter = new CSVWriter(fileWriter)) {
            Map<Integer, String> map = getMapOfProductCodes(productModels);
            csvWriter.write(Arrays.asList(Collections.singletonMap(0, "product_codes"), map));
        } catch (IOException e) {
            return new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
        }

        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    private Map<Integer, String> getMapOfProductCodes(List<ProductModel> productModels) {
        Map<Integer, String> result = new HashMap<>();
        for (int i = 0; i < productModels.size(); i++) {
            result.put(i, productModels.get(i).getCode());
        }
        return result;
    }

    public ProductModelService getProductModelService() {
        return productModelService;
    }

    public void setProductModelService(ProductModelService productModelService) {
        this.productModelService = productModelService;
    }

    public Integer getDaysBeforeOffline() {
        return daysBeforeOffline;
    }

    public void setDaysBeforeOffline(Integer daysBeforeOffline) {
        this.daysBeforeOffline = daysBeforeOffline;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}