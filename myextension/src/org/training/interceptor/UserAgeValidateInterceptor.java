package org.training.interceptor;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.ChronoUnit.YEARS;

public class UserAgeValidateInterceptor implements ValidateInterceptor<UserModel> {

    private static final String EXCEPTION_MESSAGE = "User cannot be younger than 12 years.";

    private Integer ageRestriction;

    public UserAgeValidateInterceptor(Integer ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    @Override
    public void onValidate(UserModel userModel, InterceptorContext context) throws InterceptorException {
        LocalDate dateOfBirth = userModel.getDateOfBirth()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        long userAge = YEARS.between(dateOfBirth, LocalDate.now());

        if (userAge < ageRestriction) {
            throw new InterceptorException(EXCEPTION_MESSAGE);
        }
    }
}