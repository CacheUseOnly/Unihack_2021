package com.tempVar.GeoHelp.APIs.controllers;

import com.alibaba.fastjson.JSONObject;
import com.tempVar.GeoHelp.APIs.returnDTOs.posterReturnDTO;
import com.tempVar.GeoHelp.database.entities.PostDetail;
import com.tempVar.GeoHelp.database.entities.PostInfo;
import com.tempVar.GeoHelp.database.repositories.PostDetailRepository;
import com.tempVar.GeoHelp.database.repositories.PostInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.PersistenceException;

@Slf4j
@Controller
@RequestMapping(path = "/poster")
public class PosterController {

    @Autowired
    private PostInfoRepository postInfoRepository;
    @Autowired
    private PostDetailRepository postDetailRepository;

    @PostMapping(path = "/newPost")
    @ResponseBody
    public String newPost(@RequestBody String body) {
        log.info("body: {}", body);
        JSONObject req = JSONObject.parseObject(body);
        log.info("req: {}", req);

        int status = 0;
        String msg = "success";

        try {
            PostInfo postInfo = new PostInfo();
            postInfo.setLongitude(Float.parseFloat(req.getString("longitude")));
            postInfo.setLatitude(Float.parseFloat(req.getString("latitude")));
            long post_id = postInfoRepository.save(postInfo).getPost_id();

            PostDetail postDetail = new PostDetail();
            postDetail.setPost_id(post_id);
            postDetail.setTitle(req.getString("title"));
            if (!req.getString("content").isEmpty()) postDetail.setContent(req.getString("content"));
            if (!req.getString("contact").isEmpty()) postDetail.setContact(req.getString("contact"));
            if (!req.getString("contact_type").isEmpty()) postDetail.setContact_type(Integer.parseInt(req.getString("contact_type")));
            postDetailRepository.save(postDetail);
        } catch (PersistenceException e) {
            msg = e.getMessage();
            status = 9;
        }

        posterReturnDTO ret = new posterReturnDTO(status, msg);
        return JSONObject.toJSONString(ret);
    }
}
