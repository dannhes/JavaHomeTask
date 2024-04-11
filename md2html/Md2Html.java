package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Md2Html {
    public static void main(String[] args) {
        try (BufferedReader inputData = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8));) {
            try (BufferedWriter outputData = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8));) {
                List<String> lines = new ArrayList<>();
                String line = inputData.readLine();
                while (line != null) {
                    lines.add(line);
                    if (!line.isEmpty()) {
                        lines.add(System.lineSeparator());
                    }
                    line = inputData.readLine();
                }
                lines.add("");

                StringBuilder sb = new StringBuilder();
                for (String oneLine : lines) {
                    if (!oneLine.isEmpty()) {
                        sb.append(oneLine);
                    } else {
                        if (!sb.isEmpty()) {
                            String str = Parser.parseParagraph(sb.toString());
                            outputData.write(str);
                            sb.setLength(0);
                        }
                    }
                }

            } catch (IOException e) {
                System.err.println("Error while writing to file" + e.getMessage());
            }

        } catch (IOException e) {
            System.err.println("Error while reading from file" + e.getMessage());
        }
    }
}