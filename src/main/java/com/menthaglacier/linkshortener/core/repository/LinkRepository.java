package com.menthaglacier.linkshortener.core.repository;

import com.menthaglacier.linkshortener.core.entity.LinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<LinkEntity, Long> {
    Optional<LinkEntity> findByShortCode(String shortCode);

    boolean existsByShortCode(String shortCode);
}
