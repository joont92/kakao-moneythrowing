package com.kakao.moneythrowing.rest;

import com.kakao.moneythrowing.rest.model.CreateThrowingRequest;
import com.kakao.moneythrowing.rest.model.ThrowingToken;
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
     * @param createThrowingRequest  (required)
     * @return OK (status code 201)
     * @see ThrowingApi#createThrowing
     */
    default ResponseEntity<ThrowingToken> createThrowing(UUID X_USER_ID,
        UUID X_ROOM_ID,
        CreateThrowingRequest createThrowingRequest) {
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
     * PUT /throwing/{token}/receive : 뿌린 금액 받기
     *
     * @param X_USER_ID  (required)
     * @param X_ROOM_ID  (required)
     * @param token  (required)
     * @return OK (status code 200)
     * @see ThrowingApi#receiveThrowing
     */
    default ResponseEntity<ThrowingToken> receiveThrowing(UUID X_USER_ID,
        UUID X_ROOM_ID,
        String token) {
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

}
