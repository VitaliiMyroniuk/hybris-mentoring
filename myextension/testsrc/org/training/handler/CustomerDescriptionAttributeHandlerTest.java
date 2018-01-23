package org.training.handler;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerDescriptionAttributeHandlerTest {

    private CustomerDescriptionAttributeHandler attributeHandler;

    @Mock
    private CustomerModel customerModel;

    @Before
    public void init() {
        attributeHandler = new CustomerDescriptionAttributeHandler();
    }

    @Test
    public void shouldReturnExpectedCustomerDescription() {
        String expectedCustomerDescription = "Ivan:Ivanov@gmail.com. Order quantity is 1.";
        List<OrderModel> orders = new ArrayList<>();
        orders.add(new OrderModel());
        when(customerModel.getName()).thenReturn("Ivan");
        when(customerModel.getEmail()).thenReturn("Ivanov@gmail.com");
        when(customerModel.getOrders()).thenReturn(orders);

        String customerDescription = attributeHandler.get(customerModel);

        verify(customerModel).getName();
        verify(customerModel).getEmail();
        assertEquals(expectedCustomerDescription, customerDescription);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void setMethodShouldProduceUnsupportedOperationException() {
        attributeHandler.set(customerModel, "Some value");
    }
}