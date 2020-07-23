package helloworld;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AppTest {

  private App app;

  @Before
  public void setup() {
    app = new App();
  }

  @Test
  public void shouldPrint() {
    APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent()
            .withBody("{}");
    app.handleRequest(request, null);
  }

  @Test
  public void successfulResponse() {
    APIGatewayProxyResponseEvent result = app.handleRequest(null, null);
    assertEquals(result.getStatusCode().intValue(), 200);
    assertEquals(result.getHeaders().get("Content-Type"), "application/json");
    String content = result.getBody();
    assertNotNull(content);
    assertTrue(content.contains("\"message\""));
    assertTrue(content.contains("\"hello world\""));
    assertTrue(content.contains("\"location\""));
  }
}
