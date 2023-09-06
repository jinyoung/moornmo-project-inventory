package moornmo.project.common;

import io.cucumber.spring.CucumberContextConfiguration;
import moornmo.project.InventoryApplication;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { InventoryApplication.class })
public class CucumberSpingConfiguration {}
