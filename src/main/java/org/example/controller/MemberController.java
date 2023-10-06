package org.example.controller;

import org.example.model.Member;
import org.example.service.implement.MemberService;
import org.example.utilities.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.UUID;


@RestController
@RequestMapping("/api")
public class MemberController {
    @Autowired
    MemberService memberService;
    @GetMapping("/members")
    public ResponseEntity<?> getAll() {
        return Response.createResponse(HttpStatus.OK,
                "get all member successfully",
                memberService.getAll());
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) throws NoSuchElementException{
        try {
            return Response.createResponse(HttpStatus.OK,
                    "get member by id successfully",
                    memberService.getById(id));
        } catch (NoSuchElementException e) {
            return Response.createResponse(HttpStatus.NOT_FOUND, e.getMessage(), null);
        }
    }

    @PostMapping("/member/insert")
    public ResponseEntity<?> insert(
            @RequestBody Member member
    ) throws NoSuchElementException {
        try {
            return Response.createResponse(
                    HttpStatus.OK,
                    "insert member successfully",
                    memberService.create(member)
            );
        } catch (NoSuchElementException e) {
            return Response.createResponse(HttpStatus.NOT_FOUND, e.getMessage(), null);
        }
    }

    @DeleteMapping("/member/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) throws NoSuchElementException {
        try {
            memberService.deleteById(id);
            return Response.createResponse(HttpStatus.OK,
                    "deleted member have id: " + id.toString(),
                    null);

        } catch (NoSuchElementException e) {
            return Response.createResponse(HttpStatus.OK, e.getMessage(), null);
        }

    }


    // update both member and category information
    @PutMapping("/member/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable UUID id,
            @RequestBody Member newMember) {
        try {
            return Response.createResponse(HttpStatus.OK, "update member successfully", memberService.update(id, newMember));
        } catch (NoSuchElementException e) {
            return Response.createResponse(HttpStatus.NOT_FOUND, e.getMessage(), null);

        }
    }

}
