/*****************************************************
 * Copyright (c) 2017 Real Soft Inc to Present
 *  All Rights Reserved. 
 *****************************************************
 */
package com.hb.config;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
/**
 * 
 * All rights reserved by RealSoftInc 2017
 * @see com.realsoftinc.sail.core.server.config.WebAppInitializer.java
 * @version 1.0
 * @since 2017
 * @author vankayalapatis
 * Created: May 8, 2017 9:49:38 PM
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	/**
	 * 
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SpringWebAppInitializer.class };
	}
	/**
	 * 
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}
	/***
	 * 
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}
