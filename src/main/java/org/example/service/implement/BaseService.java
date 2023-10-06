package org.example.service.implement;

import org.example.model.BaseModel;
import org.example.service.InterfaceBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public class BaseService<M extends BaseModel, R extends JpaRepository<M, UUID>> implements InterfaceBaseService<M,R> {

    @Autowired
    protected R repository;

    @Override
    public M create(M model) {
        model.setId(null);
        return repository.save(model);
    }

    @Override
    public M getById(UUID id) {
        Optional<M> model = repository.findById(id);
        if(model.isPresent()){
            return model.orElse(null);
        }
        throw new NoSuchElementException("item with id: " + id.toString() + " is not exists");
    }

    @Override
    public M update(UUID id, M newModel) {
        Optional<M> model = repository.findById(id);
        if(model.isPresent()){
            newModel.setId(id);
            return repository.save(newModel);
        } else {
            throw new NoSuchElementException("Can't find item with id: " + id.toString());
        }
    }

    @Override
    public void deleteById(UUID id) throws NoSuchElementException {
        Optional<M> model = repository.findById(id);
        if (model.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new NoSuchElementException("Can't found productId: " + id + " to delete");
        }
    }

    @Override
    public List<M> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<M> getPage(int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return repository.findAll(pageable);
    }
}
