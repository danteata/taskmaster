package com.abc.taskmaster.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class S3ServiceTest {

    @Mock
    private S3Client s3Client;

    private S3Service s3Service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        s3Service = new S3Service(s3Client);
    }

    @AfterEach
    void tearDown() {
        // Clean up any resources used by the test
    }

    @Test
    void shouldPutObject() {
        String bucketName = "test-bucket";
        String key = "test-key";
        byte[] bytes = "test-attachment".getBytes();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(bytes));

//        when(s3Client.putObject(putObjectRequest, RequestBody.fromBytes(bytes))).thenReturn(response);

        s3Service.putObject(bucketName, key, bytes);

        // Assert that the putObjectRequest was called with the correct parameters
        // and that the response was as expected
    }

    @Test
    void shouldGetObject() throws IOException {
        // Given
        String bucket = "customer";
        String key = "foo";
        byte[] data = "Hello World".getBytes();

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();

        ResponseInputStream<GetObjectResponse> res = mock(ResponseInputStream.class);
        when(res.readAllBytes()).thenReturn(data);

        when(s3Client.getObject(eq(getObjectRequest))).thenReturn(res);

        // When
        byte[] bytes = s3Service.getObject(bucket, key);

        // Then
        assertThat(bytes).isEqualTo(data);
    }

    @Test
    void willThrowWhenGetObject() throws IOException {
        // Given
        String bucket = "customer";
        String key = "foo";
        byte[] data = "Hello World".getBytes();

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();

        ResponseInputStream<GetObjectResponse> res = mock(ResponseInputStream.class);
        when(res.readAllBytes()).thenThrow(new IOException("Cannot read bytes"));

        when(s3Client.getObject(eq(getObjectRequest))).thenReturn(res);

        // When
        // Then
        assertThatThrownBy(() -> s3Service.getObject(bucket, key))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Cannot read bytes")
                .hasRootCauseInstanceOf(IOException.class);

    }
}
