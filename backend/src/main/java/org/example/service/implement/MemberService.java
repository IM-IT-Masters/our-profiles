package org.example.service.implement;

import org.example.model.Member;
import org.example.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService extends BaseService<Member, MemberRepository> {}
