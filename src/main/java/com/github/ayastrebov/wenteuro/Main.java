package com.github.ayastrebov.wenteuro;

import com.github.ayastrebov.wenteuro.api.PositionApiGatewayFactory;
import com.github.ayastrebov.wenteuro.output.PositionCsvWriter;
import java.io.IOException;
import java.io.OutputStream;
import lombok.Value;

public class Main {

    @Value
    private static class Arguments {

        String text;
    }

    public static void main(String[] args) throws IOException {
        Arguments arguments = parseArgs(args);

        try (OutputStream out = System.out) {
            new Processor(
                PositionApiGatewayFactory.create(),
                new PositionCsvWriter(out)
            ).process(arguments.getText());
        }
    }

    private static Arguments parseArgs(String[] args) {
        if (args.length != 1) {
            System.err.println(""
                + "Arguments: <text>\n"
                + "\n"
                + "    text      position suggestion API input text\n"
            );
            System.exit(1);
        }
        return new Arguments(args[0]);
    }
}
