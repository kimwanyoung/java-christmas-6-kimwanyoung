package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class DefaultReader implements Reader {

    @Override
    public String read() {
        return Console.readLine().trim();
    }
}
