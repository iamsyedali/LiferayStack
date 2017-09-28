package com.liferaystack.filter;

import java.io.IOException;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.PortletFilter;
import javax.portlet.filter.RenderFilter;
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

public class MyRenderFilter implements RenderFilter{

	private static Log _log = LogFactoryUtil.getLog(MyRenderFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws PortletException {
		_log.info("init.....");
	}

	@Override
	public void destroy() {
		_log.info("destroy.....");
	}

	@Override
	public void doFilter(RenderRequest request, RenderResponse response, FilterChain chain) throws IOException, PortletException {
		_log.info("RenderDoFilter.....");
		chain.doFilter(request, response);
	}
}