package com.example.hsdemo.repositories;

import com.example.hsdemo.entities.ClubEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<ClubEntity, Long> {
    ClubEntity findClubEntityByName(final String name);
}
