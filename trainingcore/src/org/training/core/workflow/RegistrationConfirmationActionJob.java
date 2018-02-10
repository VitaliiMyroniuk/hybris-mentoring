package org.training.core.workflow;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.workflow.model.WorkflowActionModel;
import de.hybris.platform.workflow.model.WorkflowDecisionModel;

public class RegistrationConfirmationActionJob extends AbstractUserRegistrationActionJob {

    @Override
    public WorkflowDecisionModel perform(WorkflowActionModel workflowActionModel) {
        CustomerModel user = getAttachedUser(workflowActionModel);

        if(!user.isConfirmed()) {
            user.setConfirmed(true);
            user.setLoginDisabled(false);
            getModelService().save(user);
        }

        for(WorkflowDecisionModel decisionModel : workflowActionModel.getDecisions()) {
            return decisionModel;
        }
        return null;
    }
}