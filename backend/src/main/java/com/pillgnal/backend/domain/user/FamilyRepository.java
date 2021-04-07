package com.pillgnal.backend.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long> {
    List<Family> findAllByUidFrom(User uidFrom);
    Optional<Family> findByUidFromAndUidTo(User uidFrom, User uidTo);
}
