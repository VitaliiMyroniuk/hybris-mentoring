package org.training.core.workflow;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.workflow.model.WorkflowActionModel;
import de.hybris.platform.workflow.model.WorkflowDecisionModel;

public class RegistrationDeclineActionJob extends AbstractUserRegistrationActionJob {

    @Override
    public WorkflowDecisionModel perform(WorkflowActionModel workflowActionModel) {
        CustomerModel user = getAttachedUser(workflowActionModel);

        getModelService().remove(user);

        for (WorkflowDecisionModel decisionModel : workflowActionModel.getDecisions()) {
            return decisionModel;
        }
        return null;
    }
}