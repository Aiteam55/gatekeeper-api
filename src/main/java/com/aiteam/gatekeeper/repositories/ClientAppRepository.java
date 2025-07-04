package com.aiteam.gatekeeper.repositories;

import com.aiteam.gatekeeper.entities.ClientApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientAppRepository extends JpaRepository<ClientApp, Integer> {
    List<ClientApp> findAllByUserId(int userId);
}
