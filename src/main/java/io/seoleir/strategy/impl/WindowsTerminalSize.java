package io.seoleir.strategy.impl;

import io.seoleir.model.Terminal;
import io.seoleir.strategy.TerminalSize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

public class WindowsTerminalSize implements TerminalSize {

    private static final Logger log = LoggerFactory.getLogger(WindowsTerminalSize.class);

    @Override
    public Optional<Terminal> getTerminalSize() {
        try {
            Process process = new ProcessBuilder("cmd", "/c", "mode con").start();


            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));) {
                String line;
                int width = -1, height = -1;

                while ((line = reader.readLine()) != null) {
                    if (line.contains("Columns")) {
                        width = Integer.parseInt(line.replaceAll("[^0-9]", ""));
                    } else if (line.contains("Lines")) {
                        height = Integer.parseInt(line.replaceAll("[^0-9]", ""));
                    }
                }

                if (width != -1 && height != -1) {
                    return Optional.of(new Terminal(width, height));
                }
            } catch (Exception ex) {
                log.error(ex.getMessage());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return Optional.empty();
    }
}
