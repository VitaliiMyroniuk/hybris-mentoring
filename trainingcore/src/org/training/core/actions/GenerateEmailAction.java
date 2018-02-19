package org.training.core.actions;

import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.processengine.action.AbstractProceduralAction;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.task.RetryLaterException;
import de.hybris.platform.util.Config;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.training.core.model.AccountUpdateProcessModel;
import java.util.ArrayList;
import java.util.List;

public class GenerateEmailAction extends AbstractProceduralAction<AccountUpdateProcessModel> {

    private static final String MESSAGE = "Your account was successfully updated.";

    private static final String SUBJECT = "Account update";

    private static final String FROM_EMAIL_ADDRESS = Config.getParameter("mail.from");

    private static final String REPLY_TO_EMAIL_ADDRESS = Config.getParameter("mail.replyto");

    private static final String DISPLAY_NAME = "TELCO";

    @Autowired
    private ModelService modelService;

    @Autowired
    private EmailService emailService;

    @Override
    public void executeAction(AccountUpdateProcessModel processModel) throws RetryLaterException, Exception {
        EmailMessageModel emailMessageModel = modelService.create(EmailMessageModel.class);
        emailMessageModel.setFromAddress(getFromEmailAddressModel());
        emailMessageModel.setToAddresses(getToEmailAddressModels(processModel));
        emailMessageModel.setReplyToAddress(REPLY_TO_EMAIL_ADDRESS);
        emailMessageModel.setSubject(SUBJECT);
        emailMessageModel.setBody(MESSAGE);
        addEmailMessageModelToProcessModel(emailMessageModel, processModel);
    }

    private void addEmailMessageModelToProcessModel(EmailMessageModel emailMessageModel, AccountUpdateProcessModel processModel) {
        List<EmailMessageModel> emails = new ArrayList<>(processModel.getEmails());
        emails.add(emailMessageModel);
        processModel.setEmails(emails);
        modelService.save(processModel);
    }

    private EmailAddressModel getFromEmailAddressModel() {
        return emailService.getOrCreateEmailAddressForEmail(FROM_EMAIL_ADDRESS, DISPLAY_NAME);
    }

    private List<EmailAddressModel> getToEmailAddressModels(AccountUpdateProcessModel processModel) {
        String recipientEmailAddress = processModel.getRecipientEmailAddress();
        EmailAddressModel emailAddressModel = emailService.getOrCreateEmailAddressForEmail(recipientEmailAddress, Strings.EMPTY);
        List<EmailAddressModel> emailAddressModels = new ArrayList<>();
        emailAddressModels.add(emailAddressModel);
        return emailAddressModels;
    }
}