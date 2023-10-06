package org.example.service;

import org.example.model.BaseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InterfaceBaseService<M extends BaseModel, R extends JpaRepository<M, UUID>> {
    public M create(M model);
    public M getById(UUID id);
    public M update(UUID id, M model);
    public void deleteById(UUID id);
    public List<M> getAll();
    public Page<M> getPage(int pageIndex, int pageSize);
}
