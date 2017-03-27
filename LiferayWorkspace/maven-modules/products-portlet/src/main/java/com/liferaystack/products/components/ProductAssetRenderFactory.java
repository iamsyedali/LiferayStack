package com.liferaystack.products.components;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferaystack.products.constants.ProductPortletKeys;
import com.liferaystack.products.model.Product;
import com.liferaystack.products.service.ProductLocalService;

@Component(
		 immediate = true,
		 property = {"javax.portlet.name="+ProductPortletKeys.PRODUCT_PORTLET_NAME},
		 service = AssetRendererFactory.class
)

public class ProductAssetRenderFactory extends BaseAssetRendererFactory<Product> {

	private ProductLocalService _productLocalService;
	private ResourceBundleLoader _resourceBundleLoader;
	private ServletContext _servletContext;
	private static Log _log = LogFactoryUtil.getLog(ProductAssetRenderFactory.class);

	@Reference(unbind = "-")
	 protected void setProductService(ProductLocalService productLocalService) {
		_productLocalService = productLocalService;
	 }
	
	
	@Reference(unbind = "-")
	 public void setResourceBundleLoader(ResourceBundleLoader resourceBundleLoader) {
	     _resourceBundleLoader = resourceBundleLoader;
	 }

	 @Reference(unbind = "-")
	 public void setServletContext(ServletContext servletContext) {
	     _servletContext = servletContext;
	 }
	 
	@Override
	public AssetRenderer<Product> getAssetRenderer(long classPK, int type) throws PortalException {
		Product product = _productLocalService.getProduct(classPK);
		ProductAssetRenderer productAssetRenderer = new ProductAssetRenderer(product, _resourceBundleLoader);
		productAssetRenderer.setAssetRendererType(type);
		productAssetRenderer.setServletContext(_servletContext);
	    return productAssetRenderer;
	}

	@Override
	public String getType() {
		return "product";
	}

	@Override
	public String getClassName() {
		return Product.class.getName();
	}
}
