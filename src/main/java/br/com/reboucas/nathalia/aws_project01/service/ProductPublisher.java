package br.com.reboucas.nathalia.aws_project01.service;

import br.com.reboucas.nathalia.aws_project01.enums.EventType;
import br.com.reboucas.nathalia.aws_project01.model.Envelope;
import br.com.reboucas.nathalia.aws_project01.model.Product;
import br.com.reboucas.nathalia.aws_project01.model.ProductEvent;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.Topic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductPublisher {
    private final AmazonSNS snsClient;
    private final Topic productEventsTopic;
    private final ObjectMapper objectMapper;

    public ProductPublisher(AmazonSNS snsClient,
                            @Qualifier("productEventsTopic") Topic productEventsTopic,
                            ObjectMapper objectMapper) {
        this.snsClient = snsClient;
        this.productEventsTopic = productEventsTopic;
        this.objectMapper = objectMapper;
    }

    public void publishProductEvent(Product product, EventType eventType, String username) {
        ProductEvent productEvent = ProductEvent.builder()
                .productId(product.getId())
                .code(product.getCode())
                .username(username)
                .build();

        try {
            Envelope envelope = Envelope.builder()
                    .eventType(eventType).data(objectMapper.writeValueAsString(productEvent)).build();

            PublishResult publishResult = snsClient
                    .publish(productEventsTopic.getTopicArn(), objectMapper.writeValueAsString(envelope));

            log.info("Product event sent - Event: {} - ProductId: {} - MessageId: {}",
                    envelope.getEventType(),
                    productEvent.getProductId(),
                    publishResult.getMessageId());
        } catch (JsonProcessingException e) {
            log.error("Failed to create product event message");
        }
    }


}
