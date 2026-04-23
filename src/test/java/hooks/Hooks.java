package hooks;

import io.cucumber.java.*;
import utils.*;

public class Hooks {

    @BeforeAll
    public static void generateFeature() {
        FeatureFileGenerator.generateFeatureFile();
    }

    @Before
    public void setup() {
        DriverFactory.initDriver();
    }

    @After
    public void teardown() {
        DriverFactory.quitDriver();
    }
}