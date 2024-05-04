package com.raphaelsrcunha.fipi.service;

import java.util.List;

public interface IDataConverter {
    <T> T getData(String json, Class<T> classToConvert);

    <T> List<T> getList(String json, Class<T> classToConvert);
}
