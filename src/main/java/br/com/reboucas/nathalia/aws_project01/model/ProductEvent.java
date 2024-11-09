package br.com.reboucas.nathalia.aws_project01.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEvent {
    private Long productId;
    private String code;
    private String username;
}
