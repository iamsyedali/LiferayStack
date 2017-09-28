package com.liferaystack.filter;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.filter.ActionFilter;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.PortletFilter;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferaystack.constants.MyPortletKeys;
import org.osgi.service.component.annotations.Component;

/**
 * @author Syed Ali
 */

@Component(immediate = true, 
	property = {
		"javax.portlet.name="+MyPortletKeys.My, 
	},
	service = PortletFilter.class
)

public class MyActionFilter implements ActionFilter{

	private static Log _log = LogFactoryUtil.getLog(MyActionFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws PortletException {
		_log.info("init.....");
	}

	@Override
	public void destroy() {
		_log.info("destroy.....");
	}

	@Override
	public void doFilter(ActionRequest request, ActionResponse response, FilterChain chain) throws IOException, PortletException {
		_log.info("ActionDoFilter.....");
		chain.doFilter(request, response);
	}
}