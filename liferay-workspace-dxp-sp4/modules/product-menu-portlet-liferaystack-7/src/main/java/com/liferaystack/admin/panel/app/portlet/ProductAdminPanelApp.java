package com.liferaystack.admin.panel.app.portlet;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.model.Portlet;
import com.liferaystack.admin.panel.app.constants.ProductAdminPortletKeys;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Syed Ali
 */

@Component(
	    immediate = true,
	    property = {
	        "panel.app.order:Integer=100",
	        "panel.category.key=" + PanelCategoryKeys.CONTROL_PANEL_USERS
	    },
	   service = PanelApp.class
	)
public class ProductAdminPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return ProductAdminPortletKeys.ProductAdmin;
	}

	@Override
    @Reference(
        target = "(javax.portlet.name=" + ProductAdminPortletKeys.ProductAdmin + ")",
        unbind = "-"
    )
    public void setPortlet(Portlet portlet) {
        super.setPortlet(portlet);
    }

}
