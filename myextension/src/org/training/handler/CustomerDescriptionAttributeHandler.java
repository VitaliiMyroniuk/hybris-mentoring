package org.training.handler;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;
import org.apache.commons.collections4.CollectionUtils;
import java.util.Collection;

public class CustomerDescriptionAttributeHandler implements DynamicAttributeHandler<String, CustomerModel> {
    @Override
    public String get(CustomerModel customerModel) {
        String name = customerModel.getName();
        String email = customerModel.getEmail();
        Collection<OrderModel> orders = getOrders(customerModel);
        return buildCustomerDescription(name, email, orders);
    }

    @Override
    public void set(CustomerModel customerModel, String value) {
        throw new UnsupportedOperationException();
    }

    private Collection<OrderModel> getOrders(CustomerModel customerModel) {
        Collection<OrderModel> orders = customerModel.getOrders();
        return CollectionUtils.emptyIfNull(orders);
    }

    private String buildCustomerDescription(String name, String email, Collection<OrderModel> orders) {
        StringBuilder sb = new StringBuilder()
                .append(name)
                .append(":")
                .append(email)
                .append(". Order quantity is ")
                .append(orders.size())
                .append(".");
        return sb.toString();
    }
}

