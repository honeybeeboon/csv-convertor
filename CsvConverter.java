import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, List<Map<String, String>>> roomData = new LinkedHashMap<>();

        String csvFile = "input.csv";
        String line;
        String csvSplitBy = ",";

        String prefix = args.length > 0 ? args[0] + ":" + "";
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String headerLine = br.readLine();
            String[] headers = headerLine.split(csvSplitBy);
            while ((line = br.readLine()) != null) {
                String[] cols = line.split(csvSplitBy, headers.length);
                String roomId = cols[0];
                Map<String, String> data = new LinkedHashMap<>();
                for (int i = 1; i < headers.length; i++) {
                    data.put(headers[i], cols[i]);
                }

                if (roomData.containsKey(roomId)) {
                    roomData.get(roomId).add(data);
                } else {
                    List<Map<String, String>> dataList = new ArrayList<>();
                    dataList.add(data);
                    roomData.put(roomId, dataList);
                }
            }

            // 出力結果を生成
            for (Map.Entry<String, List<Map<String, String>>> entry : roomData.entrySet()) {
                System.out.print(prefix + entry.getKey() + ",");
                List<Map<String, String>> dataList = entry.getValue();
                System.out.print("\"[");
                for (int j = 0; j < dataList.size(); j++) {
                    Map<String, String> data = dataList.get(j);
                    System.out.print("{");
                    int k = 0;
                    for (Map.Entry<String, String> item : data.entrySet()) {
                        System.out.print("\\\"" + item.getKey() + "\\\":\\\"" + item.getValue() + "\\\"");
                        if (k < data.size() - 1) {
                            System.out.print(",");
                        }
                        k++;
                    }
                    System.out.print("}");
                    if (j < dataList.size() - 1) {
                        System.out.print(",");
                    }
                }
                System.out.println("]\"");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}