package com.fast.family.mvc.validate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * @author 张顺
 * @version 1.0
 */
@Component
@Slf4j
public class SimpleValidatorHandler<T extends Annotation,K> extends AbstractValidatorHandler<T,K> {
}
