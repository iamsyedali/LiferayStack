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

package products.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Product}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Product
 * @generated
 */
@ProviderType
public class ProductWrapper implements Product, ModelWrapper<Product> {
	public ProductWrapper(Product product) {
		_product = product;
	}

	@Override
	public Class<?> getModelClass() {
		return Product.class;
	}

	@Override
	public String getModelClassName() {
		return Product.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("productId", getProductId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long productId = (Long)attributes.get("productId");

		if (productId != null) {
			setProductId(productId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}
	}

	/**
	* Returns <code>true</code> if this product is approved.
	*
	* @return <code>true</code> if this product is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _product.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _product.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this product is denied.
	*
	* @return <code>true</code> if this product is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _product.isDenied();
	}

	/**
	* Returns <code>true</code> if this product is a draft.
	*
	* @return <code>true</code> if this product is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _product.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _product.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this product is expired.
	*
	* @return <code>true</code> if this product is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _product.isExpired();
	}

	/**
	* Returns <code>true</code> if this product is inactive.
	*
	* @return <code>true</code> if this product is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _product.isInactive();
	}

	/**
	* Returns <code>true</code> if this product is incomplete.
	*
	* @return <code>true</code> if this product is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _product.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _product.isNew();
	}

	/**
	* Returns <code>true</code> if this product is pending.
	*
	* @return <code>true</code> if this product is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _product.isPending();
	}

	/**
	* Returns <code>true</code> if this product is scheduled.
	*
	* @return <code>true</code> if this product is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _product.isScheduled();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _product.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<products.model.Product> toCacheModel() {
		return _product.toCacheModel();
	}

	@Override
	public int compareTo(products.model.Product product) {
		return _product.compareTo(product);
	}

	/**
	* Returns the status of this product.
	*
	* @return the status of this product
	*/
	@Override
	public int getStatus() {
		return _product.getStatus();
	}

	@Override
	public int hashCode() {
		return _product.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _product.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ProductWrapper((Product)_product.clone());
	}

	/**
	* Returns the description of this product.
	*
	* @return the description of this product
	*/
	@Override
	public java.lang.String getDescription() {
		return _product.getDescription();
	}

	/**
	* Returns the name of this product.
	*
	* @return the name of this product
	*/
	@Override
	public java.lang.String getName() {
		return _product.getName();
	}

	/**
	* Returns the status by user name of this product.
	*
	* @return the status by user name of this product
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _product.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this product.
	*
	* @return the status by user uuid of this product
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _product.getStatusByUserUuid();
	}

	/**
	* Returns the user name of this product.
	*
	* @return the user name of this product
	*/
	@Override
	public java.lang.String getUserName() {
		return _product.getUserName();
	}

	/**
	* Returns the user uuid of this product.
	*
	* @return the user uuid of this product
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _product.getUserUuid();
	}

	/**
	* Returns the uuid of this product.
	*
	* @return the uuid of this product
	*/
	@Override
	public java.lang.String getUuid() {
		return _product.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _product.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _product.toXmlString();
	}

	/**
	* Returns the create date of this product.
	*
	* @return the create date of this product
	*/
	@Override
	public Date getCreateDate() {
		return _product.getCreateDate();
	}

	/**
	* Returns the modified date of this product.
	*
	* @return the modified date of this product
	*/
	@Override
	public Date getModifiedDate() {
		return _product.getModifiedDate();
	}

	/**
	* Returns the status date of this product.
	*
	* @return the status date of this product
	*/
	@Override
	public Date getStatusDate() {
		return _product.getStatusDate();
	}

	/**
	* Returns the company ID of this product.
	*
	* @return the company ID of this product
	*/
	@Override
	public long getCompanyId() {
		return _product.getCompanyId();
	}

	/**
	* Returns the group ID of this product.
	*
	* @return the group ID of this product
	*/
	@Override
	public long getGroupId() {
		return _product.getGroupId();
	}

	/**
	* Returns the primary key of this product.
	*
	* @return the primary key of this product
	*/
	@Override
	public long getPrimaryKey() {
		return _product.getPrimaryKey();
	}

	/**
	* Returns the product ID of this product.
	*
	* @return the product ID of this product
	*/
	@Override
	public long getProductId() {
		return _product.getProductId();
	}

	/**
	* Returns the status by user ID of this product.
	*
	* @return the status by user ID of this product
	*/
	@Override
	public long getStatusByUserId() {
		return _product.getStatusByUserId();
	}

	/**
	* Returns the user ID of this product.
	*
	* @return the user ID of this product
	*/
	@Override
	public long getUserId() {
		return _product.getUserId();
	}

	@Override
	public products.model.Product toEscapedModel() {
		return new ProductWrapper(_product.toEscapedModel());
	}

	@Override
	public products.model.Product toUnescapedModel() {
		return new ProductWrapper(_product.toUnescapedModel());
	}

	@Override
	public void persist() {
		_product.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_product.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this product.
	*
	* @param companyId the company ID of this product
	*/
	@Override
	public void setCompanyId(long companyId) {
		_product.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this product.
	*
	* @param createDate the create date of this product
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_product.setCreateDate(createDate);
	}

	/**
	* Sets the description of this product.
	*
	* @param description the description of this product
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_product.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_product.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_product.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_product.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this product.
	*
	* @param groupId the group ID of this product
	*/
	@Override
	public void setGroupId(long groupId) {
		_product.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this product.
	*
	* @param modifiedDate the modified date of this product
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_product.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this product.
	*
	* @param name the name of this product
	*/
	@Override
	public void setName(java.lang.String name) {
		_product.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_product.setNew(n);
	}

	/**
	* Sets the primary key of this product.
	*
	* @param primaryKey the primary key of this product
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_product.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_product.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the product ID of this product.
	*
	* @param productId the product ID of this product
	*/
	@Override
	public void setProductId(long productId) {
		_product.setProductId(productId);
	}

	/**
	* Sets the status of this product.
	*
	* @param status the status of this product
	*/
	@Override
	public void setStatus(int status) {
		_product.setStatus(status);
	}

	/**
	* Sets the status by user ID of this product.
	*
	* @param statusByUserId the status by user ID of this product
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_product.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this product.
	*
	* @param statusByUserName the status by user name of this product
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_product.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this product.
	*
	* @param statusByUserUuid the status by user uuid of this product
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_product.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this product.
	*
	* @param statusDate the status date of this product
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_product.setStatusDate(statusDate);
	}

	/**
	* Sets the user ID of this product.
	*
	* @param userId the user ID of this product
	*/
	@Override
	public void setUserId(long userId) {
		_product.setUserId(userId);
	}

	/**
	* Sets the user name of this product.
	*
	* @param userName the user name of this product
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_product.setUserName(userName);
	}

	/**
	* Sets the user uuid of this product.
	*
	* @param userUuid the user uuid of this product
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_product.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this product.
	*
	* @param uuid the uuid of this product
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_product.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProductWrapper)) {
			return false;
		}

		ProductWrapper productWrapper = (ProductWrapper)obj;

		if (Objects.equals(_product, productWrapper._product)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _product.getStagedModelType();
	}

	@Override
	public Product getWrappedModel() {
		return _product;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _product.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _product.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_product.resetOriginalValues();
	}

	private final Product _product;
}