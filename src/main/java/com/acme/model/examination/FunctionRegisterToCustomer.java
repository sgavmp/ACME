package com.acme.model.examination;


import com.acme.model.user.Customer;
import com.acme.model.user.Role;
import com.google.common.base.Function;

public class FunctionRegisterToCustomer implements Function<Register, Role> {

	public Role apply(Register r) {
		return r.getCustomer();
	}

}
