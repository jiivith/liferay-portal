<%--
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
--%>

<%@ include file="/html/portlet/enterprise_admin/init.jsp" %>

<%
long defaultOrganizationId = ((Long)request.getAttribute("view_organizations.jsp-defaultOrganizationId")).longValue();
PortletURL portletURL = (PortletURL)request.getAttribute("view_organizations.jsp-portletURL");
String viewOrganizationsRedirect = ((String)request.getAttribute("view_organizations.jsp-viewOrganizationsRedirect"));
%>

<liferay-ui:search-container
	rowChecker="<%= new RowChecker(renderResponse) %>"
	searchContainer="<%= new OrganizationSearch(renderRequest, portletURL) %>"
>
	<aui:input name="deleteOrganizationIds" type="hidden" />                                                             
	<aui:input name="organizationsRedirect" type="hidden" value="<%= portletURL.toString() %>" />

	<liferay-ui:search-form
		page="/html/portlet/enterprise_admin/organization_search.jsp"
	/>

	<%
	OrganizationSearchTerms searchTerms = (OrganizationSearchTerms)searchContainer.getSearchTerms();

	LinkedHashMap organizationParams = new LinkedHashMap();

	Long[][] leftAndRightOrganizationIds = null;

	if (filterManageableOrganizations) {
		leftAndRightOrganizationIds = EnterpriseAdminUtil.getLeftAndRightOrganizationIds(user.getOrganizations());
	}

	long organizationId = ParamUtil.getLong(request, "organizationId", OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID);
	long parentOrganizationId = ParamUtil.getLong(request, "parentOrganizationId", OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID);

	if (organizationId != OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID) {
		Long[][] leftAndRightParentOrganizationIds = EnterpriseAdminUtil.getLeftAndRightOrganizationIds(organizationId);

		if (leftAndRightOrganizationIds == null) {
			leftAndRightOrganizationIds = new Long[][] {
				new Long[] {
					leftAndRightParentOrganizationIds[0][0],
					leftAndRightParentOrganizationIds[0][1]
				}
			};
		}
		else {
			if (leftAndRightOrganizationIds[0][0] > leftAndRightParentOrganizationIds[0][0]) {
				leftAndRightOrganizationIds[0][0] = leftAndRightParentOrganizationIds[0][0];
			}

			if (leftAndRightOrganizationIds[0][1] < leftAndRightParentOrganizationIds[0][1]) {
				leftAndRightOrganizationIds[0][1] = leftAndRightParentOrganizationIds[0][1];
			}
		}
	}

	if (leftAndRightOrganizationIds != null) {
		organizationParams.put("organizationsTree", leftAndRightOrganizationIds);
	}

	if (parentOrganizationId <= 0) {
		parentOrganizationId = OrganizationConstants.ANY_PARENT_ORGANIZATION_ID;
	}
	%>

	<liferay-ui:search-container-results>
		<c:choose>
			<c:when test="<%= PropsValues.ORGANIZATIONS_SEARCH_WITH_INDEX %>">
				<%@ include file="/html/portlet/enterprise_admin/organization_search_results_index.jspf" %>
			</c:when>
			<c:otherwise>
				<%@ include file="/html/portlet/enterprise_admin/organization_search_results_database.jspf" %>
			</c:otherwise>
		</c:choose>
	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.liferay.portal.model.Organization"
		escapedModel="<%= true %>"
		keyProperty="organizationId"
		modelVar="organization"
	>

		<%
		PortletURL redirect = searchContainer.getIteratorURL();

		redirect.setParameter("organizationView", OrganizationConstants.ORGANIZATION_VIEW_FLAT);
		%>

		<portlet:renderURL var="rowURL">
			<portlet:param name="struts_action" value="/enterprise_admin/view_organization" />
			<portlet:param name="organizationView" value="<%= OrganizationConstants.ORGANIZATION_VIEW_TREE %>" />
			<portlet:param name="redirect" value="<%= redirect.toString() %>" />
			<portlet:param name="organizationId" value="<%= String.valueOf(organization.getOrganizationId()) %>" />
		</portlet:renderURL>

		<%
		if (!OrganizationPermissionUtil.contains(permissionChecker, organization.getOrganizationId(), ActionKeys.UPDATE)) {
			rowURL = null;
		}
		%>

		<%@ include file="/html/portlet/enterprise_admin/organization/search_columns.jspf" %>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/html/portlet/enterprise_admin/organization_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<c:if test="<%= !results.isEmpty() %>">
		<div class="separator"><!-- --></div>

		<aui:button onClick='<%= renderResponse.getNamespace() + "deleteOrganizations();" %>' value="delete" />
	</c:if>

	<c:if test="<%= PropsValues.ORGANIZATIONS_VIEWS.length > 1 %>">
		<c:if test="<%= ArrayUtil.contains(PropsValues.ORGANIZATIONS_VIEWS, OrganizationConstants.ORGANIZATION_VIEW_FLAT) %>">
			<td class="organization-view-icon">

				<portlet:renderURL var="viewOrganization">
					<portlet:param name="struts_action" value="/enterprise_admin/view_organization" />
					<portlet:param name="organizationView" value="<%= OrganizationConstants.ORGANIZATION_VIEW_FLAT %>" />
					<portlet:param name="tabs1" value="organizations" />
				</portlet:renderURL>

				<liferay-ui:icon
					cssClass="organization-view-icon"
					image="../message_boards/thread_view_flat"
					message="flat-view"
					method="get"
					url="<%= viewOrganization %>"
				/>
			</td>
		</c:if>

		<c:if test="<%= ArrayUtil.contains(PropsValues.ORGANIZATIONS_VIEWS, OrganizationConstants.ORGANIZATION_VIEW_TREE) %>">
			<td class="organization-view-icon">

				<portlet:renderURL var="viewOrganization">
					<portlet:param name="struts_action" value="/enterprise_admin/view_organization" />
					<portlet:param name="organizationView" value="<%= OrganizationConstants.ORGANIZATION_VIEW_TREE %>" />
					<portlet:param name="tabs1" value="organizations" />
				</portlet:renderURL>

				<liferay-ui:icon
					cssClass="organization-view-icon"
					image="../message_boards/thread_view_tree"
					message="tree-view"
					method="get"
					url="<%= viewOrganization %>"
				/>
			</td>
		</c:if>
	</c:if>

	<br />

	<liferay-ui:search-iterator />
</liferay-ui:search-container>