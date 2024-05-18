import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String filePath = "data.txt";
        Map<String, List<Program>> channelPrograms = new LinkedHashMap<>();
        List<String> orderOfChannels = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            String channel = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#")) {
                    channel = line.substring(1).trim(); // Extract channel name
                    orderOfChannels.add(channel);
                    channelPrograms.put(channel, new ArrayList<>());
                } else {
                    String programName = line.trim(); // Extract program name
                    String programTimeStr = reader.readLine().trim(); // Extract program start time
                    channelPrograms.get(channel).add(new Program(programName, programTimeStr));
                }
            }

            // Выводим программы для каждого канала в порядке, в котором они появлялись в файле
            for (String channelName : orderOfChannels) {
                System.out.println("Канал: " + channelName);
                for (Program program : channelPrograms.get(channelName)) {
                    System.out.println("Программа: " + program.getTime() + ", Время начала: " + program.getName());
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        ExcelWriter excelWriter = new ExcelWriter();
        excelWriter.writeExcel(channelPrograms);
    }
}