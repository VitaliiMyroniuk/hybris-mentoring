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
package org.training.components.navigationarea;

import de.hybris.platform.cockpit.components.navigationarea.DefaultNavigationAreaModel;
import de.hybris.platform.cockpit.session.impl.AbstractUINavigationArea;

import org.training.session.impl.CustomproductcockpitNavigationArea;


/**
 * Customproductcockpit navigation area model.
 */
public class CustomproductcockpitNavigationAreaModel extends DefaultNavigationAreaModel
{
	public CustomproductcockpitNavigationAreaModel()
	{
		super();
	}

	public CustomproductcockpitNavigationAreaModel(final AbstractUINavigationArea area)
	{
		super(area);
	}

	@Override
	public CustomproductcockpitNavigationArea getNavigationArea()
	{
		return (CustomproductcockpitNavigationArea) super.getNavigationArea();
	}
}
