package com.stockapp.startup;

/**
 * This class runs at startup for web20 webapp
 * 
 * author: natamah
 * @version $Id: StartupServlet.java,v 1.20 2008/07/28 20:15:14 natamah Exp $
 */

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import com.stockapp.util.HttpHelper;
import com.stockapp.util.SpringHelper;
import java.util.Collection;
import java.util.HashMap;

public class StartupServlet extends HttpServlet {
	protected final Log logger = LogFactory.getLog(getClass());
	
	public void init() throws ServletException {
		super.init();
		try {
			ApplicationContext context = SpringHelper.getApplicationContext();		
			logger.info("stockapp application started");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		

}
