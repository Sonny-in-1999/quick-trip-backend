package com.neurotoxin.quicktrip.controller;

import com.neurotoxin.quicktrip.dto.Result;
import com.neurotoxin.quicktrip.dto.request.MemberLocationRequest;
import com.neurotoxin.quicktrip.dto.request.MemberPasswordRequest;
import com.neurotoxin.quicktrip.dto.request.MemberRequest;
import com.neurotoxin.quicktrip.dto.response.MemberResponse;
import com.neurotoxin.quicktrip.service.TouristService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api/tourists")
@RestController
public class TouristController {

    private final TouristService touristService;

    @GetMapping("/{memberId}")
    public Result getTourist(@PathVariable Long memberId) {
        MemberResponse tourist = touristService.getTourist(memberId);
        return new Result(tourist);
    }

    @PostMapping
    public Result addTourist(@RequestBody @Valid MemberRequest request) {
        MemberResponse tourist = touristService.addTourist(request);
        return new Result(tourist);
    }

    @PatchMapping("{memberId}/password")
    public void changePassword(@PathVariable Long memberId, @RequestBody @Valid MemberPasswordRequest request) {
        touristService.changePassword(memberId, request);
    }

    @PatchMapping("{memberId}/location")
    public void changeLocation(@PathVariable Long memberId, @RequestBody @Valid MemberLocationRequest request) {
        touristService.changeLocation(memberId, request);
    }

    @DeleteMapping("/{memberId}")
    public void deleteTourist(@PathVariable Long memberId) {
        touristService.deleteTourist(memberId);
    }
}
