package utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileUtils {

    public static String readFileAsString(String fileName) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(fileName);
        return new String(Files.readAllBytes(classPathResource.getFile().toPath()));
    }
}
