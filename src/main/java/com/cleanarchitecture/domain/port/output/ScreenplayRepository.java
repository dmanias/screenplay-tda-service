package com.cleanarchitecture.domain.port.output;

import com.cleanarchitecture.domain.entity.Screenplay;
import java.util.List;
import java.util.Optional;

public interface ScreenplayRepository {
    Screenplay save(Screenplay screenplay);
    Optional<Screenplay> findById(String id);
    List<Screenplay> findAll();
    void deleteById(String id);
    boolean existsById(String id);
    List<Screenplay> findByTitleContaining(String title);  // Changed from findByTitle
}

