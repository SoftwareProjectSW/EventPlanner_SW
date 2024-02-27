package org.example.AcceptanveTest;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(features ="cases",monochrome = true,snippets = SnippetType.CAMELCASE,
        glue ={"org.example.AcceptanveTest"})

public class acceptanceTest {

}
