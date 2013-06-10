package com.acme.converters;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.acme.model.certification.Certification;
import com.acme.repository.CertificationRepository;

@Component
@Transactional
public class CertificationToString implements GenericConverter {

	@Autowired
	private CertificationRepository certificationRepository;
	
	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		Set<ConvertiblePair> pairs = new HashSet<ConvertiblePair>();
		 
		//pairs.add(new ConvertiblePair(Certification.class, String.class));
		pairs.add(new ConvertiblePair(String.class, Certification.class));
		pairs.add(new ConvertiblePair(String.class, Date.class));
 
		return pairs;
	}

	@Override
	public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
		Object result = null;
		if (sourceType.getObjectType().equals(String.class) & targetType.getObjectType().equals(Certification.class)) {
			Long id;
			try {
				id = Long.valueOf((String)source);
				result = certificationRepository.findOne(id);
			} catch (Throwable oops) {
				throw new IllegalArgumentException(oops);
			}
		}
		else if (sourceType.getObjectType().equals(String.class) & targetType.getObjectType().equals(Date.class)){
			String s;
			String[] formats = {"'T'HH:mm:ss", "yyyy-MM-dd"};
			try {
				s=(String)source;
				result = DateUtils.parseDate(s, formats);;
			} catch (Throwable oops) {
				throw new IllegalArgumentException(oops);
			}
		}

		return result;
	}

	

}
