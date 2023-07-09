package org.example.fongo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "trade")
public class Trade {

    @Id
    private String id;
    private String traderId;
    private Double value;
    private String exchangeCode;
}
