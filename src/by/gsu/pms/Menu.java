package by.gsu.pms;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Menu {

    private static Logger LOGGER = Logger.getLogger(Menu.class.getName());

    private List<MenuEntry> entries = new ArrayList<>();
    private boolean isExit = false;

    public Menu() {
        entries.add(new MenuEntry(Constants.SUM) {
            @Override
            public void run(ThreadGenerator generator) {
                generator.getResult();
            }
        });
        entries.add(new MenuEntry(Constants.SUB) {
            @Override
            public void run(ThreadGenerator generator) {
                generator.getResult();
            }
        });
        entries.add(new MenuEntry(Constants.MUL) {
            @Override
            public void run(ThreadGenerator generator) {
                generator.getResult();
            }
        });
        entries.add(new MenuEntry(Constants.EXIT) {
            @Override
            public void run(ThreadGenerator generator) {
                isExit = true;
            }
        });
    }

    public void run() {
        try (Scanner sc = new Scanner(System.in)) {
            final int INDEX_FAULT = 1;
            LOGGER.info(Constants.INPUT_NUMBER_MESSAGE);
            int number = sc.nextInt();
            LOGGER.info(Constants.INPUT_LIMIT_MESSAGE);
            int limit = sc.nextInt();
            ThreadGenerator generator = new ThreadGenerator(number, Action.SUM, limit);
            generator.execute();
            while (!isExit) {
                LOGGER.info(Constants.INPUT_ACTION_MESSAGE);
                int action = sc.nextInt() - INDEX_FAULT;
                generator.setAction(Action.values()[action]);
                MenuEntry entry = entries.get(action);
                entry.run(generator);
            }
        } catch (IndexOutOfBoundsException | InputMismatchException e) {
            LOGGER.warning(Constants.ACTION_EXCEPTION_MESSAGE);
        }
    }
}
