package com.liferaystack.example.logger;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogWrapper;

/**
 * @author Syed Ali
 */

public class CustomLogWrapper extends LogWrapper{

	public CustomLogWrapper(Log log) {
		super(log);
	}
	
	@Override
	public void debug(Object msg) {
		// TODO Auto-generated method stub
		super.debug(msg);
	}
}
