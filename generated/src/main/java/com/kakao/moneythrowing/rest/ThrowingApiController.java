package com.kakao.moneythrowing.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;

@Controller
@RequestMapping("${openapi.kakaoMoneythrowingAPIDocumentation.base-path:}")
public class ThrowingApiController implements ThrowingApi {

    private final ThrowingApiDelegate delegate;

    public ThrowingApiController(@org.springframework.beans.factory.annotation.Autowired(required = false) ThrowingApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new ThrowingApiDelegate() {});
    }

    @Override
    public ThrowingApiDelegate getDelegate() {
        return delegate;
    }

}
