package com.liferaystack.products.components;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferaystack.products.model.Product;

public class ProductAssetRenderer extends BaseJSPAssetRenderer<Product> {

	private final Product _product;
	private final ResourceBundleLoader _resourceBundleLoader;
	private static Log _log = LogFactoryUtil.getLog(ProductAssetRenderer.class);
	
	
	public ProductAssetRenderer(Product product, ResourceBundleLoader resourceBundleLoader) {
		_product = product;
		_resourceBundleLoader = resourceBundleLoader;
	 }
	
	@Override
	public Product getAssetObject() {
		return _product;
	}

	@Override
	public long getGroupId() {
		return _product.getGroupId();
	}

	@Override
	public long getUserId() {
		return _product.getUserId();
	}

	@Override
	public String getUserName() {
		return null;
	}

	@Override
	public String getUuid() {
		return _product.getUuid();
	}

	@Override
	public String getClassName() {
		return Product.class.getName();
	}

	@Override
	public long getClassPK() {
		return _product.getProductId();
	}

	@Override
	public String getSummary(PortletRequest arg0, PortletResponse arg1) {
		return "Summary : "+_product.getName();
	}

	@Override
	public String getTitle(Locale arg0) {
		return "Name : "+_product.getName();
	}

	@Override
	public String getJspPath(HttpServletRequest arg0, String arg1) {
		return "/product/product-details.jsp";
	}
	
	@Override
	 public boolean include(HttpServletRequest request, HttpServletResponse response, String template) throws Exception {
	    request.setAttribute("product", _product);
	    return super.include(request, response, template);
	 }

}
