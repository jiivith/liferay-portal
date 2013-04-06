/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.messageboards.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ContainerModel;
import com.liferay.portal.model.StagedGroupedModel;
import com.liferay.portal.model.WorkflowedModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the MBCategory service. Represents a row in the &quot;MBCategory&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portlet.messageboards.model.impl.MBCategoryModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portlet.messageboards.model.impl.MBCategoryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MBCategory
 * @see com.liferay.portlet.messageboards.model.impl.MBCategoryImpl
 * @see com.liferay.portlet.messageboards.model.impl.MBCategoryModelImpl
 * @generated
 */
public interface MBCategoryModel extends BaseModel<MBCategory>, ContainerModel,
	StagedGroupedModel, WorkflowedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a message boards category model instance should use the {@link MBCategory} interface instead.
	 */

	/**
	 * Returns the primary key of this message boards category.
	 *
	 * @return the primary key of this message boards category
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this message boards category.
	 *
	 * @param primaryKey the primary key of this message boards category
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this message boards category.
	 *
	 * @return the uuid of this message boards category
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this message boards category.
	 *
	 * @param uuid the uuid of this message boards category
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the category ID of this message boards category.
	 *
	 * @return the category ID of this message boards category
	 */
	public long getCategoryId();

	/**
	 * Sets the category ID of this message boards category.
	 *
	 * @param categoryId the category ID of this message boards category
	 */
	public void setCategoryId(long categoryId);

	/**
	 * Returns the group ID of this message boards category.
	 *
	 * @return the group ID of this message boards category
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this message boards category.
	 *
	 * @param groupId the group ID of this message boards category
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this message boards category.
	 *
	 * @return the company ID of this message boards category
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this message boards category.
	 *
	 * @param companyId the company ID of this message boards category
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this message boards category.
	 *
	 * @return the user ID of this message boards category
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this message boards category.
	 *
	 * @param userId the user ID of this message boards category
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this message boards category.
	 *
	 * @return the user uuid of this message boards category
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this message boards category.
	 *
	 * @param userUuid the user uuid of this message boards category
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this message boards category.
	 *
	 * @return the user name of this message boards category
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this message boards category.
	 *
	 * @param userName the user name of this message boards category
	 */
	public void setUserName(String userName);

	/**
	 * Returns the create date of this message boards category.
	 *
	 * @return the create date of this message boards category
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this message boards category.
	 *
	 * @param createDate the create date of this message boards category
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this message boards category.
	 *
	 * @return the modified date of this message boards category
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this message boards category.
	 *
	 * @param modifiedDate the modified date of this message boards category
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the parent category ID of this message boards category.
	 *
	 * @return the parent category ID of this message boards category
	 */
	public long getParentCategoryId();

	/**
	 * Sets the parent category ID of this message boards category.
	 *
	 * @param parentCategoryId the parent category ID of this message boards category
	 */
	public void setParentCategoryId(long parentCategoryId);

	/**
	 * Returns the name of this message boards category.
	 *
	 * @return the name of this message boards category
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this message boards category.
	 *
	 * @param name the name of this message boards category
	 */
	public void setName(String name);

	/**
	 * Returns the description of this message boards category.
	 *
	 * @return the description of this message boards category
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this message boards category.
	 *
	 * @param description the description of this message boards category
	 */
	public void setDescription(String description);

	/**
	 * Returns the display style of this message boards category.
	 *
	 * @return the display style of this message boards category
	 */
	@AutoEscape
	public String getDisplayStyle();

	/**
	 * Sets the display style of this message boards category.
	 *
	 * @param displayStyle the display style of this message boards category
	 */
	public void setDisplayStyle(String displayStyle);

	/**
	 * Returns the thread count of this message boards category.
	 *
	 * @return the thread count of this message boards category
	 */
	public int getThreadCount();

	/**
	 * Sets the thread count of this message boards category.
	 *
	 * @param threadCount the thread count of this message boards category
	 */
	public void setThreadCount(int threadCount);

	/**
	 * Returns the message count of this message boards category.
	 *
	 * @return the message count of this message boards category
	 */
	public int getMessageCount();

	/**
	 * Sets the message count of this message boards category.
	 *
	 * @param messageCount the message count of this message boards category
	 */
	public void setMessageCount(int messageCount);

	/**
	 * Returns the last post date of this message boards category.
	 *
	 * @return the last post date of this message boards category
	 */
	public Date getLastPostDate();

	/**
	 * Sets the last post date of this message boards category.
	 *
	 * @param lastPostDate the last post date of this message boards category
	 */
	public void setLastPostDate(Date lastPostDate);

	/**
	 * Returns the status of this message boards category.
	 *
	 * @return the status of this message boards category
	 */
	public int getStatus();

	/**
	 * Sets the status of this message boards category.
	 *
	 * @param status the status of this message boards category
	 */
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this message boards category.
	 *
	 * @return the status by user ID of this message boards category
	 */
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this message boards category.
	 *
	 * @param statusByUserId the status by user ID of this message boards category
	 */
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this message boards category.
	 *
	 * @return the status by user uuid of this message boards category
	 * @throws SystemException if a system exception occurred
	 */
	public String getStatusByUserUuid() throws SystemException;

	/**
	 * Sets the status by user uuid of this message boards category.
	 *
	 * @param statusByUserUuid the status by user uuid of this message boards category
	 */
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this message boards category.
	 *
	 * @return the status by user name of this message boards category
	 */
	@AutoEscape
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this message boards category.
	 *
	 * @param statusByUserName the status by user name of this message boards category
	 */
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this message boards category.
	 *
	 * @return the status date of this message boards category
	 */
	public Date getStatusDate();

	/**
	 * Sets the status date of this message boards category.
	 *
	 * @param statusDate the status date of this message boards category
	 */
	public void setStatusDate(Date statusDate);

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #isApproved()}
	 */
	public boolean getApproved();

	/**
	 * Returns <code>true</code> if this message boards category is approved.
	 *
	 * @return <code>true</code> if this message boards category is approved; <code>false</code> otherwise
	 */
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this message boards category is denied.
	 *
	 * @return <code>true</code> if this message boards category is denied; <code>false</code> otherwise
	 */
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this message boards category is a draft.
	 *
	 * @return <code>true</code> if this message boards category is a draft; <code>false</code> otherwise
	 */
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this message boards category is expired.
	 *
	 * @return <code>true</code> if this message boards category is expired; <code>false</code> otherwise
	 */
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this message boards category is inactive.
	 *
	 * @return <code>true</code> if this message boards category is inactive; <code>false</code> otherwise
	 */
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this message boards category is incomplete.
	 *
	 * @return <code>true</code> if this message boards category is incomplete; <code>false</code> otherwise
	 */
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this message boards category is in the Recycle Bin.
	 *
	 * @return <code>true</code> if this message boards category is in the Recycle Bin; <code>false</code> otherwise
	 */
	public boolean isInTrash();

	/**
	 * Returns <code>true</code> if this message boards category is pending.
	 *
	 * @return <code>true</code> if this message boards category is pending; <code>false</code> otherwise
	 */
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this message boards category is scheduled.
	 *
	 * @return <code>true</code> if this message boards category is scheduled; <code>false</code> otherwise
	 */
	public boolean isScheduled();

	/**
	 * Returns the container model ID of this message boards category.
	 *
	 * @return the container model ID of this message boards category
	 */
	public long getContainerModelId();

	/**
	 * Sets the container model ID of this message boards category.
	 *
	 * @param container model ID of this message boards category
	 */
	public void setContainerModelId(long containerModelId);

	/**
	 * Returns the container name of this message boards category.
	 *
	 * @return the container name of this message boards category
	 */
	public String getContainerModelName();

	/**
	 * Returns the parent container model ID of this message boards category.
	 *
	 * @return the parent container model ID of this message boards category
	 */
	public long getParentContainerModelId();

	/**
	 * Sets the parent container model ID of this message boards category.
	 *
	 * @param parent container model ID of this message boards category
	 */
	public void setParentContainerModelId(long parentContainerModelId);

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public Serializable getPrimaryKeyObj();

	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(MBCategory mbCategory);

	public int hashCode();

	public CacheModel<MBCategory> toCacheModel();

	public MBCategory toEscapedModel();

	public MBCategory toUnescapedModel();

	public String toString();

	public String toXmlString();
}