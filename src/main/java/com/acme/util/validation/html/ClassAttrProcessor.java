package com.acme.util.validation.html;

import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.attr.AbstractTextChildModifierAttrProcessor;

public class ClassAttrProcessor extends
		AbstractTextChildModifierAttrProcessor {

	public ClassAttrProcessor() {
		// Only execute this processor for 'sayto' attributes. 
		super("class");
	}
	
	/* 
     * Our processor is a subclass of the convenience abstract implementation 
     * 'AbstractTextChildModifierAttrProcessor', which takes care of the 
     * DOM modifying stuff and allows us just to implement this 'getText(...)' 
     * method to compute the text to be set as tag body. 
     */
	@Override
	protected String getText(Arguments arg0, Element arg1, String arg2) {
		return arg1.toString();
	}

	@Override
	public int getPrecedence() {
		// A value of 10000 is higher than any attribute in the 
        // SpringStandard dialect. So this attribute will execute 
        // after all other attributes from that dialect, if in the  
        // same tag.
		return 1000;
	}

}
