package com.tempVar.GeoHelp.APIs.controllers;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tempVar.GeoHelp.APIs.returnDTOs.returnDTO;
import com.tempVar.GeoHelp.database.entities.PostDetail;
import com.tempVar.GeoHelp.database.entities.PostInfo;
import com.tempVar.GeoHelp.database.repositories.PostDetailRepository;
import com.tempVar.GeoHelp.database.repositories.PostInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.QueryTimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Vector;

@Slf4j
@Controller
@RequestMapping(path="/viewer")
public class ViewerController {

    @Autowired
    private PostInfoRepository postInfoRepository;
    @Autowired
    private PostDetailRepository postDetailRepository;

    @PostMapping(path = "/queryPostsID")
    @ResponseBody
    public String queryPostsID(@RequestBody String body) {

        log.info("body: {}", body);
        JSONObject req = JSONObject.parseObject(body);
        log.info("req: {}", req);

        List<PostInfo> result = new Vector<>();
        String msg = "success";
        int status = 0;

        try {
            result = postInfoRepository.findPostsByStage(
                    Float.parseFloat(req.getString("centerLat")),
                    Float.parseFloat(req.getString("centerLongi")),
                    Float.parseFloat(req.getString("length")));
        } catch (QueryTimeoutException e) {
            msg = "Query timeout.";
            status = 2;
        } catch (NoResultException e) {
            msg = "no result.";
            status = 1;
        } catch (PersistenceException e) {
            msg = e.getMessage();
            status = 9;
        }

        returnDTO ret = new returnDTO(status, msg, result);
        return JSONObject.toJSONString(ret);
    }

    @PostMapping(path = "/queryPostDetail")
    @ResponseBody
    public String queryPostDetail(@RequestBody String body) {
        log.info("body: {}", body);
        JSONObject req = JSONObject.parseObject(body);
        log.info("req: {}", req);

        String msg = "success";
        int status = 0;
        List<PostDetail> res = new Vector<>();

        try {
            res = postDetailRepository.getDetailByID(Long.parseLong(req.getString("post_id")));
        } catch (PersistenceException e) {
            msg = e.getMessage();
            status = 9;
        }

        returnDTO ret = new returnDTO(status, msg, res.get(0));
        return JSONObject.toJSONString(ret);
    }
}
