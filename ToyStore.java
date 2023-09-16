
import java.io.*;
import java.util.*;

public class ToyStore {
    public static void main(String[] args) {
        PriorityQueue<Toy> toyQueue = new PriorityQueue<>(Comparator.comparingInt(Toy::getFrequency));
        String[] toyData = {
            "1,Мяч,5",
            "2,Кукла,3",
            "3,Конструктор,2",
            "4,Машинка,4",
            "5,Пазл,1"
        };

        // Заполнение PriorityQueue игрушками из данных
        for (String data : toyData) {
            String[] parts = data.split(",");
            if (parts.length == 3) {
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int frequency = Integer.parseInt(parts[2]);
                Toy toy = new Toy(id, name, frequency);
                toyQueue.add(toy);
            } else {
                System.out.println("Некорректные данные: " + data);
            }
        }

        // Вызываем Get 10 раз и записываем результат в файл
        try (PrintWriter writer = new PrintWriter(new FileWriter("toy_output.txt"))) {
            for (int i = 0; i < 10; i++) {
                Toy nextToy = toyQueue.poll();
                if (nextToy != null) {
                    writer.println(nextToy.getId() + "," + nextToy.getName());
                } else {
                    writer.println("Очередь игрушек пуста.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Toy {
    private int id;
    private String name;
    private int frequency;

    public Toy(int id, String name, int frequency) {
        this.id = id;
        this.name = name;
        this.frequency = frequency;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFrequency() {
        return frequency;
    }
}