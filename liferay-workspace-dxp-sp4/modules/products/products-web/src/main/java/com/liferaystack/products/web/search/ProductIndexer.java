package com.liferaystack.products.web.search;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelper;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferaystack.products.web.constants.ProductWebPortletKeys;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import products.model.Product;
import products.service.ProductLocalServiceUtil;

@Component(
		immediate = true, 
		service = Indexer.class
)

public class ProductIndexer extends BaseIndexer<Product> {

	public static final String CLASS_NAME = Product.class.getName();
	
	public ProductIndexer() {
	
		setDefaultSelectedFieldNames(
				Field.ASSET_TAG_NAMES, Field.COMPANY_ID, Field.CONTENT,
				Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK, Field.GROUP_ID,
				Field.MODIFIED_DATE, Field.SCOPE_GROUP_ID, Field.TITLE, Field.UID);
			setFilterSearch(true);
			setPermissionAware(true);
	}
	
	@Override
	public boolean isVisible(long classPK, int status) throws Exception {
		return true;
	}
	
	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, String entryClassName,
			long entryClassPK, String actionId)
		throws Exception {

		return true;
	}
	
	@Override
	protected Filter addSearchClassTypeIds(BooleanFilter contextBooleanFilter, SearchContext searchContext)
			throws Exception {
		// TODO Auto-generated method stub
		return super.addSearchClassTypeIds(contextBooleanFilter, searchContext);
	}
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
