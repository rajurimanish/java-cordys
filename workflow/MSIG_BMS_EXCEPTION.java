package com.bms.workflow;

public class MSIG_BMS_EXCEPTION extends Exception {

	private static final long serialVersionUID = 1L;

	public MSIG_BMS_EXCEPTION() {
	}

	public MSIG_BMS_EXCEPTION(String msg) {
		super(msg);
	}

	public MSIG_BMS_EXCEPTION(String msg, Throwable t) {
		super(msg, t);
	}
}
