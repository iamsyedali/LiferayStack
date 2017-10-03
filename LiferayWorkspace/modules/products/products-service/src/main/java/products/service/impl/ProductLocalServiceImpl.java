/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package products.service.impl;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLinkConstants;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringUtil;

import aQute.bnd.annotation.ProviderType;
import products.model.Product;
import products.service.ProductLocalServiceUtil;
import products.service.base.ProductLocalServiceBaseImpl;

/**
 * The implementation of the product local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link products.service.ProductLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductLocalServiceBaseImpl
 * @see products.service.ProductLocalServiceUtil
 */
@ProviderType
public class ProductLocalServiceImpl extends ProductLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link products.service.ProductLocalServiceUtil} to access the product local service.
	 */
	
	public Product addProductMy(String name, String description,ServiceContext serviceContext){
		
		long productId = CounterLocalServiceUtil.increment(Product.class.getName());
		Product product = ProductLocalServiceUtil.createProduct(productId) ;
		long groupId = serviceContext.getScopeGroupId();
		product.setGroupId(groupId);
		_log.info("addProductMy product : "+product.toString());
		product = super.addProduct(product);

		Double priority = serviceContext.getAssetPriority();
		long[] assetCategoryIds = serviceContext.getAssetCategoryIds();
		String[] assetTagNames = serviceContext.getAssetTagNames();
		long[] assetLinkEntryIds = serviceContext.getAssetLinkEntryIds();
		long userId = serviceContext.getUserId();
		
		try {
			updateAsset(userId, product, assetCategoryIds, assetTagNames, assetLinkEntryIds, priority);
		} catch (PortalException e) {
			_log.error("PortalException While Updting Asset Entry : "+e.getMessage());
		}
		Indexer<Product> productIndexer = IndexerRegistryUtil.nullSafeGetIndexer(Product.class);
		try {
			_log.info("Indexing The Document Into Elstic Search");
			productIndexer.reindex(product);
		} catch (SearchException e) {
			_log.error("SearchException : "+e.getMessage());
		}
		return product;
	}
	
	@Override
	public void updateAsset(
			long userId, Product product, long[] assetCategoryIds,
			String[] assetTagNames, long[] assetLinkEntryIds, Double priority)
		throws PortalException {

		boolean visible = false;

		/*if (product.isApproved()) {
			visible = true;
		}*/

		String summary = HtmlUtil.extractText(
			StringUtil.shorten(product.getDescription(), 500));

		AssetEntry assetEntry = assetEntryLocalService.updateEntry(
			userId, product.getGroupId(), product.getCreateDate(),
			product.getModifiedDate(), Product.class.getName(),
			product.getProductId(), product.getUuid(), 0, assetCategoryIds,
			assetTagNames, true, visible, null, null, null, null,
			ContentTypes.TEXT_HTML, product.getName(), product.getDescription(),
			summary, null, null, 0, 0, priority);

		assetLinkLocalService.updateLinks(
			userId, assetEntry.getEntryId(), assetLinkEntryIds,
			AssetLinkConstants.TYPE_RELATED);
	}
	
	private static Log _log = LogFactoryUtil.getLog(ProductLocalServiceImpl.class);
}