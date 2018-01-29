package org.training.interceptor;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class UserAgeValidateInterceptorTest {

    private static final Integer AGE_RESTRICTION = 12;

    @Mock
    private UserModel userModel;

    @Mock
    private InterceptorContext context;

    private UserAgeValidateInterceptor userAgeValidateInterceptor;

    @Before
    public void init() {
        userAgeValidateInterceptor = new UserAgeValidateInterceptor(AGE_RESTRICTION);
    }

    @Test
    public void shouldPassValidationForPermittedAge() throws InterceptorException {
        Date dateOfBirth = getDateOfBirthForUser(AGE_RESTRICTION);
        Mockito.when(userModel.getDateOfBirth()).thenReturn(dateOfBirth);

        userAgeValidateInterceptor.onValidate(userModel, context);
    }

    @Test(expected = InterceptorException.class)
    public void shouldProduceInterceptorExceptionForForbiddenAge() throws InterceptorException {
        Date dateOfBirth = getDateOfBirthForUser(AGE_RESTRICTION - 1);
        Mockito.when(userModel.getDateOfBirth()).thenReturn(dateOfBirth);

        userAgeValidateInterceptor.onValidate(userModel, context);
    }

    private Date getDateOfBirthForUser(Integer userAge) {
        LocalDate dateOfBirth = LocalDate.now().minusYears(userAge);
        return Date.from(dateOfBirth.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}