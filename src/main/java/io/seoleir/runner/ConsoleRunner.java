package io.seoleir.runner;

import io.seoleir.enums.OperationSystemEnum;
import io.seoleir.exception.BaseApplicationException;
import io.seoleir.exception.TerminalSizeException;
import io.seoleir.model.Terminal;
import io.seoleir.resolver.SystemOsResolver;
import io.seoleir.strategy.TerminalSize;
import io.seoleir.strategy.impl.UnixTerminalSize;
import io.seoleir.strategy.impl.WindowsTerminalSize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class ConsoleRunner {

    private static final Logger log = LoggerFactory.getLogger(ConsoleRunner.class);

    private final Map<OperationSystemEnum, Supplier<Optional<Terminal>>> terminalSizeStrategyMap;

    public ConsoleRunner() {
        TerminalSize windowTerminalSize = new WindowsTerminalSize();
        TerminalSize unixTerminalSize = new UnixTerminalSize();
        this.terminalSizeStrategyMap = Map.of(
                OperationSystemEnum.UNIX, unixTerminalSize::getTerminalSize,
                OperationSystemEnum.WINDOWS, windowTerminalSize::getTerminalSize
        );
    }

    public void runChristmasTree() {
        try {
            OperationSystemEnum system = SystemOsResolver.getSystem();

            log.info("OS: {}", system.name());

            Terminal terminalSize = terminalSizeStrategyMap.get(system)
                    .get()
                    .orElseThrow(() -> new TerminalSizeException("Unable to determine terminal size"));

            log.info("Terminal size - width: {}, height: {}", terminalSize.getWidth(), terminalSize.getHeight());


            /*while (true) {

            System.out.print("\033[H\033[2J");
            System.out.flush();


            for (int i = 0; i < 10; i++) {
                System.out.println(i);
            }

            Thread.sleep(100);

            int width = TerminalFactory.get().getWidth();
            int height = TerminalFactory.get().getHeight();
        }*/
        } catch (BaseApplicationException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

}
