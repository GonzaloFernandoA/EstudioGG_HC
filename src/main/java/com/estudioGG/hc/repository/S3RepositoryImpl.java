/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudioGG.hc.repository;

import com.estudioGG.hc.config.S3Config;
import com.estudioGG.hc.model.Identifiable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import software.amazon.awssdk.core.sync.ResponseTransformer;

@Repository
public class S3RepositoryImpl<T extends Identifiable> {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(S3RepositoryImpl.class);

    @Autowired
    S3Config config;

    private final S3Client s3Client;
    private final ObjectMapper objectMapper;
    private final String bucketName;

    public S3RepositoryImpl(S3Client s3Client, ObjectMapper objectMapper) {
        this.s3Client = s3Client;
        this.objectMapper = objectMapper;
        this.bucketName = "";

    }

    public void save(String key, T entity) {

        String folder = entity.getClass().getSimpleName() + "/";
        String file = folder + entity.getId() + ".json"; 
        try {
            String json = objectMapper.writeValueAsString(entity);
            logger.info("S3Repository:" + json);
            logger.info("file  :" + file);
            InputStream inputStream = new ByteArrayInputStream(json.getBytes());
            PutObjectRequest putRequest = PutObjectRequest.builder()
                    .bucket(getBucketName())
                    .key(file)
                    .build();
            s3Client.putObject(putRequest, RequestBody.fromInputStream(inputStream, json.length() +1 ));
        } catch (JsonProcessingException ex) {
            logger.error(ex.getMessage());
        }
    }

    public T findByKey(String key, Class<T> clazz) {

        String folder = clazz.getSimpleName();
        logger.info("Buscando:" + folder + "/" + key + ".json");
        try {
            GetObjectRequest getRequest = GetObjectRequest.builder()
                    .bucket(getBucketName())
                    .key(folder + "/" + key + ".json")
                    .build();

            InputStream inputStream = s3Client.getObject(getRequest);

            return objectMapper.readValue(inputStream, clazz);
        } catch (NoSuchKeyException ex) {
            logger.info("Entidad no encontrada.");
            return null;
        } catch (Exception e) {
            logger.info("Error:." + e.getMessage());
            return null;
        }
    }

    public void delete(String key) {
        DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                .bucket(getBucketName())
                .key(key)
                .build();
        s3Client.deleteObject(deleteRequest);
    }

    
        public List<T> findAll(Class<T> clazz) {
        String prefix = clazz.getSimpleName() + "/" ;

        logger.info("BucketName: [" + getBucketName() + "]");
        logger.info("Quiteria: {" + prefix + "}");

        ListObjectsV2Request request = ListObjectsV2Request.builder()
                .bucket(getBucketName())
                .prefix(prefix)
                .build();

        // Obtener los objetos de S3
        ListObjectsV2Response response = s3Client.listObjectsV2(request);

        // Mapear cada objeto S3 al tipo T usando ObjectMapper
        return response.contents().stream()
                .map(s3Object -> {
                    try (InputStream inputStream = s3Client.getObject(GetObjectRequest.builder()
                            .bucket(getBucketName())
                            .key(s3Object.key())
                            .build())) {

                        return objectMapper.readValue(inputStream, clazz);
                    } catch (Exception e) {
                        throw new RuntimeException("Error al leer el objeto S3: " + s3Object.key(), e);
                    }
                })
                .collect(Collectors.toList());
    }
    
    public List<T> findAll(String dni, Class<T> clazz) {
        String prefix = clazz.getSimpleName() + "/" + dni;

        logger.info("BucketName: [" + getBucketName() + "]");
        logger.info("Quiteria: {" + prefix + "}");

        ListObjectsV2Request request = ListObjectsV2Request.builder()
                .bucket(getBucketName())
                .prefix(prefix)
                .build();

        // Obtener los objetos de S3
        ListObjectsV2Response response = s3Client.listObjectsV2(request);

        // Mapear cada objeto S3 al tipo T usando ObjectMapper
        return response.contents().stream()
                .map(s3Object -> {
                    try (InputStream inputStream = s3Client.getObject(GetObjectRequest.builder()
                            .bucket(getBucketName())
                            .key(s3Object.key())
                            .build())) {

                        return objectMapper.readValue(inputStream, clazz);
                    } catch (Exception e) {
                        throw new RuntimeException("Error al leer el objeto S3: " + s3Object.key(), e);
                    }
                })
                .collect(Collectors.toList());
    }

    /**
     * @return the bucketName
     */
    private String getBucketName() {
        return config.getBucket();
    }

    public List<T> listAndReadAllAsObjects(String prefix, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper(); // Jackson para convertir JSON a objetos

        ListObjectsRequest listRequest = ListObjectsRequest.builder()
                .bucket(getBucketName())
                .prefix(prefix)
                .build();

        List<S3Object> objects = s3Client.listObjects(listRequest).contents();
        List<T> resultList = new ArrayList<>();

        for (S3Object object : objects) {
            GetObjectRequest getRequest = GetObjectRequest.builder()
                    .bucket(getBucketName())
                    .key(object.key())
                    .build();

            // Obtiene el contenido del archivo como JSON
            String jsonContent = s3Client.getObject(getRequest, ResponseTransformer.toBytes()).asUtf8String();

            // Convierte el JSON al objeto de la clase especificada
            try {
                T objectData = objectMapper.readValue(jsonContent, clazz);
                resultList.add(objectData);
            } catch (Exception e) {
                throw new RuntimeException("Error deserializando el JSON del archivo " + object.key(), e);
            }
        }

        return resultList;
    }

}
