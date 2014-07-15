import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.HTMLUNIT;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import play.libs.F.Callback;
import play.libs.Yaml;
import play.test.TestBrowser;

import com.avaje.ebean.Ebean;

import controllers.routes;

public class IntegrationTest {

    /**
     * add your integration test here
     * in this example we just check if the welcome page is being shown
     */
    @Test
    public void test() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333");
                assertThat(browser.pageSource()).contains("Welcome to playwell");
            }
        });
    }
    
    @Test
    public void logintest() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                Ebean.save((List) Yaml.load("test-data.yml"));
                browser.goTo("http://localhost:3333" + routes.Credential.login().url());
                
                assertThat(browser.pageSource()).contains("Sign in");
                browser.fill("input").with("apester@gmx.net", "test");
                browser.click("button");
                browser.await().atMost(3, TimeUnit.SECONDS).until("h1").with("TODOS");
                assertThat(browser.pageSource()).contains("CMP AS3");
            }
        });
    }

}
