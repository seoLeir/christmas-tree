package io.seoleir.resolver;

import io.seoleir.enums.OperationSystemEnum;
import io.seoleir.exception.OperationSystemException;
import io.seoleir.util.SystemUtil;

public class SystemOsResolver {

    public static OperationSystemEnum getSystem() {
        String osName = SystemUtil.getProperty("os.name").toLowerCase();

        if (osName.contains("win")) {
            return OperationSystemEnum.WINDOWS;
        } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("mac")) {
            return OperationSystemEnum.UNIX;
        } else {
            throw new OperationSystemException("Could not recognize SYSTEM OS");
        }
    }

}
