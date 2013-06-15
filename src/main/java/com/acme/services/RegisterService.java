package com.acme.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.exception.RegisterNoExistException;
import com.acme.model.examination.Register;
import com.acme.model.user.User;
import com.acme.repository.RegisterRepository;
import com.acme.repository.UserRepository;

@Service
public class RegisterService {

	@Autowired
	private RegisterRepository repositoryregister;
	
	@Autowired 
	private UserRepository repositoryuser;
	
	public List<Register> getAllRegisters() {
		return (List<Register>) repositoryregister.findAll();
	}

	public Register getRegisterById(Long idReg) throws RegisterNoExistException {
		if (!repositoryregister.exists(idReg)) {
			throw new RegisterNoExistException("exception.register.noexist");
		}
		return repositoryregister.findOne(idReg);
	}

	public void removeRegister(Register reg) {
		repositoryregister.delete(reg);
	}
	
	public Register saveRegister(Register reg) {
		return repositoryregister.save(reg);
	}
	
	public List<Register> allRegisterByUserUsername(String username) {
		User u =repositoryuser.findUserByUsername(username);
		return repositoryregister.findByUser(u);
	}

}
