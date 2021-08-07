package com.tempVar.GeoHelp.database.repositories;

import com.tempVar.GeoHelp.database.entities.PostDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostDetailRepository extends CrudRepository<PostDetail, Long> {
    @Query(value = "SELECT * FROM post_detail " +
            "WHERE post_id = :post_id", nativeQuery = true)
    List<PostDetail> getDetailByID(@Param("post_id") long post_id);
}
