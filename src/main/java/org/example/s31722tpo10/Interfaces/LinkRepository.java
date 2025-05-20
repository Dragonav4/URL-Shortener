package org.example.s31722tpo10.Interfaces;

import org.example.s31722tpo10.DataLayer.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface LinkRepository extends JpaRepository<Link, Repository> {
    Optional<Link> findById(String id);
}
