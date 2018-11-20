package com.fast.family.retrofit;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
@Data
public class RetrofitServiceScanner extends ClassPathBeanDefinitionScanner {


    public RetrofitServiceScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public RetrofitServiceScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
        super(registry, useDefaultFilters);
    }

    public RetrofitServiceScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters, Environment environment) {
        super(registry, useDefaultFilters, environment);
    }

    public RetrofitServiceScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters, Environment environment, ResourceLoader resourceLoader) {
        super(registry, useDefaultFilters, environment, resourceLoader);
    }


    private RetrofitServiceFactoryBean retrofitServiceFactoryBean = new RetrofitServiceFactoryBean();

    private Class<? extends Annotation> annotationClass;

    private Class<?> markerInterfact;

    private String retrofitBeanName;

    /**
     * 重写扫描包
     * 定义初始化bean逻辑
     * @param basePackages
     * @return
     */
    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);

        return beanDefinitionHolders;
    }


    private void processBeanDefinitions(AnnotatedBeanDefinition beanDefinition){
        ScannedGenericBeanDefinition definition;

        for (BeanDefinitionHolder holder : beanDefinition){
            definition = (ScannedGenericBeanDefinition) holder.getBeanDefinition();
            AnnotationAttributes annotationAttributes =
                    (AnnotationAttributes) definition.getMetadata()
                            .getAnnotationAttributes(RetrofitAnnotation.class.getName(),false);
            definition.getPropertyValues().add("serviceInterface",definition.getBeanClassName());
            definition.setBeanClass(this.retrofitServiceFactoryBean.getClass());

            String retrofitName = annotationAttributes.getString("retriofitBean");
            if (retrofitName == null || retrofitName.isEmpty()){
                definition.getPropertyValues().add("retrofitBean",new RuntimeBeanReference(retrofitBeanName));
            } else {
                definition.getPropertyValues().add("retrofitBean",new RuntimeBeanReference(retrofitName));
            }
        }
    }

    public void registerFilters(){

    }



}
