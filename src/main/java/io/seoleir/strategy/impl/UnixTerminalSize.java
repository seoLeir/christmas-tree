package io.seoleir.strategy.impl;

import io.seoleir.model.Terminal;
import io.seoleir.strategy.TerminalSize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

public class UnixTerminalSize implements TerminalSize {

    private static final Logger log = LoggerFactory.getLogger(UnixTerminalSize.class);

    @Override
    public Optional<Terminal> getTerminalSize() {
        try {
            Process processCols = new ProcessBuilder("bash", "-c", "tput cols")
                    .redirectInput(ProcessBuilder.Redirect.INHERIT)
                    .redirectOutput(ProcessBuilder.Redirect.PIPE)
                    .redirectError(ProcessBuilder.Redirect.PIPE)
                    .start();
            Process processRows = new ProcessBuilder("bash", "-c", "tput lines")
                    .redirectInput(ProcessBuilder.Redirect.INHERIT)
                    .redirectOutput(ProcessBuilder.Redirect.PIPE)
                    .redirectError(ProcessBuilder.Redirect.PIPE)
                    .start();

            try (BufferedReader readerWidth = new BufferedReader(new InputStreamReader(processCols.getInputStream()));
                 BufferedReader readerHeight = new BufferedReader(new InputStreamReader(processRows.getInputStream()))) {

                String width = readerWidth.readLine();
                String height = readerHeight.readLine();

                if (width != null && height != null) {
                    Terminal terminal = new Terminal(Integer.parseInt(width.trim()), Integer.parseInt(height.trim()));

                    return Optional.of(terminal);
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
