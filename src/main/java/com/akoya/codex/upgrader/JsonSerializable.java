package com.akoya.codex.upgrader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public interface JsonSerializable {

    String toJSON();

    default void saveToFile(File f) throws IOException {
        String js = toJSON();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
            bw.write(js);
            bw.flush();
        }
    }
}
