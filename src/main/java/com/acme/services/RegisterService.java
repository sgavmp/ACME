package com.acme.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	public void removeRegister(Register reg) {
		repositoryregister.delete(reg);
	}

	@Transactional
    public void removeRegister(Long id) {
        repositoryregister.delete(id);
    }
	
	@Transactional
	public Register saveRegister(Register reg) throws JpaOptimisticLockingFailureException {
		return repositoryregister.save(reg);
	}
	
	public List<Register> allRegisterByUserUsername(String username) {
		User u =repositoryuser.findUserByUsername(username);
		return repositoryregister.findByUser(u);
	}

}
