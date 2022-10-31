package com.example.restcall.integration;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@JGivenStage
public class ThenSomeOutcome extends Stage<ThenSomeOutcome> {
    @ExpectedScenarioState
    private ResultActions requestResult;

    public ThenSomeOutcome status_ok() throws Exception {
        requestResult.andExpect(status().isOk());
        return self();
    }
}
