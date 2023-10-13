package org.example.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.model.BaseModel;

import java.util.List;

@Data
@AllArgsConstructor
public class PaginatedResponse<P extends BaseModel> {
    private List<P> elements;
    private long totalElements;
    private int totalPages;
}

