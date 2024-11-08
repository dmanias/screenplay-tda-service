package com.cleanarchitecture.domain.port.output;

import com.cleanarchitecture.domain.entity.Screenplay;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScreenplayRepository extends BaseRepository{
    Screenplay save(Screenplay screenplay);
    Optional<Screenplay> findById(String id);
    List<Screenplay> findAll();
    void deleteById(String id);
    List<Screenplay> findByTitle(String title);
    List<Screenplay> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    List<Screenplay> findByPremiseContaining(String keyword);

}
