package com.cms.workflow;

public class MSIG_CMS_EXCEPTION extends Exception {

	private static final long serialVersionUID = 1L;

	public MSIG_CMS_EXCEPTION() {
	}

	public MSIG_CMS_EXCEPTION(String msg) {
		super(msg);
	}

	public MSIG_CMS_EXCEPTION(String msg, Throwable t) {
		super(msg, t);
	}
}
