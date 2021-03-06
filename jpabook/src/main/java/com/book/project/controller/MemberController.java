package com.book.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.book.project.model.Address;
import com.book.project.model.Member;
import com.book.project.service.MemberService;
import com.book.project.web.MemberForm;

import lombok.RequiredArgsConstructor;

	@Controller
	@RequiredArgsConstructor
	public class MemberController {
		
	 private final MemberService memberService;
	 
	 @GetMapping(value = "/members/new")
	 public String createForm(Model model) {
	 model.addAttribute("memberForm", new MemberForm());
	 return "members/createMemberForm";
	 }
	 
	 
	 @PostMapping(value = "/members/new")
	 public String create(@Valid MemberForm form, BindingResult result) {
		 
	 if (result.hasErrors()) {
	 return "members/createMemberForm";
	 }
	 
	 Address address = new Address(form.getCity(), form.getStreet(),form.getZipcode());
	 Member member = new Member(); 
	 member.setName(form.getName());
	 member.setAddress(address);
	 memberService.join(member);
	 return "redirect:/";
	 }
	 
	 //추가
	 @GetMapping(value = "/members")
	 public String list(Model model) {
	 List<Member> members = memberService.findMembers(); 
	 model.addAttribute("members", members);
	 return "members/memberList";
	 }
	 
	 
}