package com.liferaystack.products.components;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;

import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import com.liferaystack.products.model.Product;

public class ProductAssetRenderer<T> extends BaseJSPAssetRenderer<Product> {

	@Override
	public Product getAssetObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getGroupId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getUserId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUuid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getClassName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getClassPK() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getSummary(PortletRequest arg0, PortletResponse arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle(Locale arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getJspPath(HttpServletRequest arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
