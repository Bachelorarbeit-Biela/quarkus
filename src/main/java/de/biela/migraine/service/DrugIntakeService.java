package de.biela.migraine.service;

import de.biela.migraine.model.dto.DrugIntakeDto;

import java.util.UUID;

public interface DrugIntakeService {
    DrugIntakeDto getDrugIntakeById (final UUID id);
    DrugIntakeDto updateDrugIntakeById(final UUID id, final DrugIntakeDto updatedDrugIntake);
    DrugIntakeDto createDrugIntakeById(final DrugIntakeDto createDrugIntake);
    String deleteDrugIntakeById(final UUID id);
}
