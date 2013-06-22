package com.acme.model.examination;


import com.acme.model.user.User;
import com.google.common.base.Function;

public class FunctionRegisterToCustomer implements Function<Register, User> {

	public User apply(Register r) {
		return r.getCustomer();
	}

}
