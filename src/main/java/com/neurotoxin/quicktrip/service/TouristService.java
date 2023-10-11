package com.neurotoxin.quicktrip.service;

import com.neurotoxin.quicktrip.dto.request.CartRequest;
import com.neurotoxin.quicktrip.dto.request.MemberRequest;
import com.neurotoxin.quicktrip.dto.request.MemberLocationRequest;
import com.neurotoxin.quicktrip.dto.request.MemberPasswordRequest;
import com.neurotoxin.quicktrip.dto.response.BuildingResponse;
import com.neurotoxin.quicktrip.dto.response.CartResponse;
import com.neurotoxin.quicktrip.dto.response.MemberResponse;
import com.neurotoxin.quicktrip.entity.Building;
import com.neurotoxin.quicktrip.entity.Cart;
import com.neurotoxin.quicktrip.entity.Member;
import com.neurotoxin.quicktrip.entity.Role;
import com.neurotoxin.quicktrip.repository.BuildingRepository;
import com.neurotoxin.quicktrip.repository.CartRepository;
import com.neurotoxin.quicktrip.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TouristService {

    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;
    private final BuildingRepository buildingRepository;


    public List<BuildingResponse> getBuildingsByLocation(String location) {
        Optional<List<Building>> buildings = buildingRepository.findAllByLocationContaining(location);
        if (buildings.isPresent()) {
            return buildings.get().stream().map(Building::toResponse).collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("등록되지 않은 지역입니다.");
        }
    }

    @Transactional
    public MemberResponse addTourist(MemberRequest request) {
        if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 사용중인 이메일입니다.");
        }
        Member member = request.toEntity(Role.TOURIST);
        member.passwordEncode(passwordEncoder);
        Member tourist = memberRepository.save(member);
        return tourist.toResponse();
    }

    public MemberResponse getTourist(Long memberId) {
        Member tourist = validateAndReturnMemberById(memberId);
        return tourist.toResponse();
    }

    @Transactional
    public void changePassword(Long memberId, MemberPasswordRequest request) {
        Member member = validateAndReturnMemberById(memberId);
        member.changePassword(request.getPassword());
        member.passwordEncode(passwordEncoder);
    }

    @Transactional
    public void changeLocation(Long memberId, MemberLocationRequest request) {
        Member member = validateAndReturnMemberById(memberId);
        member.changeLocation(request.getLocation());
    }

    @Transactional
    public void deleteTourist(Long memberId) {
        Member member = validateAndReturnMemberById(memberId);
        memberRepository.delete(member);
    }

    @Transactional
    public CartResponse addCart(Long memberId, CartRequest request) {
        Member member = validateAndReturnMemberById(memberId);
        Cart cart = request.toEntity(member);
        Cart savedCart = cartRepository.save(cart);
        return savedCart.toResponse();
    }

    public List<CartResponse> getCarts(Long memberId) {
        Member member = validateAndReturnMemberById(memberId);
        List<Cart> carts = member.getCarts();
        return carts.stream().map(Cart::toResponse).collect(Collectors.toList());
    }

    @Transactional
    public void deleteCart(Long memberId, Long cartId) {
        Member member = validateAndReturnMemberById(memberId);
        Cart cart = validateAndReturnCartById(cartId);
        if (!member.getCarts().contains(cart)) {
            throw new IllegalArgumentException("카트가 존재하지 않거나 권한이 없습니다.");
        }
        member.getCarts().remove(cart);
        cartRepository.delete(cart);
    }

    @Transactional
    public void deleteCarts(Long memberId) {
        Member member = validateAndReturnMemberById(memberId);
        List<Cart> cartItems = cartRepository.findAllByMember(member);
        cartRepository.deleteAll(cartItems);
        memberRepository.save(member);
    }

    private Member validateAndReturnMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
    }

    private Cart validateAndReturnCartById(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("장바구니 목록을 찾을 수 없습니다."));
    }
}