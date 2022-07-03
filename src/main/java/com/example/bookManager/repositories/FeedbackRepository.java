package com.example.bookManager.repositories;

import com.example.bookManager.domain.FeedBackDetail;
import com.example.bookManager.domain.UserDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends CrudRepository<FeedBackDetail, Integer> {
    List<FeedBackDetail> findAll();

    @Query(value = "SELECT * FROM feedback_detail us WHERE us.book_detail_id = :bookDetail_id", nativeQuery = true)
    List<FeedBackDetail> findFeedBackByBookId(@Param("bookDetail_id") int idBook);
}
