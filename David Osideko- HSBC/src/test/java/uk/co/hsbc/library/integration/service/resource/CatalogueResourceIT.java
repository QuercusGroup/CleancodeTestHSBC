package uk.co.hsbc.library.integration.service.resource;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.json.JSONException;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.hsbc.library.LibraryApi;
import uk.co.hsbc.library.handler.LibraryHandler;
import uk.co.hsbc.library.model.Library;

import java.io.IOException;
import java.util.Map;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
	LibraryApi.class,
	Library.class,
	LibraryHandler.class
}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext
@TestPropertySource("classpath:test.properties")
public class CatalogueResourceIT {

	@Autowired private LibraryHandler libraryHandler;

	@Value("${uk.co.hsbc.library.api.test.endpoint.uri}") String baseUri;
	@Value("${uk.co.hsbc.library.api.test.endpoint.port}") Integer port;
	@Value("${uk.co.hsbc.library.api.test.endpoint.path}") String basePath;

	private Library library;

	@Test
	public void test_WhenARequestForTheFullCatalogueIsMade_ThenReturnAListOfBooks() {
		given(getRequest()).
		when().
			get("/catalogue").
		then().
			log().ifValidationFails().
			statusCode(200);
	}

	@Test
	public void test_GivenAValidBookId_WhenRetrievingFromTheCatalogue_ThenReturnTheBookDetails() throws IOException, JSONException {
		String bookId = "5e3e4100-d4d5-43c6-b833-e151f2723423";
		JsonPath response = given(getRequest(null, ContentType.JSON, ContentType.JSON, null)).
			when().
				get("/catalogue/book/"+ bookId).
			then().
				log().ifValidationFails().
				statusCode(200).
				assertThat().body(IsNull.notNullValue()).
				extract().jsonPath();

		Assert.assertThat(response.getString("uuid"), IsEqual.equalTo(bookId));
	}

	@Test
	public void test_GivenAValidBookId_WhenTheBookIsDeleted_ThenReturnA404Response() throws IOException, JSONException {
		String bookId = "e0e4f23b-e841-43fc-a2f2-c42110d2d9e4";

		given(getRequest()).
		when().
			delete("/catalogue/book/"+ bookId).
		then().
			log().ifValidationFails().
			statusCode(200);

		given(getRequest(null, ContentType.JSON, ContentType.JSON, null)).
			when().
				get("/catalogue/book/"+ bookId).
			then().
				log().ifValidationFails().
				statusCode(404);
	}

	private RequestSpecification getRequest() {
		return getRequest(ContentType.JSON);
	}
	private RequestSpecification getRequest(ContentType contentType) {
		return getRequest(null, contentType, null, null);
	}
	private RequestSpecification getRequest(Map<String, String> headers, ContentType contentType, ContentType accept, String payload) {
		RequestSpecBuilder specBuilder = new RequestSpecBuilder()
				.setBaseUri(baseUri)
				.setPort(port)
				.setBasePath(basePath)
				.setContentType(contentType);
		if (headers!=null) headers.forEach((k,v) -> specBuilder.addHeader(k, v));
		if (accept!=null) specBuilder.setAccept(accept);
		if (payload!=null) specBuilder.setBody(payload);

		return specBuilder.build();
	}
}