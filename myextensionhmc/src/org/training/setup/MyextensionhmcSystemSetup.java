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
package org.training.setup;

import static org.training.constants.MyextensionhmcConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import org.training.constants.MyextensionhmcConstants;
import org.training.service.MyextensionhmcService;


@SystemSetup(extension = MyextensionhmcConstants.EXTENSIONNAME)
public class MyextensionhmcSystemSetup
{
	private final MyextensionhmcService myextensionhmcService;

	public MyextensionhmcSystemSetup(final MyextensionhmcService myextensionhmcService)
	{
		this.myextensionhmcService = myextensionhmcService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		myextensionhmcService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return MyextensionhmcSystemSetup.class.getResourceAsStream("/myextensionhmc/sap-hybris-platform.png");
	}
}
