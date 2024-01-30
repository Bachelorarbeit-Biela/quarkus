package de.biela.migraine.controller;

import de.biela.migraine.model.dto.MigraineDto;
import de.biela.migraine.service.MigraineService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@RequestScoped
@Path("/migraine")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MigraineController {
    private final MigraineService migraineService;
    @Inject
    public MigraineController(MigraineService migraineService){
        this.migraineService=migraineService;
    }
    @GET
    @Path("/{id}")
    public Response getMigraine(@PathParam("id")final String id){
        return Response.ok(migraineService.getMigraineById(UUID.fromString(id))).build();
    }
    @PUT
    public MigraineDto putMigraine(@Valid MigraineDto migraineDto){
        return migraineService.createMigraineById(migraineDto);
    }
    @PATCH
    @Path("/{id}")
    public MigraineDto patchMigraine(@PathParam("id")String id, @Valid MigraineDto migraineDto){
        return migraineService.updateMigraineById(UUID.fromString(id),migraineDto);
    }
    @DELETE
    @Path("/{id}")
    public String deleteMigraine(@PathParam("id")String id){
        return migraineService.deleteMigraineById(UUID.fromString(id));
    }

}
