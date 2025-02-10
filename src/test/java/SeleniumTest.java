import org.testng.annotations.Test;

public class SeleniumTest extends BaseTest {
    @Test
    public void helloWorld(){
        HelloPage hp = new HelloPage();
        hp.helloWorld();
    }
}
