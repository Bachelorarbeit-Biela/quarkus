package de.biela.migraine.service;

import de.biela.migraine.model.dto.MigraineDto;

import java.util.UUID;


public interface MigraineService {
    MigraineDto getMigraineById (final UUID id);
    MigraineDto updateMigraineById(final UUID id,final MigraineDto updatedMigraine);
    MigraineDto createMigraineById(final MigraineDto createMigraine);
    String deleteMigraineById(final UUID id);
}
