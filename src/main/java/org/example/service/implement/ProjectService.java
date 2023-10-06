package org.example.service.implement;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Member;
import org.example.model.Project;
import org.example.repository.MemberRepository;
import org.example.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjectService extends BaseService<Project, ProjectRepository> {

    @Autowired
    MemberRepository memberRepository;

    public Project insert(Project newProject) throws NoSuchElementException {
        Set<Member> members = newProject.getMembers();
        Set<Member> newMembers = new HashSet<>();
        if(!members.isEmpty()) {
            for (Member member : members) {
                Optional<Member> memberOptional = memberRepository.findById(member.getId());
                if(memberOptional.isPresent()) {
                    Member member1 = memberOptional.get();
                    newMembers.add(member1);
                } else {
                    throw new NoSuchElementException("memberId: " + member.getId() + " not found");
                }
            }
            newProject.setMembers(newMembers);
        }
        return repository.save(newProject);
    }

    @Override
    public void deleteById(UUID id) throws NoSuchElementException {
        Optional <Project> project = repository.findById(id);
        if(project.isPresent()) {
            project.get().setMembers(new HashSet<>());
            repository.deleteById(id);
        } else {
            throw new NoSuchElementException("Can't found projectId: " + id + " to delete");
        }
    }

    public Project update(UUID productId, Project newProject) throws NoSuchElementException{
        Optional<Project> product = repository.findById(productId);
        if(product.isPresent()){
            newProject.setId(productId);
            Set<Member> membersId = newProject.getMembers();
            Set<Member> members = new HashSet<>();
            for (Member memberId : membersId) {
                Optional<Member> memberOptional = memberRepository.findById(memberId.getId());
                if(memberOptional.isPresent()) {
                    Member member = memberOptional.get();
                    members.add(member);
                } else {
                    throw new NoSuchElementException("Member id: " + memberId.getId() + "is not exist");
                }
            }
            newProject.setMembers(members);
        } else {
            throw new NoSuchElementException("Can't find project with id: " + productId.toString());
        }
        return repository.save(newProject);
    }


}
