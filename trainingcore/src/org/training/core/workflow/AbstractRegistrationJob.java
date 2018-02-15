package org.training.core.workflow;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.workflow.jobs.AutomatedWorkflowTemplateJob;
import de.hybris.platform.workflow.model.WorkflowActionModel;
import de.hybris.platform.workflow.model.WorkflowDecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public abstract class AbstractRegistrationJob implements AutomatedWorkflowTemplateJob {

    @Autowired
    private ModelService modelService;

    protected CustomerModel getAttachedUser(final WorkflowActionModel workflowActionModel) {
        final List<ItemModel> attachments = workflowActionModel.getAttachmentItems();

        if (attachments != null) {
            for (final ItemModel item : attachments) {
                if (item instanceof CustomerModel) {
                    return (CustomerModel) item;
                }
            }
        }
        return null;
    }

    protected WorkflowDecisionModel getDecision(WorkflowActionModel workflowActionModel) {
        for (WorkflowDecisionModel decisionModel : workflowActionModel.getDecisions()) {
            return decisionModel;
        }
        return null;
    }

    public ModelService getModelService() {
        return modelService;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }
}