package de.biela.migraine.service.impl;

import de.biela.migraine.model.dto.DrugIntakeDto;
import de.biela.migraine.model.entity.DrugIntake;
import de.biela.migraine.repository.DrugIntakeRepository;
import de.biela.migraine.service.DrugIntakeService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class DrugIntakeServiceImpl implements DrugIntakeService {
    private final DrugIntakeRepository drugIntakeRepository;

    public DrugIntakeServiceImpl(DrugIntakeRepository drugIntakeRepository) {
        this.drugIntakeRepository = drugIntakeRepository;
    }

    @Override
    public DrugIntakeDto getDrugIntakeById(UUID id) {

        Optional<DrugIntake> drugIntake = drugIntakeRepository.findByIdOptional(id);
        return drugIntake.map(drug -> new DrugIntakeDto(drug.getId(),drug.getDrug(),drug.getAmountEntity(),drug.getAmount(),drug.getTakeTimestamp(),drug.getCreationTimestamp(),drug.getModificationTimestamp(),drug.getMigraine())).orElse(null);

    }
    @Override
    public DrugIntakeDto updateDrugIntakeById(UUID id, DrugIntakeDto updatedDrugIntake) {

        Optional<DrugIntake> existingDrugIntake = drugIntakeRepository.findByIdOptional(id);
        if (existingDrugIntake.isPresent()) {
            DrugIntake tempDrugIntake = existingDrugIntake.get();
            if (updatedDrugIntake.drug() != null) {
                tempDrugIntake.setDrug(updatedDrugIntake.drug());
                tempDrugIntake.setModificationTimestamp(LocalDateTime.now());
            }
            if (updatedDrugIntake.amountEntity() != null) {
                tempDrugIntake.setAmountEntity(updatedDrugIntake.amountEntity());
                tempDrugIntake.setModificationTimestamp(LocalDateTime.now());
            }
            if (updatedDrugIntake.amount() != null) {
                tempDrugIntake.setAmount(updatedDrugIntake.amount());
                tempDrugIntake.setModificationTimestamp(LocalDateTime.now());
            }
            if (updatedDrugIntake.takeTimestamp() != null) {
                tempDrugIntake.setTakeTimestamp(updatedDrugIntake.takeTimestamp());
                tempDrugIntake.setModificationTimestamp(LocalDateTime.now());
            }
            if (updatedDrugIntake.migraine() != null) {
                tempDrugIntake.setMigraine(updatedDrugIntake.migraine());
                tempDrugIntake.setModificationTimestamp(LocalDateTime.now());
            }
            drugIntakeRepository.persist(tempDrugIntake);
            return new DrugIntakeDto(tempDrugIntake.getId(),tempDrugIntake.getDrug(),tempDrugIntake.getAmountEntity(),tempDrugIntake.getAmount(),tempDrugIntake.getTakeTimestamp(),tempDrugIntake.getCreationTimestamp(),tempDrugIntake.getModificationTimestamp(),tempDrugIntake.getMigraine());
        }
        return updatedDrugIntake;
    }
    @Override
    @Transactional
    public DrugIntakeDto createDrugIntakeById(DrugIntakeDto createDrugIntake) {
        try {
            DrugIntake drugIntake = new DrugIntake(createDrugIntake.drug(),createDrugIntake.amountEntity(),createDrugIntake.amount(), createDrugIntake.takeTimestamp(),createDrugIntake.creationTimestamp(),createDrugIntake.modificationTimestamp(),createDrugIntake.migraine());
            drugIntakeRepository.persistAndFlush(drugIntake);
            return new DrugIntakeDto(drugIntake.getId(),drugIntake.getDrug(),drugIntake.getAmountEntity(),drugIntake.getAmount(),drugIntake.getTakeTimestamp(),drugIntake.getCreationTimestamp(),drugIntake.getModificationTimestamp(),drugIntake.getMigraine());
        }catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException("Ungültige Argumente für die Erstellung von DrugIntake.");
        }

    }

    @Override
    @Transactional
    public String deleteDrugIntakeById(UUID id) {
        drugIntakeRepository.deleteById(id);
        return "DrugIntake wurde gelöscht";
    }
}
