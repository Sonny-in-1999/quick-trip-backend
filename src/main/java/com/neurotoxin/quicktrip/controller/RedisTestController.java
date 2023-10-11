package com.neurotoxin.quicktrip.controller;

import com.neurotoxin.quicktrip.dto.Result;
import com.neurotoxin.quicktrip.dto.request.RedisTestRequest;
import com.neurotoxin.quicktrip.service.RedisService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Redis 연결 테스트를 위한 컨트롤러 입니다."})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class RedisTestController {

    private final RedisService redisService;

    @PostMapping("/redis/test")
    public void saveData(@RequestBody RedisTestRequest request) {
        redisService.saveData(request.getKey(), request.getValue());
    }

    @GetMapping("/redis/test/{key}")
    public Result getData(@PathVariable String key) {
        String value = redisService.getData(key);
        return new Result(value);
    }
}
