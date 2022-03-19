package com.example.demo.api.member;

import org.springframework.stereotype.Service;

import com.example.demo.core.AbstractFirebaseCoreService;
import com.example.demo.exception.DataNotFoundException;

@Service
public class MemberService extends AbstractFirebaseCoreService<Member>{

	public MemberService(MemberRepository memberRepository) {
		super(memberRepository, Member.class);
	}
	
	public boolean create(final Member.CreateRequest request) {
		return create(
				Member.of(request),
				request.getUsername());
	}
	
	public boolean update(final Member.UpdateRequest request) {
		final Member member = select(request.getUsername());
		if (member == null) {
			throw new DataNotFoundException();
		}
		return update(
				Member.of(member, request),
				member.getUsername());
	}
	
	public Member select(final Member.SelectRequest request) {
		return select(request.getUsername());
	}
	
	public boolean delete(final Member.DeleteRequest request) {
		final String document = request.getUsername();
		final Member member = select(document);
		if (member == null) {
			throw new DataNotFoundException();
		}
		return delete(document);
	}
	
}
