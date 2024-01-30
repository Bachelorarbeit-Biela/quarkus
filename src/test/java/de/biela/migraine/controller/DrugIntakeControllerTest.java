package de.biela.migraine.controller;

import de.biela.migraine.model.dto.DrugIntakeDto;
import de.biela.migraine.model.entity.DrugIntake;
import de.biela.migraine.model.entity.Migraine;
import de.biela.migraine.service.DrugIntakeService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class DrugIntakeControllerTest {
    private static DrugIntakeDto drugIntakeDto;
    private static UUID uuid;
    @Inject
    private DrugIntakeService drugIntakeService;
    private void assertDrugIntakeProperties(DrugIntakeDto expected, DrugIntakeDto actual) {
        assertEquals(expected.drug(), actual.drug());
        assertEquals(expected.amountEntity(), actual.amountEntity());
        assertEquals(expected.amount(), actual.amount());
        assertEquals(expected.takeTimestamp(), actual.takeTimestamp());
        assertEquals(expected.creationTimestamp(), actual.creationTimestamp());
    }

    @BeforeEach
    public void setUpEach(){
        baseURI = "http://localhost:8081";
        basePath = "/drugintake";
    }
    @BeforeAll
    public static void setUpAll(){
        uuid = UUID.randomUUID();
        Migraine migraine = new Migraine( LocalDate.now(),"test", Migraine.PainSeverity.WEAK, LocalDateTime.now().withNano(0),LocalDateTime.now().withNano(0));
        migraine.setId(UUID.fromString("9143ea43-faf3-4dbc-affd-4b53fbd9d0fb"));
        drugIntakeDto = new DrugIntakeDto(uuid, DrugIntake.Drug.PARACETAMOL, DrugIntake.AmountEntity.PIECE, BigDecimal.ONE, LocalDateTime.now().withNano(0),LocalDateTime.now().withNano(0),LocalDateTime.now().withNano(0),migraine);
    }
    @Order(1)
    @Test
    public void TestCreateDrugIntake(){
        given().log().all()
                .contentType(ContentType.JSON)
                .body(drugIntakeDto)
                .when()
                .get("702bc3de-d2a3-4e56-8238-bb8ec203b316")
                .then()
                .assertThat()
                .statusCode(200);
    }
    @Test
    @Order(2)
    public void TestPutDrugIntake(){
        System.out.println("Base URI: " + RestAssured.baseURI);
        System.out.println("Base Path: " + RestAssured.basePath);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(drugIntakeDto)
                .when()
                .put()
                .then()
                .assertThat()
                .statusCode(200);
    }
    @Test
    @Order(3)
    public void TestCreateAndGetDrugIntake() {
        //GIVEN
        DrugIntakeDto savedDrugIntake = drugIntakeService.createDrugIntakeById(drugIntakeDto);
        uuid=savedDrugIntake.id();
        //WHEN
        DrugIntakeDto getDrugIntakeById = drugIntakeService.getDrugIntakeById(uuid);

        //THEN
        assertAll("migraine",
                () -> assertDrugIntakeProperties(drugIntakeDto,getDrugIntakeById)
        );
        assertEquals(drugIntakeDto.modificationTimestamp(), getDrugIntakeById.modificationTimestamp());
    }
}
