package com.neurotoxin.quicktrip.controller;

import com.neurotoxin.quicktrip.dto.Result;
import com.neurotoxin.quicktrip.dto.request.CartRequest;
import com.neurotoxin.quicktrip.dto.request.MemberLocationRequest;
import com.neurotoxin.quicktrip.dto.request.MemberPasswordRequest;
import com.neurotoxin.quicktrip.dto.request.MemberRequest;
import com.neurotoxin.quicktrip.dto.response.CartResponse;
import com.neurotoxin.quicktrip.dto.response.MemberResponse;
import com.neurotoxin.quicktrip.service.TouristService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/tourists")
@RestController
public class TouristController {

    private final TouristService touristService;

    @PostMapping
    public Result addTourist(@RequestBody @Valid MemberRequest request) {
        MemberResponse tourist = touristService.addTourist(request);
        return new Result(tourist);
    }

    @GetMapping("/{memberId}")
    public Result getTourist(@PathVariable Long memberId) {
        MemberResponse tourist = touristService.getTourist(memberId);
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

    @PostMapping("/{memberId}/carts")
    public void addCart(@PathVariable Long memberId, @RequestBody @Valid CartRequest request) {
        touristService.addCart(memberId, request);
    }

    @GetMapping("/{memberId}/carts")
    public Result getCarts(@PathVariable Long memberId) {
        List<CartResponse> carts = touristService.getCarts(memberId);
        return new Result(carts);
    }

    @DeleteMapping("/{memberId}/carts/{cartId}")
    public void deleteCart(@PathVariable Long memberId, @PathVariable Long cartId) {
        touristService.deleteCart(memberId, cartId);
    }
}
