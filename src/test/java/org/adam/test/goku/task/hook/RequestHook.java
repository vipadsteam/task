/**
 * 
 */
package org.adam.test.goku.task.hook;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.adam.service.IRequestHook;
import org.springframework.stereotype.Service;

/**
 * @author USER
 *
 */
@Service
public class RequestHook implements IRequestHook {

	private static final Log log = LogFactory.getLog(RequestHook.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.adam.service.IRequestHook#doBefore(java.lang.String,
	 * java.util.Map, java.lang.Object[], java.lang.Object)
	 */
	@Override
	public Object doBefore(String url, Map<String, String> headersMap, Object[] income, Object output) throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.adam.service.IRequestHook#doAfter(java.lang.String,
	 * java.util.Map, java.lang.Object[], java.lang.Object)
	 */
	@Override
	public Object doAfter(String url, Map<String, String> headersMap, Object[] income, Object output) throws Exception {
		return output;
	}

}
