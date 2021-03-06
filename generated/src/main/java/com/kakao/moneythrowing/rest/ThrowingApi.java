/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.3.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.kakao.moneythrowing.rest;

import com.kakao.moneythrowing.rest.model.AmountApiModel;
import com.kakao.moneythrowing.rest.model.CreateThrowingRequestApiModel;
import com.kakao.moneythrowing.rest.model.ThrowingApiModel;
import com.kakao.moneythrowing.rest.model.TokenApiModel;
import java.util.UUID;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;

@Validated
@Api(value = "Throwing", description = "the Throwing API")
public interface ThrowingApi {

    default ThrowingApiDelegate getDelegate() {
        return new ThrowingApiDelegate() {};
    }

    /**
     * POST /throwing : 뿌리기 생성
     *
     * @param X_USER_ID  (required)
     * @param X_ROOM_ID  (required)
     * @param createThrowingRequestApiModel  (required)
     * @return OK (status code 201)
     */
    @ApiOperation(value = "뿌리기 생성", nickname = "createThrowing", notes = "", response = TokenApiModel.class, tags={ "Throwing", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "OK", response = TokenApiModel.class) })
    @RequestMapping(value = "/throwing",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<TokenApiModel> createThrowing(@ApiParam(value = "" ,required=true) @RequestHeader(value="X-USER-ID", required=true) UUID X_USER_ID,@ApiParam(value = "" ,required=true) @RequestHeader(value="X-ROOM-ID", required=true) UUID X_ROOM_ID,@ApiParam(value = "" ,required=true )  @Valid @RequestBody CreateThrowingRequestApiModel createThrowingRequestApiModel) {
        return getDelegate().createThrowing(X_USER_ID, X_ROOM_ID, createThrowingRequestApiModel);
    }


    /**
     * GET /throwing/{token} : 뿌리기 조회
     *
     * @param X_USER_ID  (required)
     * @param X_ROOM_ID  (required)
     * @param token  (required)
     * @return OK (status code 200)
     */
    @ApiOperation(value = "뿌리기 조회", nickname = "getThrowing", notes = "", response = ThrowingApiModel.class, tags={ "Throwing", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = ThrowingApiModel.class) })
    @RequestMapping(value = "/throwing/{token}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<ThrowingApiModel> getThrowing(@ApiParam(value = "" ,required=true) @RequestHeader(value="X-USER-ID", required=true) UUID X_USER_ID,@ApiParam(value = "" ,required=true) @RequestHeader(value="X-ROOM-ID", required=true) UUID X_ROOM_ID,@ApiParam(value = "",required=true) @PathVariable("token") String token) {
        return getDelegate().getThrowing(X_USER_ID, X_ROOM_ID, token);
    }


    /**
     * PUT /throwing/{token}/threads : 뿌린 금액 받기
     *
     * @param X_USER_ID  (required)
     * @param X_ROOM_ID  (required)
     * @param token  (required)
     * @return OK (status code 200)
     */
    @ApiOperation(value = "뿌린 금액 받기", nickname = "receiveThrowing", notes = "", response = AmountApiModel.class, tags={ "Throwing", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = AmountApiModel.class) })
    @RequestMapping(value = "/throwing/{token}/threads",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    default ResponseEntity<AmountApiModel> receiveThrowing(@ApiParam(value = "" ,required=true) @RequestHeader(value="X-USER-ID", required=true) UUID X_USER_ID,@ApiParam(value = "" ,required=true) @RequestHeader(value="X-ROOM-ID", required=true) UUID X_ROOM_ID,@ApiParam(value = "",required=true) @PathVariable("token") String token) {
        return getDelegate().receiveThrowing(X_USER_ID, X_ROOM_ID, token);
    }

}
