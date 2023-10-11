package com.neurotoxin.quicktrip.controller;

import com.neurotoxin.quicktrip.dto.Result;
import com.neurotoxin.quicktrip.dto.request.CartRequest;
import com.neurotoxin.quicktrip.dto.request.MemberLocationRequest;
import com.neurotoxin.quicktrip.dto.request.MemberPasswordRequest;
import com.neurotoxin.quicktrip.dto.response.CartResponse;
import com.neurotoxin.quicktrip.dto.response.MemberResponse;
import com.neurotoxin.quicktrip.service.TouristService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {"Tourist의 주요기능과 관련된 컨트롤러입니다."})
@RequiredArgsConstructor
@RequestMapping("/api/tourists")
@RestController
public class TouristController {

    private final TouristService touristService;

    @ApiOperation(value = "여행자의 상세정보를 조회합니다")
    @GetMapping("/{memberId}")
    public Result getTourist(@PathVariable Long memberId) {
        MemberResponse tourist = touristService.getTourist(memberId);
        return new Result(tourist);
    }

    @ApiOperation(value = "여행자의 비밀번호를 변경합니다")
    @PatchMapping("{memberId}/password")
    public Result changePassword(@PathVariable Long memberId, @RequestBody @Valid MemberPasswordRequest request) {
        touristService.changePassword(memberId, request);
        return new Result("여행자 비밀번호 변경 성공");
    }

    @ApiOperation(value = "여행자의 주소를 변경합니다")
    @PatchMapping("{memberId}/location")
    public Result changeLocation(@PathVariable Long memberId, @RequestBody @Valid MemberLocationRequest request) {
        touristService.changeLocation(memberId, request);
        return new Result("여행자 주소 변경 성공");
    }

    @ApiOperation(value = "여행자 회원탈퇴")
    @DeleteMapping("/{memberId}")
    public Result deleteTourist(@PathVariable Long memberId) {
        touristService.deleteTourist(memberId);
        return new Result("여행자 회원탈퇴 성공");
    }

    @ApiOperation(value = "여행자의 장바구니에 상품을 추가합니다")
    @PostMapping("/{memberId}/carts")
    public Result addCart(@PathVariable Long memberId, @RequestBody @Valid CartRequest request) {
        CartResponse cartResponse = touristService.addCart(memberId, request);
        return new Result(cartResponse);
    }

    @ApiOperation(value = "여행자의 장바구니를 조회합니다")
    @GetMapping("/{memberId}/carts")
    public Result getCarts(@PathVariable Long memberId) {
        List<CartResponse> carts = touristService.getCarts(memberId);
        return new Result(carts);
    }

    @ApiOperation(value = "여행자의 장바구니 품목을 삭제합니다")
    @DeleteMapping("/{memberId}/carts/{cartId}")
    public Result deleteCart(@PathVariable Long memberId, @PathVariable Long cartId) {
        touristService.deleteCart(memberId, cartId);
        return new Result("장바구니 품목 삭제 성공");
    }

    @ApiOperation(value = "여행자의 장바구니 품목을 모두 삭제합니다")
    @DeleteMapping("/{memberId}/carts")
    public Result deleteCarts(@PathVariable Long memberId) {
        touristService.deleteCarts(memberId);
        return new Result("장바구니 비우기 성공");
    }
}
