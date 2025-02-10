import org.testng.annotations.Test;

public class SeleniumTest {
    @Test
    public void helloWorld(){
        HelloPage hp = new HelloPage();
        hp.helloWorld();
    }
}
