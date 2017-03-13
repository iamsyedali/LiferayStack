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

package com.liferaystack.products.service.impl;

import java.util.Date;
import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferaystack.products.model.Product;
import com.liferaystack.products.service.base.ProductServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the product remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferaystack.products.service.ProductService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Syed Ali
 * @see ProductServiceBaseImpl
 * @see com.liferaystack.products.service.ProductServiceUtil
 */
@ProviderType
public class ProductServiceImpl extends ProductServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.liferaystack.products.service.ProductServiceUtil} to access the product remote service.
	 */
	
	private static Log _log = LogFactoryUtil.getLog(ProductServiceImpl.class.getName());
	
	public List<Product> findBystatusAndGroupId(long groupId, int status) {
		return productPersistence.findBystatusAndGroupId(groupId, status);
	}
	
	public Product updateWorkFlowStatus(long userId,long productId,int status,ServiceContext serviceContext) throws PortalException{
		 
		Product product = productPersistence.fetchByPrimaryKey(productId);
		product.setStatus(status);
		product.setStatusByUserId(userId);
		product.setStatusDate(new Date());
		User user = null;
		 try {
		      user = userLocalService.getUser(userId);
		      product.setStatusByUserName(user.getFullName());
		      product.setStatusByUserUuid(user.getUserUuid());
		 } catch (PortalException e) {
			 _log.error("PortalException : "+e.getMessage());
		 }
		 
		 product = productPersistence.update(product);
		 if (status == WorkflowConstants.STATUS_APPROVED) {  
		    assetEntryLocalService.updateEntry(Product.class.getName(), productId, new Date(),null, true, true);
		 } else {
		     assetEntryLocalService.updateVisible(Product.class.getName(), productId, false);  
		 }
		 return product;
	}
}