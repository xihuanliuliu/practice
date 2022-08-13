package com.jd.edi.es.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EsMessage {

    private String index;
    private String uniqueKey;
    private String message;
}
