package com.liferaystack.products.web.asset;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;

import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import products.model.Product;

public class ProductAssetRenderer extends BaseJSPAssetRenderer<Product>{

	private final Product _entry;
	public static final String TYPE = "product";
	
	public ProductAssetRenderer(Product entry) {
		_entry = entry;
	}

	@Override
	public Product getAssetObject() {
		return _entry;
	}

	@Override
	public long getGroupId() {
		return _entry.getGroupId();
	}

	@Override
	public long getUserId() {
		return _entry.getUserId();
	}
	
	@Override
	public String getUserName() {
		return _entry.getUserName();
	}

	@Override
	public String getUuid() {
		return _entry.getUuid();
	}

	@Override
	public String getClassName() {
		return Product.class.getName();
	}

	@Override
	public long getClassPK() {
		return _entry.getProductId();
	}

	@Override
	public String getSummary(PortletRequest portletRequest, PortletResponse portletResponse) {
		String summary = _entry.getDescription();
		return summary;
	}

	@Override
	public String getTitle(Locale locale) {
		return _entry.getName();
	}

	@Override
	public String getJspPath(HttpServletRequest request, String template) {
		_log.info("getJspPath...");
		return null;
	}
	
	/*@Override
	public int getAssetRendererType() {
		// TODO Auto-generated method stub
		return super.getAssetRendererType();
	}*/
	

	private static Log _log = LogFactoryUtil.getLog(ProductAssetRenderer.class);
}
