package com.liferaystack.products.components;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.Summary;
import com.liferaystack.products.model.Product;

@Component(immediate = true, service = Indexer.class)
public class ProductsIndexer extends BaseIndexer<Product>{

	private static Log _log = LogFactoryUtil.getLog(ProductsIndexer.class);
	
	@Override
	public String getClassName() {
		return Product.class.getName();
	}

	@Override
	protected void doDelete(Product product) throws Exception {
		_log.info("doDelete...");
	}

	@Override
	protected Document doGetDocument(Product product) throws Exception {
		_log.info("doDelete...");
		return null;
	}

	@Override
	protected Summary doGetSummary(Document arg0, Locale arg1, String arg2, PortletRequest arg3, PortletResponse arg4)
			throws Exception {
		_log.info("doGetSummary..."); 
		return null;
	}

	@Override
	protected void doReindex(String[] arg0) throws Exception {
		_log.info("doReindex...");
		
	}

	@Override
	protected void doReindex(Product product) throws Exception {
		_log.info("doReindex");
		
	}

	@Override
	protected void doReindex(String arg0, long arg1) throws Exception {
		_log.info("doReindex...");
		
	}

}
