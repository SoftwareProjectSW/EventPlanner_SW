package org.example.AcceptanceTest;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;


@RunWith(Cucumber.class)
@CucumberOptions(features ="cases",
        monochrome = true,
        snippets = SnippetType.CAMELCASE,
        glue ={"org.example.AcceptanceTest"})

public class AcceptanceTest {


}
