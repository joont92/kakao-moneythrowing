package com.kakao.moneythrowing.rest;

import com.kakao.moneythrowing.rest.model.AmountApiModel;
import com.kakao.moneythrowing.rest.model.CreateThrowingRequestApiModel;
import com.kakao.moneythrowing.rest.model.ThrowingApiModel;
import com.kakao.moneythrowing.rest.model.TokenApiModel;
import java.util.UUID;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A delegate to be called by the {@link ThrowingApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */

public interface ThrowingApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /throwing : 뿌리기 생성
     *
     * @param X_USER_ID  (required)
     * @param X_ROOM_ID  (required)
     * @param createThrowingRequestApiModel  (required)
     * @return OK (status code 201)
     * @see ThrowingApi#createThrowing
     */
    default ResponseEntity<TokenApiModel> createThrowing(UUID X_USER_ID,
        UUID X_ROOM_ID,
        CreateThrowingRequestApiModel createThrowingRequestApiModel) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"token\" : \"token\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /throwing/{token} : 뿌리기 조회
     *
     * @param X_USER_ID  (required)
     * @param X_ROOM_ID  (required)
     * @param token  (required)
     * @return OK (status code 200)
     * @see ThrowingApi#getThrowing
     */
    default ResponseEntity<ThrowingApiModel> getThrowing(UUID X_USER_ID,
        UUID X_ROOM_ID,
        String token) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"receivedUser\" : [ { \"amount\" : 5, \"acquirer\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"amount\" : 5, \"acquirer\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"time\" : { \"start\" : \"2000-01-23T04:56:07.000+00:00\", \"end\" : \"2000-01-23T04:56:07.000+00:00\" }, \"amountStatus\" : { \"total\" : 0, \"remain\" : 1, \"completed\" : 6 } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /throwing/{token}/receive : 뿌린 금액 받기
     *
     * @param X_USER_ID  (required)
     * @param X_ROOM_ID  (required)
     * @param token  (required)
     * @return OK (status code 200)
     * @see ThrowingApi#receiveThrowing
     */
    default ResponseEntity<AmountApiModel> receiveThrowing(UUID X_USER_ID,
        UUID X_ROOM_ID,
        String token) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"amount\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
