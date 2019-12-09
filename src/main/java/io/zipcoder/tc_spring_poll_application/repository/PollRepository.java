package io.zipcoder.tc_spring_poll_application.repository;

import io.zipcoder.tc_spring_poll_application.domain.Poll;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends PagingAndSortingRepository<Poll, Long> {

}
