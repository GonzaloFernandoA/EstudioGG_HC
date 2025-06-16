package com.estudioGG.hc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.estudioGG.hc.model.LogMensaje;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;

import java.util.List;

@Service
public class SqsMessageListener {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(SqsMessageListener.class);

    private final SqsClient sqsClient;

    @Value("${aws.sqs.respond_turnos}")
    private String turnoQueueUrl ; //= "https://sqs.us-east-2.amazonaws.com/034362075244/RespondTurnos";

    @Autowired
    TurnoService sevice;

    public SqsMessageListener(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

//    @Scheduled(fixedDelay = 10000) // cada 30 segundos después de terminar
    public void pollSqsQueue() {
        try {
            ReceiveMessageRequest request = ReceiveMessageRequest.builder()
                    .queueUrl(turnoQueueUrl)
                    .maxNumberOfMessages(1)  // ✅ Solo 1 mensaje
                    .waitTimeSeconds(1)
                    .build();

            ReceiveMessageResponse response = sqsClient.receiveMessage(request);

            List<Message> messages = response.messages();

            if (messages.isEmpty()) {
                logger.info("No se encontraron mensajes en la cola: {}", turnoQueueUrl);
            } else {
                messages.forEach(message -> {
                    String body = message.body();
                    logger.info("Mensaje recibido WebhookMessages: " + body);

                    this.processMessage(body);

                    sqsClient.deleteMessage(DeleteMessageRequest.builder()
                            .queueUrl(turnoQueueUrl)
                            .receiptHandle(message.receiptHandle())
                            .build());
                });
            }


        } catch (Exception e) {
            logger.error("⚠️ Error al leer desde la cola SQS: " + e.getMessage());
        }
    }
    public void processMessage(String message) {
        try {
            logger.info("Mensaje recibido WebhookMessges:" + message);
            LogMensaje mensaje = objectMapper.readValue(message, LogMensaje.class);
            sevice.updateTemplate(mensaje.getDni(), mensaje.getTamplte(), mensaje.getValorRespuesta());

        } catch (Exception e) {
            logger.error("Error al procesar el mensaje: " + e.getMessage());
        }
    }
}