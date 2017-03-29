package com.magicformula.main;

import com.magicformula.process.PopulateFinancials;
import com.magicformula.process.PopulatePrice;
import org.kohsuke.args4j.*;

import java.util.Properties;

public class MagicFormula {

    @Option(name = "-p", aliases = "--process", required = true, usage = "process: Company|Financial|Price|Rank")
    private String process;

    @Option(name = "-s", aliases = "--start", usage = "starting entry. default=''")
    private String start = "";

    public static Properties properties = new Properties();

    public static void main(final String[] args) {
        final MagicFormula magicFormula = new MagicFormula();
        final CmdLineParser cmdParser = new CmdLineParser(magicFormula);

        try {
            cmdParser.parseArgument(args);
            properties.load(MagicFormula.class.getClassLoader().getResourceAsStream("config.properties"));
            magicFormula.run();
            System.exit(0);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            cmdParser.printUsage(System.err);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    private void run() throws Exception {
        properties.setProperty("start", start);

        if (process.equals("Price")) {
            PopulatePrice.getInstance().populate();
        } else if (process.equals("Financial")) {
            PopulateFinancials.getInstance().populate();
        } else if (process.equals("Rank")) {

        }
    }
}
