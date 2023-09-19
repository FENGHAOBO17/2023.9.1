package com.cleansoft.demo;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class ImgTest {
  public static void main(String[] args) throws Exception {
    byte[] imageBytes = Files.readAllBytes(Paths.get("D:\\picture\\富士山.jpg"));
    String base64Encoded = Base64.getEncoder().encodeToString(imageBytes);
    System.out.println(base64Encoded);
  }
}