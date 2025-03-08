package com.dtw.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AmazonS3Service {

    private final S3Client s3Client;
    private final String bucketName;

    /**
     * Constructor with Spring dependency injection for AWS credentials
     *
     * @param accessKey AWS access key
     * @param secretKey AWS secret key
     * @param region AWS region
     * @param bucketName S3 bucket name
     */

    public AmazonS3Service(
            @Value("${aws.accessKey}") String accessKey,
            @Value("${aws.secretKey}") String secretKey,
            @Value("${aws.region}") String region,
            @Value("${aws.s3.bucketName}") String bucketName) {

        AwsCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(
                AwsBasicCredentials.create(accessKey, secretKey));

        this.s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(credentialsProvider)
                .build();

        this.bucketName = bucketName;
    }

    /**
     * Upload a file to S3
     *
     * @param key The key (filename/path) in S3
     * @param file Local path to the file to upload
     * @return string
     */

    public String uploadFile(String key, MultipartFile file) {
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            s3Client.putObject(putObjectRequest,
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

            // Generate the URL to the uploaded file
            String fileUrl = String.format("https://%s.s3.amazonaws.com/%s", bucketName, key);

            System.out.println("Successfully uploaded file: " + file.getOriginalFilename() + " to S3 bucket: " + bucketName + " with key: " + key);
            return fileUrl;
        } catch (Exception e) {
            System.err.println("Error uploading file to S3: " + e.getMessage());
            return null; // Return null on failure
        }
    }


    /**
     * Download a file from S3
     *
     * @param key The key (filename/path) in S3
     * @param destinationPath Local path where the file should be saved
     * @return true if successful, false otherwise
     */

    public boolean downloadFile(String key, String destinationPath) {
        try {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            ResponseInputStream<GetObjectResponse> s3Object = s3Client.getObject(getObjectRequest);

            // Create parent directories if they don't exist
            Path destination = Paths.get(destinationPath);
            Files.createDirectories(destination.getParent());

            // Save the file locally
            try (InputStream is = s3Object;
                 OutputStream os = new FileOutputStream(destinationPath)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
            }

            System.out.println("Successfully downloaded file from S3 bucket: " + bucketName + " with key: " + key);
            return true;
        } catch (S3Exception | IOException e) {
            System.err.println("Error downloading file from S3: " + e.getMessage());
            return false;
        }
    }

    /**
     * Delete a file from S3
     *
     * @param key The key (filename/path) in S3 to delete
     * @return true if successful, false otherwise
     */
    public boolean deleteFile(String key) {
        try {
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            s3Client.deleteObject(deleteObjectRequest);
            System.out.println("Successfully deleted file from S3 bucket: " + bucketName + " with key: " + key);
            return true;
        } catch (S3Exception e) {
            System.err.println("Error deleting file from S3: " + e.awsErrorDetails().errorMessage());
            return false;
        }
    }

    /**
     * Check if a file exists in S3
     *
     * @param key The key (filename/path) in S3
     * @return true if file exists, false otherwise
     */
    public boolean fileExists(String key) {
        try {
            // We use headObject which only retrieves metadata, not the file content
            s3Client.headObject(req -> req.bucket(bucketName).key(key));
            return true;
        } catch (S3Exception e) {
            // NoSuchKey error code means the file doesn't exist
            if (e.awsErrorDetails().errorCode().equals("NoSuchKey") ||
                    e.awsErrorDetails().errorCode().equals("404")) {
                return false;
            }
            // For other errors, log them but assume file doesn't exist
            System.err.println("Error checking if file exists: " + e.awsErrorDetails().errorMessage());
            return false;
        }
    }

    /**
     * Close the S3 client connection
     */
    public void close() {
        if (s3Client != null) {
            s3Client.close();
            System.out.println("S3 client connection closed");
        }
    }
}