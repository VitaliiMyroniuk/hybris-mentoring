package mypackage.handler;

import de.hybris.platform.core.model.user.CustomerModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
        when(customerModel.getName()).thenReturn("Ivan");
        when(customerModel.getEmail()).thenReturn("Ivanov@gmail.com");
        String customerDescription = attributeHandler.get(customerModel);
        verify(customerModel, times(1)).getName();
        verify(customerModel, times(1)).getEmail();
        assertEquals(customerDescription, "Ivan:Ivanov@gmail.com. Order quantity is 0.");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void setMethodShouldProduceUnsupportedOperationException() {
        attributeHandler.set(customerModel, "Some value");
    }
}