package de.biela.migraine.controller;


import de.biela.migraine.model.dto.MigraineDto;
import de.biela.migraine.model.entity.Migraine;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;


@QuarkusTest
public class MigraineControllerTest {
    private static UUID uuid;
    private static MigraineDto migraineDto;
    @BeforeEach
    public void setUpEach(){
        baseURI = "http://localhost:8081";
        basePath = "/migraine";
    }

    @BeforeAll
    public static void setUpAll(){
        uuid=UUID.randomUUID();

        migraineDto = new MigraineDto(uuid, LocalDate.now(),"test", Migraine.PainSeverity.WEAK, LocalDateTime.now().withNano(0),LocalDateTime.now().withNano(0),null);
    }
    @Test
    @Order(1)
    public void TestGetMigraine(){
        given()
                .when()
                .get("9143ea43-faf3-4dbc-affd-4b53fbd9d0fb")
                .then()
                .assertThat()
                .statusCode(200);
    }
    @Test
    @Order(2)
    @Transactional
    public void TestPutMigraine(){
        System.out.println("Base URI: " + RestAssured.baseURI);
        System.out.println("Base Path: " + RestAssured.basePath);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(migraineDto)
                .when()
                .put()
                .then()
                .assertThat()
                .statusCode(200);
    }

}
