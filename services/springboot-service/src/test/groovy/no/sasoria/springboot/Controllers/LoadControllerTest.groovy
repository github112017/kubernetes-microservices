package no.sasoria.springboot.Controllers

import no.sasoria.springboot.Service.LoadService
import org.springframework.test.context.web.WebAppConfiguration

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.test.web.servlet.ResultActions
import org.springframework.web.client.RestTemplate


@WebAppConfiguration
@SpringBootTest(classes = [LoadService, RestTemplate], webEnvironment = NONE)
class LoadControllerTest extends AbstractControllerTest {

    @Autowired
    LoadService loadService

    @Autowired
    RestTemplate restTemplate

    def controller

    Boolean shouldSucceed

    // PUT
    def "Test for documenting the player PUT endpoint"() {
        given:
            def url = '/api/player?name=John'

        when:
            shouldSucceed = true
            ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.put(url))

        then:
            result.andExpect(status().isBadRequest())
                .andDo(
                    document("player-put",
                            preprocessResponse(prettyPrint()),
                            responseFields(
                                    fieldWithPath("errorMessage").type(JsonFieldType.STRING).
                                            description("Player already loaded"),
                            )))

        when:
            shouldSucceed = false
            result = mockMvc.perform(RestDocumentationRequestBuilders.put(url))

        then:
            result.andExpect(status().is2xxSuccessful())
            .andDo(
                document("player-put",
                    preprocessResponse(prettyPrint()),
                    responseFields(
                            fieldWithPath("result").type().
                                    description("Player loaded"),
                    )))

}

    // GET
    def "Test for documenting the player GET endpoint"() {

        given:
            def url = '/api/player?name=John'

        when:
            shouldSucceed = false
            ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.get(url))

        then:
            result
                .andExpect(status().isBadRequest())
                .andDo(
                    document('player-get',
                        preprocessResponse(prettyPrint()),
                        responseFields(
                            fieldWithPath("errorMessage").type(JsonFieldType.STRING).
                                description("Player does not exist"),
                )))

        when:
            // PUT
            shouldSucceed = false
            ResultActions putResult = mockMvc.perform(RestDocumentationRequestBuilders.put(url))
            // GET
            shouldSucceed = true
            ResultActions getResult = mockMvc.perform(RestDocumentationRequestBuilders.get(url))

        then:
            putResult
                .andExpect(status().is2xxSuccessful())

            getResult
                .andExpect(status().is2xxSuccessful())
                .andDo(
                    document('player-get',
                        preprocessResponse(prettyPrint()),
                        responseFields(
                            fieldWithPath("game").type(JsonFieldType.STRING).
                                description("bf4"),

                                fieldWithPath("name").type(JsonFieldType.STRING).
                                description("John"),

                                fieldWithPath("rank").type(JsonFieldType.NUMBER).
                                        description("0"),

                                fieldWithPath("country").type(JsonFieldType.STRING).
                                        description("null"),


                        )))
    }

    @Override
    protected List<Object> getControllersUnderTest() {

        controller = new LoadController(restTemplate, loadService) {
            @Override
            protected boolean hasPlayer(String name) {
                return shouldSucceed
            }
        }
        return [controller]
    }
}