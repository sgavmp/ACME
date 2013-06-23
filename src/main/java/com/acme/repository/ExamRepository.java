package com.acme.repository;

import com.acme.model.exam.Exam;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExamRepository  extends PagingAndSortingRepository<Exam, Long> {

}
