package de.biela.migraine.repository;

import de.biela.migraine.model.entity.Migraine;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class MigraineRepository implements PanacheRepositoryBase<Migraine,UUID> {
}
