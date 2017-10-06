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

import java.util.Date;

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
		product.setName(name);
		product.setDescription(description);
		ProductLocalServiceUtil.addProduct(product);
		_log.info("addProduct..... product : "+product.toString());
//		product = super.addProduct(product);

		Double priority = serviceContext.getAssetPriority();
		long[] assetCategoryIds = serviceContext.getAssetCategoryIds();
		String[] assetTagNames = serviceContext.getAssetTagNames();
		long[] assetLinkEntryIds = serviceContext.getAssetLinkEntryIds();
		long userId = serviceContext.getUserId();
		
		/*try {
			updateAsset(userId, product, assetCategoryIds, assetTagNames, assetLinkEntryIds, priority);
		} catch (PortalException e) {
			_log.error("PortalException While Updting Asset Entry : "+e.getMessage());
		}*/
		
		/*try {
			ProductLocalServiceUtil.updateAsset(userId, product, assetCategoryIds, assetTagNames, assetLinkEntryIds, priority);
			_log.info("NMEW SSET........."); 
		} catch (PortalException e) {
			_log.error("PortalException : "+e.getMessage());
		}*/
		
		
		AssetEntry assetEntry = null;
		try {
			_log.info("WESOMWE AssetEntry : ");
			assetEntry = assetEntryLocalService.updateEntry(userId,
			        groupId, product.getCreateDate(),
			        product.getModifiedDate(), Product.class.getName(),
			        productId, product.getUuid(), 0,
			        serviceContext.getAssetCategoryIds(),
			        serviceContext.getAssetTagNames(), true, true, null, null, null, null,
			        ContentTypes.TEXT_HTML, product.getName(), product.getDescription(), product.getDescription(), null,
			        null, 0, 0, null);
			
		} catch (PortalException e) {
			_log.error("PortalException : "+e.getMessage());
		}

		try {
			_log.info("WESOMWE assetLinkLocalService : ");
			assetLinkLocalService.updateLinks(userId, assetEntry.getEntryId(),
			        serviceContext.getAssetLinkEntryIds(),
			        AssetLinkConstants.TYPE_RELATED);
		} catch (PortalException e) {
			_log.error("PortalException : "+e.getMessage());
		}
		
		Indexer<Product> productIndexer = IndexerRegistryUtil.nullSafeGetIndexer(Product.class);
		try {
			_log.info("Indexing The Document Into Elstic Search");
			productIndexer.reindex(product);
			_log.info("Indexing DONE");
		} catch (SearchException e) {
			_log.error("SearchException : "+e.getMessage());
		}
		return product;
	}
	
public Product updteProductMy(long productId,String name, String description,ServiceContext serviceContext) throws PortalException{
		
		Product product = ProductLocalServiceUtil.getProduct(productId);
		long groupId = serviceContext.getScopeGroupId();
		product.setGroupId(groupId);
		product.setName(name);
		product.setDescription(description);
		product = ProductLocalServiceUtil.updateProduct(product);
		
		_log.info("addProduct..... product : "+product.toString());
//		product = super.addProduct(product);

		Double priority = serviceContext.getAssetPriority();
		long[] assetCategoryIds = serviceContext.getAssetCategoryIds();
		String[] assetTagNames = serviceContext.getAssetTagNames();
		long[] assetLinkEntryIds = serviceContext.getAssetLinkEntryIds();
		long userId = serviceContext.getUserId();
		
	/*	try {
			updateAsset(userId, product, assetCategoryIds, assetTagNames, assetLinkEntryIds, priority);
		} catch (PortalException e) {
			_log.error("PortalException While Updting Asset Entry : "+e.getMessage());
		}*/
		/*
		ProductLocalServiceUtil.updateAsset(userId, product, assetCategoryIds, assetTagNames, assetLinkEntryIds, priority);*/
		
		 AssetEntry assetEntry = assetEntryLocalService.updateEntry(product.getUserId(),
				 product.getGroupId(), product.getCreateDate(),
                 product.getModifiedDate(), Product.class.getName(),
                 productId, product.getUuid(), 0,
                 serviceContext.getAssetCategoryIds(),
                 serviceContext.getAssetTagNames(), true, true, product.getCreateDate(), 
                 null, null, null, ContentTypes.TEXT_HTML, product.getName(), product.getName(), null, 
                 null, null, 0, 0, serviceContext.getAssetPriority());

assetLinkLocalService.updateLinks(serviceContext.getUserId(),
                 assetEntry.getEntryId(), serviceContext.getAssetLinkEntryIds(),
                 AssetLinkConstants.TYPE_RELATED);

		Indexer<Product> productIndexer = IndexerRegistryUtil.nullSafeGetIndexer(Product.class);
		try {
			_log.info("Re Indexing The Document Into Elstic Search");
			productIndexer.reindex(product);
			_log.info("Indexing DONE");
		} catch (SearchException e) {
			_log.error("SearchException : "+e.getMessage());
		}
		return product;
	}

@Override
public Product deleteProduct(long productId) throws PortalException {
	_log.info("deleteProduct WESES");
	Product product = super.deleteProduct(productId);
	AssetEntry assetEntry = assetEntryLocalService.fetchEntry(Product.class.getName(), productId);
	assetLinkLocalService.deleteLinks(assetEntry.getEntryId());
	assetEntryLocalService.deleteEntry(assetEntry);
	return product;
}
	
	@Override
	public void updateAsset(
			long userId, Product product, long[] assetCategoryIds,
			String[] assetTagNames, long[] assetLinkEntryIds, Double priority)
		throws PortalException {

		boolean visible = true;

		/*if (product.isApproved()) {
			visible = true;
		}*/

		String summary = HtmlUtil.extractText(
			StringUtil.shorten(product.getDescription(), 500));

		Date publishDate = new Date();
		long classTypeID = 0;
		AssetEntry assetEntry = assetEntryLocalService.updateEntry(
			userId, product.getGroupId(), product.getCreateDate(),
			product.getModifiedDate(), Product.class.getName(),
			product.getProductId(), product.getUuid(), classTypeID, assetCategoryIds,
			assetTagNames, true, visible, null, null, publishDate , null,
			ContentTypes.TEXT_HTML, product.getName(), product.getDescription(),
			summary, null, null, 0, 0, priority);

		assetLinkLocalService.updateLinks(
			userId, assetEntry.getEntryId(), assetLinkEntryIds,
			AssetLinkConstants.TYPE_RELATED);
	}
	
	private static Log _log = LogFactoryUtil.getLog(ProductLocalServiceImpl.class);
}