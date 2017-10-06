package com.liferay.docs.product.asset;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferaystack.products.web.constants.ProductWebPortletKeys;

import products.model.Product;
import products.service.ProductLocalServiceUtil;

@Component(
		immediate = true,
		property = {"javax.portlet.name=" + ProductWebPortletKeys.ProductWeb},
		service = AssetRendererFactory.class
	)

public class ProductAssetRendererFactory extends BaseAssetRendererFactory<Product>{

	private ServletContext _servletContext;
	
	@Override
	public AssetRenderer<Product> getAssetRenderer(long classPK, int type) throws PortalException {
		
		_log.info("getAssetRenderer");
		Product entry = _productLocalServiceUtil.getProduct(classPK);
		ProductAssetRenderer productAssetRenderer = new ProductAssetRenderer(entry);
		productAssetRenderer.setAssetRendererType(type);
		productAssetRenderer.setServletContext(_servletContext);
		return productAssetRenderer;
	}
	
	@Override
	public String getType() {
	    return ProductAssetRenderer.TYPE;
	}
	
	@Reference(
			target = "(osgi.web.symbolicname=com.liferaystack.products.web)",
			unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		_log.info("setServletContext");
		_servletContext = servletContext;
	}
	
	@Reference(unbind = "-")
	protected void setProductLocalServiceUtil(ProductLocalServiceUtil productLocalServiceUtil) {
		_log.info("setProductLocalServiceUtil");
		_productLocalServiceUtil = productLocalServiceUtil;
	}

	private ProductLocalServiceUtil _productLocalServiceUtil;
	private static Log _log = LogFactoryUtil.getLog(ProductAssetRendererFactory.class);

}
