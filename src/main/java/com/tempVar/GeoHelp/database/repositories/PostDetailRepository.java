package com.tempVar.GeoHelp.database.repositories;

import com.tempVar.GeoHelp.database.entities.PostDetail;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface PostDetailRepository extends CrudRepository<PostDetail, Long> {
    @Query(value = "SELECT * FROM post_detail " +
            "WHERE post_id = :post_id", nativeQuery = true)
    List<PostDetail> getDetailByID(@Param("post_id") long post_id);

    @Modifying
    @Query(value = "INSERT INTO post_detail (title, content, contact, contact_type) " +
            "VALUE (:title, :content, :contact, :contact_type)", nativeQuery = true)
    @Transactional
    void newPostDetail(
            @Param("title") String title,
            @Param("content") String content,
            @Param("contact") String contact,
            @Param("contact_type") Integer contact_type
    );
}
