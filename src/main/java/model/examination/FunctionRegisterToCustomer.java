package model.examination;

import model.user.Customer;
import model.user.Role;

import com.google.common.base.Function;

public class FunctionRegisterToCustomer implements Function<Register, Role> {

	public Role apply(Register r) {
		return r.getCustomer();
	}

}
