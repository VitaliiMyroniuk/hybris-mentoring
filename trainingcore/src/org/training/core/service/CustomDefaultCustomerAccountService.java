package org.training.core.service;

import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.commerceservices.customer.impl.DefaultCustomerAccountService;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.workflow.WorkflowProcessingService;
import de.hybris.platform.workflow.WorkflowService;
import de.hybris.platform.workflow.WorkflowTemplateService;
import de.hybris.platform.workflow.model.WorkflowActionModel;
import de.hybris.platform.workflow.model.WorkflowModel;
import de.hybris.platform.workflow.model.WorkflowTemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomDefaultCustomerAccountService extends DefaultCustomerAccountService {

    private static final String WORKFLOW_TEMPLATE_NAME = "NewUserRegistration";

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private WorkflowTemplateService workflowTemplateService;

    @Autowired
    private WorkflowProcessingService workflowProcessingService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelService modelService;

    @Override
    public void register(CustomerModel customerModel, String password) throws DuplicateUidException {
        super.register(customerModel, password);

        WorkflowTemplateModel workflowTemplateModel = this.workflowTemplateService.getWorkflowTemplateForCode(WORKFLOW_TEMPLATE_NAME);
        WorkflowModel workflowModel = this.workflowService.createWorkflow(workflowTemplateModel, customerModel, userService.getAdminUser());
        modelService.save(workflowModel);

        for (WorkflowActionModel actionModel : workflowModel.getActions()) {
            modelService.save(actionModel);
        }
        this.workflowProcessingService.startWorkflow(workflowModel);
    }
}