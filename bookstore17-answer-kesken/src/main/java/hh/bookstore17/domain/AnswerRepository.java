package hh.bookstore17.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, Long>{

	
	
	List<Answer> findByComment(Long commentId);

}
