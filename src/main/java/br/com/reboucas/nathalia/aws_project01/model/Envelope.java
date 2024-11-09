package br.com.reboucas.nathalia.aws_project01.model;

import br.com.reboucas.nathalia.aws_project01.enums.EventType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Envelope {
    private EventType eventType;
    private String data;
}
