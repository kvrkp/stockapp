package com.stockapp.generateView;

import javax.servlet.http.HttpServletRequest;

/*
 * This is the interface that all the pages implement
 */
public interface GenerateView {
	public String buildContent(HttpServletRequest req);
}
