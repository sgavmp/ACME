package com.acme.util.validation.html;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.processor.IProcessor;

public class ValidateDialect extends AbstractDialect {

	@Override
	public String getPrefix() {
		return "validate";
	}

	@Override
	public boolean isLenient() {
		// TODO Auto-generated method stub
		return false;
	}

	 /* 
     * The processors. 
     */ 
    @Override 
    public Set<IProcessor> getProcessors() { 
        final Set<IProcessor> processors = new HashSet<IProcessor>(); 
        processors.add(new ClassAttrProcessor()); 
        return processors; 
    }
    
}
