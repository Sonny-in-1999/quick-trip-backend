package com.neurotoxin.quicktrip.service;

import com.neurotoxin.quicktrip.dto.request.CartRequest;
import com.neurotoxin.quicktrip.dto.request.MemberRequest;
import com.neurotoxin.quicktrip.dto.request.MemberLocationRequest;
import com.neurotoxin.quicktrip.dto.request.MemberPasswordRequest;
import com.neurotoxin.quicktrip.dto.response.CartResponse;
import com.neurotoxin.quicktrip.dto.response.MemberResponse;
import com.neurotoxin.quicktrip.entity.Cart;
import com.neurotoxin.quicktrip.entity.Member;
import com.neurotoxin.quicktrip.repository.CartRepository;
import com.neurotoxin.quicktrip.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
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


    @Transactional
    public MemberResponse addTourist(MemberRequest request) {
        Member member = request.toEntity("ROLE_USER");
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
        return cart.toResponse();
    }

    public List<CartResponse> getCarts(Long memberId) {
        Member member = validateAndReturnMemberById(memberId);
        List<Cart> carts = member.getCarts();
        return carts.stream().map(Cart::toResponse).collect(Collectors.toList());
    }

    public void deleteCart(Long memberId, Long cartId) {
        Member member = validateAndReturnMemberById(memberId);
        Cart cart = validateAndReturnCartById(cartId);
        if (member.getCarts().contains(cart)) {
            member.getCarts().remove(cart);
            cartRepository.delete(cart);
        }
    }

    private Member validateAndReturnMemberById(Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (findMember.isPresent()) return findMember.get();
        throw new IllegalArgumentException("유저를 찾을 수 없습니다.");
    }

    private Cart validateAndReturnCartById(Long cartId) {
        Optional<Cart> findCart = cartRepository.findById(cartId);
        if (findCart.isPresent()) return findCart.get();
        throw new IllegalArgumentException("유저를 찾을 수 없습니다.");
    }
}
