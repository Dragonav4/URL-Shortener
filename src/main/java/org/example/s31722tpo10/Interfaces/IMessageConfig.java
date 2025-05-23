package org.example.s31722tpo10.Interfaces;

import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

public interface IMessageConfig {
    void addInterceptors(InterceptorRegistry interceptorRegistry);

}
