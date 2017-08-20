/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liferaystack.activator;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
//import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PortalUtil;

@Component(
	immediate = true,
	property = {
        "key=login.events.post"
    },
    service = LifecycleAction.class
)
public class LiferaystackActivatorLoginPreAction implements LifecycleAction {

    @Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent)
		throws ActionException {
	    	 HttpServletRequest request = lifecycleEvent.getRequest();
	    	 System.out.println("http request is "+request);
	    	 try {
	    		 long userId = PortalUtil.getUser(request).getUserId();
	    		 System.out.println("userId is >>>>>>>>>>>>>>>>>>>"+userId);
	    		 List<Organization> organizations= OrganizationLocalServiceUtil.getUserOrganizations(userId);
	    		 
	    		 for(Organization organization:organizations)
	    		 {
	    			 System.out.println("users organisation is "+organization);
	    			 Group organizationGroup = GroupLocalServiceUtil.getOrganizationGroup(organization.getCompanyId(), organization.getOrganizationId());
	    			 List<Layout> privateLayouts = LayoutLocalServiceUtil.getLayouts(organizationGroup.getGroupId(), true);
	    			 List<Layout> publicLayouts = LayoutLocalServiceUtil.getLayouts(organizationGroup.getGroupId(), false);
	    			 String rightURL = null;
	    			 for (Layout layout : publicLayouts) {
	    				 //if(layout.getP){
	    					rightURL = PortalUtil.getLayoutActualURL(layout);
	    					System.out.println("rightURL : "+rightURL);
	    					
	    				 //}
						
					}
	    		 }
	    		 
			} catch (PortalException e) {
				e.printStackTrace();
			}
		System.out.println("login.event.post=" + lifecycleEvent+", session s: ");
	}
    }

