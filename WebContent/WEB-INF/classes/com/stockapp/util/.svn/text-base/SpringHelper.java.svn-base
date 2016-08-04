package com.stockapp.util;

/**
 * This class provides helper methods for accessing the Spring context
 * 
 * author: natamah
 * @version $Id: SpringHelper.java,v 1.2 2008/02/07 15:08:58 natamah Exp $
 */

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringHelper
{
	private static ApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");
    private SpringHelper()
    {
        
    }
    /**
     * Method used to load all the spring bean config files and create
     * a ApplicationContext out of them
     * Usage: ApplicationContext context = SpringHelper.getApplicationContext();
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext()
    {
        //ApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");
        return context;
    }
    
    /**
     * Method used to get the Bean by id
     * @param id
     * @return
     */
    public static Object getBeanById(String id)
    {
        return getApplicationContext().getBean(id);
    }
    
    
}