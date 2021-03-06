package org.training.core.workflow;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.workflow.model.WorkflowActionModel;
import de.hybris.platform.workflow.model.WorkflowDecisionModel;
import org.training.core.enums.RegistrationStatus;

public class ConfirmedUserActionJob extends AbstractRegistrationJob {

    @Override
    public WorkflowDecisionModel perform(WorkflowActionModel workflowActionModel) {
        CustomerModel user = getAttachedUser(workflowActionModel);
        user.setRegistrationStatus(RegistrationStatus.CONFIRMED);
        getModelService().save(user);
        return getDecision(workflowActionModel);
    }
}