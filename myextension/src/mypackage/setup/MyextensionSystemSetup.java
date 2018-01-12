/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package mypackage.setup;

import static mypackage.constants.MyextensionConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import mypackage.constants.MyextensionConstants;
import mypackage.service.MyextensionService;


@SystemSetup(extension = MyextensionConstants.EXTENSIONNAME)
public class MyextensionSystemSetup
{
	private final MyextensionService myextensionService;

	public MyextensionSystemSetup(final MyextensionService myextensionService)
	{
		this.myextensionService = myextensionService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		myextensionService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return MyextensionSystemSetup.class.getResourceAsStream("/myextension/sap-hybris-platform.png");
	}
}
