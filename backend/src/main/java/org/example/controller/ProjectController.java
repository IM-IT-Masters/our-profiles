package org.example.controller;

import org.example.model.Project;
import org.example.service.implement.ProjectService;
import org.example.utilities.PaginatedResponse;
import org.example.utilities.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.UUID;


@RestController
@RequestMapping("/api")
public class ProjectController {
    @Autowired
    ProjectService projectService;
    @GetMapping("/projects")
    public ResponseEntity<?> getAll() {
        return Response.createResponse(HttpStatus.OK,
                "get all project successfully",
                projectService.getAll());
    }

    @GetMapping("/project/page")
    public ResponseEntity<?> getPage(
            @RequestParam(required = false) UUID categoryId,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int pageIndex,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Page<Project> project;
        project = projectService.getPage(pageIndex, pageSize);
        PaginatedResponse<Project> paginatedResponse = new PaginatedResponse<>(
                project.getContent(), project.getTotalElements(), project.getTotalPages()
        );
        return Response.createResponse(HttpStatus.OK, "get project successfully", paginatedResponse);
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) throws NoSuchElementException{
        try {
            return Response.createResponse(HttpStatus.OK,
                    "get project by id successfully",
                    projectService.getById(id));
        } catch (NoSuchElementException e) {
            return Response.createResponse(HttpStatus.NOT_FOUND, e.getMessage(), null);
        }
    }

    @PostMapping("/project/insert")
    public ResponseEntity<?> insert(
            @RequestBody Project project
    ) throws NoSuchElementException {
        try {
            return Response.createResponse(
                    HttpStatus.OK,
                    "insert project successfully",
                    projectService.insert(project)
            );
        } catch (NoSuchElementException e) {
            return Response.createResponse(HttpStatus.NOT_FOUND, e.getMessage(), null);
        }
    }

    @DeleteMapping("/project/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) throws NoSuchElementException {
        try {
            projectService.deleteById(id);
            return Response.createResponse(HttpStatus.OK,
                    "deleted project have id: " + id.toString(),
                    null);

        } catch (NoSuchElementException e) {
            return Response.createResponse(HttpStatus.OK, e.getMessage(), null);
        }

    }


    // update both project and category information
    @PutMapping("/project/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable UUID id,
            @RequestBody Project newProject) {
        try {
            return Response.createResponse(HttpStatus.OK, "update project successfully", projectService.update(id, newProject));
        } catch (NoSuchElementException e) {
            return Response.createResponse(HttpStatus.NOT_FOUND, e.getMessage(), null);

        }
    }

}
