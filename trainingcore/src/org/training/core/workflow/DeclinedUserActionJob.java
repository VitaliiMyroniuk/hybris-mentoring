package org.training.core.workflow;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.workflow.model.WorkflowActionModel;
import de.hybris.platform.workflow.model.WorkflowDecisionModel;

public class DeclinedUserActionJob extends AbstractRegistrationJob {

    @Override
    public WorkflowDecisionModel perform(WorkflowActionModel workflowActionModel) {
        CustomerModel user = getAttachedUser(workflowActionModel);
        getModelService().remove(user);
        return getDecision(workflowActionModel);
    }
}