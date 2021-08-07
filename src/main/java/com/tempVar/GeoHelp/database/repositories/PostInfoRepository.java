package com.tempVar.GeoHelp.database.repositories;

import com.tempVar.GeoHelp.database.entities.PostInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface PostInfoRepository extends CrudRepository<PostInfo, Long> {
    @Query(value = "SELECT * FROM post_info WHERE " +
            "ABS(latitude - :centerLat ) < :length AND " +
            "ABS(longitude - :centerLongi ) < :length " +
            "ORDER BY ts DESC", nativeQuery = true)
    List<PostInfo> findPostsByStage(
            @Param("centerLat") float centerLat,
            @Param("centerLongi") float centerLongi,
            @Param("length") float length);

    @Modifying
    @Query(value = "INSERT INTO post_info (latitude, longitude) " +
            "VALUE (:latitude, :longitude)", nativeQuery = true)
    @Transactional
    void newPostInfo(
            @Param("latitude") Float latitude,
            @Param("longitude") Float longitude
    );
}
