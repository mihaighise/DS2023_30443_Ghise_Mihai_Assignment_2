package com.utcn.assignment2.Assignment2;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.Gson;
import com.opencsv.CSVReader;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;


@Component
public class Sender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    @Value("classpath:csv/sensor.csv")
    Resource resourseFile;

    CSVReader csvReader;

    public int indexCSV = 0;

    @Bean
    private void initializeCSV() {
        try{
            FileReader fileReader = new FileReader(resourseFile.getFile());
            csvReader = new CSVReader(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedDelay = 10000, initialDelay = 500)
    public void send() throws IOException {
        String[] value = csvReader.readNext();
        final var message = new CustomMessage(LocalDateTime.now().toString(), 4L, Float.valueOf(value[0]));
        this.template.convertAndSend(queue.getName(), new Gson().toJson(message));
        System.out.println(" [x] Sent '" + message + "'");
    }
}
