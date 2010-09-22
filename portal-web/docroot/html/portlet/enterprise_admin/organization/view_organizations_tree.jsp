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
long organizationId = ((Long)request.getAttribute("view_organizations.jsp-organizationId")).longValue();
long defaultOrganizationId = ((Long)request.getAttribute("view_organizations.jsp-defaultOrganizationId")).longValue();
PortletURL portletURL = (PortletURL)request.getAttribute("view_organizations.jsp-portletURL");
String redirect = ParamUtil.getString(request, "redirect");

Organization organization = null;

long organizationGroupId = 0;

if (organizationId != 0) {
	organization = OrganizationServiceUtil.getOrganization(organizationId);

	organizationGroupId = organization.getGroup().getGroupId();
}

int organizationsCount = OrganizationServiceUtil.getOrganizationsCount(CompanyThreadLocal.getCompanyId(), (organization != null ? organization.getOrganizationId() : 0));
int usersCount = UserLocalServiceUtil.getOrganizationUsersCount(organizationId);
int teamsCount = TeamLocalServiceUtil.searchCount(organizationGroupId, null, null, null);

request.setAttribute("view_organizations.jsp-organization", organization);
request.setAttribute("view_organizations.jsp-organizationId", String.valueOf((organization != null ? organization.getOrganizationId() : 0)));

%>

<aui:form method="get" name="fm1">
	<liferay-portlet:renderURLParams varImpl="searchURL" />

	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="companyId" type="hidden" value="<%= String.valueOf(CompanyThreadLocal.getCompanyId()) %>" />
	<aui:input name="deleteOrganizationIds" type="hidden" />
	<aui:input name="organizationsRedirect" type="hidden" value="<%= portletURL.toString() %>" />
	<aui:input name ="organizationView" type="hidden" value="<%= OrganizationConstants.ORGANIZATION_VIEW_FLAT%>" />
	<aui:input name="searchOrganizationIds" type="hidden" value="<%= organizationId %>" />
	<aui:input name="viewOrganizationsRedirect" type="hidden" value="<%= currentURL %>" />

	<aui:layout>
		<c:if test="<%= (organization != null) && (organization.getOrganizationId() != defaultOrganizationId) %>">

			<%
			long parentOrganizationId = defaultOrganizationId;
			String parentOrganizationName = LanguageUtil.get(pageContext, "organizations-home");

			if (!organization.isRoot()) {
				Organization parentOrganization = organization.getParentOrganization();

				parentOrganizationId = parentOrganization.getOrganizationId();

				if (parentOrganization.getOrganizationId() != defaultOrganizationId) {
					parentOrganizationName = parentOrganization.getName();
				}
			}
			%>

			<aui:input name="organizationId" type="hidden" value="<%= organizationId %>" />

			<portlet:renderURL var="backURL">
				<portlet:param name="struts_action" value="/organization/view" />
				<portlet:param name="organizationId" value="<%= String.valueOf(parentOrganizationId) %>" />
			</portlet:renderURL>

			<liferay-ui:header
				title="<%= organization.getName() %>"
				backLabel='<%= "&laquo; " + LanguageUtil.format(pageContext, "back-to-x", HtmlUtil.escape(parentOrganizationName)) %>'
				backURL="<%= (Validator.isNotNull(redirect)) ? redirect : backURL.toString() %>"
			/>
		</c:if>

		<liferay-util:buffer var="showOrganizationsTree">
			<c:if test="<%= organization != null %>">
				<div class="lfr-asset-metadata">
					<div class="lfr-asset-icon lfr-asset-organizations">
						<%= organizationsCount %> <liferay-ui:message key='<%= (organizationsCount == 1) ? "suborganization" : "suborganizations" %>' />
					</div>

					<div class="lfr-asset-icon lfr-asset-users">
						<portlet:renderURL var="viewUsersURL">
							<portlet:param name="struts_action" value="/enterprise_admin/view" />
							<portlet:param name="viewUsersRedirect" value="<%= currentURL %>" />
							<portlet:param name="tabs1" value="users" />
							<portlet:param name="organizationId" value="<%= String.valueOf(organizationId) %>" />
						</portlet:renderURL>

						<c:choose>
							<c:when test="<%= usersCount > 0 %>" >
								<aui:a href="<%= viewUsersURL %>"> <%= usersCount %> <liferay-ui:message key='<%= (usersCount == 1) ? "user" : "users" %>' /></aui:a>
							</c:when>
							<c:otherwise>
								<%= usersCount %> <liferay-ui:message key='<%= (usersCount == 1) ? "user" : "users" %>' />
							</c:otherwise>
						</c:choose>
					</div>

					<div class="lfr-asset-icon lfr-asset-teams">
						<portlet:renderURL var="manageTeamsURL">
							<portlet:param name="struts_action" value="/enterprise_admin/view_teams" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="groupId" value="<%= String.valueOf(organizationGroupId) %>" />
						</portlet:renderURL>

						<c:choose>
							<c:when test="<%= teamsCount > 0 %>" >
								<aui:a href="<%= manageTeamsURL %>"> <%= teamsCount %> <liferay-ui:message key='<%= (usersCount == 1) ? "team" : "teams" %>' /></aui:a>
							</c:when>
							<c:otherwise>
								<%= teamsCount %> <liferay-ui:message key='<%= (usersCount == 1) ? "team" : "teams" %>' />
							</c:otherwise>
						</c:choose>
					</div>
				</div>

				<span class="entry-categories">
					<liferay-ui:asset-categories-summary
						className="<%= Organization.class.getName() %>"
						classPK="<%= organization.getOrganizationId() %>"
						portletURL="<%= renderResponse.createRenderURL() %>"
					/>
				</span>

				<span class="entry-tags">
					<liferay-ui:asset-tags-summary
						className="<%= Organization.class.getName() %>"
						classPK="<%= organization.getOrganizationId() %>"
						portletURL="<%= renderResponse.createRenderURL() %>"
					/>
				</span>

			</c:if>
			<%request.setAttribute(WebKeys.ORGANIZATION, organization);
				request.setAttribute("addresses.className", Organization.class.getName());
				request.setAttribute("addresses.classPK", organizationId);
				request.setAttribute("emailAddresses.className", Organization.class.getName());
				request.setAttribute("emailAddresses.classPK", organizationId);
				request.setAttribute("phones.className", Organization.class.getName());
				request.setAttribute("phones.classPK", organizationId);
				request.setAttribute("websites.className", Organization.class.getName());
				request.setAttribute("websites.classPK", organizationId);
			%>

			<c:if test="<%= organization != null %>">
				<div class="organization-information">
					<div class="section entity-email-addresses">
						<liferay-util:include page="/html/portlet/directory/common/additional_email_addresses.jsp" />
					</div>

					<div class="section entity-websites">
						<liferay-util:include page="/html/portlet/directory/common/websites.jsp" />
					</div>

					<div class="section entity-addresses">
						<liferay-util:include page="/html/portlet/directory/organization/addresses.jsp" />
					</div>

					<div class="section entity-phones">
						<liferay-util:include page="/html/portlet/directory/organization/phone_numbers.jsp" />
					</div>

					<div class="section entity-comments">
						<liferay-util:include page="/html/portlet/directory/organization/comments.jsp" />
					</div>
				</div>

				<br />

				<liferay-ui:custom-attributes-available className="<%= Organization.class.getName() %>">
					<liferay-ui:custom-attribute-list
						className="<%= Organization.class.getName() %>"
						classPK="<%= (organization != null) ? organization.getOrganizationId() : 0 %>"
						editable="<%= false %>"
						label="<%= true %>"
					/>
				</liferay-ui:custom-attributes-available>
			</c:if>

			<br />

			<c:if test="<%= organizationsCount > 0 %>">
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

				<liferay-ui:search-container
					curParam="cur1"
					delta="<%= organizationsPerPage %>"
					deltaConfigurable="<%= false %>"
					headerNames="<%= StringUtil.merge(organizationColumns) %>"
					iteratorURL="<%= portletURL %>"
					rowChecker="<%= new RowChecker(renderResponse) %>"
					searchContainer="<%= new OrganizationSearch(renderRequest, portletURL) %>"
				>

					<liferay-ui:search-form
						page="/html/portlet/enterprise_admin/organization_search.jsp"
					/>

					<liferay-ui:search-container-results
						results="<%= OrganizationServiceUtil.getOrganizations(CompanyThreadLocal.getCompanyId(), (organization != null) ? organization.getOrganizationId() : 0, searchContainer.getStart(), searchContainer.getEnd()) %>"
						total="<%= organizationsCount %>"
					/>

					<liferay-ui:search-container-row
						className="com.liferay.portal.model.Organization"
						escapedModel="<%= true %>"
						keyProperty="organizationId"
						modelVar="curOrganization"
					>
						<liferay-portlet:renderURL varImpl="rowURL">
							<portlet:param name="struts_action" value="/enterprise_admin_organizations/view" />
							<portlet:param name="organizationId" value="<%= String.valueOf(curOrganization.getOrganizationId()) %>" />
						</liferay-portlet:renderURL>

						<%
						if (!OrganizationPermissionUtil.contains(permissionChecker, curOrganization.getOrganizationId(), ActionKeys.UPDATE)) {
							rowURL = null;
						}
						%>

						<%@ include file="/html/portlet/enterprise_admin/organization/organization_columns.jspf" %>
					</liferay-ui:search-container-row>

					<c:if test="<%= !results.isEmpty() %>">
						<div class="separator"><!-- --></div>

						<aui:button onClick='<%= renderResponse.getNamespace() + "deleteOrganizations();" %>' value="delete" />
					</c:if>

					<liferay-ui:search-iterator />
				</liferay-ui:search-container>
			</c:if>
		</liferay-util:buffer>


		<aui:column columnWidth="<%= (showOrganizationMenu && organization != null) ? 75 : 100 %>" cssClass="lfr-asset-column lfr-asset-column-details" first="<%= true %>">
			<c:choose>
				<c:when test="<%= (organization != null) %>" >
					<liferay-ui:panel-container extended="<%= false %>" persistState="<%= true %>">
						<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" persistState="<%= true %>" title='<%= LanguageUtil.get(pageContext, (organization != null) ? "suborganizations" : "organizations") %>'>
							<%= showOrganizationsTree %>
						</liferay-ui:panel>
					</liferay-ui:panel-container>
				</c:when>
				<c:otherwise>
					<%= showOrganizationsTree %>
				</c:otherwise>
			</c:choose>
		</aui:column>

		<c:if test="<%= showOrganizationMenu && organization != null%>">
			<aui:column columnWidth="<%= 25 %>" cssClass="lfr-asset-column lfr-asset-column-actions" last="<%= true %>">
				<div class="lfr-asset-summary">
					<img alt="<%= HtmlUtil.escape(organization.getName()) %>" class="avatar" src='<%= (organization != null) ? themeDisplay.getPathImage() + "/organization_logo?img_id=" + organization.getLogoId() + "&t=" + ImageServletTokenUtil.getToken(organization.getLogoId()) : "" %>' />

					<div class="lfr-asset-name">
						<h4><% organization.getName(); %></h4>
					</div>
				</div>

				<%
				request.removeAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
				%>

				<liferay-util:include page="/html/portlet/enterprise_admin/organization_action.jsp" />

			</aui:column>
		</c:if>
	</aui:layout>
</aui:form>

<%
if (organization != null) {
	EnterpriseAdminUtil.addPortletBreadcrumbEntries(organization, request, renderResponse);
}

%>