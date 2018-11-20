package com.fast.family.retrofit;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;
import retrofit2.Retrofit;

/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
@Setter
public class RetrofitServiceFactoryBean<T> implements FactoryBean<T> {

    private Class<T> serviceInterfacte;

    private Retrofit retrofitBean;

    @Override
    public T getObject() throws Exception {
        return retrofitBean.create(serviceInterfacte);
    }

    @Override
    public Class<?> getObjectType() {
        return serviceInterfacte;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
