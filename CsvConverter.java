import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, List<Map<String, String>>> roomData = new LinkedHashMap<>();

        String csvFile = "input.csv";
        String line;
        String csvSplitBy = ",";

        String prefix = args.length > 0 ? args[0] + ":" : "";
        
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
            StringBuilder output = new StringBuilder();
            for (Map.Entry<String, List<Map<String, String>>> entry : roomData.entrySet()) {
                output.append(prefix).append(entry.getKey()).append(",");
                List<Map<String, String>> dataList = entry.getValue();
                output.append("[");
                for (int j = 0; j < dataList.size(); j++) {
                    Map<String, String> data = dataList.get(j);
                    output.append("{");
                    int k = 0;
                    for (Map.Entry<String, String> item : data.entrySet()) {
                        output.append("\\\"").append(item.getKey()).append("\\\": \\\"").append(item.getValue()).append("\\\"");
                        if (k < data.size() - 1) {
                            output.append(",");
                        }
                        k++;
                    }
                    output.append("}");
                    if (j < dataList.size() - 1) {
                        output.append(",");
                    }
                }
                output.append("]");
                output.append("\n");
            }
            
            // 結果をファイルに書き込む
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.csv"))) {
                writer.write(output.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
