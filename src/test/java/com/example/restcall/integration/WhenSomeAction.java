package com.example.restcall.integration;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@JGivenStage
public class WhenSomeAction extends Stage<WhenSomeAction> {
    @ProvidedScenarioState
    ResultActions requestResult;
    @Autowired
    private MockMvc mockMvc;
    private MockHttpServletRequestBuilder requestBuilder;

    /**
     * Prepares a request
     *
     * @param httpMethod     give method
     * @param endpoint       value
     * @param pathParameters parameters
     * @return this
     */
    public WhenSomeAction a_$_request(HttpMethod httpMethod, String endpoint, Object... pathParameters) {
        requestBuilder = MockMvcRequestBuilders.request(httpMethod, endpoint, pathParameters);
        return self();
    }

    /**
     * Sets header parameters to request
     *
     * @param key   header key
     * @param value header value
     * @return this
     */
    public WhenSomeAction with_header_parameter(String key, String value) {
        requestBuilder.header(key, value);
        return self();
    }

    /**
     * Sets query parameter for request
     *
     * @param key   query parameter key
     * @param value query parameter value
     * @return this
     */
    public WhenSomeAction with_query_parameter(String key, String value) {
        requestBuilder.queryParam(key, value);
        return self();
    }

    /**
     * Request is sent
     *
     * @return this
     * @throws Exception in case something goes wrong sending request
     */
    public WhenSomeAction is_sent() throws Exception {
        requestResult = mockMvc.perform(requestBuilder);
        return self();
    }
}
