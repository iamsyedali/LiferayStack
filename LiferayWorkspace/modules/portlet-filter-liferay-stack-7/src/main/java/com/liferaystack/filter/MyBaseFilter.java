package com.liferaystack.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BaseFilter;
import org.osgi.service.component.annotations.Component;

/**
 * @author Syed Ali
 */

@Component(
	immediate = true, 
	property = {
		"servlet-context-name=", 
		"servlet-filter-name=http-filter",
		"url-pattern=/*" 
	}, 
	service = Filter.class
)

public class MyBaseFilter extends BaseFilter{

	private static final Log _log = LogFactoryUtil.getLog(MyBaseFilter.class);

	@Override
	protected void processFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws Exception {
		_log.info("processFilter BaseFilter..........");
		//filterChain.doFilter(request, response);
		super.processFilter(request, response, filterChain);
	}
	
	@Override
	protected Log getLog() {
		return _log;
	}
}