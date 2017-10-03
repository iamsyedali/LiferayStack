package com.liferaystack.products.web.search;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelper;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferaystack.products.web.constants.ProductWebPortletKeys;

import products.model.Product;
import products.service.ProductLocalServiceUtil;

@Component(
		immediate = true, 
		service = Indexer.class
)

public class ProductIndexer extends BaseIndexer<Product> {

	public static final String CLASS_NAME = Product.class.getName();
	
	@Override
	public String getClassName() {
		_log.info("getClassName");
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(Product product) throws Exception {
		_log.info("STRT doDelete....");
		deleteDocument(product.getCompanyId(), product.getProductId());
		_log.info("END doDelete....");
		
	}

	@Override
	protected Document doGetDocument(Product product) throws Exception {
		_log.info("STRT doReindex product : "+product.toString());
		Document document = getBaseModelDocument(ProductWebPortletKeys.ProductWeb, product);
		document.addText(Field.TITLE, product.getName());
		document.addText(Field.DESCRIPTION, product.getDescription());
		document.addDate(Field.MODIFIED_DATE, product.getModifiedDate());
		document.addDate(Field.CREATE_DATE, product.getCreateDate());
		_log.info("END doReindex");
		return document;
	}

	@Override
	protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletRequest portletRequest,
			PortletResponse portletResponse) throws Exception {
		_log.info("doGetSummary");
		Summary summary = createSummary(document);
		summary.setMaxContentLength(200);
		return summary;
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		_log.info("doReindex  className : "+className+", classPK : "+classPK);
		Product product = ProductLocalServiceUtil.getProduct(classPK);
		doReindex(product);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		_log.info("doReindex");
		long companyId = GetterUtil.getLong(ids[0]);
		//reindexEntries(companyId);
	}

	@Override
	protected void doReindex(Product product) throws Exception {
		_log.info("doReindex");
		Document document = getDocument(product);

		indexWriterHelper.updateDocument(getSearchEngineId(), 
				product.getCompanyId(), document, isCommitImmediately());
	}
	
	@Reference
	protected IndexWriterHelper indexWriterHelper;
	
	private static Log _log = LogFactoryUtil.getLog(ProductIndexer.class);
}
