package de.biela.migraine.controller;

import de.biela.migraine.model.dto.DrugIntakeDto;
import de.biela.migraine.service.DrugIntakeService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.UUID;

@RequestScoped
@Path("/drugintake")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DrugIntakeController {
    private final DrugIntakeService drugIntakeService;
    @Inject
    public DrugIntakeController(DrugIntakeService drugIntakeService){this.drugIntakeService=drugIntakeService;}
    @GET
    @Path("/{id}")
    public DrugIntakeDto getMigraine(@PathParam("id")String id){
        return drugIntakeService.getDrugIntakeById(UUID.fromString(id));
    }
    @PUT
    public DrugIntakeDto putMigraine(@Valid DrugIntakeDto drugIntakeDto){
        return drugIntakeService.createDrugIntakeById(drugIntakeDto);
    }
    @PATCH
    @Path("/{id}")
    public DrugIntakeDto patchMigraine(@PathParam("id")String id, @Valid DrugIntakeDto migraineDto){
        return drugIntakeService.updateDrugIntakeById(UUID.fromString(id),migraineDto);
    }
    @DELETE
    @Path("/{id}")
    public String deleteMigraine(@PathParam("id")String id){
        return drugIntakeService.deleteDrugIntakeById(UUID.fromString("id"));
    }

}
