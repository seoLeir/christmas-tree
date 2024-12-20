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

import java.util.*;
import java.util.function.Supplier;

public class ConsoleRunner {

    private static final Logger log = LoggerFactory.getLogger(ConsoleRunner.class);

    private static final Character SPACE = ' ';

    private static final Character SNOWFLAKE = 'x';

    private static final Character TREE_LINE = '*';

    private static final Character TREE_TRUNK = '|';

    private final Map<OperationSystemEnum, Supplier<Optional<Terminal>>> terminalSizeStrategyMap;

    private static final Random random = new Random();

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

            int treeBodyRation = 9 * terminalSize.getHeight() / 10;

            while (true) {
                System.out.print("\033[H\033[2J");
                System.out.flush();

                buildTreeBranch(terminalSize.getWidth(), treeBodyRation / 6);
                buildTreeBranch(terminalSize.getWidth(), treeBodyRation / 3);
                buildTreeBranch(terminalSize.getWidth(), treeBodyRation / 2);

                buildTreeTrunk(terminalSize.getHeight() / 10, terminalSize.getWidth());

                Thread.sleep(200);
            }
        } catch (BaseApplicationException e) {
            log.error("ERROR: {}", e.getMessage());
            System.err.println(e.getMessage());
            System.exit(1);
        }
        catch (InterruptedException e) {
            log.error("INTERRUPTED EXCEPTION ERROR: {}", e.getMessage());
            System.out.println("Runtime exception");
            System.exit(1);
        }
    }


    private void buildTreeBranch(Integer width, Integer heightTo) {
        for (int i = 0; i < heightTo; i++) {
            int spaces = (width / 2) - i - 1;
            int stars = 2 * i + 1;

            System.out.print(generateSnowflakeLine(0, spaces));

            for (int j = 0; j < stars; j++) {
                System.out.print(TREE_LINE);
            }

            System.out.print(generateSnowflakeLine(spaces + stars, width));


            System.out.println();
        }
    }

    private void buildTreeTrunk(Integer trunkHeight, Integer width) {
        int trunkSpaces = (width - 1 - 3) / 2;

        for (int i = 0; i < trunkHeight; i++) {
            System.out.print(generateSnowflakeLine(0, trunkSpaces));

            for (int j = 0; j < 3; j++) {
                System.out.print(TREE_TRUNK);
            }

            System.out.print(generateSnowflakeLine(trunkSpaces + 3, width));

            System.out.println();
        }
    }

    private String generateSnowflakeLine(int indexFrom, int indexTo) {
        Set<Integer> snowflakes = new HashSet<>((indexTo - indexFrom) / 12);
        for (int i = indexFrom; i <  indexTo; i++) {
            snowflakes.add(random.nextInt(indexTo - indexFrom) + indexFrom);
            if (snowflakes.size() == (indexTo - indexFrom) / 12) {
                break;
            }
        }

        String line = SPACE.toString().repeat(indexTo - indexFrom);
        char[] lineChars = line.toCharArray();
        snowflakes.forEach(snowflake -> {
            if (indexFrom > 0) {
                lineChars[snowflake - indexFrom] = SNOWFLAKE;
            } else {
                lineChars[snowflake] = SNOWFLAKE;
            }
        });

        return String.valueOf(lineChars);
    }



}
