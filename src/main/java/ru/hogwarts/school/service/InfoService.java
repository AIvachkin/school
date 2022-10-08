package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class InfoService {

    public String modifiedLogic() {

        long start1 = System.currentTimeMillis();
        int sum1 = Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, (a, b) -> a + b);
        long executionTime1 = System.currentTimeMillis() - start1;

        long start2 = System.currentTimeMillis();
        int sum2  = 0;
        for (int i = 1; i <= 1000000; i++) {
            sum2 += i;
        }
        long executionTime2 = System.currentTimeMillis() - start2;

        return "Ответ: " + sum1 + " Время с использованием стрима: " + executionTime1 +
                " , Время с использованием цикла: " + sum2 + "  " + executionTime2;

    }


}
