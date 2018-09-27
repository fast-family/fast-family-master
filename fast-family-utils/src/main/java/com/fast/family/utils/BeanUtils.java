package com.fast.family.utils;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class BeanUtils {

    private static MapperFacade mapper;


    static{
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapper = mapperFactory.getMapperFacade();
    }


    public static <S,D> D map(S source,Class<D> destinationClass){
        return mapper.map(source,destinationClass);
    }

    public static <S,D> D map(S souce, Type<S> sourceType,Type<D> destinationType){
        return mapper.map(souce,sourceType,destinationType);
    }


    public static <S,D> List<D> mapAsList(Iterable<S> sourceList,Class<S> sourceClass,
                                          Class<D> destinationClass){
        return mapper.mapAsList(sourceList, TypeFactory.valueOf(sourceClass),
                TypeFactory.valueOf(destinationClass));
    }

    public static <S,D> List<D> mapAsList(Iterable<S> sourceList,Type<S> sourceType,
                                          Type<D> destinationType){
        return mapper.mapAsList(sourceList,sourceType,destinationType);
    }

    public static <S,D> D[] mapAsArray(final D[] destination,final S[] source,
                                       final Class<D> destinationClass){
        return mapper.mapAsArray(destination,source,destinationClass);
    }

    public static <S,D> D[] mapAsArray(D[] destination,S[] source,Type<S> sourceType,
                                       Type<D> destinationType){
        return mapper.mapAsArray(destination,source,sourceType,destinationType);
    }

    public static <E> Type<E> getType(final Class<E> rawType){
        return TypeFactory.valueOf(rawType);
    }



    public static void copy(Object source,Object target){
         org.springframework.beans.BeanUtils.copyProperties(source,target);
    }


    public static void copyPropertiesIgnoreNull(Object src, Object target) {
        org.springframework.beans.BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
